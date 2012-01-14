package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.model.ContentPanel;
import org.uengine.codi.mw3.model.ILogin;
import org.uengine.codi.mw3.model.Session;

public class Admin {
	
	public Admin(ILogin login) throws Exception{
//		formDefinition = new FormDefinition();
//		formInstance = new FormInstance();
		
		resourcePanel = new ResourcePanel();
		resourcePanel.refresh();
		
		Session session = new Session();
		session.setLogin(login);
		this.session = session;
		
		contentPanel = new ContentPanel();
		

	}
	
	ResourcePanel resourcePanel;
		public ResourcePanel getResourcePanel() {
			return resourcePanel;
		}
		public void setResourcePanel(ResourcePanel resourcePanel) {
			this.resourcePanel = resourcePanel;
		}
		
		
	ContentPanel contentPanel;
	
		public ContentPanel getContentPanel() {
			return contentPanel;
		}
		public void setContentPanel(ContentPanel contentPanel) {
			this.contentPanel = contentPanel;
		}
		
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

}
