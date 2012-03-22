package org.uengine.codi.mw3.model;

	
public class Session {
	IUser user;	
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}

	Navigation navigation;

		public Navigation getNavigation() {
			return navigation;
		}
	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

}
