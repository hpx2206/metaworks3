package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", 
      displayName="Navigation", 
      options={"hideLabels", "minimize"}, 
      values={"true", "true"})
public class NavigationWindow {
	
	public NavigationWindow() throws Exception{
		setNavigation(new Navigation());
	}
	
	Navigation navigation;
		public Navigation getNavigation() {
			return navigation;
		}	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}
}
