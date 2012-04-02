package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.ILogin;
import org.uengine.codi.mw3.admin.PageNavigator;

public class Main {

	public Main(){
		
	}
	
	public Main(Session session) throws Exception {
		
		setSession(session);		

		//
		
		Layout westLayout = new Layout();
		westLayout.setNorth(new  NavigationWindow());
		westLayout.setCenter(new ContactWindow(session.getUser()));
		westLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__spacing_open:5, north__size:'50%'");
		westLayout.setName("west");
		
		Layout eastLayout = new Layout();
		eastLayout.setWest(new  InstanceListWindow(session));
		eastLayout.setCenter(new ContentWindow());
		eastLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__spacing_open:5, west__size:'40%'");
		eastLayout.setName("east");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52");
		outerLayout.setNorth(new ProcessTopPanel());
		outerLayout.setWest(westLayout);
		outerLayout.setCenter(eastLayout);		
		outerLayout.setName("center");
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator());		

		// this.logo = new Logo();
	}

	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}

	Session session;
		@AutowiredToClient
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
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

