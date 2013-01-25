package org.metaworks.example.loginAndSubscribe;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;

public class Main {

	Menu menu;
		public Menu getMenu() {
			return menu;
		}
		public void setMenu(Menu menu) {
			this.menu = menu;
		}

	Content content;
		public Content getContent() {
			return content;
		}
		public void setContent(Content content) {
			this.content = content;
		}
		
	Session session;
//	@Hidden
//	@AutowiredToClient
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	protected Main(String loginUserId){
		setMenu(new Menu());
		setContent(new Content());
		setSession(new Session());
		getSession().setUserId(loginUserId);
	}

}
