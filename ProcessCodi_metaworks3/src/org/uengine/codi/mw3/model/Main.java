package org.uengine.codi.mw3.model;

import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;

public class Main {

	public Main(){
		
	}
	
	public Main(Session session) throws Exception {
		
		//
		
		Layout westLayout = new Layout();
		westLayout.setNorth(new  NavigationWindow());
		westLayout.setCenter(new ContactWindow(session.getUser()));
		westLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__spacing_open:5, north__size:'50%'");
		westLayout.setName("west");
		
		Layout eastLayout = new Layout();
		eastLayout.setWest(new  InstanceListWindow(session));
		
		ContentWindow contentWindow = new ContentWindow();
		NewInstancePanel instancePanel = new NewInstancePanel();
		instancePanel.session = session;
		instancePanel.load();
		contentWindow.setPanel(instancePanel);
		
		eastLayout.setCenter(contentWindow);
		eastLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__spacing_open:5, west__size:'40%'");
		eastLayout.setName("east");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52");
		outerLayout.setNorth(new ProcessTopPanel(session));
		outerLayout.setWest(westLayout);
		outerLayout.setCenter(eastLayout);		
		outerLayout.setName("center");
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator("process"));	
		

		// this.logo = new Logo();
	}

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
		
	Logo logo;
		public Logo getLogo() {
			return logo;
		}
		public void setLogo(Logo logo) {
			this.logo = logo;
		}
		
}

