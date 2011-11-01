package org.metaworks.dwr;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ObjectInstance;
import org.metaworks.ObjectType;
import org.metaworks.Type;
import org.metaworks.WebObjectType;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MetaworksRemoteService {
	
//    public static ThreadLocal<String> callingObjectTypeName = new ThreadLocal<String>();

	
	static Hashtable<String, WebObjectType> metadataStorage = new Hashtable<String, WebObjectType>();
	

	static public WebObjectType getMetaworksType(String className) throws Exception {
		try{
			
			//TODO: this is debug mode option
			if(metadataStorage.containsKey(className))
				return metadataStorage.get(className);
			
			
		//	if(className.length() == 0) return null;
			
			WebObjectType objType = new WebObjectType(Class.forName(className));
			
			metadataStorage.put(className, objType);

			//TODO: this will be better performance but, it is little different information between realization object and DAO's field 
//			if(objType.iDAOClass()!=null)
//				metadataStorage.put(objType.iDAOClass().getName(), objType);

			
			return objType;
		}catch(Exception e){
			System.out.println("viewer tried to get metadata for class "+ className);
			e.printStackTrace();
			throw e;
		}
	}
	
    public Object callMetaworksService(String objectTypeName, Object object, String methodName, Map<String, Object> autowiredFields) throws Throwable{
	
    	//getBeanFactory().getBean(arg0)
    	
//		callingObjectTypeName.set(objectTypeName);
		Class serviceClass = Class.forName(objectTypeName);
		
		//if the requested value object is IDAO which need to be converted to implemented one so that it can be invoked by its methods
		//Another case this required is when Spring is used since the spring base object should be auto-wiring operation
		WebApplicationContext springAppContext = null;
		if(TransactionalDwrServlet.useSpring) springAppContext = getBeanFactory();
		Object springBean = null;
		if(springAppContext!=null)
		try{
			springBean = getBeanFactory().getBean(serviceClass);
		}catch(Exception e){
			//TODO: check if there's any occurrance of @Autowired in the field list, it is required to check and shows some WARNING to the developer.
		}
		
		if(serviceClass.isInterface() || springBean!=null){
			String serviceClassNameOnly = WebObjectType.getClassNameOnly(serviceClass);
			
			if(serviceClass.isInterface()){
				serviceClassNameOnly = serviceClassNameOnly.substring(1, serviceClassNameOnly.length());
				serviceClass = Class.forName(serviceClass.getPackage().getName() + "." + serviceClassNameOnly);
			}
			
			if(springAppContext!=null)
				try{
					springBean = getBeanFactory().getBean(serviceClass);
				}catch(Exception e){
					//TODO: check if there's any occurrance of @Autowired in the field list, it is required to check and shows some WARNING to the developer.
				}

			
			WebObjectType wot = getMetaworksType(serviceClass.getName());
			
			//Convert to service object from value object (IDAO)
			ObjectInstance srcInst = (ObjectInstance) wot.metaworks2Type().createInstance();
			srcInst.setObject(object);
			ObjectInstance targetInst = (ObjectInstance) wot.metaworks2Type().createInstance();
			
			if(springBean!=null){
				targetInst.setObject(springBean);
			}
			
			for(FieldDescriptor fd : wot.metaworks2Type().getFieldDescriptors()){
				Object srcFieldValue = srcInst.getFieldValue(fd.getName());
				
				if("MetaworksContext".equals(fd.getName()) && srcFieldValue==null && IDAO.class.isAssignableFrom(serviceClass)){
					srcFieldValue = new MetaworksContext();
				}
				
				targetInst.setFieldValue(fd.getName(), srcFieldValue);
			}
			
			object = targetInst.getObject();
		}
		
		//injecting autowired fields from client
		if(autowiredFields!=null){
			for(String fieldName : autowiredFields.keySet()){
				Object autowiringValue = autowiredFields.get(fieldName);
				serviceClass.getField(fieldName).set(object, autowiringValue);
			}
		}
		
		TransactionContext tx = TransactionContext.getThreadLocalInstance();
		if(connectionFactory!=null)
			tx.setConnectionFactory(getConnectionFactory());
		
		try {
			Method m = serviceClass.getMethod(methodName, new Class[]{});
			Object returned = m.invoke(object, new Object[]{});
			if(m.getReturnType()!=void.class)
				return returned;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw e.getTargetException();
		}
		
		return object;
	}

    private WebApplicationContext getBeanFactory()
    {
        try {
			ServletContext srvCtx = ServerContextFactory.get().getServletContext();

			return WebApplicationContextUtils.getWebApplicationContext(srvCtx);
		} catch (Exception e) {
		//	e.printStackTrace();
			
			return null;
		}
    }
	

	ConnectionFactory connectionFactory;
		public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}
	
		public void setConnectionFactory(ConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}
}
