package org.uengine.codi.mw3.model;

import java.util.Hashtable;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.common.MainPanel;

	
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

	public void load(){
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
	
	@ServiceMethod(callByContent=true)
	public MainPanel loadMain() throws Exception{
		MainPanel mainPanel = new MainPanel(new Main(session));
		
		return mainPanel;
	}
	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
			setLanguage(session.getEmployee().getLocale());
		}
	

}
