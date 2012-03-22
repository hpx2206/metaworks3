package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;

public class IDE {

	public IDE() throws Exception {
		Layout innerLayout = new Layout();
		innerLayout.setCenter(new ContentWindow());
		innerLayout.setSouth(new ConsoleWindow());
		innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:'20%'");
		innerLayout.setLoad(false);
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:300, north__size:52, center__onresize: 'mw3.getFaceHelper(\\''+this.objectId+'\\').resizeChild()'");
		outerLayout.setNorth(new TopPanel());
		outerLayout.setWest(new ResourceWindow());
		outerLayout.setEast(new HintWindow("ide"));
		outerLayout.setCenter(innerLayout);		
		outerLayout.setLoadChild(true);
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator());
	}
	
	public IDE(IUser user) throws Exception {
		this();
		
		Session session = new Session();
		session.setUser(user);
		setSession(session);			
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
}
