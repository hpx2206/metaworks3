package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;

public class MappingTree extends Tree{
	
	boolean preLoaded;
		@Hidden
		public boolean isPreLoaded() {
			return preLoaded;
		}
		public void setPreLoaded(boolean preLoaded) {
			this.preLoaded = preLoaded;
		}

	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public void init(){
		String treeId = this.getId();
		RoleTreeNode roleNode = new RoleTreeNode();
		roleNode.setId(treeId + "Roles");
		roleNode.setType(TreeNode.TYPE_FOLDER);
		roleNode.setFolder(true);
		roleNode.load(defineTab.getRolePanel().getRoles());

		VariableTreeNode variableTreeNode = new VariableTreeNode();
		variableTreeNode.setId(treeId + "Variables");
		variableTreeNode.setTreeId(treeId);
		variableTreeNode.setType(TreeNode.TYPE_FOLDER);
		variableTreeNode.setFolder(true);
		variableTreeNode.load(defineTab.getPrcsValiablePanel().getPrcsValiables());
		
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(treeId + "Root");
		rootnode.setName(treeId + "Root");
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		
		rootnode.add(roleNode);
		rootnode.add(variableTreeNode);
		
		this.setNode(rootnode);
		
		setPreLoaded(true);
	}
	
	@AutowiredFromClient
	public DefineTab defineTab;
}
