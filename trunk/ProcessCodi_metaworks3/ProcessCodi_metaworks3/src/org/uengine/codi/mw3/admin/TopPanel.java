package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.menu.MainMenuTop;

public class TopPanel {

	public TopPanel() {
		setMainMenu(new MainMenuTop());
		setWindowPanel(new WindowPanel());
	}
	
	public TopPanel(Login user){
		
	}
	
	MainMenuTop mainMenu;
		public MainMenuTop getMainMenu() {
			return mainMenu;
		}
		public void setMainMenu(MainMenuTop mainMenu) {
			this.mainMenu = mainMenu;
		}
		
	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
	
}
