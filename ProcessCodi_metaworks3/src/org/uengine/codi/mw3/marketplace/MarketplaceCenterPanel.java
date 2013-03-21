package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.marketplace.category.ICategory;

public class MarketplaceCenterPanel {
	
	public MarketplaceCenterPanel(){
		
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

		
	@AutowiredFromClient
	public Session session;


}
