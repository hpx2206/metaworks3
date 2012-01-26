package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.model.ContentPanel;
import org.uengine.codi.mw3.model.ILogin;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WindowPanel;


public class Admin {
	
	public Admin(ILogin login) throws Exception{
//		formDefinition = new FormDefinition();
//		formInstance = new FormInstance();

		ResourcePanel resourcePanel = new ResourcePanel();
		resourcePanel.refresh();

		resourceWindowPanel = new WindowPanel();
		resourceWindowPanel.setPanel(resourcePanel);
		
		Session session = new Session();
		session.setLogin(login);
		this.session = session;
		
		contentPanel = new ContentPanel();
		

	}
	
	WindowPanel resourceWindowPanel;		
		public WindowPanel getResourceWindowPanel() {
			return resourceWindowPanel;
		}
		public void setResourceWindowPanel(WindowPanel resourceWindowPanel) {
			this.resourceWindowPanel = resourceWindowPanel;
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
