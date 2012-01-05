package org.metaworks.example.loginAndSubscribe;

import org.metaworks.dao.Database;

public class Login extends Database<ILogin> implements ILogin{
	
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
		
	public Main login() throws Exception{
		if(databaseMe().getPassword().equals(getPassword()))
			return new Main(getUserId());
		else
			throw new Exception("Password is wrong. Forgot?");
	}
	
	public void subscribe() throws Exception {
		createDatabaseMe();
	}
}
