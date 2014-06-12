package org.uengine.codi.mw3.marketplace;


import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.Session;

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
		MarketplaceWindow marketplaceWindow = new MarketplaceWindow();
		
		
		/*
		MarketplaceCenterPanel appListPanel = new MarketplaceCenterPanel();
		
		
		App findlisting = new App();
		findlisting.setComcode(session.getCompany().getComCode());
		findlisting.session = session;
		
		IApp listing = findlisting.findHome();
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
		*/
		
		session.getEmployee().setPreferUX("sns");
		session.setLastPerspecteType("topic");
		session.setLastSelectedItem("Marketplace");
		
		InstanceList instList = new InstanceList(session);
		instList.load();
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.setInstanceList(instList);
		
		ContentWindow topicStreamWindow = new ContentWindow();
		topicStreamWindow.setPanel(instanceListPanel);
		
		Layout marketPlaceLayout =  new Layout();
		marketPlaceLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, east__size:250");
		marketPlaceLayout.setCenter(marketplaceWindow);
		marketPlaceLayout.setEast(topicStreamWindow);
		
		Layout mainLayout = new Layout();
		mainLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__size:52");
		mainLayout.setNorth(top);
		mainLayout.setCenter(marketPlaceLayout);
		
		this.setLayout(mainLayout);
		
		pageNavigator = new PageNavigator();
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object showMyVendor() throws Exception {
		/*
		MyVendor myVendor = new MyVendor();
		myVendor.session = session;
		
		
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.setCategory(Category.loadRootCategory());
		centerPanel.setListing(myVendor.load());
		centerPanel.setVendor(myVendor);
		
		MarketplaceCenterWindow centerWin = new MarketplaceCenterWindow(session);
		centerWin.setCenterPanel(centerPanel);
		centerWin.getCenterPanel().getListing().getMetaworksContext().setWhen("myVendor");
		*/
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		//mainLayout.setCenter(centerWin);
		
		return mainLayout;
		
		
	}

}
