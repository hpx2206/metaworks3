package org.uengine.codi.mw3.marketplace;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.marketplace.category.ICategory;


@Table(name="APP")
public interface IApp extends IDAO{
	
	@Id
	public int getAppId();
	public void setAppId(int appId);

	@Name
	public String getAppName();
	public void setAppName(String appName);
	
	public String getSimpleOverview();
	public void setSimpleOverview(String simpleOverview);

	public String getFullOverview();
	public void setFullOverview(String fullOverview);

	public String getPricing();
	public void setPricing(String pricing);

	public Date getCreateDate();
	public void setCreateDate(Date createDate);

	public Date getUpdateDate();
	public void setUpdateDate(Date updateDate);

	public String getVersion();
	public void setVersion(String version);

	@ORMapping(
			databaseFields = {"fileContent", "extfileName"}, 
			objectFields = {"uploadedPath", "filename"}
	)
	public MetaworksFile getExtfile();
	public void setExtfile(MetaworksFile extfile);

	public String getStatus();
	public void setStatus(String status);

	public String getVendorId();
	public void setVendorId(String vendorId);


	public boolean isDeleted();
	public void setIsDeleted(boolean isDeleted);
		
	@ORMapping(
		databaseFields = {"logoContent", "logoFileName"}, 
		objectFields = {"uploadedPath", "filename"}
	)
	public MetaworksFile getLogoFile();
	public void setLogoFile(MetaworksFile logoFile);
	
	@ORMapping(
			databaseFields = {"categoryId"}, 
			objectFields = {"categoryId"}
	)
	public ICategory getCategory();
	public void setCategory(ICategory category);
	
	@NonLoadable
	@NonSavable
	public IAppMapping getAppMapping();
	public void setAppMapping(IAppMapping appMapping);
	
	public IApp findMe() throws Exception;
	
	public IApp findByVendor() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void readyPublished() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object detailListing() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object editListing() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void addApp()throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object gomarketHome() throws Exception;
	
}
