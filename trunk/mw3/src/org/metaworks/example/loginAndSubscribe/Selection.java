package org.metaworks.example.loginAndSubscribe;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class Selection {
	String contentClassName;
	@Id
		public String getContentClassName() {
			return contentClassName;
		}
		public void setContentClassName(String contentClassName) {
			this.contentClassName = contentClassName;
		}
		
	@ServiceMethod
	public Content select() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Content content =  (Content) Class.forName("org.metaworks.example.loginAndSubscribe." + getContentClassName()).newInstance();
		 
		return content;
	}
	
	@AutowiredFromClient
	public Session session;
	
}
