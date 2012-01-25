package org.uengine.codi.mw3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.metaworks.dwr.TransactionalDwrServlet;



public class CodiDwrServlet extends TransactionalDwrServlet{

	public static ClassLoader codiClassLoader;
	
	

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
		
		JavaSourceClassLoader cl = new JavaSourceClassLoader(CodiMetaworksRemoteService.class.getClassLoader());
		
		
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
	}
	
}