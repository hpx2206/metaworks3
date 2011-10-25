package org.metaworks.website;

import org.metaworks.MetaworksObject;

public class User extends MetaworksObject<IUser> implements IUser{
	int userId;
	String firstName;
	String lastName;
	String emailaddr;
	String password;
	String portrait;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailaddr() {
		return emailaddr;
	}
	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	
	
}
