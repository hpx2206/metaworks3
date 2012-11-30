package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class RolePicker {
	
	String rootFolder;
		public String getRootFolder() {
			return rootFolder;
		}
		public void setRootFolder(String rootFolder) {
			this.rootFolder = rootFolder;
		}
	ArrayList<String> childList;
		public ArrayList<String> getChildList() {
			return childList;
		}
		public void setChildList(ArrayList<String> childList) {
			this.childList = childList;
		}

	Tree tree;
		public Tree getTree() {
			return tree;
		}
		public void setTree(Tree tree) {
			this.tree = tree;
		}
		
	public RolePicker(){
		
	}
	
	public void loadRole() throws Exception{
		
		RoleTreeNode rootNode = new RoleTreeNode();
		rootNode.setRoot(true);
		rootNode.setId("ROLE");
		rootNode.setName("ROLE");
		rootNode.setType(TreeNode.TYPE_FOLDER);
		
		rootNode.setLoaded(true);
		rootNode.setExpanded(true);

		String roleListSql = " SELECT ROLECODE, DESCR FROM ROLETABLE WHERE ISDELETED = '0' ";
		IDAO roleDao = Database.sql(IDAO.class, roleListSql);
		roleDao.select();
		while(roleDao.next()){
			String code = (String) roleDao.get("ROLECODE");
			String name = (String) roleDao.get("DESCR");
			
			RoleTreeNode node = new RoleTreeNode();
			
			node.setId(code);
			node.setName(name);
			node.setParentId("ROLE");
			node.setType(TreeNode.TYPE_FILE_IMAGE);
			rootNode.add(node);
			
		}
		
		Tree tree = new Tree();
		tree.setNode(rootNode);
		
		this.setTree(tree);
	}
	
	public void loadGroup() throws Exception{
		String groupListSql = " SELECT PARTCODE, PARTNAME FROM PARTTABLE WHERE ISDELETED = '0' ";
		IDAO groupDao = Database.sql(IDAO.class, groupListSql);
		groupDao.select();
		while(groupDao.next()){
			String code = (String) groupDao.get("PARTCODE");
			String name = (String) groupDao.get("PARTNAME"); 
		}
	}
}
