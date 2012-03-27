package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", 
	  options={"hideLabels", "hideCloseBtn", "hideNewBtn"}, 
	  values={"true", "true", "true"})

public class ContentWindow  {
	public ContentWindow(){
	}
	
	public ContentWindow(Object panel){
		setPanel(panel);
	}

	public Object panel;
		public Object getPanel() {
			return panel;
		}	
		public void setPanel(Object panel) {
			this.panel = panel;
		}
	
}
