package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", displayName="네비게이션", options={"hideLabels"}, values={"true"})
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

}
