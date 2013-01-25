package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ToAppend;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;

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
	
	public ArrayList<TreeNode> loadExpand() throws Exception{
		
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT roleCode, descr");
		sb.append("  FROM roleTABLE");
		//sb.append("WHERE ISDELETED = '0'");
		
		IDAO dao = (IDAO)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance().getConnectionFactory(), sb.toString(), IDAO.class);
		dao.set("ISDELETED", "0");
		dao.select();
		
		while(dao.next()){
			GroupTreeNode node = new GroupTreeNode();
			node.setId(dao.getString("roleCode"));
			node.setName(dao.getString("descr"));
			node.setParentId(this.getId());
			node.setType(TreeNode.TYPE_FILE_HTML);
			
			child.add(node);
		}
		
		this.setExpanded(true);
		this.setLoaded(true);

		
		return child;
	}
	
	@Override
	public Object expand() throws Exception{
		return new ToAppend(this, this.loadExpand());
	}
}
