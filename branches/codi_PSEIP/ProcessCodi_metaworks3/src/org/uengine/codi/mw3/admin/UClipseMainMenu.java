package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.ServiceMethod;

public class UClipseMainMenu extends MainMenu{
	
	@ServiceMethod(target="stick")
	public FileSubMenu file(){
		return new FileSubMenu();
	}
	
	@ServiceMethod(target="stick")
	public EditSubMenu edit(){
		return new EditSubMenu();
	}
	


}
