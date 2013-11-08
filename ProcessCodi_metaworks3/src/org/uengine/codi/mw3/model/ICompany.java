package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name = "COMTABLE")
@Face(displayName="$Company")
public interface ICompany extends IDAO {
	@Id
	@Hidden
	public String getComCode();
	public void setComCode(String comCode);

	@Face(displayName="$SALESEARLYSALES_BASECOMPANY_NAME")
	public String getAlias();
	public void setAlias(String alias);
	
	@Name
	@Hidden
	public String getComName();
	public void setComName(String comName);

	@Hidden
	public String getDescription();
	public void setDescription(String description);

	@Hidden
	public String getIsDeleted();	
	public void setIsDeleted(String deleted);

	
	public String getRepMail();
	public void setRepMail(String repMail);

	public String getRepMlHst();
	public void setRepMlHst(String repMlHst);
	
	public String getRepMlPwd();
	public void setRepMlPwd(String repMlPwd);
	
	//@ServiceMethod
	public ICompany load() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Remover save() throws Exception;
	
	public ICompany findByAlias() throws Exception;
}
