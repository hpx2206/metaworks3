package org.uengine.codi.mw3.menu;

import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.menu.SubMenu;
import org.uengine.codi.mw3.admin.ClassDefinition;

public class SubMenuProject extends SubMenu {
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
	
	@ServiceMethod(keyBinding="Ctrl+S@Global")
	@Face(displayName="Compile")
	public Object[] compile() throws Exception {
		classDefinition.compile();
		
		return new Object[]{classDefinition, new Remover(this)};
	}

	
	@AutowiredFromClient
	public ClassDefinition classDefinition;


}
