package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
      displayName="New Object...",
      options={"hideLabels", "maximize"},
      values={"true", "true"})
public class NewChildWindow extends ContentWindow {
	
	NewChildContentPanel newChildContentPanel;
		public NewChildContentPanel getNewChildContentPanel() {
			return newChildContentPanel;
		}
		public void setNewChildContentPanel(NewChildContentPanel newChildContentPanel) {
			this.newChildContentPanel = newChildContentPanel;
		}

	public NewChildWindow() {
		this.newChildContentPanel = new NewChildContentPanel();
	}
	
	public void setParentFolder(String defId){
		newChildContentPanel.setParentFolder(defId);
	}
	
	
}