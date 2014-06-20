package org.essencia.ide;

import org.essencia.mini.view.TreeNodeView;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.component.Menu;

public class CloudMenu extends Menu {
		
	
	TreeNodeView componentTreeNode;
		@Hidden
		@AutowiredToClient
		public TreeNodeView getComponentTreeNode() {
			return componentTreeNode;
		}
	
		public void setComponentTreeNode(TreeNodeView componentTreeNode) {
			this.componentTreeNode = componentTreeNode;
		}
	
}
