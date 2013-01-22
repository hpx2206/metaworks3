package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.And;
import org.uengine.kernel.Condition;
import org.uengine.kernel.Or;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.RoleExist;

public class ConditionPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String conditionLabel;
		public String getConditionLabel() {
			return conditionLabel;
		}
		public void setConditionLabel(String conditionLabel) {
			this.conditionLabel = conditionLabel;
		}
		
	String conditionId;
		public String getConditionId() {
			return conditionId;
		}
		public void setConditionId(String conditionId) {
			this.conditionId = conditionId;
		}

//	public ArrayList<ConditionNode>	 conditionNodes;
//		public ArrayList<ConditionNode> getConditionNodes() {
//			return conditionNodes;
//		}
//		public void setConditionNodes(ArrayList<ConditionNode> conditionNodes) {
//			this.conditionNodes = conditionNodes;
//		}
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
		
//	String dragClassName;
//		public String getDragClassName() {
//			return dragClassName;
//		}
//		public void setDragClassName(String dragClassName) {
//			this.dragClassName = dragClassName;
//		}
//		
//	String valiableString;
//		public String getValiableString() {
//			return valiableString;
//		}
//		public void setValiableString(String valiableString) {
//			this.valiableString = valiableString;
//		}	
	String conditionString;
		public String getConditionString() {
			return conditionString;
		}
		public void setConditionString(String conditionString) {
			this.conditionString = conditionString;
		}
	ConditionTree conditionTree;	
		public ConditionTree getConditionTree() {
			return conditionTree;
		}
		public void setConditionTree(ConditionTree conditionTree) {
			this.conditionTree = conditionTree;
		}
	ConditionExPressionPanel conditionExPressionPanel;
		public ConditionExPressionPanel getConditionExPressionPanel() {
			return conditionExPressionPanel;
		}
		public void setConditionExPressionPanel(
				ConditionExPressionPanel conditionExPressionPanel) {
			this.conditionExPressionPanel = conditionExPressionPanel;
		}
	Condition condition;	
		public Condition getCondition() {
			return condition;
		}
		public void setCondition(Condition condition) {
			this.condition = condition;
		}
	public ConditionPanel() throws Exception{
			this("");
	}
	public ConditionPanel(String conditionLabel) throws Exception{
		setConditionLabel(conditionLabel);
	}
	public void load()  throws Exception{
		conditionTree = new ConditionTree();
		conditionTree.setId("tree");
		
		ConditionTreeNode treeNode = new ConditionTreeNode();
		treeNode.setLoaded(true);
		treeNode.setFolder(true);
		treeNode.setRoot(true);
		treeNode.setExpanded(true);
		treeNode.setId("rootNode");
		treeNode.setName("만족조건");
		if( condition != null ){
			makeChildTreeNode(treeNode , condition);
		}
		conditionTree.setNode(treeNode);
			
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.setRoleList(roleList);
		conditionExPressionPanel.setPrcsValiableList(prcsValiableList);
		conditionExPressionPanel.init();
		setConditionExPressionPanel(conditionExPressionPanel);
		
		/*
		conditionNodes = new ArrayList<ConditionNode>();
		if( conditionString == null ){
			ConditionNode conditionNode = new ConditionNode();
			int index = 1;
			conditionNode.setIdx(index);
			conditionNode.setRoleList(getRoleList());
			conditionNode.setPrcsValiableList(getPrcsValiableList());
			conditionNode.init();
			conditionNodes.add(index - 1, conditionNode);
		}else{
			JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(conditionString);
			if( jsonArray != null && jsonArray.size() > 0){
				for( int i = 0; i < jsonArray.size() ; i++){
					JSONObject jsonObj = (JSONObject) jsonArray.get(i);
					ConditionNode conditionNode = new ConditionNode();
					int index = jsonObj.getInt("index");
					conditionNode.setIdx(index);
					conditionNode.setRoleList(getRoleList());
					conditionNode.setPrcsValiableList(getPrcsValiableList());
					conditionNode.init();
					conditionNode.getValiableChoice().setSelected(jsonObj.getString("valiableChoice"));
					conditionNode.getSignChoice().setSelected(jsonObj.getString("signChoice"));
					conditionNode.getOperandChoice().setSelected(jsonObj.getString("operandChoice"));
					conditionNode.getExpressionChoice().setSelected(jsonObj.getString("expressionChoice"));
					if( jsonObj.containsKey("expressionText") ){
						conditionNode.setExpressionText(jsonObj.getString("expressionText"));
					}
					conditionNodes.add(index - 1, conditionNode);
				}
			}
		}
		*/
		
	}
	
	public void makeChildTreeNode( ConditionTreeNode rootNode , Condition condition ) throws Exception{
		ConditionTreeNode treeNode = new ConditionTreeNode();
		treeNode.setParentId( rootNode.getId() );
		treeNode.setType(TreeNode.TYPE_FILE_CODE);
		
		treeNode.setRoleList(roleList);
		treeNode.setPrcsValiableList(prcsValiableList);
		treeNode.conditionInit();
		String nodeName = "";
		if( condition instanceof Or ){
			nodeName = "Or";
			treeNode.setExpressionType("expression");
			treeNode.getConditionNode().getOperandChoice().setSelected("Or");
		}else if( condition instanceof And ){
			nodeName = "And";
			treeNode.setExpressionType("expression");
			treeNode.getConditionNode().getOperandChoice().setSelected("And");
		}else if( condition instanceof RoleExist ){
			nodeName = "roleExist";
			treeNode.setExpressionType("roleExist");
		}else if( condition instanceof Otherwise ){
			nodeName = "otherwise";
			treeNode.setExpressionType("otherwise");
		}
		treeNode.setName(nodeName);
		rootNode.add(treeNode);
		// 자식을 가지고 있을 경우 재귀 호출
		if( condition instanceof Or || condition instanceof And){
			Condition[] condis = ((And)condition).getConditions();
			if( condis != null){
				for( int i=0; i< condis.length; i++){
					Condition condi = condis[i];
					makeChildTreeNode(rootNode, condi);
				}
			}
		}
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveCondition() throws Exception{
		LineShape lineShape = new LineShape();
		lineShape.setId(this.getConditionId());
		lineShape.setLabel(this.getConditionLabel());
		
		ConditionTreeNode rootNode = conditionTree.getNode();
		Condition condition = lineShape.makeCondition(rootNode);
//		lineShape.setLineCondition(condition);
		processDesignerWebContentPanel.getConditionMap().put(this.getConditionId(), condition);
		
//		if( conditionNodes != null && conditionNodes.size() > 0){
//			JSONArray jsonArray = new JSONArray();
//			for (Iterator<ConditionNode> iterator = conditionNodes.iterator() ; iterator.hasNext(); ) {
//				JSONObject jsonObject = new JSONObject();
//				ConditionNode conditionNode = (ConditionNode)iterator.next();
//				jsonObject.put("index", conditionNode.getIdx() );
//				jsonObject.put("valiableChoice", conditionNode.getValiableChoice().getSelected());
//				jsonObject.put("signChoice", conditionNode.getSignChoice().getSelected());
//				jsonObject.put("operandChoice", conditionNode.getOperandChoice().getSelected());
//				jsonObject.put("expressionChoice", conditionNode.getExpressionChoice().getSelected());
//				jsonObject.put("expressionText", conditionNode.getExpressionText());
//				jsonArray.add(jsonObject);
//			}
//			lineShape.setCustomData(jsonArray.toString());
//		}
		
		return new Object[]{ new Remover(new ModalWindow()), lineShape};
	}
	
//	@ServiceMethod(callByContent=true)
//	public Object[] addConditionNode() throws Exception{
//		ConditionNode newNode = new ConditionNode();
//		int index = conditionNodes.size();
//		newNode.setIdx(index + 1);
//		newNode.setRoleList(getRoleList());
//		newNode.setPrcsValiableList(getPrcsValiableList());
//		newNode.init();
//		conditionNodes.add(index , newNode);
//		return new Object[]{conditionNodes};
//	}

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
}
