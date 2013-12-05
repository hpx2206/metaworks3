package org.uengine.codi.mw3.marketplace;


import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.Session;

public class AppMap {
	
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
		AppMapWindow center = new AppMapWindow(session);
		
		
		Layout mainLayout = new Layout();
		mainLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__size:52");
		mainLayout.setNorth(top);
		mainLayout.setCenter(center);
		
		this.setLayout(mainLayout);
		
		pageNavigator = new PageNavigator();
		
	}

}
