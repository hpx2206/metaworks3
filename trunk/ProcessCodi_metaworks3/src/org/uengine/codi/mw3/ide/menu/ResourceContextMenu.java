package org.uengine.codi.mw3.ide.menu;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;

public class ResourceContextMenu extends Menu {

	public ResourceContextMenu(){
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		this.add(new SubMenuItem(new NewMenu()));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new SubMenuItem(new TeamMenu()));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("showProperties", "Properties"));
	}
	
	@ServiceMethod
	public Object showProperties(){
		return null;
	}
	
}
