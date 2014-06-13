package org.essencia.ide;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.component.Menu;

public class CloudMenu extends Menu {
		
	
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
