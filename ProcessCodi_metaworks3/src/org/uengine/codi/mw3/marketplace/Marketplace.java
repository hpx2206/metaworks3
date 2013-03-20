package org.uengine.codi.mw3.marketplace;


import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.Category;
import org.uengine.processmarket.MarketCategoryPanel;

public class Marketplace {
	
	@AutowiredFromClient
	public Session session;
	
	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
		
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}		

		
	@ServiceMethod
	public void load() throws Exception {
		
		//top
		MarketplaceTopPanel top = new MarketplaceTopPanel(session);

		//center
		MarketplaceCenterWindow center = new MarketplaceCenterWindow(session);
		
		MarketplaceCenterPanel appListPanel = new MarketplaceCenterPanel();
		
		App findlisting = new App();
		findlisting.setVendorId(session.getCompany().getComCode());
		findlisting.session = session;
		
		IApp listing = findlisting.findByVendor();
		listing.getMetaworksContext().setWhen("marketplaceHome");
		
		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);

		appListPanel.setListing(listing);
		appListPanel.setSearchBox(searchBox);
		
		center.setCenterPanel(appListPanel);
		
		
		//west
		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(Category.loadRootCategory());
		
		//east
		MarketplaceEastPanel east = new MarketplaceEastPanel();
		east.session = session;
		east.load();
		
		Layout mainLayout = new Layout();
		
		mainLayout.setName("center");
		mainLayout.setId("main");
		mainLayout.setWest(marketCategory);
		mainLayout.setCenter(center);
		mainLayout.setEast(east);

		
		Layout storeLayout = new Layout();
		storeLayout.setNorth(top);
		storeLayout.setCenter(mainLayout);
		
		this.setLayout(storeLayout);
		
		pageNavigator = new PageNavigator();
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object showMyVendor() throws Exception {
		
		MyVendor myVendor = new MyVendor();
		
		myVendor.session = session;
		myVendor.load();
		
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(myVendor);
		
		return mainLayout;
		
		
	}

}
