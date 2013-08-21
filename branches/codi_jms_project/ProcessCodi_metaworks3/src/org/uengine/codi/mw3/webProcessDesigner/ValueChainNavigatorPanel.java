package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.ValueChain;

public class ValueChainNavigatorPanel {

	Tree majorProcessDefinitionTree;
		public Tree getMajorProcessDefinitionTree() {
			return majorProcessDefinitionTree;
		}
		public void setMajorProcessDefinitionTree(Tree majorProcessDefinitionTree) {
			this.majorProcessDefinitionTree = majorProcessDefinitionTree;
		}
	String valueChainName;
		public String getValueChainName() {
			return valueChainName;
		}
		public void setValueChainName(String valueChainName) {
			this.valueChainName = valueChainName;
		}
	ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
		}
	
	@AutowiredFromClient
	public Session session;

	public void load(){
		MajorProcessDefinitionNode majorProcessDefinitionNode;
		
		if( valueChain != null && valueChain.getMajorProcessDefinitionNode() != null ){
			majorProcessDefinitionNode = valueChain.getMajorProcessDefinitionNode();
			majorProcessDefinitionNode.setName(valueChainName);
			majorProcessDefinitionNode.setExpanded(true);
		}else{
			majorProcessDefinitionNode= new MajorProcessDefinitionNode();
			majorProcessDefinitionNode.setId(valueChainName + "node");
			majorProcessDefinitionNode.setName(valueChainName);
			majorProcessDefinitionNode.setRoot(true);
			majorProcessDefinitionNode.setExpanded(true);
			majorProcessDefinitionNode.setType(TreeNode.TYPE_FOLDER);
			majorProcessDefinitionNode.setFolder(true);
		}
		majorProcessDefinitionNode.session = session;
		
		Tree tree = new Tree();
		tree.setId(valueChainName);
		tree.setNode(majorProcessDefinitionNode);
		
		setMajorProcessDefinitionTree(tree);
	}

}
