package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.ValueChain;

public class ValueChainNavigatorPanel implements ContextAware {

	public ValueChainNavigatorPanel() {
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
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
		
		this.getMetaworksContext().setHow("viewer");
		MajorProcessDefinitionNode majorProcessDefinitionNode;
		
		if( valueChain != null && valueChain.getMajorProcessDefinitionNode() != null ){
			majorProcessDefinitionNode = valueChain.getMajorProcessDefinitionNode();
			for(int i = 0; i < majorProcessDefinitionNode.getChild().size(); i++) {
				if(majorProcessDefinitionNode.getChild().get(i) instanceof MajorProcessDefinitionNode) {
					MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)majorProcessDefinitionNode.getChild().get(i);
					node.setMetaworksContext(this.getMetaworksContext());
					node.injectionMetaworksContext(this.getMetaworksContext(), node.getChild());
				} else {
					MinorProcessDefinitionNode node = (MinorProcessDefinitionNode)majorProcessDefinitionNode.getChild().get(i);
					node.setMetaworksContext(this.getMetaworksContext());
				}
			}
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
