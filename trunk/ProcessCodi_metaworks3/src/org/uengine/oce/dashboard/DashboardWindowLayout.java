package org.uengine.oce.dashboard;

import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.Session;

public class DashboardWindowLayout {

	Object content;
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}

	DashboardWindow dashboardWindow;
		public DashboardWindow getDashboardWindow() {
			return dashboardWindow;
		}
		public void setDashboardWindow(DashboardWindow dashboardWindow) {
			this.dashboardWindow = dashboardWindow;
		}
		
	Window appWindow;
		public Window getLeftWindow() {
			return appWindow;
		}
		public void setLeftWindow(Window appWindow) {
			this.appWindow = appWindow;
		}
	public DashboardWindowLayout(){
	}
	
	public void load(Session session) throws Exception{
		Layout innerLayout = new Layout();
		if( appWindow != null ){
			innerLayout.setWest(appWindow);
			innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:1, west__size:'65%'");
			dashboardWindow = OceMain.createInstanceListOnDashboardWindow(session);
//			dashboardWindow = (DashboardWindow) OcePerspectivePanel.loadAllICanSee()[1];
		}else{
			dashboardWindow = OceMain.createDashboardWindow(session);
		}
//		dashboardWindow = OceMain.createInstanceListOnDashboardWindow(session);

		innerLayout.setCenter(dashboardWindow);
		setContent(innerLayout);
	}

}
