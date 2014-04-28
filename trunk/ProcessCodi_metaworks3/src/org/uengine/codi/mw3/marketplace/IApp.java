package org.uengine.codi.mw3.marketplace;

import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.model.ICompany;


@Table(name="APP")
@Face(
	  ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	  ejsPathForArray="dwr/metaworks/genericfaces/ListFace.ejs",
      ejsPathMappingByContext={
		"{how: 'inList', face: 'dwr/metaworks/org/uengine/codi/mw3/marketplace/IApp.ejs'}",
		"{how: 'detail', face: 'dwr/metaworks/org/uengine/codi/mw3/marketplace/IApp.ejs'}",
		"{how: 'myVendor', face: 'dwr/metaworks/org/uengine/codi/mw3/marketplace/IApp.ejs'}",
		"{where: 'mapList', face: 'dwr/metaworks/org/uengine/codi/mw3/marketplace/IAppMap.ejs'}"
	  },
      options={"fieldOrder"},
      values={"categories,appTypePanel,appName,url,subDomain,simpleOverview,fullOverview,logoFile"})
public interface IApp extends IDAO{
	
	public static final String APP_TYPE_PROJECT = "project";
	public static final String APP_TYPE_PROCESS = "processApp";
	public static final String APP_TYPE_SERVICE = "service";
	
	@Id
	public int getAppId();
	public void setAppId(int appId);

	@Name
	@Face(displayName="$App.Name", options={"size"}, values={"131"})
	public String getAppName();
	public void setAppName(String appName);
	
	@Face(displayName = "$App.Overview.Simple", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values = {"5", "130"})
	public String getSimpleOverview();
	public void setSimpleOverview(String simpleOverview);

	@Face(displayName = "$App.Overview.Full", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values = {"7", "130"})
	public String getFullOverview();
	public void setFullOverview(String fullOverview);

	@Face(displayName = "$App.Pricing")
	public String getPricing();
	public void setPricing(String pricing);

	public Date getCreateDate();
	public void setCreateDate(Date createDate);

	public Date getUpdateDate();
	public void setUpdateDate(Date updateDate);

	public String getVersion();
	public void setVersion(String version);

	public String getStatus();
	public void setStatus(String status);

	public String getComcode();
	public void setComcode(String comcode);
	
	public String getComName();
	public void setComName(String comName);
	
	@NonSavable
	@ORMapping(objectFields={"comCode", "comName", "description", "repMail"}, databaseFields={"comCode", "comName", "description", "repMail"})
	public ICompany getCompany();
	public void setCompany(ICompany company);

	public boolean isDeleted();
	public void setIsDeleted(boolean isDeleted);
	
	public int getInstallCnt();
	public void setInstallCnt(int installCnt);
	
	public String getUrl();
	public void setUrl(String url);
	
	public String getAppType();
	public void setAppType(String appType);
	
	@Face(displayName="$App.LogoFileAttach")
	@ORMapping(
		databaseFields={"logoContent", "logoFileName"}, 
		objectFields={"uploadedPath", "filename"}
	)
	public MetaworksFile getLogoFile();
	public void setLogoFile(MetaworksFile logoFile);
	
	@ORMapping(
			databaseFields={"categoryId"}, 
			objectFields={"categoryId"}
	)
	public ICategory getCategory();
	public void setCategory(ICategory category);
	
	@Face(displayName = "$App.Category")
	@NonLoadable
	@NonSavable
	public SelectBox getCategories();
	public void setCategories(SelectBox categories);

	@Face(displayName="$App.AppType")
	@NonLoadable
	@NonSavable
	public AppTypePanel getAppTypePanel();
	public void setAppTypePanel(AppTypePanel appTypePanel);
	
	public String getProjectId();
	public void setProjectId(String projectId);
	
//	@Face(displayName = "$ReleaseVersion")
//	@NonLoadable
//	@NonSavable
//	public SelectBox getReleaseVersion();
//	public void setReleaseVersion(SelectBox releaseVersion);
	
	@NonLoadable
	@NonSavable
	public IAppMapping getAppMapping();
	public void setAppMapping(IAppMapping appMapping);
	
	
	@NonLoadable
	@NonSavable
	public boolean isCompanyUsed() ;
	public void setCompanyUsed(boolean companyUsed);
	
	
	public int getRunningVersion();
	public void setRunningVersion(int runningVersion);	
	
	
	@Face(displayName = "$SubDomainName", options={"size"}, values={"131"})
	public String getSubDomain();
	public void setSubDomain(String subDomain);
	
	
	@NonLoadable
	@NonSavable
	public boolean isUseIaas();
	public void setUseIaas(boolean useIaas);
	
	
	public IApp findMe() throws Exception;
	
	public IApp findByVendor() throws Exception;

	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object[] readyPublished() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object[] readyApproved() throws Exception;

	@Hidden
	@ServiceMethod(callByContent=true)
	public Object detail() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true)
	public Object edit() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true)
	public Object serverManage() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true)
	public Object[] addApp()throws Exception;
	
	@Face(displayName="$Next")
	@Hidden
//	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true)
	public Object step1() throws Exception;

	@Face(displayName="$MyApps.add")
	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
//	@Hidden
	@ServiceMethod(callByContent=true)
	public Object save() throws Exception;
	
	@Face(displayName="$Cancel")
	@Hidden
//	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
	@ServiceMethod
	public Object cancel() throws Exception;
	
}
