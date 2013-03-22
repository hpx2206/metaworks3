package org.uengine.codi.mw3.marketplace;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;

public class MarketplaceCenterPanel implements ContextAware{
	
	public MarketplaceCenterPanel(){
		
		this.setMetaworksContext(new MetaworksContext());
		
	}
	
	IApp listing;
		public IApp getListing() {
			return listing;
		}
		public void setListing(IApp listing) {
			this.listing = listing;
		}
		
	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
		
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

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	

	@AutowiredFromClient
	public Session session;

	
	@ServiceMethod(callByContent=true)
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
	}
	

}
