package org.metaworks.example.navigation;

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
		return (Content) Thread.currentThread().getContextClassLoader().loadClass("org.metaworks.example.navigation." + getContentClassName()).newInstance(); 
	}
	
}
