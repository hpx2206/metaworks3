package org.uengine.codi.mw3;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class SignUp {

	Login login;
		public Login getLogin() {
			return login;
		}
		public void setLogin(Login login) {
			this.login = login;
		}

	public SignUp(){
		Login login = new Login();
		login.getMetaworksContext().setWhere("index");
		login.getMetaworksContext().setHow("signup");
		
		this.setLogin(login);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object[] goLogin(){
		return new Object[]{new Login()};
	}
	
}
