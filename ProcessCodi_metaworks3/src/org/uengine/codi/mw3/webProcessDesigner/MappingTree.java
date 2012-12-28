package org.uengine.codi.mw3.webProcessDesigner;

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
		String align = this.getAlign();
		RoleTreeNode leftRoleNode = new RoleTreeNode();
		leftRoleNode.setId(align + "Roles");
		leftRoleNode.setType(TreeNode.TYPE_FOLDER);
		leftRoleNode.load(defineTab.getRolePanel().getRoles());

		VariableTreeNode leftVariableTreeNode = new VariableTreeNode();
		leftVariableTreeNode.setId(align + "Variables");
		leftVariableTreeNode.setType(TreeNode.TYPE_FOLDER);
		leftVariableTreeNode.load(defineTab.getPrcsValiablePanel().getPrcsValiables());
		
		TreeNode leftRootnode = new TreeNode();
		leftRootnode.setRoot(true);
		leftRootnode.setId(align + "Root");
		leftRootnode.setName(align);
		leftRootnode.setType(TreeNode.TYPE_FOLDER);
		leftRootnode.setLoaded(true);
		leftRootnode.setExpanded(true);
		
		leftRootnode.add(leftRoleNode);
		leftRootnode.add(leftVariableTreeNode);
		
		this.setNode(leftRootnode);
		
		setPreLoaded(true);
	}
	
	@AutowiredFromClient
	public DefineTab defineTab;
}
