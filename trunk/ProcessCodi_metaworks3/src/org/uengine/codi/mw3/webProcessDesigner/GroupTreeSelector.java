package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.component.Tree;

public class GroupTreeSelector extends Selector {	

	public GroupTreeSelector(){
		this.setType(Selector.TYPE_TREE);
	}
		
	@Override
	public void load() {
		GroupTreeNode node = new GroupTreeNode();
		
		try{
			node.setRoot(true);
			node.setType(GroupTreeNode.TYPE_FOLDER);
			node.setName("$groups");
			node.setChild(node.loadExpand());
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Tree tree = new Tree();
		tree.setId("GroupTree");
		tree.setNode(node);
		
		this.setTarget(tree);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
	}	
}
