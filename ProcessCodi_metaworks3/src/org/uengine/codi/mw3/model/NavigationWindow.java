package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Resource;

@Face(ejsPath="genericfaces/Window.ejs", 
      displayName="$windowTitle", 
      options={"hideLabels", "innerLayoutName", "innerLayout", "outerLayoutName", "outerLayout"}, 
      values={"true", "north", "inner", "west", "outer"})
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
