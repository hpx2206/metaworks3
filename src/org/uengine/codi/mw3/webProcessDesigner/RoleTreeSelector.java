package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.component.Tree;

public class RoleTreeSelector extends Selector {	

	public RoleTreeSelector(){
		this.setType(Selector.TYPE_TREE);
	}
		
	@Override
	public void load() {
		RoleTreeNode node = new RoleTreeNode();
		
		try{
			node.setRoot(true);
			node.setType(RoleTreeNode.TYPE_FOLDER);
			node.setName("$roles");
			node.setChild(node.loadExpand());
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Tree tree = new Tree();
		tree.setId("RoleTree");
		tree.setNode(node);
		
		this.setTarget(tree);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
	}
}