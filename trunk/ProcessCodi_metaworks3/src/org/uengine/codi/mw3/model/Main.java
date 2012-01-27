package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredToClient;
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
		
		
		this.navigationWindowPanel = new WindowPanel();
		this.navigationWindowPanel.setPanel(new Navigation());
		this.navigationWindowPanel.setLayoutName("navigation");
		this.navigationWindowPanel.setName("네비게이션");
		
		this.contentWindowPanel = new WindowPanel();
		this.contentWindowPanel.setPanel(new ContentPanel());
		this.contentWindowPanel.setLayoutName("wih");
		this.contentWindowPanel.setName("");
		
		this.contactWindowPanel = new WindowPanel();
		this.contactWindowPanel.setPanel(new ContactPanel(login));
		this.contactWindowPanel.setLayoutName("contact");
		this.contactWindowPanel.setName("연락처");
				
		this.instanceListWindowPanel = new WindowPanel();
		this.instanceListWindowPanel.setPanel(new InstanceListPanel(session));
		this.instanceListWindowPanel.setLayoutName("worklist");
		this.instanceListWindowPanel.setName("개인중심 - 내가 할 일");
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
