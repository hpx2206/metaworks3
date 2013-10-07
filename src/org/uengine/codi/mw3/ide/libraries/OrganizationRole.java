package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Role;
import org.uengine.codi.mw3.model.Session;

public class OrganizationRole extends Role {
	
	@ServiceMethod(callByContent=true, mouseBinding="drag")
	public Session drag() {
		session.setClipboard(this);
		
		return session;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		
		Popup popup = new Popup();
		org.uengine.kernel.Role role =  new org.uengine.kernel.Role();
		role.setName(this.getDescr());
		popup.setPanel(role);
		popup.setWidth(700);
		popup.setHeight(500);
		
		return popup;
	}	
}
