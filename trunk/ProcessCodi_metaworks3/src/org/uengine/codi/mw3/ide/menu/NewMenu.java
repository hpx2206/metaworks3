package org.uengine.codi.mw3.ide.menu;

import org.metaworks.component.MenuItem;


public class NewMenu extends ResourceMenu {

	public NewMenu(){

		this.setId("new");
		this.setName("New");
		
		this.add(new MenuItem("newPackage", "Package"));
		this.add(new MenuItem("newClass", "Class"));
	}
}
