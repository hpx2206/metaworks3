package org.uengine.codi.mw3.tadpole;

import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.Tray;
import org.uengine.codi.mw3.model.User;

public class TadpoleTopPanel {
	
	public TadpoleTopPanel() throws Exception {
		
	}
	
	public TadpoleTopPanel(Session session) throws Exception {
		
		session.getMetaworksContext().setWhere("marketplace");
		
		setSession(session);

		IUser loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());
		setLoginUser(loginUser);

	}

	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
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
