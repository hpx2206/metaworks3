package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.admin.Admin;

public class LoginAdmin {

	String userId;	
		public String getUserId() {
			return userId;
		}	
		public void setUserId(String userId) {
			this.userId = userId;
		}

	@ServiceMethod
	public Admin load() throws Exception {
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)session.getAttribute("userId");

		if(userId == null){
			throw new Exception("user id is null");
		}
		
		Login login = new Login();
		login.setUserId(userId);

		Admin admin = new Admin(login);

		return admin;
	}

}
