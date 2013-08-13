package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Session;

public class ValueChainViewPanel {
	@AutowiredFromClient
	public Session session;
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		
	Tree majorPorcessDefinitionTree;
		public Tree getMajorPorcessDefinitionTree() {
			return majorPorcessDefinitionTree;
		}
		public void setMajorPorcessDefinitionTree(Tree majorPorcessDefinitionTree) {
			this.majorPorcessDefinitionTree = majorPorcessDefinitionTree;
		}
		
	public void load(){
		//TODO : value chain view panel load
	}

	public void loadTree(){
		
		ProcessDefinitionNode rootNode = new ProcessDefinitionNode();
		rootNode.setRoot(true);
		rootNode.setId("MajorProcessList");
		rootNode.setTreeId("MajorProcessList");
		rootNode.setName("MajorProcessList");
		rootNode.setType(TreeNode.TYPE_FOLDER);
		rootNode.setFolder(true);
		rootNode.setLoaded(true);
		rootNode.setExpanded(true);
		
		
		Tree tree = new Tree();
		tree.setNode(rootNode);
		
		setMajorPorcessDefinitionTree(tree);
		
	}
	
}
