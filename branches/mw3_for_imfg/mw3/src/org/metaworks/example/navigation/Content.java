package org.metaworks.example.navigation;

import org.metaworks.annotation.Face;

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
	
}
