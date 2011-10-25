package org.metaworks.website;

import org.metaworks.dao.IDAO;

public interface IUser extends IDAO{
	
	public int getUserId();
	public void setUserId(int userId);
	
	
	public String getFirstName();
	public void setFirstName(String firstName);
	
	public String getLastName();
	public void setLastName(String lastName);

	
	public String getEmailaddr();
	public void setEmailaddr(String emailaddr);
	
	
	public String getPassword();
	public void setPassword(String password);
	
	
	public String getPortrait();
	public void setPortrait(String portrait);
	
	
}
