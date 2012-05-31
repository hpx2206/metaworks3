package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="New InstancePanel",
	  options={"hideLabels", "maximize"},
	  values={"true", "true"})
public class NewInstanceWindow extends ContentWindow {
	
	public NewInstanceWindow(Object panel){
		super(panel);
	}
	
	NewInstancePanel newInstancePanel;
		public NewInstancePanel getNewInstancePanel() {
			return newInstancePanel;
		}	
		public void setNewInstancePanel(NewInstancePanel newInstancePanel) {
			this.newInstancePanel = newInstancePanel;
		}
	
}
