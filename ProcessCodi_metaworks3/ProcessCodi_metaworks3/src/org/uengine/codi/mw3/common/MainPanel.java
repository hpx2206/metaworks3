package org.uengine.codi.mw3.common;

public class MainPanel {

	public MainPanel(){
		
	}
	
	public MainPanel(Object panel){
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
