package org.metaworks.example;

import org.metaworks.dao.Database;

public class Login extends Database<ILogin> implements ILogin {
	
	Long id;
		public Long getId() throws Exception { return id; }
		public void setId(Long id) throws Exception { this.id = id; }
	
	String userId;
		public String getUserId() throws Exception { return userId;}
		public void setUserId(String userId) throws Exception { this.userId = userId; }
	
	String password;
		public String getPassword() throws Exception { return password; }
		public void setPassword(String password) throws Exception { this.password = password; }
	
	
	public void createUser() throws Exception {
		createDatabaseMe();
		
		System.out.println("id = " + id + ", userId = " + userId + ", password = " + password);
	}
}
