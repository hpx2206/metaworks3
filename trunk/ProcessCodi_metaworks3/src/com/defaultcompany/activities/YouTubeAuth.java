package com.defaultcompany.activities;

import java.io.Serializable;

import org.metaworks.Type;

public class YouTubeAuth implements Serializable {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "UserName", "Password", "DeveloperKey" });
	}

	private String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	private String password;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	private String developerKey;
		public String getDeveloperKey() {
			return developerKey;
		}
		public void setDeveloperKey(String developerKey) {
			this.developerKey = developerKey;
		}
}
