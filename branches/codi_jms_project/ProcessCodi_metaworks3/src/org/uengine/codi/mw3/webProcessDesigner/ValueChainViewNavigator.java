package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;

public class ValueChainViewNavigator {

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
		
		ProcessDefinitionNode rootNode = new ProcessDefinitionNode();
		rootNode.setRoot(true);
		rootNode.setId("DefinitionList");
		rootNode.setTreeId("DefinitionList");
		rootNode.setName("DefinitionList");
		rootNode.setType(TreeNode.TYPE_FOLDER);
		rootNode.setFolder(true);
		rootNode.setLoaded(true);
		rootNode.setExpanded(true);
		
		
		Tree tree = new Tree();
		tree.setNode(rootNode);
		
		setProcessDefinitionTree(tree);
		
	}
	
}
