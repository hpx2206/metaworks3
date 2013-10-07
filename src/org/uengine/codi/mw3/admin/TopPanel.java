package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredToClient;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.menu.MainMenuTop;
import org.uengine.codi.mw3.model.Session;

public class TopPanel {

	public TopPanel(Session session) {
		setSession(session);
		setWindowPanel(new WindowPanel());
		setMainMenu(new MainMenuTop());		
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

	Session session;
		@AutowiredToClient
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}		
}
