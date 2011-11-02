package org.metaworks.website;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;


public class FacebookLoginUser extends Database<IFacebookLoginUser> implements IFacebookLoginUser{

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

	@Override
	public Main personalizeMain() throws Exception {
		try{
			setAdmin(databaseMe().isAdmin());
		}catch(Exception e){}
		
		Main main = new Main();
		
		Session session = new Session();
		session.setLoginUser(this);
		
		main.setSession(session);
		main.load();
		
		return main;
	}
	
}
