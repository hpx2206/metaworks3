package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Date;
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
import org.uengine.kernel.Evaluate;
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
	public ArrayList<PrcsVariable>	 prcsValiableList;
		public ArrayList<PrcsVariable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsVariable> prcsValiableList) {
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
		
	}
	
	public void makeChildTreeNode( ConditionTreeNode rootNode , Condition condition ) throws Exception{
		// TODO 현재 tree 의 dapth 를 형성시키지 못하니 1depth로 loop를 돌린다 추후 뎁스가 있도록 변경 - 김형국
		// 처음 들어오는 condition 은 무조건 or 가 최상위임
		Condition[] condis = ((And)condition).getConditions();
		if( condis != null){
			for( int i=0; i< condis.length; i++){
				Condition condi = condis[i];
				ConditionTreeNode treeNode = new ConditionTreeNode();
				treeNode.setMetaworksContext(new MetaworksContext());
				treeNode.setParentId( rootNode.getId() );
				treeNode.setType(TreeNode.TYPE_FILE_CODE);
				
				treeNode.setRoleList(roleList);
				treeNode.setPrcsValiableList(prcsValiableList);
				treeNode.conditionInit();
				String nodeName = condi.getDescription() != null ? condi.getDescription().getText() : "";
				String nodeType = "";
				if( condi instanceof Or ){
					nodeType = "Or";
					treeNode.getConditionNode().getOperandChoice().setSelected("Or");
				}else if( condi instanceof And ){
					nodeType = "And";
					treeNode.getConditionNode().getOperandChoice().setSelected("And");
				}else if( condi instanceof RoleExist ){
					nodeType = "roleExist";
					treeNode.setExpressionType("roleExist");
				}else if( condi instanceof Otherwise ){
					nodeType = "otherwise";
					treeNode.setExpressionType("otherwise");
				}
				// and 와 or 의 공통 로직 처리
				if( condi instanceof Or || condi instanceof And){
					treeNode.setExpressionType("expression");
					if( !"".equals(nodeName) ){
						Condition childCondition[] = ((And)condi).getConditions();
						if( childCondition != null && childCondition.length > 0){
							for(int j = 0; j < childCondition.length; j++){
								Condition cd = childCondition[j];
								if( cd instanceof Evaluate){
									Evaluate eval = (Evaluate)cd;
									
									treeNode.getConditionNode().getValiableChoice().setSelected(eval.getKey());
									treeNode.getConditionNode().getSignChoice().setSelected(eval.getCondition());
									Object value = eval.getValue();
									if( value instanceof String){
										String expString = (String)value;
										if( "yes".equalsIgnoreCase(expString) || "no".equalsIgnoreCase(expString) ){
											treeNode.getConditionNode().getExpressionChoice().setSelected("Yes or No");
											treeNode.getConditionNode().getConditionInput().getMetaworksContext().setHow("Yes or No");
											treeNode.getConditionNode().getConditionInput().setYesNo(expString);
										}else{
											treeNode.getConditionNode().getExpressionChoice().setSelected("text");
											treeNode.getConditionNode().getConditionInput().getMetaworksContext().setHow("text");
											treeNode.getConditionNode().getConditionInput().setExpressionText(expString);
										}
									}else if( value instanceof Long){
										treeNode.getConditionNode().getExpressionChoice().setSelected("number");
										treeNode.getConditionNode().getConditionInput().getMetaworksContext().setHow("number");
										treeNode.getConditionNode().getConditionInput().setExpressionText(value.toString());
									}else if( value instanceof Date){
										treeNode.getConditionNode().getExpressionChoice().setSelected("date");
										treeNode.getConditionNode().getConditionInput().getMetaworksContext().setHow("date");
										treeNode.getConditionNode().getConditionInput().setExpressionDate((Date)value);
									}
								}
							}
						}
					}
				}
				
				treeNode.setType(nodeType);
				treeNode.setName(nodeName);
				rootNode.add(treeNode);
			}
		}
		
//		// 자식을 가지고 있을 경우 재귀 호출
//		if( condition instanceof Or || condition instanceof And){
//			Condition[] condis = ((And)condition).getConditions();
//			if( condis != null){
//				for( int i=0; i< condis.length; i++){
//					Condition condi = condis[i];
//					makeChildTreeNode(rootNode, condi);
//				}
//			}
//		}
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveCondition() throws Exception{
		LineShape lineShape = new LineShape();
		lineShape.setId(this.getConditionId());
		lineShape.setLabel(this.getConditionLabel());
		
		ConditionTreeNode rootNode = conditionTree.getNode();
		Condition condition = lineShape.makeCondition(rootNode);
		lineShape.setLineCondition(condition);
		
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
}
