package org.uengine.codi.mw3.marketplace;


import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
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
//		MarketplaceCenterPanel center = new MarketplaceCenterPanel();
//		center.session = session;
//		center.load();
		
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
