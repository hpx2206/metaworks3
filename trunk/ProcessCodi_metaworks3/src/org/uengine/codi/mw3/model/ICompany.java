package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name = "COMTABLE")
public interface ICompany extends IDAO {
	@Id
	public String getComCode();

	public void setComCode(String comCode);

	@Name
	public String getComName();

	public void setComName(String comName);

	public String getDescription();

	public void setDescription(String description);

	@Hidden
	public String getIsDeleted();
	
	public void setIsDeleted(String deleted);

	@ServiceMethod
	public ICompany load() throws Exception;
}
