package org.uengine.codi.mw3.marketplace;

import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.Tray;
import org.uengine.codi.mw3.model.User;

public class MarketplaceTopPanel {
	
	public MarketplaceTopPanel() throws Exception {
		
	}
	
	public MarketplaceTopPanel(Session session) throws Exception {
		
		session.getMetaworksContext().setWhere("marketplace");
		
		setSession(session);

		IUser loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());
		setLoginUser(loginUser);
		
		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);
		setSearchBox(searchBox);
	}

	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
	
	MarketplaceSearchBox searchBox;		
		public MarketplaceSearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(MarketplaceSearchBox searchBox) {
			this.searchBox = searchBox;
		}

	Tray tray;
		public Tray getTray() {
			return tray;
		}
		public void setTray(Tray tray) {
			this.tray = tray;
		}
	
	IUser loginUser;
		public IUser getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(IUser loginUser) {
			this.loginUser = loginUser;
		}

}
