package org.uengine.codi.mw3.menu;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.menu.SubMenu;
import org.uengine.codi.mw3.svn.CheckoutWindow;

public class SubMenuSource extends SubMenu {
	
	@ServiceMethod
	@Face(displayName="Check out")
	public CheckoutWindow checkout() throws Exception {
		return new CheckoutWindow();
	}
}
