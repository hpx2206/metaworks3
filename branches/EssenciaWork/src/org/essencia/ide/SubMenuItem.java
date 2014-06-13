package org.essencia.ide;

import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;

public class SubMenuItem extends MenuItem {

	public SubMenuItem(){
		
	}
	
	public SubMenuItem(Menu menu){
		menu.setSub(true);
		
		this.setId(menu.getId());
		this.setName(menu.getName());
		this.setSubMenu(menu);
	}
}