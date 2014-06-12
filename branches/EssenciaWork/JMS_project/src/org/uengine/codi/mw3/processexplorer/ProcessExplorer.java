package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

public class ProcessExplorer {
	String defId;
		@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	
	ProcessViewPanel processViewPanel;
		@Hidden
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
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
	ProcessAttributePanel processAttributePanel;
		public ProcessAttributePanel getProcessAttributePanel() {
			return processAttributePanel;
		}
		public void setProcessAttributePanel(ProcessAttributePanel processAttributePanel) {
			this.processAttributePanel = processAttributePanel;
		}
	@AutowiredToClient
	public Session session;
		
	public ProcessExplorer() throws Exception{
		
	}
	
	public void load(Session session) throws Exception{
		ProcessExplorerPerspectiveWindow processExplorerPerspectiveWindow = new ProcessExplorerPerspectiveWindow(session);
		processExplorerPerspectiveWindow.setTitle("$ProcessExplorer");
		
		ProcessExploreWindow processExplorerWindow = new ProcessExploreWindow();  //1
		
		ProcessViewPanel viewContentPanel = new ProcessViewPanel(); //2
		viewContentPanel.load();
		processAttributePanel = new ProcessAttributePanel(); //3 
		processViewPanel = new ProcessViewPanel();
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(defId);
		processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		
		
		FormViewPanel formViewPanel = new FormViewPanel(); //4
		formViewPanel.load();
		
		
//		processExplorerWindow.setPanel(processViewPanel);
		
		ProcessTopPanel processTopPanel = new ProcessTopPanel(session);
		processTopPanel.setPageType("ProcessExplorer");
		ProcessExploreContent processExplorerContent = new ProcessExploreContent();
		processExplorerContent.load();
		ProcessExploreWindow explorerWindow = new ProcessExploreWindow();
		explorerWindow.setTitle("운영 절차");
		explorerWindow.setPanel(processExplorerContent);
		
		//centerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0,south__spacing_open:2, east__spacing_open:2, east__size:550, north__size:250");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:2, east__spacing_open:2, west__size:250, north__size:52");
		outerLayout.setWest(processExplorerPerspectiveWindow);
		outerLayout.setName("center");
		outerLayout.setCenter(explorerWindow);
		outerLayout.setNorth(processTopPanel);
		
		
		
		
		
		this.setLayout(outerLayout);
		this.pageNavigator = new PageNavigator();
	}
	
}
