package org.essencia.ide;

import org.essencia.mini.test.ComponentTreeNode;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.component.Menu;

public class CloudMenu extends Menu {
		
	
	ComponentTreeNode componentTreeNode;
		@Hidden
		@AutowiredToClient
		public ComponentTreeNode getComponentTreeNode() {
			return componentTreeNode;
		}
	
		public void setComponentTreeNode(ComponentTreeNode componentTreeNode) {
			this.componentTreeNode = componentTreeNode;
		}
	
}
