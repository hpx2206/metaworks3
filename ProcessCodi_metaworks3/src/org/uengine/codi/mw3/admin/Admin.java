package org.uengine.codi.mw3.admin;

import javax.persistence.Id;
import javax.servlet.http.HttpSession;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ClassDesignerContentPanel;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.ILogin;
import org.uengine.codi.mw3.model.Login;
import org.uengine.codi.mw3.model.Session;


public class Admin {
	
	public Admin() throws Exception{
		resourceWindow = new ResourceWindow();
		contentWindow = new ContentWindow();
		mainMenu = new UClipseMainMenu();
		
	}
	
	
	public Admin(ILogin login) throws Exception{
		this();

		Session session = new Session();
		//session.setLogin(login);
		this.session = session;

	}
	
	UClipseMainMenu mainMenu;
	
		public UClipseMainMenu getMainMenu() {
			return mainMenu;
		}
	
		public void setMainMenu(UClipseMainMenu mainMenu) {
			this.mainMenu = mainMenu;
		}

	String defId;
		@Id
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	
	@ServiceMethod
	public void load() throws Exception{

		//setting the facebook user Id into session attribute;
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)httpSession.getAttribute("userId");
		
		Login login = new Login();
		login.setUserId(userId);
		
		resourceWindow = new ResourceWindow();

		Session session = new Session();
		//session.setLogin(login);
		this.session = session;

		contentWindow = new ContentWindow();		
	}
	
	@ServiceMethod
	public ContentWindow loadClassDef() throws Exception {

		classDesignerContentPanel.load(getDefId());
	
		return classDesignerContentPanel;
	}	
	
	@Autowired
	public ClassDesignerContentPanel classDesignerContentPanel;
	
		
	ResourceWindow resourceWindow;
		public ResourceWindow getResourceWindow() {
			return resourceWindow;
		}
		public void setResourceWindow(ResourceWindow resourceWindow) {
			this.resourceWindow = resourceWindow;
		}

	ContentWindow contentWindow;
		public ContentWindow getContentWindow() {
			return contentWindow;
		}
		public void setContentWindow(ContentWindow contentWindow) {
			this.contentWindow = contentWindow;
		}
	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
}
