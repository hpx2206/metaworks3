package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.knowledge.ProjectPanel;
import org.uengine.codi.mw3.model.Session;

public class DashboardPanel {
	@AutowiredFromClient
	public Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

	MetaworksContext metaworksContext;		
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
/*
 * News
 * MyApp
 * MyProject
*/		
	MyAppPanel myAppPanel;
		public MyAppPanel getMyAppPanel() {
			return myAppPanel;
		}
		public void setMyAppPanel(MyAppPanel myAppPanel) {
			this.myAppPanel = myAppPanel;
		}

	MyServicePanel myServicePanel;
		public MyServicePanel getMyServicePanel() {
			return myServicePanel;
		}
		public void setMyServicePanel(MyServicePanel myServicePanel) {
			this.myServicePanel = myServicePanel;
		}

	ProjectPanel projectPanel;
		public ProjectPanel getProjectPanel() {
			return projectPanel;
		}
		public void setProjectPanel(ProjectPanel projectPanel) {
			this.projectPanel = projectPanel;
		}
		

	OcePageNavigator pageNavigator;
		public OcePageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(OcePageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}

	public	DashboardPanel(){
		
	}
	
	public DashboardPanel load(Session session) throws Exception{		
		setMetaworksContext(new MetaworksContext());
		
		myAppPanel = new MyAppPanel();
		myAppPanel.session = session;
		myAppPanel.load();
		
		myServicePanel = new MyServicePanel(session);
		projectPanel = new ProjectPanel();
		//projectPanel.load();
		//projectPanel.loadList();
		return this;
	}

	
}
