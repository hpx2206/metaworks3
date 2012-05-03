package com.defaultcompany.sample;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.dao.IDAO;

public interface IComtable extends IDAO {
	@Id
	public String getComCode();
	public void setComCode(String comCode);
	
	@Name
	public String getComName();
	public void setComName(String comName);
	
	public String getDescription();
	public void setDescription(String description);
	
	public String getIsDeleted();
	public void setIsDeleted(String isDeleted);	
	
//	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
//	public IComtable list() throws Exception;
}
