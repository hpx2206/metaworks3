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

	@ServiceMethod
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
}
