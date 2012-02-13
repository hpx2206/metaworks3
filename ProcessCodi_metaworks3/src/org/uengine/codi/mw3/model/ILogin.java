package org.uengine.codi.mw3.model;

import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface ILogin extends IDAO{

	@Id
	public String getUserId();
	public void setUserId(String userId);

	public String getName();
	public void setName(String name);

	public String getPortrait();
	public void setPortrait(String portrait);
	
	public boolean isAdmin();
	public void setAdmin(boolean isAdmin);

	@ServiceMethod(callByContent=true)
	public Main login() throws Exception;
}
