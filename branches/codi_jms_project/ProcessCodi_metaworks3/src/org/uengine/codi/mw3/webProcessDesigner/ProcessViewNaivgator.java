package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;

public class ProcessViewNaivgator {
	
	Tree processDefinitionTree;
		public Tree getProcessDefinitionTree() {
			return processDefinitionTree;
		}
		public void setProcessDefinitionTree(Tree processDefinitionTree) {
			this.processDefinitionTree = processDefinitionTree;
		}
	public void load(){
		
	}
	public void loadTree(){
		
		ProcessDefinitionNode rootnode = new ProcessDefinitionNode();
		rootnode.setRoot(true);
		rootnode.setId("DefinitionList");
		rootnode.setTreeId("DefinitionList");
		rootnode.setName("DefinitionList");
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		
		
		Tree tree = new Tree();
		tree.setNode(rootnode);
		
		setProcessDefinitionTree(tree);
	}
}
