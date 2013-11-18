package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.facebook.api.Facebook;

@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
public class UnifiedAddContactPanel {
	
	public UnifiedAddContactPanel(Session session) throws Exception {
		setLocalContact(new AddLocalContactPanel(session));
		
		if("1".equals(Facebook.USE_FACEBOOK))
			setFacebookContact(new AddContactPanel());
		
		Invitation invitation = new Invitation();
		invitation.setMetaworksContext(new MetaworksContext());
		invitation.getMetaworksContext().setHow("invite");
		setInviteByEmail(invitation);
	}

	AddLocalContactPanel localContact;
		@Face(displayName="$LocalContact")
		public AddLocalContactPanel getLocalContact() {
			return localContact;
		}
		public void setLocalContact(AddLocalContactPanel localContact) {
			this.localContact = localContact;
		}

	AddContactPanel facebookContact;
//	@Face(displayName="$Facebook")
		@Hidden
		public AddContactPanel getFacebookContact() {
			return facebookContact;
		}
		public void setFacebookContact(AddContactPanel facebookContact) {
			this.facebookContact = facebookContact;
		}
		
	Invitation inviteByEmail;
		@Face(displayName="$InviteByEmail")
		public Invitation getInviteByEmail() {
			return inviteByEmail;
		}
		public void setInviteByEmail(Invitation inviteByEmail) {
			this.inviteByEmail = inviteByEmail;
		}

	@AutowiredFromClient
	public Session session;

	
}
