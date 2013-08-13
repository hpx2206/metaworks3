package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewWindow;

public class ProcessExplorer {
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

	ProcessDefinitionNode node;
		@Hidden
		@AutowiredToClient
		public ProcessDefinitionNode getNode() {
			return node;
		}
		public void setNode(ProcessDefinitionNode node) {
			this.node = node;
		}

	@AutowiredToClient
	public Session session;
		
	
	public ProcessExplorer() throws Exception{
		
	}
	
	
	public void load(Session session) throws Exception{
			//TODO
		
		
		Workspace workspace = new Workspace();
		workspace.load();
		ProcessExplorerPerspectiveWindow processExplorerPerspectiveWindow = new ProcessExplorerPerspectiveWindow(session);
		processExplorerPerspectiveWindow.setTitle("ProcessExplorer");
		
		
		ProcessExplorerContentWindow processExplorerContentWindow = new ProcessExplorerContentWindow();
		
		ProcessViewWindow processViewWindow = new ProcessViewWindow();
		processViewWindow.load();
		
		processExplorerContentWindow.setPanel(processViewWindow);
		
		ProcessTopPanel processTopPanel = new ProcessTopPanel(session);
		processTopPanel.setPageType("ProcessExplorer");
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(processExplorerContentWindow);
		centerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__size:250");
		
		Layout outerLayout = new Layout();

		
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:250, north__size:52");
		outerLayout.setWest(processExplorerPerspectiveWindow);
		outerLayout.setName("center");
		outerLayout.setCenter(centerLayout);
		outerLayout.setNorth(processTopPanel);
		
		this.setLayout(outerLayout);
		this.pageNavigator = new PageNavigator();
	}
	
}
