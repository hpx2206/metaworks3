package org.uengine.codi.mw3.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.menu.SubMenu;
import org.uengine.codi.mw3.svn.CheckoutWindow;

public class SubMenuSource extends SubMenu {
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Check out")
	public ModalWindow checkout() throws Exception {
		
		ModalWindow checkoutWindow = new ModalWindow();
		checkoutWindow.setTitle("Check out");
		checkoutWindow.setPanel(new CheckoutWindow());
		
		return checkoutWindow;
	}
}
