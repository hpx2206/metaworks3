package org.uengine.codi.mw3.model;

import java.util.Hashtable;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.metaworks.annotation.ServiceMethod;

	
public class Locale{
	
	static Hashtable<String, Properties> webMessageBundles = new Hashtable<String, Properties>();

	String language;
		public String getLanguage() {
			return language;
		}	
		public void setLanguage(String language) {
			this.language = language;
		}
		
	Properties resourceBundle;		
		public Properties getResourceBundle() {
			return resourceBundle;
		}	
		public void setResourceBundle(Properties resourceBundle) {
			this.resourceBundle = resourceBundle;
		}

	@ServiceMethod(payload={"language"})
	public void load(){		
		// default language setting
		String language = "en";		
		if(this.getLanguage() != null)
			language = this.getLanguage();
		
		if (!webMessageBundles.containsKey(language)) {
			java.util.Locale locale = new java.util.Locale(language);
			PropertyResourceBundle propertyResourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle("org.uengine.messages", locale, getClass().getClassLoader());
			
			Properties props = new Properties();
			for(String key : propertyResourceBundle.keySet()){
				props.put(key, propertyResourceBundle.getString(key));
			}
			
			webMessageBundles.put(language, props);
		}
		
		resourceBundle = webMessageBundles.get(language);		
	}
	
	public String getString(String... keys){
	
		String message = keys[0];
		
		if(message.startsWith("$")){
			message = resourceBundle.getProperty(message.substring(1));			
			message = (message == null) ? keys[0] : message;
		}
		
		if(keys.length == 1)
			return message;		
		
		String ret_message = "";		
		String arr_message[] = message.split("%s");
		
		for(int i=0; i < arr_message.length; i++) {
			ret_message += arr_message[i];
			
			if (i+1 < keys.length) {
				String key = keys[i+1]; 		
				key = this.getString(key);
//				if(key.startsWith("$")) {
//					key = resourceBundle.getProperty(key.substring(1));					
//					key = (key == null) ? keys[i+1] : key;
//				}
				ret_message += key;	
			}													
	    }
		return ret_message;
	}
}
