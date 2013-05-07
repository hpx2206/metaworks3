package org.uengine.codi.mw3.marketplace;

import java.util.Date;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.layout.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.marketplace.category.MarketCategoryPanel;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

public class App extends Database<IApp> implements IApp{
	
	public final static String STATUS_REQUEST = "Request";
	public final static String STATUS_APPROVED = "Approved";
	public final static String STATUS_REJECTED = "Rejected";
	public final static String STATUS_PUBLISHED = "Published";
	public final static String STATUS_UNPUBLISHED = "Unpublished";


	public App() throws Exception{
		
	}
	
	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}

	String appName;
		public String getAppName() {
			return appName;
		}
	
		public void setAppName(String appName) {
			this.appName = appName;
		}
		
	String simpleOverview;
		public String getSimpleOverview() {
			return simpleOverview;
		}
		public void setSimpleOverview(String simpleOverview) {
			this.simpleOverview = simpleOverview;
		}
	
	String fullOverview;
		public String getFullOverview() {
			return fullOverview;
		}
		public void setFullOverview(String fullOverview) {
			this.fullOverview = fullOverview;
		}
	
	String pricing;
		public String getPricing() {
			return pricing;
		}
		public void setPricing(String pricing) {
			this.pricing = pricing;
		}
	
	Date createDate;
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
	
	Date updateDate;
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
	
	String version;
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
	
	MetaworksFile extfile;
		public MetaworksFile getExtfile() {
			return extfile;
		}
		public void setExtfile(MetaworksFile extfile) {
			this.extfile = extfile;
		}
		
	MetaworksFile logoFile;
		@Override
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		@Override
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	
	String vendorId;
		public String getVendorId() {
			return vendorId;
		}
		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}
		
	boolean isDeleted;
		public boolean isDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
	
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

		
	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
	
	IWfNode project;
		public IWfNode getProject() {
			return project;
		}
		public void setProject(IWfNode project) {
			this.project = project;
		}

	IAppMapping appMapping;
		public IAppMapping getAppMapping() {
			return appMapping;
		}
		public void setAppMapping(IAppMapping appMapping) {
			this.appMapping = appMapping;
		}

	IUser user;
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}
	
	AppInformation appInfo;
		public AppInformation getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(AppInformation appInfo) {
			this.appInfo = appInfo;
		}	
	
	int installCnt;
		public int getInstallCnt() {
			return installCnt;
		}
		public void setInstallCnt(int installCnt) {
			this.installCnt = installCnt;
		}
		

	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceView;
	
	
	public IApp findByVendor() throws Exception {
		
		IApp findListings = (IApp) Database.sql(IApp.class, "select * from app where vendorId=?vendorId and isdeleted=?isdeleted order by installCnt desc");
		
		findListings.setVendorId(this.getVendorId());
		findListings.setIsDeleted(false);
		
		findListings.select();

		
		return findListings;
		
	}
	
	public IApp findMe() throws Exception {
		return this.databaseMe();
	}
	
	public IApp findNewApps() throws Exception {

		//from 15days ago
		IApp findListing = (IApp) Database.sql(IApp.class, "select * from app where DATE_FORMAT(createdate,'%Y-%m-%d') between DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY) and CURRENT_DATE");
		
		findListing.setAppId(this.getAppId());
		findListing.setVendorId(this.getVendorId());
		findListing.select();
		
		return findListing;
		
	}
	
	public IApp searchApps() throws Exception{
		
		IApp findListing = (IApp) Database.sql(IApp.class, "select * from app where appName like ?AppName");
		
		findListing.setAppName("%" + this.getAppName() + "%");
		findListing.select();
		
		return findListing;
		
	}
	
	public IApp findForCategory() throws Exception{
		
		IApp findListing = (IApp) Database.sql(IApp.class, "select * from app where categoryId=?categoryId and vendorid=?vendorId");
		
		findListing.set("categoryId", this.getCategory().getCategoryId());
		findListing.setVendorId(this.getVendorId());
		findListing.select();
		
		return findListing;
		
	}
	
	public static IApp findPublishedApps(Session session) throws Exception {
		
		IApp findListing = (IApp) Database.sql(IApp.class, "select * from app where status=?status and isdeleted=?isDeleted and vendorid=?vendorId");
		
		findListing.setStatus("Published");
		findListing.setIsDeleted(false);
		findListing.setVendorId(session.getCompany().getComCode());
		findListing.select();
		
		return findListing;
		
	}
	
	public Object readyPublished() throws Exception {
		
		App selectedApp = new App();
		
		selectedApp.setAppId(getAppId());
		selectedApp.databaseMe().setStatus(STATUS_PUBLISHED);
		
		flushDatabaseMe();
		
		return this.gomarketHome();
		
	}
	
	public Object detailListing() throws Exception {
		
		Category category = new Category();
		category.setCategoryId(this.getCategory().getCategoryId());
		this.setCategory(category.databaseMe());
		
		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);
		
		
		MarketplaceCenterPanel centerPenal = new MarketplaceCenterPanel();
		centerPenal.setListing(this.findMe());
		centerPenal.setSearchBox(searchBox);
		centerPenal.setCategory(this.getCategory());
		centerPenal.getMetaworksContext().setWhen("detailList");
		centerPenal.getListing().getMetaworksContext().setWhen("detailList");
		
		MarketplaceCenterWindow centerWin = new MarketplaceCenterWindow(session);
		centerWin.setCenterPanel(centerPenal);
		
		//west
		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(Category.loadRootCategory());
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(centerWin);
		mainLayout.setWest(marketCategory);
		
		return mainLayout;
	}
	
	public Object editListing() throws Exception {
		
		AppInformation editListing = new AppInformation();
		editListing.session = session;
		
		SelectBox categories = new SelectBox();

		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(Category.loadRootCategory());

		if (marketCategory.getCategory().size() > 0) {
			while (marketCategory.getCategory().next()) {

				String categoryId = Integer.toString(marketCategory.getCategory().getCategoryId());
				String categoryName = marketCategory.getCategory().getCategoryName();

				categories.add(categoryName, categoryId);
				
			}
		}
		
		categories.setSelected(Integer.toString(this.getCategory().getCategoryId()));
		
		
		SelectBox projects = new SelectBox();
		
		IProjectNode projectList = ProjectNode.load(session);
		
		if(projectList.size() > 0) {
			while(projectList.next()){
				
				String projectId = projectList.getId();
				String projectName = projectList.getName();
				
				
				projects.add(projectName, projectId);
			}
		}
		
		projects.setSelected(this.getProject().getId());
		
//		getExtfile().getMetaworksContext().setWhen("edit");
		getLogoFile().getMetaworksContext().setWhen("edit");
		
		editListing.setAppId(getAppId());
		editListing.setCategories(categories);
		editListing.setAttachProject(projects);
		editListing.setAppName(getAppName());
		editListing.setSimpleOverview(getSimpleOverview());
		editListing.setFullOverview(getFullOverview());
		editListing.setPricing(getPricing());
		editListing.setLogoFile(getLogoFile());
		
		editListing.getMetaworksContext().setWhen("edit");
		
		
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.setAppInfo(editListing);
		
		MarketplaceCenterWindow centerWin = new MarketplaceCenterWindow(session);
		centerWin.setCenterPanel(centerPanel);
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(centerWin);
		
		return mainLayout;
	}

	
	public void showDescription() throws Exception {
		this.getMetaworksContext().setWhere("showDescription");
		
	}
	
	public void showVendor() throws Exception {
		this.getMetaworksContext().setWhere("showVendor");
		
	}
	
	public void addApp()throws Exception {
		
		App app = new App();
		app.setAppId(this.getAppId());
		app.setAppName(this.getAppName());
		app.setVendorId(this.getVendorId());
		app.setInstallCnt(this.getInstallCnt());
		
		
		AppInformation setAppInfo = new AppInformation();
		
		setAppInfo.getMetaworksContext().setWhen("view");
		
		setAppInfo.setAppId(this.getAppId());
		setAppInfo.setAppName(this.getAppName());
		setAppInfo.setSimpleOverview(this.getSimpleOverview());
		setAppInfo.setFullOverview(this.getFullOverview());
		setAppInfo.setPricing(this.getPricing());
		setAppInfo.setLogoFile(this.getLogoFile());
		setAppInfo.setCategory(this.getCategory());
		
		String defId = "AppAcquisition.process";
		
		ProcessMap goProcess = new ProcessMap();
		goProcess.session = session;
		goProcess.processManager = processManager;
		goProcess.instanceView = instanceView;
		goProcess.setDefId(defId);
		
		
		// 프로세스 발행
	    Long instId = Long.valueOf(goProcess.initializeProcess());
	    
	    // 프로세스 실행
	    ResultPayload rp = new ResultPayload();
	    rp.setProcessVariableChange(new KeyedParameter("requestAquisitionApp", setAppInfo));
	    
	    //무조건 compleate
	    processManager.executeProcessByWorkitem(instId.toString(), rp);
		
		
	}
	
	public Object gomarketHome() throws Exception {
		
		PageNavigator gomarketHome = new PageNavigator();
		gomarketHome.session = session;
		
		return new Refresh(gomarketHome.goMarketplace(), true);

	}
	

}
