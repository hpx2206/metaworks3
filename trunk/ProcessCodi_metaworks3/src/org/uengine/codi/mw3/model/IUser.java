package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IUser extends IDAO{
	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	
	public String getName();
	public void setName(String name);
	
	
	@ServiceMethod(when="addContact", callByContent=true)
	public ContactList addContact() throws Exception;
}
