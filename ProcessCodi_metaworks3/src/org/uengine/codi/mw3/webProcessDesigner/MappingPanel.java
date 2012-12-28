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
import org.uengine.kernel.ParameterContext;

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
		
	ParameterContext[] mappingElements;
		public ParameterContext[] getMappingElements() {
			return mappingElements;
		}
		public void setMappingElements(ParameterContext[] mappingElements) {
			this.mappingElements = mappingElements;
		}
		
	public MappingPanel() throws Exception{
		setMetaworksContext(new MetaworksContext());
	}
	public void load() throws Exception{
		loadTree();
	}
	public void loadTree() throws Exception{
		
		//Tree rightTree = new Tree();
		
		/*
		 * load left tree
		 */
		RoleTreeNode leftRoleNode = new RoleTreeNode();
		leftRoleNode.setId("leftRoles");
		leftRoleNode.setType(TreeNode.TYPE_FOLDER);
		leftRoleNode.load(this.getRoleList());

		VariableTreeNode leftVariableTreeNode = new VariableTreeNode();
		leftVariableTreeNode.setId("leftVariables");
		leftVariableTreeNode.setType(TreeNode.TYPE_FOLDER);
		leftVariableTreeNode.load(this.getPrcsValiableList());
		
		TreeNode leftRootnode = new TreeNode();
		leftRootnode.setRoot(true);
		leftRootnode.setId("leftRoot");
		leftRootnode.setName("left");
		leftRootnode.setType(TreeNode.TYPE_FOLDER);
		leftRootnode.setLoaded(true);
		leftRootnode.setExpanded(true);
		
		leftRootnode.add(leftRoleNode);
		leftRootnode.add(leftVariableTreeNode);
		
		
		Tree leftTree = new Tree();
		leftTree.setNode(leftRootnode);
		
		this.setLeftTree(leftTree);
		
		
		/*
		 * load right tree
		 */
		RoleTreeNode rightRoleNode = new RoleTreeNode();
		rightRoleNode.setId("rightRoles");
		rightRoleNode.setType(TreeNode.TYPE_FOLDER);
		rightRoleNode.load(this.getRoleList());

		VariableTreeNode rightVariableTreeNode = new VariableTreeNode();
		rightVariableTreeNode.setId("rightVariables");
		rightVariableTreeNode.setType(TreeNode.TYPE_FOLDER);
		rightVariableTreeNode.load(this.getPrcsValiableList());
		
		TreeNode rightRootnode = new TreeNode();
		rightRootnode.setRoot(true);
		rightRootnode.setId("rightRoot");
		rightRootnode.setName("right");
		rightRootnode.setType(TreeNode.TYPE_FOLDER);
		rightRootnode.setLoaded(true);
		rightRootnode.setExpanded(true);
		
		rightRootnode.add(rightRoleNode);
		rightRootnode.add(rightVariableTreeNode);
		
		Tree rightTree = new Tree();
		rightTree.setNode(rightRootnode);
		
		this.setRightTree(rightTree);
		
		
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
