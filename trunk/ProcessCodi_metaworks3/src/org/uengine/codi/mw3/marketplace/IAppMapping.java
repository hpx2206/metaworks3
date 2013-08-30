package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;

@Table(name="APPMAPPING")
@Face(ejsPathMappingByContext={"{where: 'mapList', face: 'dwr/metaworks/org/uengine/codi/mw3/marketplace/IAppMap.ejs'}"})
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
	
	@NonSavable
	public String getProjectName();
	public void setProjectName(String projectName);
	
	@NonSavable
	@NonLoadable	
	public MetaworksFile getLogoFile();
	public void setLogoFile(MetaworksFile logoFile);
	
	@NonLoadable
	@NonSavable
	public boolean isSelected();
	public void setSelected(boolean selected);
	
	@ServiceMethod(callByContent=true)
	public IAppMapping findMe() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public IAppMapping findMyApps() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void findProject(String appId) throws Exception;

}
