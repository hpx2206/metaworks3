package org.uengine.oce.dashboard;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="Content",
options={"hideLabels", "maximize"}, 
values={"true", "true"})
public class DashboardWindow extends Window{
	public DashboardWindow(){}
	
	public DashboardWindow(Object panel){
		this.setPanel(panel);
	}
	
	public DashboardWindow(Session session) throws Exception {
		
		this.session = session;
				
		DashboardPanel dashboardPanel = new DashboardPanel();
		dashboardPanel.load(session);
	}
	
	public DashboardWindow(Object panel, String title){
		super(panel, title);
	}	
		
	@AutowiredFromClient
	public Session session;
}
