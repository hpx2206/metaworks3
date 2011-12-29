package org.uengine.codi.mw3.model;

public class Session {
	
	ILogin login;

		public ILogin getLogin() {
			return login;
		}
	
		public void setLogin(ILogin login) {
			this.login = login;
		}
	
	Navigation navigation;

		public Navigation getNavigation() {
			return navigation;
		}
	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

}
