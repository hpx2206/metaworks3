package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Children;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IProcessDefinition extends IDAO{

	@ServiceMethod(callByContent=true)
	public void drillDown() throws Exception;
	
	@ServiceMethod
	public Object[] initiate() throws Exception;
	
	public IProcessDefinition findAll() throws Exception;

	@Id
	public Long getDefId();
	public void setDefId(Long defId);
	
	public String getDescription();
	public void setDescription(String description);

	public boolean getIsFolder();
	public void setIsFolder(boolean folder);

	@Hidden
	public boolean getIsAdhoc();
	public void setIsAdhoc(boolean adhoc);

	@Hidden
	public Long getParentFolder();
	public void setParentFolder(Long parentFolder);
	
	@Hidden
	public int getProdVer();
	public void setProdVer(int prodVer);
	
	@Hidden
	public String getObjType();
	public void setObjType(String objType);
	
	@Hidden
	public Long getProdVerId();
	public void setProdVerId(Long prodVerId) ;

	public String getName() ;
	public void setName(String name);

	@Hidden
	public boolean getIsDeleted();
	public void setIsDeleted(boolean deleted);

	@Hidden
	public boolean getIsVisible();
	public void setIsVisible(boolean visible);

	@Hidden
	public String getAlias();
	public void setAlias(String alias);

	@Hidden
	public String getSuperDefId();
	public void setSuperDefId(String superDefId);

	@Hidden
	public String getComCode();
	public void setComCode(String comCode);
	
	@NonLoadable
	@NonSavable
	public IProcessDefinition getChilds();
	public void setChilds(IProcessDefinition child);

	
}
