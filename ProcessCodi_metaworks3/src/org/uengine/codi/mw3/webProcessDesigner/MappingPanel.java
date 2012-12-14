package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;

public class MappingPanel implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String elementId;
		public String getElementId() {
			return elementId;
		}
		public void setElementId(String elementId) {
			this.elementId = elementId;
		}
	String mapperData;
		public String getMapperData() {
			return mapperData;
		}
		public void setMapperData(String mapperData) {
			this.mapperData = mapperData;
		}
	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	public ArrayList<PrcsValiable>	 prcsValiableList;
		public ArrayList<PrcsValiable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsValiable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}
	Tree leftTree;	
		public Tree getLeftTree() {
			return leftTree;
		}
		public void setLeftTree(Tree leftTree) {
			this.leftTree = leftTree;
		}
	Tree rightTree;	
		public Tree getRightTree() {
			return rightTree;
		}
		public void setRightTree(Tree rightTree) {
			this.rightTree = rightTree;
		}
	public MappingPanel() throws Exception{
		setMetaworksContext(new MetaworksContext());
	}
	public void load() throws Exception{
		loadTree();
	}
	public void loadTree() throws Exception{
		RoleTreeNode rootNode = new RoleTreeNode();
		rootNode.setRoot(true);
		rootNode.setId("root");
		rootNode.setName("getSchemaLocation");
		rootNode.setType(TreeNode.TYPE_FOLDER);
		
		rootNode.setLoaded(true);
		rootNode.setExpanded(true);
		
		if( this.getPrcsValiableList() != null){
			RoleTreeNode valiableNode = new RoleTreeNode();
			valiableNode.setId("valiables");
			valiableNode.setName("Valiables");
			valiableNode.setParentId("root");
			valiableNode.setType(TreeNode.TYPE_FOLDER);
			valiableNode.setLoaded(true);
			valiableNode.setExpanded(true);
			rootNode.add(valiableNode);
			
			for(int i = 0; i < prcsValiableList.size(); i++){
				PrcsValiable prcsValiable = prcsValiableList.get(i);
				String nameAttr = prcsValiable.getName();
				String typeIdAttr = prcsValiable.getTypeId();
				String typeAttr = prcsValiable.getDataType().getSelected();
				
				RoleTreeNode node = new RoleTreeNode();
				node.setId(typeIdAttr);
				node.setName(nameAttr);
				node.setParentId("valiables");
				node.setType(TreeNode.TYPE_FILE_HTML);
				node.setLoaded(true);
				node.setExpanded(true);
				valiableNode.add(node);
				// TODO 처음에 로딩할 필요가 없다면 아래 루프 부분은 클릭시 작동하는걸로 뺀다. 
				// RoleTreeNode 를 따로 만들어 주어야 한다.
				if( "complexType".equals(typeAttr)){
					WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( typeIdAttr.substring(0, typeIdAttr.lastIndexOf(".")).replaceAll("/", ".") ); 
					WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
					FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
					for(int j=0; j<fields.length; j++){
						WebFieldDescriptor wfd = wfields[j];
//						FieldDescriptor fd = fields[i];
						RoleTreeNode childNode = new RoleTreeNode();
						childNode.setId(typeIdAttr + "." + wfd.getName());
						childNode.setName(wfd.getName());
						childNode.setParentId(typeIdAttr);
						childNode.setType(TreeNode.TYPE_FILE_TEXT);
						node.add(childNode);
					}
				}
			}
		}
		if( this.getRoleList() != null){
			RoleTreeNode roleNode = new RoleTreeNode();
			roleNode.setId("roles");
			roleNode.setName("Roles");
			roleNode.setParentId("root");
			roleNode.setType(TreeNode.TYPE_FOLDER);
			rootNode.add(roleNode);
			rootNode.setLoaded(true);
			rootNode.setExpanded(true);
			for(int i = 0; i < roleList.size(); i++){
				Role role = roleList.get(i);
				RoleTreeNode node = new RoleTreeNode();
				node.setId(role.name);
				node.setName(role.name);
				node.setParentId("roles");
				node.setType(TreeNode.TYPE_FILE_HTML);
				roleNode.add(node);
			}
		}
		
		Tree tree = new Tree();
		tree.setNode(rootNode);
		
		this.setLeftTree(tree);
		this.setRightTree(tree);
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] doSaveMapper() throws Exception{
		GeomShape geomShape = new GeomShape();
		geomShape.setId(elementId);
		geomShape.setData(mapperData);
//		System.out.println(mapperData);
		return new Object[]{ new Remover(new ModalWindow()), geomShape};
	}
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
}
