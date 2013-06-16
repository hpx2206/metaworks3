package org.uengine.codi.mw3.marketplace;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;

public class MarketplaceCenterPanel implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	@AutowiredFromClient
	public Session session;
	
	public MarketplaceCenterPanel(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
	AppList appList;
		public AppList getAppList() {
			return appList;
		}
		public void setAppList(AppList appList) {
			this.appList = appList;
		}
	
	AppDetail appDetail;
		public AppDetail getAppDetail() {
			return appDetail;
		}
		public void setAppDetail(AppDetail appDetail) {
			this.appDetail = appDetail;
		}
	
	MyVendor myVendor;
		public MyVendor getMyVendor() {
			return myVendor;
		}
		public void setMyVendor(MyVendor myVendor) {
			this.myVendor = myVendor;
		}
	

	/*
	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
	*/
		
	/*
	MarketplaceSearchBox searchBox;	
		@Face(options={"keyupSearch"}, values={"true"})
		public MarketplaceSearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(MarketplaceSearchBox searchBox) {
			this.searchBox = searchBox;
		}
	
	MyVendor vendor;
		public MyVendor getVendor() {
			return vendor;
		}
		public void setVendor(MyVendor vendor) {
			this.vendor = vendor;
		}
		
	AppInformation appInfo;
		public AppInformation getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(AppInformation appInfo) {
			this.appInfo = appInfo;
		}
	*/
		
	
	/*@ServiceMethod(callByContent=true)
	public Object gomarketHome() throws Exception {
		
		PageNavigator gomarketHome = new PageNavigator();
		gomarketHome.session = session;
		
		return new Refresh(gomarketHome.goMarketplace(), true);

	}
	
	@ServiceMethod(callByContent=true)
	public Object selectCategory() throws Exception {
		
		Category selectCategory = new Category();
		
		selectCategory.setCategoryId(this.getCategory().getCategoryId());
		selectCategory.setCategoryName(this.getCategory().getCategoryName());
		selectCategory.session = session;
		
		return  selectCategory.selectCategory();
	}*/
	

}
