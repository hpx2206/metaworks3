package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Resource;

@Face(ejsPath="genericfaces/Window.ejs", 
      displayName="$windowTitle", 
      options={"hideLabels", "layout"}, 
      values={"true", "north"})
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
	@Resource(def="네비게이션")
		public String getWindowTitle() {
			return windowTitle;
		}
		public void setWindowTitle(String windowTitle) {
			this.windowTitle = windowTitle;
		}

}
