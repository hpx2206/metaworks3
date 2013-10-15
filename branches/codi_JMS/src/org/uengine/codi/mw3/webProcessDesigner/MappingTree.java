package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;

public class MappingTree extends Tree{
	
	boolean preLoaded;
		@Hidden
		public boolean isPreLoaded() {
			return preLoaded;
		}
		public void setPreLoaded(boolean preLoaded) {
			this.preLoaded = preLoaded;
		}

	@ServiceMethod(payload={"id", "align"} , target=ServiceMethodContext.TARGET_SELF)
	public void init(){
		ArrayList<Role>	 roleList = rolePanel.getRoleList();
        ArrayList<ProcessVariable> variableList = processVariablePanel.getVariableList();
        
		String treeId = this.getId();
		RoleTreeNode roleNode = new RoleTreeNode();
		roleNode.setId(treeId + "Roles");
		roleNode.setType(TreeNode.TYPE_FOLDER);
		roleNode.setFolder(true);
		roleNode.setAlign(this.getAlign());
		roleNode.load(roleList);

		VariableTreeNode variableTreeNode = new VariableTreeNode();
		variableTreeNode.setId(treeId + "Variables");
		variableTreeNode.setTreeId(treeId);
		variableTreeNode.setType(TreeNode.TYPE_FOLDER);
		variableTreeNode.setFolder(true);
		variableTreeNode.setAlign(this.getAlign());
		variableTreeNode.load(variableList);
		
		InstanceTreeNode InstanceTreeNode = new InstanceTreeNode();
		InstanceTreeNode.setId(treeId + "Instance");
		InstanceTreeNode.setTreeId(treeId);
		InstanceTreeNode.setType(TreeNode.TYPE_FOLDER);
		InstanceTreeNode.setFolder(true);
		InstanceTreeNode.setAlign(this.getAlign());
		InstanceTreeNode.load();
		
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(treeId + "Root");
		rootnode.setName(treeId + "Root");
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		rootnode.setAlign(this.getAlign());
		
		rootnode.add(roleNode);
		rootnode.add(variableTreeNode);
		rootnode.add(InstanceTreeNode);
		
		this.setNode(rootnode);
		
		setPreLoaded(true);
	}
	
	@AutowiredFromClient
	transient public RolePanel rolePanel;
	
	@AutowiredFromClient
	transient public ProcessVariablePanel processVariablePanel;
	
	
}
