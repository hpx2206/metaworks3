package org.uengine.codi.mw3.ide.menu;

import java.io.File;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.CloudTab;
import org.uengine.codi.mw3.ide.ResourceNode;

public class ResourceContextMenu extends CloudMenu {

	public ResourceContextMenu(){
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		this.add(new SubMenuItem(new NewMenu()));
		this.add(new MenuItem("open", "Open"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("copy", "Copy"));
		this.add(new MenuItem("paste", "Paste"));
		this.add(new MenuItem("remove", "Delete"));
		this.add(new MenuItem("move", "Move..."));
		this.add(new MenuItem("rename", "Rename..."));
		
/*		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new SubMenuItem(new TeamMenu()));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("showProperties", "Properties"));*/
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object open(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			return node.action();			
		}else{
			return null;
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] remove(){
/*		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			File file = new File(jbPath.getBasePath() + node.getId());
			if(file.exists()){
				file.delete();
			}
			
			return new Object[]{new Remover(new CloudTab(node.getId()), true), new Remover(node)};
		}else{
			return null;
		}*/
		
		return null;
	}
	
	@ServiceMethod
	public Object showProperties(){		
		return null;
	}
	
}
