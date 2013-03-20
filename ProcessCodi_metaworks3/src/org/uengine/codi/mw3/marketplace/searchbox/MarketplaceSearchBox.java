package org.uengine.codi.mw3.marketplace.searchbox;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.IApp;
import org.uengine.codi.mw3.marketplace.MarketplaceCenterPanel;
import org.uengine.codi.mw3.marketplace.MarketplaceCenterWindow;
import org.uengine.codi.mw3.marketplace.MarketplaceEastPanel;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.MarketCategoryPanel;
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
		
		App findListing = new App();
		findListing.setAppName(getKeyword());
		
		IApp getListing = findListing.searchApps();
		getListing.getMetaworksContext().setWhen("searchForSearchBox");
		
		//west - category combo box, list panel
		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(Category.loadRootCategory());
		
		//center - search result
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.session = session;
		centerPanel.setListing(getListing);
		centerPanel.getListing().getMetaworksContext().setWhen("searchForSearchBox");
		
		MarketplaceCenterWindow centerwin = new MarketplaceCenterWindow(session);
		centerwin.setCenterPanel(centerPanel);
		
		//east - new apps
		MarketplaceEastPanel east = new MarketplaceEastPanel();
		east.session = session;
		east.load();

		
		Layout mainPanel = new Layout();
		
		mainPanel.setId("main");
		mainPanel.setName("center");
		mainPanel.setCenter(centerwin);
		mainPanel.setWest(marketCategory);
		mainPanel.setEast(east);
		
		return new Object[] {mainPanel};
	}	

}
