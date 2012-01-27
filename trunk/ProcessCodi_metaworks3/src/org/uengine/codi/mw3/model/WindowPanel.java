package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="faces/org/uengine/codi/mw3/model/Window.ejs",
      options={"hideLabels"}, 
      values={"true"})
public class WindowPanel {
	
	public WindowPanel() {
		
	}
	
	public WindowPanel(Object panel) {
		this.panel = panel;
	}
	
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}
}
