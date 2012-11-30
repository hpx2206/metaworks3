package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

public class RoleSettingPanel implements ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
	String desc;
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	String selectedGroupName;
		public String getSelectedGroupName() {
			return selectedGroupName;
		}
		public void setSelectedGroupName(String selectedGroupName) {
			this.selectedGroupName = selectedGroupName;
		}
	String selectedRoleName;
		public String getSelectedRoleName() {
			return selectedRoleName;
		}
		public void setSelectedRoleName(String selectedRoleName) {
			this.selectedRoleName = selectedRoleName;
		}
	Tree groupTree;
		public Tree getGroupTree() {
			return groupTree;
		}
		public void setGroupTree(Tree groupTree) {
			this.groupTree = groupTree;
		}
	SelectBox roleSelect;
		public SelectBox getRoleSelect() {
			return roleSelect;
		}
		public void setRoleSelect(SelectBox roleSelect) {
			this.roleSelect = roleSelect;
		}
	public RoleSettingPanel(){
		setRoleName(null);
	}
	public RoleSettingPanel(String roleName){
		setRoleName(roleName);
	}
	
	public void load() throws Exception{
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		loadRole();
		loadGroup();
		if( desc != null ){
			// TODO 롤 셋팅부분
		}
	}
	public void loadRole() throws Exception{
		
		SelectBox choice = new SelectBox();
		choice.setSelectSize(5);
		choice.setSelectStyle("width:100%;");
		String roleListSql = " SELECT ROLECODE, DESCR FROM ROLETABLE WHERE ISDELETED = '0' ";
		IDAO roleDao = Database.sql(IDAO.class, roleListSql);
		roleDao.select();
		while(roleDao.next()){
			String code = (String) roleDao.get("ROLECODE");
			String name = (String) roleDao.get("DESCR");
			choice.add(name, code);
		}
		this.setRoleSelect(choice);
	}
	
	public void loadGroup() throws Exception{
		RoleTreeNode rootNode = new RoleTreeNode();
		rootNode.setRoot(true);
		rootNode.setId("GROUP");
		rootNode.setName("GROUP");
		rootNode.setType(TreeNode.TYPE_FOLDER);
		
		rootNode.setLoaded(true);
		rootNode.setExpanded(true);
		
		String groupListSql = " SELECT PARTCODE, PARTNAME FROM PARTTABLE WHERE ISDELETED = '0' ";
		IDAO groupDao = Database.sql(IDAO.class, groupListSql);
		groupDao.select();
		while(groupDao.next()){
			String code = (String) groupDao.get("PARTCODE");
			String name = (String) groupDao.get("PARTNAME"); 
			
			RoleTreeNode node = new RoleTreeNode();
			
			node.setId(code);
			node.setName(name);
			node.setParentId("GROUP");
			node.setType(TreeNode.TYPE_FILE_IMAGE);
			rootNode.add(node);
		}
		Tree tree = new Tree();
		tree.setNode(rootNode);
		
		this.setGroupTree(tree);
	}
//	@ServiceMethod(callByContent=true, target="popup")
//	public ModalWindow findRole() throws Exception{
//		RolePicker rolePicker = new RolePicker();
//		rolePicker.loadRole();
//		return new ModalWindow(rolePicker, 400, 450,  "역할찾기" );
//	}
//	@ServiceMethod(callByContent=true, target="popup")
//	public ModalWindow findGroup() throws Exception{
//		RolePicker rolePicker = new RolePicker();
//		rolePicker.loadGroup();
//		return new ModalWindow(rolePicker , 400, 450,  "그룹찾기" );
//	}
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		
		
		return new Object[]{ new Remover(new ModalWindow())};
	}
	@ServiceMethod
	public Object cancel() throws Exception{
		return new Remover(new ModalWindow());
	}
}
