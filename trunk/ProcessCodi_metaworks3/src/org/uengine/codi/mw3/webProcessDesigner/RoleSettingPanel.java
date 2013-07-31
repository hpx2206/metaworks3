package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

import com.defaultcompany.organization.DefaultCompanyRoleResolutionContext;

public class RoleSettingPanel implements ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	Role role;
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
	String selectedGroupCode;
		public String getSelectedGroupCode() {
			return selectedGroupCode;
		}
		public void setSelectedGroupCode(String selectedGroupCode) {
			this.selectedGroupCode = selectedGroupCode;
		}
	String selectedRoleCode;
		public String getSelectedRoleCode() {
			return selectedRoleCode;
		}
		public void setSelectedRoleCode(String selectedRoleCode) {
			this.selectedRoleCode = selectedRoleCode;
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
	public RoleSettingPanel(Role role){
		setRole(role);
		setRoleName(role.getName());
	}
	
	public void load() throws Exception{
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		loadRole();
		loadGroup();
		if( getRole().getRoleResolutionContext() != null ){
			DefaultCompanyRoleResolutionContext context = (DefaultCompanyRoleResolutionContext)getRole().getRoleResolutionContext();
			this.selectedRoleCode = context.getRoleId();
			this.selectedGroupCode = context.getGroupId();
		}
	}
	public void loadRole() throws Exception{
		
		SelectBox choice = new SelectBox();
		choice.setSelectSize(8);
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
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		ArrayList<Role> roles = rolePanel.getRoles();
		for(int i=0; i<roles.size(); i++){
			Role role = roles.get(i);
			if( role.getName().equals(this.getRoleName())){
				DefaultCompanyRoleResolutionContext context = new DefaultCompanyRoleResolutionContext();
				context.setRoleId(this.getSelectedRoleCode());
				context.setGroupId(this.getSelectedGroupCode());
				role.setRoleResolutionContext(context);
			}
		}
		
		
		return new Object[]{ new Remover(new ModalWindow())};
	}
	@ServiceMethod
	public Object cancel() throws Exception{
		return new Remover(new ModalWindow());
	}
	
	@AutowiredFromClient
	public RolePanel rolePanel;
}
