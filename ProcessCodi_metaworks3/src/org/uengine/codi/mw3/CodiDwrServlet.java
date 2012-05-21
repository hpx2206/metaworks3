package org.uengine.codi.mw3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.util.HotSwapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.ObjectType;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.dwr.TransactionalDwrServlet;
import org.uengine.codi.platform.Console;
import org.uengine.codi.platform.SecurityContext;
import org.uengine.kernel.GlobalContext;




public class CodiDwrServlet extends TransactionalDwrServlet{

	final static String SECURITYMSG_SYSTEM_USAGE_PROHIBITED = " 패키지 이하의 클래스는 사용할 수 없습니다.".intern();///"Your App can't use any of classes under :".intern();
	
	final static HashMap<String, String> securedPackages = new HashMap<String, String>();
	
	static PrintStream originalSystemOut = System.out;
	static PrintStream originalSystemErr = System.err;

	static{
		securedPackages.put("java.sql", SECURITYMSG_SYSTEM_USAGE_PROHIBITED);
//		securedPackages.put(File.class.getPackage().getName(), SECURITYMSG_SYSTEM_USAGE_PROHIBITED);
///		securedPackages.put(Mongo.class.getPackage().getName(), SECURITYMSG_SYSTEM_USAGE_PROHIBITED);
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
		
		boolean needToCheck = SecurityContext.getThreadLocalInstance().isNeedSecurityCheck(); //false;
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

		
		
		//Security setting 1


		
		System.setOut(new PrintStream(new ByteArrayOutputStream()){
			public void println(String str){
				originalSystemOut.println(str);
				
				if(SecurityContext.getThreadLocalInstance().isNeedSecurityCheck()){
					Console.addLog(str);
				}
			}

			public void println(Object x) {
				originalSystemOut.println(x);

				if(SecurityContext.getThreadLocalInstance().isNeedSecurityCheck()){
					Console.addLog(""+x);
				}
			}
			
		});	

		System.setErr(new PrintStream(new ByteArrayOutputStream()){
			public void println(String str){
				originalSystemErr.println(str);
//				addDebugInfo(new StringBuilder().append(str).append("\n").toString());
			}
			public void print(String str){
				originalSystemErr.println(str);
//				addDebugInfo(str);
			}
			public void println(Object x) {
				originalSystemOut.println(x);
//				addDebugInfo(x.toString());
			}
		});


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
		
    	try {
/////////////// TODO: disabled for easy testing. someday we need to find dettach with the server

    		
 //   		HotSwapper hs = new HotSwapper(8000);
    		
//			ClassPool pool = ClassPool.getDefault();
//			
//			String[] securedClassMehtods = GlobalContext.getPropertyStringArray("secured.classmethods");
//			
//			for(String securedClassMethod : securedClassMehtods){
//				
//				securedClassMethod = securedClassMethod.trim();
//				
//				int whereLastDot = securedClassMethod.lastIndexOf('.');
//				final String className = securedClassMethod.substring(0, whereLastDot);
//				final String methodName = securedClassMethod.substring(whereLastDot+1);
//				
//				CtClass cc = pool.get(className);
//				CtMethod cm = cc.getDeclaredMethod(methodName);
//				
//				cm.instrument(
//						new ExprEditor() {
//							boolean checked = false;
//							
//							public void edit(MethodCall m)
//							throws CannotCompileException
//							{
//								
//								if(!checked){
//									m.replace("{ if(codi.platform.SecurityContext.getThreadLocalInstance().isNeedSecurityCheck())" +
//											" throw new SecurityException(\"platform denies your request to access '" + 
//											className + "." + methodName + "()" +
//											"' directly. Use org.uengine.codi.platform.* instead.\"); $_ = $proceed($$); }");
//									checked = true;
//								}
//							}
//						});
//				
//				Class.forName(className);
	
//				hs.reload(className, cc.toBytecode());
	//		}

		}catch(Exception e){
			throw new RuntimeException(e);
		}
		

		
    	CodiClassLoader.refreshClassLoader(null);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		if(session!=null){
			String sourceCodeBase = (String) session.getAttribute("sourceCodeBase");
			if(sourceCodeBase!=null){
				
				CodiClassLoader clForSession = CodiClassLoader.createClassLoader(sourceCodeBase);
				
				Thread.currentThread().setContextClassLoader(clForSession);				
			}else{
				Thread.currentThread().setContextClassLoader(CodiClassLoader.codiClassLoader);
			}
		}else{
			Thread.currentThread().setContextClassLoader(CodiClassLoader.codiClassLoader);
		}

		// TODO Auto-generated method stub
		super.doPost(request, response);
	}


	

	
}
