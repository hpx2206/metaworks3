package org.metaworks.example.navigation;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.TransactionalDwrServlet;

public class Login {
	
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String password;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	@ServiceMethod(callByContent=true)
	public Main login() throws Exception{
		
		
		return new Main();
	}
}
