package org.metaworks.widget;

import org.metaworks.annotation.Face;

@Face(displayName="ModalWindow")
public class ModalWindow {
	
	public ModalWindow() {
		
	}
	
	public ModalWindow(Object panel) {
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
