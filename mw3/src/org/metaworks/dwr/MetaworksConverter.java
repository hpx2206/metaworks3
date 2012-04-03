package org.metaworks.dwr;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.directwebremoting.ConversionException;
import org.directwebremoting.convert.ArrayConverter;
import org.directwebremoting.convert.BeanConverter;
import org.directwebremoting.convert.ObjectConverter;
import org.directwebremoting.convert.StringConverter;
import org.directwebremoting.extend.ArrayOutboundVariable;
import org.directwebremoting.extend.ConvertUtil;
import org.directwebremoting.extend.ErrorOutboundVariable;
import org.directwebremoting.extend.InboundContext;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.ObjectOutboundVariable;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;
import org.directwebremoting.extend.Property;
import org.directwebremoting.extend.PropertyDescriptorProperty;
import org.directwebremoting.extend.ProtocolConstants;
import org.directwebremoting.util.LocalUtil;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ObjectInstance;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;

public class MetaworksConverter extends BeanConverter{
	
    /* (non-Javadoc)
     * @see org.directwebremoting.Converter#convertInbound(java.lang.Class, org.directwebremoting.InboundVariable, org.directwebremoting.InboundContext)
     */
    public Object convertInbound(Class<?> paramType, InboundVariable data) throws ConversionException
    {
    	
    	try {
    		
		 	if(paramType == Object.class){
		 		if("string".equals(data.getType())){
		 			paramType = String.class;
		 			
		 			StringConverter stringConverter = new StringConverter();
		 			return stringConverter.convertInbound(String.class, data);
		 			
		 		}else if("number".equals(data.getType())){
		 			paramType = Number.class;
		 			
		 			ObjectConverter objectConverter = new ObjectConverter();
		 			return objectConverter.convertInbound(paramType, data);
		 		}
		 	}

    		 //when unknown object from javascript, metaworks need to get the class Information from the JSON's property value '__className'
			if(paramType == Object.class){
			 	String value = data.getValue();
			 	
			 	if(value.length() >= 2 && !("null".equals(value))){
				    value = value.substring(1, value.length() - 1);
	
				    Map<String, String> tokens = extractInboundTokens(paramType, value);
				    
				    String refName = tokens.get("__className");
				    
				    if(refName!=null){
					    refName = refName.split(":")[1];
					    
					   	String className = data.getContext().getInboundVariable(refName).getFormField().getString();
					  
					   	try {
							paramType = Thread.currentThread().getContextClassLoader().loadClass(className);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			 	}
			 	
			}
			   	
			
			
			if( paramType.isArray() || "array".equals(data.getType())){
				
				ArrayConverter arrayConverter = new ArrayConverter();
				arrayConverter.setConverterManager(this.getConverterManager());
				
				if(!paramType.isArray() && IDAO.class.isAssignableFrom(paramType)){
					
					Class arrParamType = Array.newInstance(paramType, 0).getClass();
					IDAO[] converted = (IDAO[]) arrayConverter.convertInbound(arrParamType, data);
					
					try {
						IDAO cursoredDAO = MetaworksDAO.createDAOImpl(paramType);
	
						for(int i=0; i<converted.length; i++){
							cursoredDAO.getImplementationObject().moveToInsertRow(converted[i]);
						}
						
						return cursoredDAO;
	
					} catch (Exception e) {
						throw new ConversionException(paramType, e);
					}
				}else{
					
					if(!paramType.isArray())
						paramType = Object[].class;
					
					return arrayConverter.convertInbound(paramType, data);
				}
				
				
			}else{
				return super.convertInbound(paramType, data);
			}
			
		} catch (ConversionException e) {
			System.err.println("[WARN] You may need to give the primitive types'(e.g. int, boolean) default value of in the Javascript object for current Metaworks call.");
			e.printStackTrace();
			
			throw e;
		}
		
    }	
    
    
 

	
    protected Object createParameterInstance(Class<?> paramType) throws InstantiationException, IllegalAccessException
    {
		if( paramType.isInterface() && IDAO.class.isAssignableFrom(paramType) ){
			
			IDAO iDAOInstance;
			try {
				iDAOInstance = MetaworksDAO.createDAOImpl(paramType);
				return iDAOInstance;

			} catch (Exception e) {

				throw new RuntimeException(e);
			}
			
		}else{
	        try {
				return paramType.newInstance();
			} catch(java.lang.InstantiationException e){
				throw new ConversionException(paramType, "Service Object should have constructor with empty parameter. Add new Constructor with no argument into " + paramType.getName() + ". 문제를 해결하려면 다음 클래스에 아규먼트가 하나도 없는 생성자를 추가해주세요--> " + paramType.getName(), e);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ConversionException(paramType, "Service Object couldn't be instantiated due to : " +  e.getClass(), e);
			}
		}
    	
    	
    }
	
    public OutboundVariable convertOutbound(Object data, OutboundContext outctx) throws ConversionException
    {
    	try {
			//sometimes due to wierd operation of DWR
			if(data.getClass().isArray()){
				ArrayConverter arrayConverter = new ArrayConverter();
				arrayConverter.setConverterManager(this.getConverterManager());
				return arrayConverter.convertOutbound(data, outctx);
			}
			
			if(data instanceof IDAO && ((IDAO) data).getImplementationObject()!=null){
				
				IDAO dao = (IDAO)data;
				
			    ArrayOutboundVariable ov = new ArrayOutboundVariable(outctx);
			    outctx.put(data, ov);

			    // Convert all the data members
			    List<OutboundVariable> ovs = new ArrayList<OutboundVariable>();
			    
			    try{
			    	if(dao.getImplementationObject().getRowSet()!=null || dao.getImplementationObject().getCachedRows()!=null){
				        dao.beforeFirst();		        

				        OutboundVariable nested = null;

				        while(dao.next()){
			                nested = iDaoConvertOutbound(dao, outctx);
			                ovs.add(nested);
				        }

				        //disabled since 'add' button mechanism
				  
//				        if(ovs.size()<=1){
//				        	if(nested!=null)
//				        		return nested; //return single object if there's only one object. client (Javascript) should consider object.length is undefined or not to recognize whether the data is array or not
//				        }
			    	}else{
			            return iDaoConvertOutbound(dao, outctx);
			    	}
			        
			    }catch(Exception e){
			    	throw new ConversionException(((IDAO) data).getImplementationObject().getClass(), e.getMessage());
			    }

			   // if(ovs.size()>0)
				    // Group the list of converted objects into this OutboundVariable
				    ov.setChildren(ovs);
				    
				//TODO: since this part doesn't return __className property, the array type cannot recognize the dao's class type?
			    
			    return ov;
			    
			}else{
			       // Where we collect out converted children
			    Map<String, OutboundVariable> ovs = new TreeMap<String, OutboundVariable>();

			    // We need to do this before collecting the children to save recursion
			    ObjectOutboundVariable ov = new ObjectOutboundVariable(outctx, data.getClass(), getJavascript());
			    outctx.put(data, ov);

			    try
			    {
			        Map<String, Property> properties = getPropertyMapFromObject(data, true, false);
			        for (Entry<String, Property> entry : properties.entrySet())
			        {
			            String name = entry.getKey();
			            Property property = entry.getValue();

			            Object value = property.getValue(data);
			            OutboundVariable nested = getConverterManager().convertOutbound(value, outctx);

			            ovs.put(name, nested);
			        }
			    }
			    catch (ConversionException ex)
			    {
			        throw ex;
			    }
			    catch (Exception ex)
			    {
			        throw new ConversionException(data.getClass(), ex);
			    }

				OutboundVariable classNameOV = getConverterManager().convertOutbound(data.getClass().getName(), outctx);
						
				ovs.put("__className", classNameOV);
				ov.setChildren(ovs);
				
				return ov;

			}
		} catch (ConversionException e) {
			e.printStackTrace();
			
			throw e;
		}

    }
    
    
	    public OutboundVariable iDaoConvertOutbound(Object data, OutboundContext outctx) throws Exception
	    {
		        Map<String, OutboundVariable> ovs = new TreeMap<String, OutboundVariable>();

		        // We need to do this before collecting the children to save recursion
		        ObjectOutboundVariable ov = new ObjectOutboundVariable(outctx, data.getClass(), getJavascript());
		        outctx.put(data, ov);

				IDAO dao = (IDAO)data;
				
					WebObjectType webObjectType = MetaworksRemoteService.getInstance().getMetaworksType(((IDAO) data).getImplementationObject().getDaoClass().getName());
					Class daoClass = dao.getImplementationObject().getDaoClass();
					
		            Map<String, Property> properties = new HashMap<String, Property>();
		 			for(WebFieldDescriptor property : webObjectType.getFieldDescriptors()){
		                String propertyName = property.getName();
		                Object value;
						try {
							String upperPropertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
							
							Class propertyClass = null;
							Method getterMethod = null;
							try{
								propertyClass = (getterMethod = daoClass.getMethod("get" + upperPropertyName)).getReturnType();
							}catch(Exception e){
							}
							
							if(propertyClass==null)
							try{
								propertyClass = (getterMethod = daoClass.getMethod("is" + upperPropertyName)).getReturnType();
							}catch(Exception e){
							}
							
//							if("initiator".equals(propertyName))
//								System.out.println();
							
							value = getterMethod.invoke(dao, new Object[]{});
							//value = dao.get(propertyName);
							

							if(!propertyClass.isPrimitive() && 
									propertyClass!=String.class &&
									(value==null || !propertyClass.isAssignableFrom(value.getClass()))){
								
								boolean needToProhibitRecursivelyGenerateReferenceObject = dao.getImplementationObject().getDaoClass().getName().equals(property.getClassName());

								if(!needToProhibitRecursivelyGenerateReferenceObject)
									value = Database.createReferenceObject(Thread.currentThread().getContextClassLoader().loadClass(property.getClassName()), dao);
							}
							
							OutboundVariable nested = null;
							
							//primitive default value mapping
//							if(value==null){
//
//								if(propertyClass!=null){
//									if(propertyClass == String.class || propertyClass == MetaworksContext.class || propertyClass == Date.class){
//										//do nothing. null is ok.
//									}else
//									if(propertyClass == int.class)
//										value = new Integer(0);
//									else if(propertyClass == boolean.class)
//										value = new Boolean(false);
//									else if(propertyClass == Long.class){
//										value = new Long(0);
//									}else if(Number.class.isAssignableFrom(propertyClass)){
//										value = new Integer(0);
//									}else if(Date.class.isAssignableFrom(propertyClass)){
//										value = new Date();
//									}else{
//										//new Exception("please add more primitive type's default value mapping for " + propertyClass).printStackTrace();
//									}
//								}
//							}
							
							if(value==null){
								nested = null;
							}else if(value instanceof IDAO && ((IDAO) value).getImplementationObject()!=null){
								nested = this.convertOutbound(value, outctx);
							}else{
				                nested = getConverterManager().convertOutbound(value, outctx);
							}

							if(nested!=null)
								ovs.put(propertyName, nested);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                
		 			}
		 			
		 			//if there's TypeSelector setting exist, try to give cast information as possible as desired.
		 			Class type = Database.getDesiredTypeByTypeSelector(dao);
		 			if(type==null)
		 				type = daoClass;
		 			
		 			OutboundVariable classNameOV = getConverterManager().convertOutbound(type.getName(), outctx);
		 			ovs.put("__className", classNameOV);
		 			
		 	        ov.setChildren(ovs);
		 	        
		 	        return ov;
		 	        
			
	    }


}
