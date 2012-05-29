package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.admin.WindowPanel;

public class ProcessTopPanel {

	public ProcessTopPanel() {
		setWindowPanel(new WindowPanel());
		setTray(new Tray());

	}
	
	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
	
	Tray tray;
		public Tray getTray() {
			return tray;
		}
		public void setTray(Tray tray) {
			this.tray = tray;
		}

}
