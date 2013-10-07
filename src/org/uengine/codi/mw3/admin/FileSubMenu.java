package org.uengine.codi.mw3.admin;

import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.NewChildContentPanel;
import org.uengine.codi.mw3.model.NewChildWindow;


public class FileSubMenu extends SubMenu{

	@ServiceMethod(keyBinding="Ctrl+N")
	public Object[] newFile(){
		NewChildWindow newChildWindow = new NewChildWindow(); 
		newChildWindow.setParentFolder(null);
		
		return new Object[]{newChildWindow, new Remover(this)};

	}
	
	
}
