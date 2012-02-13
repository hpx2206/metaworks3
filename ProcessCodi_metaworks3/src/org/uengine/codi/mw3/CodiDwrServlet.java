package org.uengine.codi.mw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.dwr.TransactionalDwrServlet;


import com.mongodb.Mongo;



public class CodiDwrServlet extends TransactionalDwrServlet{

	public static CodiClassLoader codiClassLoader;
	
	final static String SECURITYMSG_SYSTEM_USAGE_PROHIBITED = " 패키지 이하의 클래스는 사용할 수 없습니다.".intern();///"Your App can't use any of classes under :".intern();
	
	final static HashMap<String, String> securedPackages = new HashMap<String, String>();
	
	static{
		securedPackages.put(File.class.getPackage().getName(), SECURITYMSG_SYSTEM_USAGE_PROHIBITED);
		securedPackages.put(Mongo.class.getPackage().getName(), SECURITYMSG_SYSTEM_USAGE_PROHIBITED);
	}

	static public boolean okIfSystem(){
		StackTraceElement[] stackElem = Thread.currentThread().getStackTrace();
		
		for(int i = 0; i<stackElem.length; i++){
			if(MetaworksRemoteService.class.getName().equals(stackElem[i].getClassName())){

				return false;
			}
		}
		
		return true;
	
	}
	
	static public void checkPermission(String pkg) throws SecurityException{
		
		//StackTraceElement[] stackElem = Thread.currentThread().getStackTrace();
		
		boolean needToCheck = TransactionContext.getThreadLocalInstance().isNeedSecurityCheck(); //false;
//		for(int i = stackElem.length-1; i > 4; i--){ //beyond 5 steps, they are the actual call stack.
//			if(MetaworksRemoteService.class.getName().equals(stackElem[i].getClassName())
//					&& "callMetaworksService".equals(stackElem[i].getMethodName())
//					){
//				
//				needToCheck = true;
//			
//			}else if(stackElem[i].getClassName().startsWith("org.metaworks") || stackElem[i].getClassName().startsWith("org.uengine")){
//				needToCheck = false;
//			}else if(JavaSourceClassLoader.class.getName().equals(stackElem[i].getClassName())
//					//&& !"getJavaFileForInput".equals(stackElem[i].getMethodName())
//					){
//				
//				needToCheck = true;
//			
//			}else if("FileInputJavaFileManager.java".equals(stackElem[i].getFileName())
//					//&& "getJavaFileForInput".equals(stackElem[i].getMethodName())
//					){
//				
//				needToCheck = false;
//			
//			}else if("WebappClassLoader.java".equals(stackElem[i].getFileName())
//					&& "findClass".equals(stackElem[i].getMethodName())
//					){
//				
//				needToCheck = false;
//			
//			}else if("ByteArrayJavaFileManager.java".equals(stackElem[i].getFileName())
//					&& "getJavaFileForOutput".equals(stackElem[i].getMethodName())
//					){
//				
//				needToCheck = false;
//			
//			}
//		}

		if(needToCheck && securedPackages.containsKey(pkg)){
			
			//we will prohibit only when user creates some object directly
			StackTraceElement[] stackElem = Thread.currentThread().getStackTrace();
			
			throw new SecurityException("'" + pkg + "' " + securedPackages.get(pkg));
		
		}
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(servletConfig);
		
		
		
		
		System.setSecurityManager(new SecurityManager(){

			
//		    public void checkPermission(Permission perm)
//		    		throws AccessControlException 
//		    	    {
//		    	
//		    	super.checkPermission(perm);
//		    	//Object context = super.getSecurityContext();
//		    	//System.out.print(context);
//		    }
//		    
		    
			@Override
			public void checkPackageAccess(String pkg){
				CodiDwrServlet.checkPermission(pkg);
				
				super.checkPackageAccess(pkg);
			}

//			@Override
//			public void checkPermission(Permission perm, Object context) {
//				
//				if(okIfSystem()) return;//TODO or we need to change the security profile.
//				
//				super.checkPermission(perm, context);
//			}
//
			
		});
		
		refreshClassLoader(null);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		if(session!=null){
			String sourceCodeBase = (String) session.getAttribute("sourceCodeBase");
			if(sourceCodeBase!=null){
				
				CodiClassLoader clForSession = createClassLoader(sourceCodeBase);
				
				Thread.currentThread().setContextClassLoader(clForSession);				
			}else{
				Thread.currentThread().setContextClassLoader(codiClassLoader);
			}
		}else{
			Thread.currentThread().setContextClassLoader(codiClassLoader);
		}

		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	public static CodiClassLoader createClassLoader(String sourceCodeBase){
		
		CodiClassLoader cl = new CodiClassLoader(CodiMetaworksRemoteService.class.getClassLoader());
		
		URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
		URL urls[] = classLoader.getURLs();
		StringBuffer sbClasspath = new StringBuffer();
		for(URL url : urls){
			sbClasspath.append(url.getFile().toString()).append(":");
		}

		cl.setCompilerOptions(
				new String[]{
//						"-classpath", "/Users/jyjang/Documents/workspace/ProcessCodi_metaworks3/WebContent/WEB-INF/lib/metaworks3.jar:/Users/jyjang/Documents/workspace/ProcessCodi_metaworks3/WebContent/WEB-INF/lib/mongo-2.7.2.jar"		
						"-classpath", sbClasspath.toString()
				});

		if(sourceCodeBase==null)
			sourceCodeBase = "/Users/jyjang/javasources/";
		
		cl.setSourcePath(new File[]{new File(sourceCodeBase)});
				
		return cl;
	}
	
	public static void refreshClassLoader(String resourceName){
		
		String sourceCodeBase = null;
		
		if(TransactionContext.getThreadLocalInstance()!=null && TransactionContext.getThreadLocalInstance().getRequest()!=null){
			HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession();
			if(session!=null){
				sourceCodeBase = (String) session.getAttribute("sourceCodeBase");
			}
		}
		
		CodiClassLoader cl = createClassLoader(sourceCodeBase);

		Thread.currentThread().setContextClassLoader(cl);
		codiClassLoader = cl;
		
	}

	public static void initClassLoader(){
		if(codiClassLoader==null)
			refreshClassLoader(null);
		
		Thread.currentThread().setContextClassLoader(codiClassLoader);
		
	}
	
}
