package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
      displayName="$Navigator",
      options={"hideLabels", "minimize"},
      values={"true", "true"})
public class ResourceWindow extends Window {

	public ResourceWindow() throws Exception {
		super();
		
		setResourcePanel(new ResourcePanel());		
	}
	
	ResourcePanel resourcePanel;		
		public ResourcePanel getResourcePanel() {
			return resourcePanel;
		}
		public void setResourcePanel(ResourcePanel resourcePanel) {
			this.resourcePanel = resourcePanel;
		}
		
}
