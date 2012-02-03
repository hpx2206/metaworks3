package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.Admin;

public class Main {
	
	public Main(){}
	
	protected Main(ILogin login) throws Exception{
		
		//login.setUserId("1401720840");		
		this.login = login;

		Session session = new Session();
		session.setLogin(login);
		this.session = session;		
		
		this.navigationWindow = new NavigationWindow();
		
		this.contentWindow = new ContentWindow();
		
		this.contactWindow = new ContactWindow(login);
				
		this.instanceListWindow = new InstanceListWindow(session);
	}
	
	ILogin login;
		@Hidden
		@Id
		@AutowiredToClient
		public ILogin getLogin() {
			return login;
		}
		public void setLogin(ILogin login) {
			this.login = login;
		}
		
	Session session;
		@AutowiredToClient
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	NavigationWindow navigationWindow;
		public NavigationWindow getNavigationWindow() {
			return navigationWindow;
		}
		public void setNavigationWindow(NavigationWindow navigationWindow) {
			this.navigationWindow = navigationWindow;
		}

	ContentWindow contentWindow;
		public ContentWindow getContentWindow() {
			return contentWindow;
		}
		public void setContentWindow(ContentWindow contentWindow) {
			this.contentWindow = contentWindow;
		}


	ContactWindow contactWindow;
		public ContactWindow getContactWindow() {
			return contactWindow;
		}
		public void setContactWindow(ContactWindow contactWindow) {
			this.contactWindow = contactWindow;
		}

	InstanceListWindow instanceListWindow;
		public InstanceListWindow getInstanceListWindow() {
			return instanceListWindow;
		}
	
		public void setInstanceListWindow(InstanceListWindow instanceListWindow) {
			this.instanceListWindow = instanceListWindow;
		}
	

	@ServiceMethod
	public ILogin logout(){
		return login;
	}
	
	@ServiceMethod
	public Main refresh() throws Exception{
		return new Main(login);
	}
	
	@ServiceMethod
	public Admin admin() throws Exception{
		return new Admin(login);
	}	
}
