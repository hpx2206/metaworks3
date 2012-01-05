package org.metaworks.example.loginAndSubscribe;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

//@Face(ejsPath="genericfaces/Window.ejs")

public class Content {

	String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	String html;
		public String getHtml() {
			return html;
		}
		public void setHtml(String html) {
			this.html = html;
		}
		
	@ServiceMethod(callByContent=true)
	public void refresh(){
		html = html  + session.getUserId();
	}

	@AutowiredFromClient
	public Session session;
	
}
