package org.metaworks.widget;

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
