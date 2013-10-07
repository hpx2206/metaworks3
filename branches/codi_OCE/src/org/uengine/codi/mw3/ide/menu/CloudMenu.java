package org.uengine.codi.mw3.ide.menu;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.component.Menu;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.model.Session;

public class CloudMenu extends Menu {
		
	@AutowiredFromClient
	public Session session;
	
	ResourceNode resourceNode;
		@Hidden
		@AutowiredToClient
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
	
}
