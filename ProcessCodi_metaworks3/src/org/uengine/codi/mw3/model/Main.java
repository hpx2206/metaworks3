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
		
		login.setUserId("1401720840");		
		this.login = login;

		Session session = new Session();
		session.setLogin(login);
		this.session = session;		
		
		this.navigationWindowPanel = new WindowPanel(new Navigation());
		
		this.contentWindowPanel = new WindowPanel(new ContentPanel());
		
		this.contactWindowPanel = new WindowPanel(new ContactPanel(login));
				
		this.instanceListWindowPanel = new WindowPanel(new InstanceListPanel(session));
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
		
	WindowPanel navigationWindowPanel;
		public WindowPanel getNavigationWindowPanel() {
			return navigationWindowPanel;
		}
		public void setNavigationWindowPanel(WindowPanel navigationWindowPanel) {
			this.navigationWindowPanel = navigationWindowPanel;
		}

	WindowPanel instanceListWindowPanel;
		public WindowPanel getInstanceListWindowPanel() {
			return instanceListWindowPanel;
		}	
		public void setInstanceListWindowPanel(WindowPanel instanceListWindowPanel) {
			this.instanceListWindowPanel = instanceListWindowPanel;
		}

	WindowPanel contentWindowPanel;
		public WindowPanel getContentWindowPanel() {
			return contentWindowPanel;
		}
		public void setContentWindowPanel(WindowPanel contentWindowPanel) {
			this.contentWindowPanel = contentWindowPanel;
		}

	WindowPanel contactWindowPanel;
		public WindowPanel getContactWindowPanel() {
			return contactWindowPanel;
		}
		public void setContactWindowPanel(WindowPanel contactWindowPanel) {
			this.contactWindowPanel = contactWindowPanel;
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
