package org.uengine.codi.mw3.ide;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.menu.ServerContextMenu;
import org.uengine.codi.mw3.model.Session;

public class ServerNode extends TreeNode {
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, except={"child"}, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		
		return new Object[]{new Refresh(session), new ServerContextMenu()};
	}
}