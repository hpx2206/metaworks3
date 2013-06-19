package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="APPMAPPING")
public interface IAppMapping extends IDAO{
	
	@Id
	public int getAppId();
	public void setAppId(int appId);

	@Id
	public String getComCode();
	public void setComCode(String comCode);

	@Name
	public String getAppName();
	public void setAppName(String appName);

	public boolean getIsDeleted();
	public void setIsDeleted(boolean isDeleted);
	
	@NonLoadable
	@NonSavable
	public boolean isSelected();
	public void setSelected(boolean selected);
	
	@ServiceMethod(callByContent=true)
	public IAppMapping findMe() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public IAppMapping findMyApps() throws Exception;
	
}
