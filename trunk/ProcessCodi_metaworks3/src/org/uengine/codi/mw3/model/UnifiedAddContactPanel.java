package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs")
public class UnifiedAddContactPanel {
	
	public UnifiedAddContactPanel(Session session) throws Exception {
		setLocalContact(new AddLocalContactPanel(session));
		setFacebookContact(new AddContactPanel());
		setInviteByEmail(new Invitation());
	}

	AddLocalContactPanel localContact;
		public AddLocalContactPanel getLocalContact() {
			return localContact;
		}
		public void setLocalContact(AddLocalContactPanel localContact) {
			this.localContact = localContact;
		}

	AddContactPanel facebookContact;
		public AddContactPanel getFacebookContact() {
			return facebookContact;
		}
		public void setFacebookContact(AddContactPanel facebookContact) {
			this.facebookContact = facebookContact;
		}
		
	Invitation inviteByEmail;

		public Invitation getInviteByEmail() {
			return inviteByEmail;
		}
		public void setInviteByEmail(Invitation inviteByEmail) {
			this.inviteByEmail = inviteByEmail;
		}

	@AutowiredFromClient
	public Session session;

	
}
