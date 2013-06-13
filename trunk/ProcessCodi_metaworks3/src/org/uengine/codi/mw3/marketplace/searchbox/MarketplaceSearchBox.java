package org.uengine.codi.mw3.marketplace.searchbox;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.marketplace.AppDetail;
import org.uengine.codi.mw3.marketplace.AppList;
import org.uengine.codi.mw3.marketplace.MarketplaceCenterPanel;
import org.uengine.codi.mw3.model.SearchBox;

public class MarketplaceSearchBox extends SearchBox {	

	@AutowiredFromClient
	public AppDetail appDetail;
	
	public MarketplaceSearchBox(){
		this.setKeyEntetSearch(true);
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
				
		AppList appList = new AppList();
		try {
			appList.load(session.getLastSelectedItem(), this.getKeyword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(appDetail == null){
			return new Object[]{appList};
		}else{
			MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
			
			centerPanel.setAppList(appList);
			centerPanel.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			
			return new Object[]{centerPanel};
		}
		/*
		session.setSearchKeyword(getKeyword());
		
		App findListing = new App();
		findListing.setAppName(getKeyword());
		
		IApp getListing = findListing.searchApps();
		getListing.getMetaworksContext().setWhen("searchForSearchBox");
		
		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
		searchBox.setKeyEntetSearch(true);
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyword(getKeyword());
		
		//west - category combo box, list panel
//		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
//		marketCategory.setCategory(Category.loadRootCategory());		
		//center - search result
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.session = session;
		centerPanel.setListing(getListing);
		centerPanel.setSearchBox(searchBox);
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
		//mainPanel.setWest(marketCategory);
		mainPanel.setEast(east);
		
		return new Object[] {mainPanel};
		*/
	}	

}
