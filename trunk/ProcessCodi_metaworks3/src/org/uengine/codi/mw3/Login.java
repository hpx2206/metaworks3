package org.uengine.codi.mw3;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.User;

public class Login extends Database<ILogin> implements ILogin{

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

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public MainPanel login() throws Exception {
		//setting the facebook user Id into session attribute;
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		session.setAttribute("userId", getUserId());
		
		if(new File(CodiClassLoader.mySourceCodeBase()).exists()){
			session.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());
		}
		
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getUserId());
		
		//return new MainPanel(new Knowledge(loginUser));
		//return new MainPanel(new IDE(loginUser));
		return new MainPanel(new Main(loginUser));
	}
}