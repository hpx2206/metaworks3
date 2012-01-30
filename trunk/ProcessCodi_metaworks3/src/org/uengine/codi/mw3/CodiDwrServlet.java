package org.uengine.codi.mw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.metaworks.dwr.TransactionalDwrServlet;



public class CodiDwrServlet extends TransactionalDwrServlet{

	public static ClassLoader codiClassLoader;
	
	final static String SECURITYMSG_SYSTEM_CLASS_USAGE_PROHIBITED = "Platform can't support to use this class";
	
	final static HashMap<String, String> securedClasses = new HashMap<String, String>();
	static{
		securedClasses.put(File.class.getName(), SECURITYMSG_SYSTEM_CLASS_USAGE_PROHIBITED);
	}
	

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(servletConfig);
		
		refreshClassLoader(null);
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Thread.currentThread().setContextClassLoader(codiClassLoader);

		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	
	public static void refreshClassLoader(String resourceName){
//		ClassLoader cl = new CodiClassLoader(CodiMetaworksRemoteService.class.getClassLoader());
		
		
		
		
		
		JavaSourceClassLoader cl = new JavaSourceClassLoader(CodiMetaworksRemoteService.class.getClassLoader()){


			@Override
			public InputStream getResourceAsStream(String name) {

				if(name.endsWith(".ejs") || name.endsWith(".ejs.js")){
					try {
						FileInputStream fis = new FileInputStream("/Users/jyjang/javasources/" + name);
						return fis;
					} catch (FileNotFoundException e) {
					}
					
				}

				return super.getResourceAsStream(name);
			}

			protected Class<?> findClass(String className)
					throws ClassNotFoundException {

				if(securedClasses.containsKey(className)){
					throw new ClassNotFoundException(securedClasses.get(className));
				}
				
				return super.findClass(className);
			}
			
			
		};
		
		
		URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
		URL urls[] = classLoader.getURLs();
		StringBuffer sbClasspath = new StringBuffer();
		for(URL url : urls){
			sbClasspath.append(url.getFile().toString()).append(";");
		}

		cl.setCompilerOptions(new String[]{"-classpath", "/Users/jyjang/Documents/workspace/ProcessCodi_metaworks3/WebContent/WEB-INF/lib/metaworks3.jar"});//sbClasspath.toString()});

		
		cl.setSourcePath(new File[]{new File("/Users/jyjang/javasources/")});
				
		Thread.currentThread().setContextClassLoader(cl);
		codiClassLoader = cl;
		
	}

	public static void initClassLoader(){
		if(codiClassLoader==null)
			refreshClassLoader(null);
		
		Thread.currentThread().setContextClassLoader(codiClassLoader);
		
	}
	
}