package org.uengine.codi.mw3.collaboration;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;

public class Collaboration {
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

	@AutowiredToClient
	public Session session;
		
	
	public Collaboration() throws Exception{
		
	}
	
	
	public void load(Session session) throws Exception{
			//TODO
		
		
		Workspace workspace = new Workspace();
		workspace.load();
		CollaborationPerspectiveWindow collaborationPerspectiveWindow = new CollaborationPerspectiveWindow(session);
		collaborationPerspectiveWindow.setTitle("collaboration");
		
		
		CollaborationContentWindow collaborationContentWindow = new CollaborationContentWindow(session);
		ProcessTopPanel processTopPanel = new ProcessTopPanel(session);
		processTopPanel.setPageType("collaboration");
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(collaborationContentWindow);
		centerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__size:250");
		
		Layout outerLayout = new Layout();

		
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:250, north__size:52");
		outerLayout.setWest(collaborationPerspectiveWindow);
		outerLayout.setName("center");
		outerLayout.setCenter(centerLayout);
		outerLayout.setNorth(processTopPanel);
		
		this.setLayout(outerLayout);
		this.pageNavigator = new PageNavigator();
	}
}
