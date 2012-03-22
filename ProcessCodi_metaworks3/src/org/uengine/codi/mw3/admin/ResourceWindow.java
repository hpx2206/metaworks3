package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
      displayName="Navigator",
      options={"hideLabels", "hideCloseBtn", "hideHeader", "layout"},
      values={"true", "true", "true", "west"})
public class ResourceWindow {

	public ResourceWindow() throws Exception {
		resourcePanel = new ResourcePanel();
		resourcePanel.refresh();
	}
	
	ResourcePanel resourcePanel;		
	public ResourcePanel getResourcePanel() {
		return resourcePanel;
	}
	public void setResourcePanel(ResourcePanel resourcePanel) {
		this.resourcePanel = resourcePanel;
	}
	
}
