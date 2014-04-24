package com.defaultcompany.sample;

import org.metaworks.dao.Database;

public class Login extends Database<ILogin> implements ILogin {
	
	int id;
		public int getId() throws Exception { return id; }
		public void setId(int id) throws Exception { this.id = id; }
	
	String userId;
		public String getUserId() throws Exception { return userId;}
		public void setUserId(String userId) throws Exception { this.userId = userId; }
	
	String password;
		public String getPassword() throws Exception { return password; }
		public void setPassword(String password) throws Exception { this.password = password; }
	
	
	public void createUser() throws Exception {
		createDatabaseMe();
	}
}
