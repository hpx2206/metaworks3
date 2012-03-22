package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;

public class Knowledge {

	public Knowledge() throws Exception {
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size:300, north__size:52");
		outerLayout.setNorth(new KnowledgeTopPanel());
		outerLayout.setCenter(new WorkflowyWindow());		
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator());		
	}
	
	public Knowledge(IUser user) throws Exception {
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
