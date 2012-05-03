package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.IUser;

@Face(ejsPath="genericfaces/Window.ejs", 
	  displayName="Hint & Feedback",
	  options={"hideLabels", "hideCloseBtn", "layout"}, 
	  values={"true", "true", "east"})
public class HintWindow {

	public HintWindow(IUser user, String pageName) throws Exception  {
		setHintPanel(new HintPanel(user, pageName));
	}
	
	HintPanel hintPanel;
		public HintPanel getHintPanel() {
			return hintPanel;
		}
		public void setHintPanel(HintPanel hintPanel) {
			this.hintPanel = hintPanel;
		}
}
