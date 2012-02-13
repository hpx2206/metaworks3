package org.uengine.codi.mw3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.RemoteException;
import java.util.Map;

import org.apache.tools.ant.filters.StringInputStream;
import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.metaworks.WebObjectType;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
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
	public Object callMetaworksService(String objectTypeName, Object object,
			String methodName, Map<String, Object> autowiredFields)
			throws Throwable {
		//Thread.currentThread().setContextClassLoader(codiClassLoader);
		// TODO Auto-generated method stub
		
		Class serviceClass = Thread.currentThread().getContextClassLoader().loadClass(objectTypeName);
		
		boolean underPlatform = (serviceClass.getClassLoader().getClass() == CodiClassLoader.class);
		if(underPlatform)
			TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(true);

		Object returnVal = instance.callMetaworksService(objectTypeName, object, methodName,
				autowiredFields);
		

		if(underPlatform)
			TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(false);
			
		
		return returnVal;
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
