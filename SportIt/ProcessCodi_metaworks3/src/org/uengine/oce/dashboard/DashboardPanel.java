package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.PersonalPerspective;
import org.uengine.codi.mw3.model.Session;

public class DashboardPanel {
		
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
	
	ScheduleCalendar calendar;
		public ScheduleCalendar getCalendar() {
			return calendar;
		}
		public void setCalendar(ScheduleCalendar calendar) {
			this.calendar = calendar;
		}

		
	public	DashboardPanel(){
		
	}
	
	public DashboardPanel load(Session session) throws Exception{		
		setMetaworksContext(new MetaworksContext());
		//InstanceListPanel
		instanceListPanel = createInstanceListPanel(session);
		
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
		
		calendar= new ScheduleCalendar();
		calendar.session = session;
		calendar.getMetaworksContext().setHow("small");
		calendar.getMetaworksContext().setWhere(OceMain.WHERE_DASHBOARD);
		calendar.load();

		return this;
	}

	public InstanceListPanel createInstanceListPanel(Session session) throws Exception{
	
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
//		instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		
		InstanceListPanel instanceListPanel = new InstanceListPanel();
		instanceListPanel.session = session;
		
		//instanceListPanel = (InstanceListPanel) personalPerspective.loadDashboard()[1];

		return instanceListPanel;
		
	}
}