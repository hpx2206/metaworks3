package org.metaworks.website;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;


public interface IFacebookLoginUser extends IDAO{

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
	public Main personalizeMain() throws Exception;
		
}
