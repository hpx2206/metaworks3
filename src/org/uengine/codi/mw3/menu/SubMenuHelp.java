package org.uengine.codi.mw3.menu;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.menu.SubMenu;

public class SubMenuHelp extends SubMenu {
	@ServiceMethod
	@Face(displayName="New")
	public void newObject() throws Exception {
		
	}
	
	@ServiceMethod
	@Face(displayName="OpenFile", options={"separator"}, values={"true"})	
	public void openFile() throws Exception {
		
	}

	@ServiceMethod
	@Face(displayName="Close")
	public void close() throws Exception {
		
	}
	
	@ServiceMethod
	@Face(displayName="Close All", options={"separator"}, values={"true"})
	public void closeAll() throws Exception {
		
	}

	@ServiceMethod
	@Face(displayName="Save")
	public void save() throws Exception {
		
	}

	@ServiceMethod
	@Face(displayName="Save As...")
	public void saveAs() throws Exception {
		
	}
	
	@ServiceMethod
	@Face(displayName="Save All", options={"separator"}, values={"true"})
	public void saveAll() throws Exception {
		
	}
}
