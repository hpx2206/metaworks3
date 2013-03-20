package org.uengine.codi.mw3.marketplace.searchbox;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.marketplace.IListing;
import org.uengine.codi.mw3.marketplace.Listing;
import org.uengine.codi.mw3.marketplace.MarketplaceCenterPanel;
import org.uengine.codi.mw3.marketplace.MarketplaceEastPanel;
import org.uengine.codi.mw3.marketplace.MarketplaceWestPanel;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;

public class MarketplaceSearchBox {
	
	@AutowiredFromClient
	public Session session;
	
	boolean keyUpSearch;
		public boolean isKeyUpSearch() {
			return keyUpSearch;
		}
		public void setKeyUpSearch(boolean keyUpSearch) {
			this.keyUpSearch = keyUpSearch;
		}
	
	boolean keyEntetSearch;
		public boolean isKeyEntetSearch() {
			return keyEntetSearch;
		}
		public void setKeyEntetSearch(boolean keyEntetSearch) {
			this.keyEntetSearch = keyEntetSearch;
		}
	
	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
		
	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
		
		session.setSearchKeyword(getKeyword());
		
		Listing findListing = new Listing();
		findListing.setAppName(getKeyword());
		
		IListing getListing = findListing.searchApps();
		getListing.getMetaworksContext().setWhen("searchForSearchBox");
		
		//west - category combo box, list panel
		MarketplaceWestPanel west = new MarketplaceWestPanel();
		
		//center - search result
		MarketplaceCenterPanel center = new MarketplaceCenterPanel();
		center.session = session;
		center.setListing(getListing);
		center.getListing().getMetaworksContext().setWhen("searchForSearchBox");
		
		//east - new apps
		MarketplaceEastPanel east = new MarketplaceEastPanel();
		east.session = session;
		east.load();

		
		Layout mainPanel = new Layout();
		
		mainPanel.setId("main");
		mainPanel.setName("center");
		mainPanel.setCenter(center);
		mainPanel.setWest(west);
		mainPanel.setEast(east);
		
		return new Object[] {mainPanel};
	}	

}
