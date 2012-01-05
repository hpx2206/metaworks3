package org.metaworks.example.loginAndSubscribe;

import javax.persistence.Id;
import javax.persistence.Table;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Table(name="login")
public interface ILogin extends IDAO{
	
	@Id
	public String getUserId();
	public void setUserId(String userId);

	public String getPassword();
	public void setPassword(String password);
		
	@ServiceMethod(callByContent=true)
	public Main login() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void subscribe() throws Exception;
}
