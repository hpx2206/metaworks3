package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", displayName="개인중심 - 내가 할 일", options={"hideLabels", "hideHeader"}, values={"true", "true"})
public class InstanceListWindow {
	public InstanceListWindow(){}
	public InstanceListWindow(Session session) throws Exception {
		this.instanceListPanel = new InstanceListPanel(session);
	}
	
	InstanceListPanel instanceListPanel;
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
}
