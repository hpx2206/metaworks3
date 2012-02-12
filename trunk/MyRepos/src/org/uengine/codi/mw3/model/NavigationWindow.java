package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Resource;

@Face(ejsPath="genericfaces/Window.ejs", displayName="$windowTitle", options={"hideLabels"}, values={"true"})
public class NavigationWindow {
    
	public NavigationWindow(){
		navigation = new Navigation();
	}
	
	Navigation navigation;
		public Navigation getNavigation() {
			return navigation;
		}	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}
		
	String windowTitle;
    @Resource(def="?????")
		public String getWindowTitle() {
			return windowTitle;
		}
		public void setWindowTitle(String windowTitle) {
			this.windowTitle = windowTitle;
		}

}
