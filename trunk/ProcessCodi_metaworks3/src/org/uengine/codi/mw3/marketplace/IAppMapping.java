package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.model.ICompany;

@Table(name="APPMAPPING")
public interface IAppMapping extends IDAO{
	
	@Id
	public int getAppId();
	public void setAppId(int appId);

	@Id
	@ORMapping(
			databaseFields = {"comcode"},
			objectFields = {"comeCode"}
		)
	public ICompany getComCode();
	public void setComCode(ICompany comCode);

	@Name
	public String getAppName();
	public void setAppName(String appName);

	public boolean getIsDeleted();
	public void setIsDeleted(boolean isDeleted);


}
