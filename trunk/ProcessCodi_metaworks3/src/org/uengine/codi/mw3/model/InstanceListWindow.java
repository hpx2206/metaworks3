package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs", 
	  displayName="개인중심 - 내가 할 일", 
      options={"hideLabels", "minimize"}, 
      values={"true", "true"})

public class InstanceListWindow extends Window {
	public InstanceListWindow() {
		setInstanceListPanel(new InstanceListPanel());
	}
	
	InstanceListPanel instanceListPanel;
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
}
