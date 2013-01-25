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
		
	String message;
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	public Object login() throws Exception{
		if(databaseMe().getPassword().equals(getPassword()))
			return new Main(getUserId());
		else
			setMessage("Password is wrong. Forgot?");
		
		return this;
	}
	
	public void subscribe() throws Exception {
		createDatabaseMe();
	}
	
	@Override
	public void unsubscribe() throws Exception {
		deleteDatabaseMe();
		
	}
}
