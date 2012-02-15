package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.admin.Admin;

public class LoginAdmin {
	
	@ServiceMethod
	public Admin load() throws Exception {
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		Login login = new Login();
		login.setUserId(userId);
		
		Admin admin = new Admin(login);
		
		return admin;
	}
		
		
		
}
