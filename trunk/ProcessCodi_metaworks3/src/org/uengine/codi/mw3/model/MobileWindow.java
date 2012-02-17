package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.platform.Console;

@Face(ejsPath="genericfaces/MobileWindow.ejs",
	  options={"hideLabels"}, 
       values={"true"})
public class MobileWindow {
	
	public MobileWindow(){
	}
	
	public MobileWindow(Object panel){
		setPanel(panel);
	}
	
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}	

}
