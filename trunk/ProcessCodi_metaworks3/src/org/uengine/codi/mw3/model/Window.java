package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/WindowTab.ejs")
public class Window {
	
	public Window() {
		
	}
	
	public Window(Object panel) {
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
