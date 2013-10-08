package org.uengine.oce.dashboard;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.Session;


public class DashboardWindow extends Window{
	public DashboardWindow(){}
	
	public DashboardWindow(Object panel){
		this.setPanel(panel);
	}
	
	public DashboardWindow(Session session) throws Exception {
		this.session = session;
				
		DashboardPanel dashboardPanel = new DashboardPanel();
/*		dashboardPanel.session = session;
		dashboardPanel.load(session);*/
	}
		
	@AutowiredFromClient
	public Session session;
}
