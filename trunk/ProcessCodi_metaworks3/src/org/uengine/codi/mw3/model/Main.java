package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.ILogin;
import org.uengine.codi.mw3.admin.PageNavigator;

public class Main {

	public Main(){
		
	}
	
	public Main(IUser user) throws Exception {
		
		Session session = new Session();
		session.setUser(user);
		setSession(session);		


		Layout westLayout = new Layout();
		westLayout.setNorth(new  NavigationWindow());
		westLayout.setCenter(new ContactWindow(user));
		westLayout.setLoad(false);
		westLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__spacing_open:5");

		Layout eastLayout = new Layout();
		eastLayout.setWest(new  InstanceListWindow(session));
		eastLayout.setCenter(new ContentWindow());
		eastLayout.setLoad(false);
		eastLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__spacing_open:5, west__size:'40%'");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52, center__onresize: 'mw3.getFaceHelper(\\''+this.objectId+'\\').resizeChild()'");
		outerLayout.setNorth(new ProcessTopPanel());
		outerLayout.setWest(westLayout);
		outerLayout.setCenter(eastLayout);		
		outerLayout.setLoadChild(true);
		
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
