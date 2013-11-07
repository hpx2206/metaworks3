package org.metaworks.example;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.Popup;

@Table(name="mylogintb")
public interface ILogin extends IDAO {

	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BPM_SEQ")
	public Long getId() throws Exception;
	public void setId(Long id) throws Exception;
	
	public String getUserId() throws Exception;
	public void setUserId(String userId) throws Exception;
	
	public String getPassword() throws Exception;
	public void setPassword(String password) throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup createUser() throws Exception;
}
