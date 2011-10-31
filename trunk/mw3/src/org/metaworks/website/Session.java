package org.metaworks.website;

import org.metaworks.annotation.Hidden;


public class Session { //this holds only the session context

	
	IMenu menu;
		@Hidden
		public IMenu getMenu() {
			return menu;
		}
	
		public void setMenu(IMenu menu) {
			this.menu = menu;
		}

	IFacebookLoginUser loginUser;
	
		public IFacebookLoginUser getLoginUser() {
			return loginUser;
		}
	
		public void setLoginUser(IFacebookLoginUser loginUser) {
			this.loginUser = loginUser;
		}

}
