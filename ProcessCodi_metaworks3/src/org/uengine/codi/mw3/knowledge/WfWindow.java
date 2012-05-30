package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.IUser;

@Face(ejsPath="genericfaces/Window.ejs", 
      displayName="Knowledge Mode", 
      options={"hideLabels"}, 
      values={"true"})
public class WfWindow {
	
	public WfWindow(IUser user) throws Exception {
		WfPanel panel = new WfPanel();
		panel.load(user.getUserId());
		
		setWfPanel(panel);
	}
	
	WfPanel wfPanel;
		public WfPanel getWfPanel() {
			return wfPanel;
		}
		public void setWfPanel(WfPanel wfPanel) {
			this.wfPanel = wfPanel;
		}
	
}
