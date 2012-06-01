package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.IUser;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="Hint & Feedback",
	  options={"hideLabels", "minimize"},
	  values={"true", "true"})
public class HintWindow extends Window {

	public HintWindow()  {
		super();
	}
	
	public void load(IUser user, String pageName) throws Exception {
		HintPanel hintPanel = new HintPanel();
		hintPanel.load(user, pageName);
		
		setHintPanel(hintPanel);
	}
	
	HintPanel hintPanel;
		public HintPanel getHintPanel() {
			return hintPanel;
		}
		public void setHintPanel(HintPanel hintPanel) {
			this.hintPanel = hintPanel;
		}
}
