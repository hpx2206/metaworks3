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
		
	@ServiceMethod(callByContent=true, keyBinding="enter", inContextMenu=true)
	public Object login() throws Exception;
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public void subscribe() throws Exception;
	
	@ServiceMethod(callByContent=true, inContextMenu=true, needToConfirm=true)
	public void unsubscribe() throws Exception;
	
	
}
