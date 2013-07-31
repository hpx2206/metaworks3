package org.uengine.codi.mw3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.metaworks.MetaworksException;
import org.metaworks.WebObjectType;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.InvocationContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.dwr.TransactionalDwrServlet;
import org.springframework.web.context.WebApplicationContext;
import org.uengine.codi.platform.SecurityContext;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class CodiMetaworksRemoteService extends MetaworksRemoteService{
	
	public static ClassLoader codiClassLoader;
	static Class metaworksServiceLocatorClass;
	
	public CodiMetaworksRemoteService() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException{
		
//		ClassLoader cl = new JavaSourceClassLoader(
//				this.getClass().getClassLoader(), 
//				new ResourceFinder(){
//
//					@Override
//					public Resource findResource(final String resourceName) {
//						
//						try {
//							if("MetaworksRemoteServiceLocator.java".equals(resourceName)){
//								Resource resource = new Resource(){
//
//									@Override
//									public String getFileName() {
//										return resourceName;
//									}
//
//									@Override
//									public long lastModified() {
//										return 0;
//									}
//
//									@Override
//									public InputStream open() throws IOException {
//										return new StringInputStream("public class MetaworksRemoteServiceLocator{ public org.metaworks.dwr.MetaworksRemoteService getInstance(){return new org.metaworks.dwr.MetaworksRemoteService();}}");
//
//									}
//									
//								};
//								
//								return resource;
//							}
//							
//							String defVerId = processManager.getProcessDefinitionProductionVersionByAlias(resourceName);
//							String classDefinition = processManager.getResource(defVerId);
//							final ClassDefinition classDef = (ClassDefinition) GlobalContext.deserialize(classDefinition, ClassDefinition.class);
//							
//							Resource resource = new Resource(){
//
//								@Override
//								public String getFileName() {
//									return resourceName;
//								}
//
//								@Override
//								public long lastModified() {
//									return 0;
//								}
//
//								@Override
//								public InputStream open() throws IOException {
//									return new StringInputStream(classDef.getSourceCode().getCode());
//								}
//								
//							};
//							
//							return resource;
//							
//						} catch (RemoteException e) {
//							// TODO Auto-generated catch block
//							//e.printStackTrace();
//							return null;
//						}
//						// TODO Auto-generated method stub
//						catch (Exception e) {
//							// TODO Auto-generated catch block
//							//e.printStackTrace();
//							
//							return null;
//						}
//					}
//					
//				},
//				"UTF-8", 
//				DebuggingInformation.NONE
//			){
//
//				@Override
//				protected synchronized Class<?> loadClass(String name,
//						boolean resolve) throws ClassNotFoundException {
//					
//					try{
//						Class<?> clazz = CodiMetaworksRemoteService.class.getClassLoader().loadClass(name);
//						if(resolve)
//							resolveClass(clazz);
//						
//						return clazz;
//					}catch(Exception e){
//						
//					}
//					
//					return super.loadClass(name, resolve);
//				}
//			
//			
//			
//		};
//		
		
//		refreshClassLoader(null);
		
//		metaworksServiceLocatorClass = cl.loadClass(CodiClassLoader.MetaworksServiceClassLoader);
//		Object mrsLocator = metaworksServiceLocatorClass.newInstance();
//		MetaworksRemoteService mrs = (MetaworksRemoteService) mrsLocator.getClass().getMethod("getInstance", new Class[]{}).invoke(mrsLocator, null);
		
		setInstance(new MetaworksRemoteService());

	}
	
	

	@Override
	//@Transactional(propagation=Propagation.REQUIRED)
	public Object callMetaworksService(String objectTypeName, Object object,
			String methodName, Map<String, Object> autowiredFields)
			throws Throwable {
		//Thread.currencotThread().setContextClassLoader(codiClassLoader);
		// TODO Auto-generated method stub
		
		Class serviceClass = Thread.currentThread().getContextClassLoader().loadClass(objectTypeName);
		
		boolean underPlatform = (serviceClass.getClassLoader().getClass() == CodiClassLoader.class);
		if(underPlatform)
			SecurityContext.getThreadLocalInstance().setNeedSecurityCheck(true);

//		Object returnVal = instance.callMetaworksService(objectTypeName, object, methodName,
//				autowiredFields);
		
	

    	InvocationContext invocationContext = instance.prepareToCall(objectTypeName, object, methodName, autowiredFields);
    	object = invocationContext.getObject();
    	
    	ProcessManagerRemote processManager = null;
    	
    	//1. try to get the processmanager which has been autowired among the properties of the service class
    	
		if(invocationContext.getAutowiredObjects().containsKey(ProcessManagerBean.class)){ //TODO: later this should check the hierarchy of ProcessManagerRemote 
			processManager = (ProcessManagerRemote) invocationContext.getAutowiredObjects().get(ProcessManagerBean.class);
		}

		
    	Object returnVal = null;
		
//    	
//    		final Thread currThread = Thread.currentThread();
//    		Thread threadChecker = new Thread(){
//    			Thread theThread = currThread;
//    			
//    			@Override
//    			public void run() {
//    				try {
//    					sleep(50000); //TODO: will occur thread full.
//    					if(theThread.isAlive())
//    						CodiMetaworksRemoteService.this.stopRequested();
//    				} catch (InterruptedException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
//    				
//    			}
//    			
//    		};
//    		
//    		threadCheck.start();
//    	
    	
		try {
			Method m = object.getClass().getMethod(methodName, new Class[]{});
			returnVal = m.invoke(object, new Object[]{});
			
			if(m.getReturnType()==void.class)
				returnVal = object;

			if(TransactionContext.getThreadLocalInstance().getSharedContext("processManagerBeanChanged") != null){
				processManager = getDirtyProcessManager(processManager);
	
				if(processManager!=null){
					processManager.applyChanges();				
				}
			}
			
			return returnVal;
			
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			if(!(e.getTargetException() instanceof MetaworksException))			
				e.printStackTrace();

			if(TransactionContext.getThreadLocalInstance().getSharedContext("processManagerBeanChanged") != null){
				processManager = getDirtyProcessManager(processManager);
				
				if(processManager!=null){
					try {
						processManager.cancelChanges();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
			}
			
			throw e.getTargetException();

		} finally{

			processManager = getDirtyProcessManager(processManager);

			if(processManager!=null){
				try {
					processManager.remove();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}

			if(underPlatform)
				SecurityContext.getThreadLocalInstance().setNeedSecurityCheck(false);
				
		}
		
	}



	private ProcessManagerRemote getDirtyProcessManager(
			ProcessManagerRemote processManager) {
		//2. try the case that the one of inner classes issued the processmanager
		if(TransactionContext.getThreadLocalInstance().getSharedContext("processManagerBeanUsed") != null){
			if(TransactionalDwrServlet.useSpring){
				WebApplicationContext springAppContext = getBeanFactory();
				
				Map beanMap = springAppContext.getBeansOfType(ProcessManagerRemote.class);
				Set keys = beanMap.keySet();			

				Object springBean = null;
					
				for (Object key : keys) {
					if(springBean != null) {
						break;
					}
					springBean = beanMap.get(key);
				}
				
				processManager = (ProcessManagerRemote) springBean;
			}
			
			return processManager;
		}

		return null;
		//return processManager;
	}

	@Override
	public WebObjectType getMetaworksType(String className) throws Exception {
		//Thread.currentThread().setContextClassLoader(codiClassLoader);
		// TODO Auto-generated method stub
		return instance.getMetaworksType(className);
	}
	
	
	
	
	@Override
	public ConnectionFactory getConnectionFactory() {
		//Thread.currentThread().setContextClassLoader(codiClassLoader);
		// TODO Auto-generated method stub
		return instance.getConnectionFactory();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		//Thread.currentThread().setContextClassLoader(codiClassLoader);
		// TODO Auto-generated method stub
		instance.setConnectionFactory(connectionFactory);
	}




	@Override
	public WebApplicationContext getBeanFactory() {
		// TODO Auto-generated method stub
//		if(codiClassLoader!=null)
//			Thread.currentThread().setContextClassLoader(codiClassLoader);
//		
		return instance.getBeanFactory();
	}




//	@Autowired
//	protected ProcessManagerRemote processManager;
//	

}
