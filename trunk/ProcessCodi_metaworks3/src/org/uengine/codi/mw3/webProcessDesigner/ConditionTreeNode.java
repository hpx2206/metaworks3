package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Popup;

@Face(
	ejsPath="dwr/metaworks/org/metaworks/component/TreeNode.ejs"
)
public class ConditionTreeNode  implements ContextAware{
	
	public final static String CONDITION_AND				= "And";
	public final static String CONDITION_OR				= "Or";
	public final static String CONDITION_OTHERWISE	= "Otherwise";
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}

	boolean root;
		public boolean isRoot() {
			return root;
		}
		public void setRoot(boolean root) {
			this.root = root;
		}
		
	boolean expanded;
		public boolean isExpanded() {
			return expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}

	boolean loaded;
		public boolean isLoaded() {
			return loaded;
		}
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	boolean folder;
		public boolean isFolder() {
			return folder;
		}
		public void setFolder(boolean folder) {
			this.folder = folder;
		}
		
	ConditionTreeNode parentNode;
		public ConditionTreeNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(ConditionTreeNode parentNode) {
			this.parentNode = parentNode;
		}
		
	ArrayList<ConditionTreeNode> child;
		public ArrayList<ConditionTreeNode> getChild() {
			return child;
		}
		public void setChild(ArrayList<ConditionTreeNode> child) {
			this.child = child;
		}
		
	public ConditionTreeNode() {
		this.setMetaworksContext(new MetaworksContext());
		ArrayList<ConditionTreeNode> child = new ArrayList<ConditionTreeNode>();
		
		this.setChild(child);
	}
	public void add(ConditionTreeNode node) {
		node.setParentId(this.getId());
		this.getChild().add(node);
	}
	
	String expression;
		public String getExpression() {
			return expression;
		}
		public void setExpression(String expression) {
			this.expression = expression;
		}

	ConditionNode conditionNode;
		public ConditionNode getConditionNode() {
			return conditionNode;
		}
		public void setConditionNode(ConditionNode conditionNode) {
			this.conditionNode = conditionNode;
		}
	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	public ArrayList<PrcsVariable>	 prcsValiableList;
		public ArrayList<PrcsVariable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsVariable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}
		
	public void conditionInit() throws Exception{
		ConditionNode conditionNode = new ConditionNode();
		conditionNode.setRoleList(getRoleList());
		conditionNode.setPrcsValiableList(getPrcsValiableList());
		conditionNode.init();
		
		setConditionNode(conditionNode);
	}
	
	public ConditionTreeNodeView makeNodeView(ConditionTreeNode treeNode) throws Exception{
		ConditionTreeNodeView conditionTreeNode = new ConditionTreeNodeView();
		conditionTreeNode.setId("expression");
		conditionTreeNode.setRoleList(getRoleList());
		conditionTreeNode.setPrcsValiableList(getPrcsValiableList());
		conditionTreeNode.conditionInit();
		conditionTreeNode.getConditionNode().getMetaworksContext().setHow("new");
		
		conditionTreeNode.setParentNode(treeNode);
		conditionTreeNode.getConditionNode().setParentTreeNode(treeNode);
		
		return conditionTreeNode;
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true)
	@Available(how="tree")
	public Object newAND() throws Exception{
		ConditionTreeNodeView conditionTreeNode = this.makeNodeView(this);
		conditionTreeNode.getConditionNode().setConditionType(CONDITION_AND);
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.setConditionTreeNode(conditionTreeNode);
		return conditionExPressionPanel;
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true)
	@Available(how="tree")
	public Object newOR() throws Exception{
		ConditionTreeNodeView conditionTreeNode = this.makeNodeView(this);
		conditionTreeNode.getConditionNode().setConditionType(CONDITION_OR);
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.setConditionTreeNode(conditionTreeNode);
		return conditionExPressionPanel;
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target=ServiceMethodContext.TARGET_AUTO)
	@Available(how="tree")
	public Object newOtherwise() throws Exception{
		if( conditionTree != null ){
			ConditionTreeNode parentNode = conditionTree.getNode();
			
			ConditionTreeNode node = new ConditionTreeNode();
			Long idByTime = new Date().getTime();
			node.setId(idByTime.toString());
			node.setParentNode(this);
			node.setParentId(this.getId());
			node.getMetaworksContext().setHow("otherwise");
			node.setConditionNode(new ConditionNode());
	//		node.setPrcsValiableList(this.getPrcsValiableList());
	//		node.setRoleList(this.getRoleList());
			node.setName(CONDITION_OTHERWISE);
			node.setType("page_white_text");	// TODO 아이콘 관련이기때문에.. 추후 변경
			
			parentNode.add(node);
		}
		
		return new Refresh( conditionTree);
	}
	
	@ServiceMethod(inContextMenu=true, payload={"id","parentNode"})
	@Available(how="tree")
	public Object delete() throws Exception{
		
		ConditionTreeNode parentNode = this.getParentNode();
		if( parentNode != null && parentNode.getChild() != null ){
			for(int i =0; i<parentNode.getChild().size(); i++){
				if( parentNode.getChild().get(i).getId().equals(this.getId())){
					parentNode.getChild().remove(i);
				}
			}
		}
			
		return new Refresh(parentNode);
	}
//	public ConditionTreeNode findNode(ConditionTreeNode node , String nodeId) throws Exception{
//		ConditionTreeNode resultNode = null;
//		if( node.getId().equals(nodeId)){
//			return node;
//		}else{
//			for(int i =0; i<node.getChild().size(); i++){
//				resultNode = node.getChild().get(i).findNode(node.getChild().get(i), nodeId);
//				
//				if(resultNode != null)
//					break;				
//			}
//			return resultNode;
//		}
//	}
	
	@ServiceMethod(callByContent = true , target=ServiceMethodContext.TARGET_AUTO)
	public Object action() throws Exception {
		
		ConditionTreeNodeView conditionTreeNode = new ConditionTreeNodeView();
		conditionTreeNode.setId("expression");
		conditionTreeNode.setRoleList(getRoleList());
		conditionTreeNode.setPrcsValiableList(getPrcsValiableList());
		conditionTreeNode.setConditionNode(getConditionNode());
		conditionTreeNode.getConditionNode().getMetaworksContext().setHow("edit");
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.setConditionTreeNode(conditionTreeNode);
		
		return conditionExPressionPanel;
	}
	
	@AutowiredFromClient
	public ConditionTree conditionTree;
	
}
