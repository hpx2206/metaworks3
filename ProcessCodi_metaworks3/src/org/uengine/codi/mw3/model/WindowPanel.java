package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="genericfaces/Window.ejs",
      options={"hideLabels"}, 
      values={"true"})
public class WindowPanel {
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}
			
	String layoutName;
		@Hidden
		public String getLayoutName() {
			return layoutName;
		}
		public void setLayoutName(String layoutName) {
			this.layoutName = layoutName;
		}
}
