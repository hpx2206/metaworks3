package org.uengine.codi.mw3.ide.menu;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.model.Session;

public class ResourceContextMenu extends Menu {

	@AutowiredFromClient
	public ResourceTree tree;
	
	@AutowiredFromClient
	public Session session;
	
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
		tree.getNode();
		//tree.getNode().getChild().get(0).getName()
		
		return session.getClipboard();
	}
	
}
