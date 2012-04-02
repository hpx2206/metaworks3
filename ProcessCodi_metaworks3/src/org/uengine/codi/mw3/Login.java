package org.uengine.codi.mw3;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.admin.IDE;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.Session;
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

	public MainPanel login() throws Exception {
		//setting the facebook user Id into session attribute;
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.setAttribute("userId", getUserId());
		
		if(new File(CodiClassLoader.mySourceCodeBase()).exists()){
			httpSession.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());
		}
		
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getUserId());
		
		Session session = new Session();
		session.setUser(loginUser);
		
/*		
*/		
		//return new MainPanel(new Knowledge(loginUser));
//		return new MainPanel(new IDE(session));
		return new MainPanel(new Main(session));
	}

	public MainPanel loginSocialCoding() throws Exception {
		//setting the facebook user Id into session attribute;
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.setAttribute("userId", getUserId());
				
		if(new File(CodiClassLoader.mySourceCodeBase()).exists()){
			httpSession.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());
		}
		
		IUser loginUser = new User();
		
		loginUser.setName(getName());
		loginUser.setUserId(getUserId());
				
		Session session = new Session();
		session.setUser(loginUser);
		session.setDefId(getDefId());

		return new MainPanel(new IDE(session));
	}

}