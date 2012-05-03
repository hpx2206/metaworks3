package org.uengine.codi.mw3.model;

	
public class Session {
	IUser user;	
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}

	String defId;	
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	Navigation navigation;

		public Navigation getNavigation() {
			return navigation;
		}
	
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

}
