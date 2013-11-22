package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.ServerNode;
import org.uengine.codi.mw3.ide.ServerNodeManager;

public class ServerContextMenu extends CloudMenu {

	public ServerContextMenu(){
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		this.add(new MenuItem("debug", "Debug"));
		this.add(new MenuItem("start", "Start"));
		this.add(new MenuItem("stop", "Stop"));
		this.add(new MenuItem("publish", "Publish"));
		this.add(new MenuItem("clean", "Clean"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object debug(){
		return null;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object start(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ServerNode){
			ServerNode node = (ServerNode)clipboard;
						
			return new ServerNodeManager(node, "Starting");
		}else
			return null;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object stop(){
		return null;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object publish(){
		return null;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object clean(){
		return null;
	}

}
