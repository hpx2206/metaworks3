package org.uengine.codi.mw3.model;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.admin.Admin;

public class LoginAdmin {

	@Id
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String portrait;
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		
	boolean isAdmin;
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		
	String defId;	
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	@Autowired
	ClassDesignerContentPanel classDesignerContentPanel;
		@Autowired
		public ClassDesignerContentPanel getClassDesignerContentPanel() {
			return classDesignerContentPanel;
		}
		public void setClassDesignerContentPanel(
				ClassDesignerContentPanel classDesignerContentPanel) {
			this.classDesignerContentPanel = classDesignerContentPanel;
		}

	@ServiceMethod(callByContent=true)
	public Admin login() throws Exception {
		//setting the facebook user Id into session attribute;
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		session.setAttribute("userId", getUserId());
		
		if(new File(CodiClassLoader.mySourceCodeBase()).exists()){
			session.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());
		}
		
		Login loginUser = new Login();
		
		loginUser.setAdmin(isAdmin());
		loginUser.setName(getName());
		loginUser.setPortrait(getPortrait());
		loginUser.setUserId(getUserId());
		
		Admin admin = new Admin(loginUser);
		
		if(getDefId() != null && getDefId().length() > 0){
			classDesignerContentPanel.load(getDefId());
		
			admin.setContentWindow(classDesignerContentPanel);
		}
		
		return admin;
	}
}
