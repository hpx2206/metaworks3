package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.ICategory;

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

	@AutowiredFromClient
	public Session session;

	
//	public void load() throws Exception {
//		
////		Listing findlisting = new Listing();
////		findlisting.setVendorId(session.getCompany().getComCode());
////		findlisting.session = session;
////		
////		listing = findlisting.findByVendor();
//////		listing.getMetaworksContext().setWhen("marketplaceHome");
//
//		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
//		searchBox.setKeyUpSearch(true);
//		searchBox.setKeyEntetSearch(true);
//
//		
////		setListing(listing);
//		setSearchBox(searchBox);
//
//	}

}
