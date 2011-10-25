package org.metaworks.example;

import javax.persistence.Id;

import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface ILogin extends IDAO{

	@Id
	public String getUserId();
	public void setUserId(String userId);

	public String getPassword();
	public void setPassword(String password);

	@NonEditable
	public String getMessage();
	public void setMessage(String message);
	
	@ServiceMethod(callByContent=true)
	Object login() throws Exception;
	
}
