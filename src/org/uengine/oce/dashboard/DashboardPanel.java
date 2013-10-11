package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.admin.OcePageNavigator;
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
	
	OcePageNavigator pageNavigator;
		public OcePageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(OcePageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}

	MyAppPanel myAppPanel;
		public MyAppPanel getMyAppPanel() {
			return myAppPanel;
		}
		public void setMyAppPanel(MyAppPanel myAppPanel) {
			this.myAppPanel = myAppPanel;
		}

	MyProjectPanel projectPanel;
		public MyProjectPanel getProjectPanel() {
			return projectPanel;
		}
		public void setProjectPanel(MyProjectPanel projectPanel) {
			this.projectPanel = projectPanel;
		}

	MyServicePanel myServicePanel;
		public MyServicePanel getMyServicePanel() {
			return myServicePanel;
		}
		public void setMyServicePanel(MyServicePanel myServicePanel) {
			this.myServicePanel = myServicePanel;
		}
		
	
	
	public	DashboardPanel(){
		
	}
	
	public DashboardPanel load(Session session) throws Exception{		
		setMetaworksContext(new MetaworksContext());

		//MyApp
		myAppPanel = new MyAppPanel();
		myAppPanel.session = session;
		myAppPanel.load();
		

		//MyProject
		projectPanel = new MyProjectPanel();
		projectPanel.session = session;
		projectPanel.load();

		//MyService
		setMyServicePanel(new MyServicePanel(session));
		

		return this;
	}

	
}
