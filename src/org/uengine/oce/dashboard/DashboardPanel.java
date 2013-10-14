package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.PersonalPerspective;
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
		
	InstanceListPanel instanceListPanel;
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
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

		//InstanceListPanel
		instanceListPanel = createInstanceListPanel();
		
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

	public InstanceListPanel createInstanceListPanel() throws Exception{
		InstanceListPanel instanceListPanel;
		
		if("asana".equals(session.getEmployee().getPreferUX())){
		
			instanceListPanel = new InstanceListPanel(session);
			instanceListPanel.session = session;
			instanceListPanel.switchToKnowledge();
			return instanceListPanel;
			
		}else{
			PersonalPerspective personalPerspective = new PersonalPerspective();
			personalPerspective.session = session;
			instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
	
			instanceListPanel.session = session;
			instanceListPanel.setMetaworksContext(new MetaworksContext());
			instanceListPanel.getMetaworksContext().setWhere(MetaworksContext.WHERE_EVER);
			return instanceListPanel;
		}
	}
}