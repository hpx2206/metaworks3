package org.uengine.kernel;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class GlobalContext {
	
	static Properties properties;
	
	public static String getPropertyString(String key, String defaultValue){
		Properties sysProp = getProperties();
			
		if(sysProp!=null)
			return sysProp.getProperty(key, defaultValue);
				
		return defaultValue;
	}
	
	public static void setProperty(String key, String value){
		properties.put(key, value);
	}
	
	public static String getPropertyString(String key){
		return getPropertyString(key, null);
	}
	
	public static Properties getProperties() {
		if(properties==null){
			try {
				URL url = null;
								
				url = getResourceURL("org/uengine/uengine.properties");

				if (url != null) {
					InputStream is = url.openStream();
					properties = new Properties();
					properties.load(is);
					is.close();

					System.out.println("Loading " + url);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		return properties;
	}

	public static URL getResourceURL(String path) throws ClassNotFoundException{
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		
		return  url;
	}
	
	public static String getCodeBaseRoot() {
		String coderoot = GlobalContext.getPropertyString("codebase", "uengine/codebase/");
		
		if(!coderoot.endsWith("/")) coderoot=coderoot+"/";
		
		return coderoot;
	}
}
