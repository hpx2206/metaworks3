package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Session;

@Face(displayName="$AppMapList", ejsPath="dwr/metaworks/genericfaces/Window.ejs", options={"hideLabels"}, values={"true"})
public class AppMapWindow {

	AppMapPanel appMapPanel;
		public AppMapPanel getAppMapPanel() {
			return appMapPanel;
		}
		public void setAppMapPanel(AppMapPanel appMapPanel) {
			this.appMapPanel = appMapPanel;
		}
	public AppMapWindow(Session session) throws Exception{
		
		AppMapPanel panel = new AppMapPanel();
		panel.load(session);
		
		this.setAppMapPanel(panel);
	}
	
}
