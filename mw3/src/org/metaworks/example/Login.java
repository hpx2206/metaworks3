package org.metaworks.example;

import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksObject;
import org.metaworks.annotation.ServiceMethod;

public class Login extends MetaworksObject<ILogin> implements ILogin{
	
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
		
		try {
			ILogin databaseLoginInfo = databaseMe();
			
			if(databaseLoginInfo.getPassword().equals(getPassword())){ //check userPassword
				
				
				Main main = new Main(this);
				
				return main; //lets login success;
			}else{
				setMessage("wrong password or id");
				return this;
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
			e.printStackTrace();
			return this;
		}
	
	}

	@ServiceMethod(when=WHEN_NEW, callByContent=true)
	public void subscribe() throws Exception{
		
		createDatabaseMe();
	
	}
}
