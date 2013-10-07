package com.defaultcompany.activities;

import java.io.Serializable;

import org.metaworks.FieldDescriptor;
import org.metaworks.Type;

public class MetaWeblogAuth implements Serializable {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "ApiUrl", "BlogId", "UserName", "Password" });
		FieldDescriptor apiUrlFd = type.getFieldDescriptor("ApiUrl");
		apiUrlFd.setDisplayName("APIUrl");
	}
	
	private String apiUrl;
		public String getApiUrl() {
			return apiUrl;
		}
		public void setApiUrl(String apiUrl) {
			this.apiUrl = apiUrl;
		}

	private String blogId;
		public String getBlogId() {
			return blogId;
		}
		public void setBlogId(String blogId) {
			this.blogId = blogId;
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

}
