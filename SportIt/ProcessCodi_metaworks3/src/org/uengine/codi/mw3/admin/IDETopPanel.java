package org.uengine.codi.mw3.admin;

import org.uengine.codi.mw3.menu.MainMenuTop;
import org.uengine.codi.mw3.model.Session;

public class IDETopPanel extends TopPanel {

	public IDETopPanel(){
		
	}
	
	public IDETopPanel(Session session) {
		super(session);
		
		this.setMainMenu(new MainMenuTop());
	}
	
	MainMenuTop mainMenu;
		public MainMenuTop getMainMenu() {
			return mainMenu;
		}
		public void setMainMenu(MainMenuTop mainMenu) {
			this.mainMenu = mainMenu;
		}
}
