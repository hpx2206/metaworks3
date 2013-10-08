package org.metaworks.dwr;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.ObjectInstance;
import org.metaworks.WebObjectType;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MetaworksRemoteService {
	
//    public static ThreadLocal<String> callingObjectTypeName = new ThreadLocal<String>();

	static Hashtable<String, WebObjectType> metadataStorage = new Hashtable<String, WebObjectType>();
	
	protected static MetaworksRemoteService instance;
		protected static void setInstance(MetaworksRemoteService instance) {
			MetaworksRemoteService.instance = instance;
		}
		public static MetaworksRemoteService getInstance() {

			if(instance==null){

					instance = new MetaworksRemoteService();
			}
			
			return instance;
		}
	
	public static void pushClientObjects(final Object[] object){
		 Browser.withAllSessions(new Runnable(){
			   @Override
			   public void run() {
			    ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{object, null, "body"});
			    ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});

			   }			  
		});
	}
	
	public static void pushTargetClientObjects(String sessionId, final Object[] object){
		 Browser.withSession(sessionId, new Runnable(){
			   @Override
			   public void run() {
			    ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{object, null, "body"});
			    ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});

			   }			  
		});
	}
		
	public static void pushOtherClientObjects(final String sessionId, final Object[] object){
		Browser.withAllSessionsFiltered(
				new ScriptSessionFilter(){
					@Override
					public boolean match(ScriptSession session)
					{
						if(session.getId().equals(sessionId))
							return false;
						else
							return true;
					}			
				},

				new Runnable(){
					@Override
					public void run() {
						ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{object, null, "body"});
						ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});

					}			  
				});
	}
	
	public void clearMetaworksType(String className) throws Exception {
		
		if("*".equals(className))
			metadataStorage.clear();
		
		if(metadataStorage.containsKey(className))
			metadataStorage.remove(className);
		
		
		
		////////// alert to other session users :  COMET //////////
		
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();

	   // For all the browsers on the current page:
	   Collection sessions = wctx.getScriptSessionsByPage(currentPage);

	   //TODO: filter other topic's postings;
	   Util utilAll = new Util(sessions);
	   utilAll.addFunctionCall("mw3.clearMetaworksType('"+ className +"')");

	}


	public WebObjectType getMetaworksType(String className) throws Exception {
		try{
				
			//TODO: this is debug mode option
			if(metadataStorage.containsKey(className))
				return metadataStorage.get(className);
			
			
		//	if(className.length() == 0) return null;
			WebObjectType objType = new WebObjectType(Thread.currentThread().getContextClassLoader().loadClass(className));

			
			
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
	
	public ArrayList<WebObjectType> getMetaworksTypes(String[] classNames) throws Exception {
		
		ArrayList<WebObjectType> metadataArray = new ArrayList<WebObjectType>();
		
		for(int i=0; i<classNames.length; i++){
			if(classNames[i].endsWith("*")){
				List<Class> classes = findMyTypes(classNames[i]);
				
				for(Class clsInPkg : classes){
					metadataArray.add(getMetaworksType(clsInPkg.getName()));
				}
			}else{
				try{
					metadataArray.add(getMetaworksType(classNames[i]));
				}catch(Exception e){e.printStackTrace();}
			}
		}
		
		return metadataArray;
		
	}
	
	private List<Class> findMyTypes(String basePackage) throws IOException, ClassNotFoundException
	{
	    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

	    List<Class> candidates = new ArrayList<Class>();
	    String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
	                               resolveBasePackage(basePackage) + ".class";
	    Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
	    for (Resource resource : resources) {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
            
//	        if (resource.isReadable()) {
//	            if (isCandidate(metadataReader)) {
//	                candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
//	            }
//	        }
	    }
	    return candidates;
	}

	private String resolveBasePackage(String basePackage) {
	    return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
	}

//	private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException
//	{
//	    try {
//	        Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
//	        if (c.getAnnotation(XmlRootElement.class) != null) {
//	            return true;
//	        }
//	    }
//	    catch(Throwable e){
//	    }
//	    return false;
//	}
	
	public InvocationContext prepareToCall(String objectTypeName, Object object, String methodName, Map<String, Object> autowiredFields) throws Throwable{

		Class serviceClass = Thread.currentThread().getContextClassLoader().loadClass(objectTypeName);
		
		
    	//getBeanFactory().getBean(arg0)
    	
//		callingObjectTypeName.set(objectTypeName);
		
		//if the requested value object is IDAO which need to be converted to implemented one so that it can be invoked by its methods
		//Another case this required is when Spring is used since the spring base object should be auto-wiring operation
		WebApplicationContext springAppContext = null;
		if(TransactionalDwrServlet.useSpring) springAppContext = getBeanFactory();
		Object springBean = null;
		if(springAppContext!=null)
		try{
			//springBean = getBeanFactory().getBean(serviceClass);
			Map beanMap = springAppContext.getBeansOfType(serviceClass);
			Set keys = beanMap.keySet();			
			for (Object key : keys) {
			    if(springBean != null) {
				System.err.println("====== Warnning : MetaworksRemoteService.callMetaworksService get only one bean object of one class.");
				break;
			    }
			    springBean = beanMap.get(key);
			}
		}catch(Exception e){
			//TODO: check if there's any occurrance of @Autowired in the field list, it is required to check and shows some WARNING to the developer.
		}
		
		if(serviceClass.isInterface() || springBean!=null){
			String serviceClassNameOnly = WebObjectType.getClassNameOnly(serviceClass);
			
			if(serviceClass.isInterface()){
				serviceClassNameOnly = serviceClassNameOnly.substring(1, serviceClassNameOnly.length());
				serviceClass = Thread.currentThread().getContextClassLoader().loadClass(serviceClass.getPackage().getName() + "." + serviceClassNameOnly);
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

				//MetaworksObject need to initialize the property MetaworksContext if there's no data.
				if("MetaworksContext".equals(fd.getName()) && srcFieldValue==null && IDAO.class.isAssignableFrom(serviceClass)){
					srcFieldValue = new MetaworksContext();
				}
				
				boolean isSpringAutowiredField = false;
				try{
					isSpringAutowiredField = ((serviceClass.getMethod( "get"+ fd.getName(), new Class[]{})).getAnnotation(Autowired.class) != null);
				}catch(Exception e){					
				}
				
				if(!isSpringAutowiredField)
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
		
		Map autowiredObjects = autowireSpringFields(object);
		
		TransactionContext tx = TransactionContext.getThreadLocalInstance();
		if(connectionFactory!=null)
			tx.setConnectionFactory(getConnectionFactory());
		
		InvocationContext invocationContext = new InvocationContext();
		invocationContext.setObject(object);
		invocationContext.setAutowiredObjects(autowiredObjects);
		
		return invocationContext;
	}
		
    public Object callMetaworksService(String objectTypeName, Object object, String methodName, Map<String, Object> autowiredFields) throws Throwable{

    	InvocationContext invocationContext = prepareToCall(objectTypeName, object, methodName, autowiredFields);
    	object = invocationContext.getObject();
		
		try {
			Method m = object.getClass().getMethod(methodName, new Class[]{});
			Object returned = m.invoke(object, new Object[]{});
			if(m.getReturnType()!=void.class)
				return returned;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			if(!(e.getTargetException() instanceof MetaworksException))
				e.printStackTrace();
			
			throw e.getTargetException();
		}
		
		return object;
	}

    public WebApplicationContext getBeanFactory()
    {
        try {
			ServletContext srvCtx = ServerContextFactory.get().getServletContext();

			return WebApplicationContextUtils.getWebApplicationContext(srvCtx);
		} catch (Exception e) {
		//	e.printStackTrace();
			
			return null;
		}
    }
	
	public Map<Class, Object> autowireSpringFields(Object object) throws IllegalAccessException {
		Map<Class, Object> autowiredObjects = new HashMap<Class, Object>();

		if(object==null)
			return autowiredObjects;
		
		WebApplicationContext springAppContext = null;
		if(TransactionalDwrServlet.useSpring) springAppContext = MetaworksRemoteService.getInstance().getBeanFactory();
		else return autowiredObjects;
		
		if(springAppContext==null)
			return autowiredObjects;
		
		
		
		for(Field field: object.getClass().getFields()){
			if(field.getAnnotation(Autowired.class)!=null){
				

				Object springBean = null;
				if(springAppContext!=null)
				try{
					//springBean = getBeanFactory().getBean(serviceClass);
					Map beanMap = springAppContext.getBeansOfType(field.getType());
					Set keys = beanMap.keySet();			
					for (Object key : keys) {
					    springBean = beanMap.get(key);
					    
					    if(springBean != null) {
					    	break;
					    }
					}
				}catch(Exception e){
					//TODO: check if there's any occurrance of @Autowired in the field list, it is required to check and shows some WARNING to the developer.
				}
				
				if(springBean!=null){
					field.set(object, springBean);
					autowiredObjects.put(springBean.getClass(), springBean);
				}

			}
		}
		
		return autowiredObjects;
	}

	ConnectionFactory connectionFactory;
		public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}
	
		public void setConnectionFactory(ConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}
		
	boolean debugMode;
		public boolean isDebugMode() {
			return debugMode;
		}
		public void setDebugMode(boolean debugMode) {
			this.debugMode = debugMode;
		}
}