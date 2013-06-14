package org.uengine.codi.mw3.selfservice;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Session;


/**
 * id: It's tab key.
 * tabs: Tab's List
 * 
 * Draw tab for CloudTab
 * 
 * @author JISUN
 *
 */

@Face(displayName="$SelfServicePortal", ejsPath="genericfaces/Window.ejs", options={"hideAddBtn", "hideLabels"}, values={"true", "true"})
public class SelfServiceWindow {
	
	SelfServiceControlPanel selfServiceControlPanel;
		public SelfServiceControlPanel getSelfServiceControlPanel() {
			return selfServiceControlPanel;
		}
		public void setSelfServiceControlPanel(
				SelfServiceControlPanel selfServiceControlPanel) {
			this.selfServiceControlPanel = selfServiceControlPanel;
		}
	
	public SelfServiceWindow(Session session) throws Exception{
		
		SelfServiceControlPanel selfServiceControlPanel = new SelfServiceControlPanel();
		selfServiceControlPanel.load(session);
		
		this.setSelfServiceControlPanel(selfServiceControlPanel);
	}

}
