package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="Hint & Feedback",
	  options={"hideLabels", "minimize"},
	  values={"true", "true"})
public class HintWindow extends Window {

	public HintWindow()  {
		super();
	}
	
	public void load(Session session, String pageName) throws Exception {
		HintPanel hintPanel = new HintPanel();
		hintPanel.load(session, pageName);
		
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
