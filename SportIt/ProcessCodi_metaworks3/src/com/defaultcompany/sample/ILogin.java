package com.defaultcompany.sample;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface ILogin extends IDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BPM_SEQ")
	public int getId() throws Exception;
	public void setId(int id) throws Exception;
	
	public String getUserId() throws Exception;
	public void setUserId(String userId) throws Exception;
	
	public String getPassword() throws Exception;
	public void setPassword(String password) throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void createUser() throws Exception;
}
