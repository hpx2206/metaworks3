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

@Face(
	ejsPath="dwr/metaworks/org/metaworks/component/TreeNode.ejs",
	ejsPathForArray="genericfaces/CleanArrayFace.ejs",
		ejsPathMappingByContext=
	{
		"{how: 'condition', face: 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/ConditionTreeNodeView.ejs'}",
	}		
)
public class ConditionTreeNode  implements ContextAware{
	
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

	ArrayList<ConditionTreeNode> child;
		public ArrayList<ConditionTreeNode> getChild() {
			return child;
		}
		public void setChild(ArrayList<ConditionTreeNode> child) {
			this.child = child;
		}
		
	public ConditionTreeNode() {
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
	String expressionType;
		public String getExpressionType() {
			return expressionType;
		}
		public void setExpressionType(String expressionType) {
			this.expressionType = expressionType;
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
	public ArrayList<PrcsValiable>	 prcsValiableList;
		public ArrayList<PrcsValiable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsValiable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}
		
	public void conditionInit() throws Exception{
		
		ConditionNode conditionNode = new ConditionNode();
		conditionNode.setRoleList(getRoleList());
		conditionNode.setPrcsValiableList(getPrcsValiableList());
		conditionNode.init();
		
		setConditionNode(conditionNode);
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_AUTO)
	public Object[] addCondition() throws Exception{
		this.getMetaworksContext().setHow("");
		String nodeName = "";
		this.getConditionNode().getExpressionChoice().getSelected();
		if( expressionType != null && expressionType.equals("expression") ){
			nodeName = this.getConditionNode().getOperandChoice().getSelected();
		}else if( expressionType != null && expressionType.equals("roleExist") ){
			nodeName = "roleExist";
		}else if( expressionType != null && expressionType.equals("otherwise") ){
			nodeName = "otherwise";
		}else{
			nodeName = "오류";
		}
		this.setName(nodeName);
		Long idByTime = new Date().getTime();
		this.setId(idByTime.toString());
		
		ArrayList<ConditionTreeNode> rootTreechilds = conditionTree.getNode().getChild();
		rootTreechilds.add(this);
		//conditionPanel.getConditionTree().getNode().setChild(rootTreechilds);
		return new Object[]{conditionTree};
	}
	
	@ServiceMethod(callByContent = true , target=ServiceMethodContext.TARGET_AUTO)
	public Object action() throws Exception {
		this.getMetaworksContext().setHow("condition");
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.setConditionTreeNode(this);
		return conditionExPressionPanel;
	}
	
	@AutowiredFromClient
	public ConditionTree conditionTree;
}
