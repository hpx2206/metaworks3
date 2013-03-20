package org.uengine.codi.mw3.marketplace;

import java.util.Date;


import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.Category;
import org.uengine.processmarket.MarketCategoryPanel;

import org.uengine.processmarket.ICategory;

public class App extends Database<IApp> implements IApp{
	
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
	
	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
	

	@AutowiredFromClient
	public Session session;
		
	
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
		
		findListing.getMetaworksContext().setWhen("newApps");
		
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
	
	public void readyPublished() throws Exception {
		
		App selectedApp = new App();
		
		selectedApp.setAppId(getAppId());
		selectedApp.databaseMe().setStatus(AppInformation.STATUS_PUBLISHED);
		
		flushDatabaseMe();
		
	}
	
	public Object detailListing() throws Exception {
		
		this.copyFrom(this.findMe());
		this.getMetaworksContext().setWhen("detailList");
		
		Category category = new Category();
		category.setCategoryId(this.getCategory().getCategoryId());
		
		this.setCategory(category.databaseMe());
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(this);
		
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
		
		editListing.setListingId(getAppId());
		editListing.setCategories(categories);
		editListing.setListingName(getAppName());
		editListing.setSimpleOverview(getSimpleOverview());
		editListing.setFullOverview(getFullOverview());
		editListing.setPricing(getPricing());
		editListing.setFile(getExtfile());
		editListing.setLogoFile(getLogoFile());
		
		getMetaworksContext().setWhen("edit");
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(editListing);
		
		return mainLayout;
	}

	
	public void showDescription() throws Exception {
		this.getMetaworksContext().setWhere("showDescription");
		
	}
	
	public void showVendor() throws Exception {
		this.getMetaworksContext().setWhere("showVendor");
		
	}
	
	public void addAppOurGroup()throws Exception {
		
		AppMapping addApp = new AppMapping();
		
		addApp.setAppId(getAppId());
		addApp.setAppName(getAppName());
		addApp.setComCode(session.getCompany());
		addApp.setIsDeleted(false);
		
		addApp.createDatabaseMe();
		
	}
	
}
