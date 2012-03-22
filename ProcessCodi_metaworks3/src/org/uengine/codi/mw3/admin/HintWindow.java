package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/WindowTab.ejs", 
	  displayName="Hint & Feedback",
	  options={"hideLabels", "hideCloseBtn", "layout"}, 
	  values={"true", "true", "east"})
public class HintWindow {

	public HintWindow(String pageName) {
	}
	
	HintPanel hintPanel;
		public HintPanel getHintPanel() {
			return hintPanel;
		}
		public void setHintPanel(HintPanel hintPanel) {
			this.hintPanel = hintPanel;
		}
}
