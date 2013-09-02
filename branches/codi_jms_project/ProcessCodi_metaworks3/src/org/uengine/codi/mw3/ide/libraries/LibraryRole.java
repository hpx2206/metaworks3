package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Role;
import org.uengine.codi.mw3.model.Session;

public class LibraryRole extends Role {

	@ServiceMethod(callByContent=true, mouseBinding="drag")
	public Session drag() {
		session.setClipboard(this);
		return session;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		org.uengine.kernel.Role role =  new org.uengine.kernel.Role();
		role.setName("uengine");
		modalWindow.setPanel(role);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		return modalWindow;
	}	
}
