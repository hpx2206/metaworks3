package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;


public class IDE {
	public IDE() throws Exception {
	}
	
	public IDE(Session session) throws Exception {
		Layout innerLayout = new Layout();
		
		if(session.getDefId() != null){
			ClassDefinition classDefinition = new ClassDefinition();
			classDefinition.setAlias(session.getDefId());
			classDefinition.afterDeserialization();
			classDefinition.getMetaworksContext().setWhen("view");
			
			innerLayout.setCenter(new ContentWindow(classDefinition));
		}else{
			innerLayout.setCenter(new ContentWindow());	
		}
		
		innerLayout.setSouth(new ConsoleWindow());
		innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:'20%'");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:300, north__size:52");
		outerLayout.setNorth(new TopPanel());
		outerLayout.setWest(new ResourceWindow(session.getUser()));
		outerLayout.setEast(new HintWindow(session.getUser(), "ide"));
		outerLayout.setCenter(innerLayout);		
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator());
		
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
