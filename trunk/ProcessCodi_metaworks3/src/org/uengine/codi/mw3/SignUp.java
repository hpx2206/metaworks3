package org.uengine.codi.mw3;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Invitation;

public class SignUp {

	Invitation invitation;
		public Invitation getInvitation() {
			return invitation;
		}
		public void setInvitation(Invitation invitation) {
			this.invitation = invitation;
		}
		
	public SignUp(){
		this.setInvitation(new Invitation());
		this.getInvitation().getMetaworksContext().setHow("signup");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object[] goLogin(){
		return new Object[]{new Login()};
	}
	
}
