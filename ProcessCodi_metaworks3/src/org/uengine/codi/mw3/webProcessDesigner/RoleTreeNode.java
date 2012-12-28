package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.component.TreeNode;

public class RoleTreeNode extends TreeNode {
	
	public void load(ArrayList<Role> roleList){
		this.setName("roles");
		this.setLoaded(true);
		this.setExpanded(true);
		
		for(int i = 0; i < roleList.size(); i++){
			Role role = roleList.get(i);			
			
			RoleTreeNode node = new RoleTreeNode();			
			node.setId("[roles]." + role.getName());
			node.setName(role.getName());
			node.setParentId(this.getId());
			node.setType(TreeNode.TYPE_FILE_HTML);
			
			this.add(node);
		}
	}
	
	/*
	 * service method
	 */
	@Override
	public Object expand() throws Exception { 
		// Override method
		
		return null;
	}
}
