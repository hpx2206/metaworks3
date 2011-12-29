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
		this.login = login;
		this.navigation = new Navigation();
		
		this.contentPanel = new ContentPanel();
		
		this.contactPanel = new ContactPanel(login);
		
		Session session = new Session();
		session.setLogin(login);
		this.session = session;
		
		instanceListPanel = new InstanceListPanel(session);

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

	Navigation navigation;
	
		public Navigation getNavigation() {
			return navigation;
		}
	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}
	

	InstanceListPanel instanceListPanel;
	
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
	
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}

	ContentPanel contentPanel;
		public ContentPanel getContentPanel() {
			return contentPanel;
		}
		public void setContentPanel(ContentPanel contentPanel) {
			this.contentPanel = contentPanel;
		}
		
	ContactPanel contactPanel;
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
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
	public Admin admin(){
		return new Admin(login);
	}
}
