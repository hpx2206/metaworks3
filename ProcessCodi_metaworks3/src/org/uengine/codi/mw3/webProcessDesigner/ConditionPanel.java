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
		treeNode.getMetaworksContext().setHow("tree");
		treeNode.setLoaded(true);
		treeNode.setFolder(true);
		treeNode.setRoot(true);
		treeNode.setExpanded(true);
		treeNode.setId("rootNode");
		treeNode.setName("만족조건");
		treeNode.setRoleList(roleList);
		treeNode.setPrcsValiableList(prcsValiableList);
		treeNode.setParentNode(treeNode);
		
		if( condition != null ){
			makeChildTreeNode(treeNode , condition); 
		}
		conditionTree.setNode(treeNode);
			
		ConditionExPressionPanel conditionExPressionPanel = new ConditionExPressionPanel();
		conditionExPressionPanel.getConditionTreeNode().getConditionNode().getMetaworksContext().setWhen("view");
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
				Long idByTime = new Date().getTime();
				treeNode.setId(idByTime.toString());
				treeNode.setParentNode(rootNode);
				treeNode.setParentId( rootNode.getId() );
				treeNode.setRoleList(roleList);
				treeNode.setPrcsValiableList(prcsValiableList);
				treeNode.conditionInit();
				
				ConditionNode conditionNode = makeConditionNode(treeNode , condi);
				
				String nodeName = condi.getDescription() != null ? condi.getDescription().getText() : "";
				String nodeType = "";
				if( condi instanceof Or ){
					nodeType = ConditionTreeNode.CONDITION_OR;
				}else if( condi instanceof And ){
					nodeType = ConditionTreeNode.CONDITION_AND;
				}else if( condi instanceof Otherwise ){
					nodeType = ConditionTreeNode.CONDITION_OTHERWISE;
				}
				conditionNode.setConditionType(nodeType);
				treeNode.setType("page_white_text");
//				treeNode.setType(nodeType);
				treeNode.setName(nodeName);
				conditionNode.setParentTreeNode(treeNode);
				treeNode.setConditionNode(conditionNode);
				rootNode.add(treeNode);
			}
		}
	}
	
	public ConditionNode makeConditionNode(ConditionTreeNode treeNode , Condition condition ) throws Exception{
		ConditionNode conditionNode = treeNode.getConditionNode();
		// and 와 or 의 공통 로직 처리
		if( condition instanceof Or || condition instanceof And){
			Condition childCondition[] = ((And)condition).getConditions();
			if( childCondition != null && childCondition.length > 0){
				for(int j = 0; j < childCondition.length; j++){
					Condition cd = childCondition[j];
					if( cd instanceof Evaluate){
						Evaluate eval = (Evaluate)cd;
						
						conditionNode.getValiableChoice().setSelected(eval.getKey());
						conditionNode.getSignChoice().setSelected(eval.getCondition());
						Object value = eval.getValue();
						if( value instanceof String){
							String expString = (String)value;
							if( "yes".equalsIgnoreCase(expString) || "no".equalsIgnoreCase(expString) ){
								conditionNode.getExpressionChoice().setSelected("Yes or No");
								conditionNode.getConditionInput().getMetaworksContext().setHow("Yes or No");
								conditionNode.getConditionInput().setYesNo(expString);
							}else{
								conditionNode.getExpressionChoice().setSelected("text");
								conditionNode.getConditionInput().getMetaworksContext().setHow("text");
								conditionNode.getConditionInput().setExpressionText(expString);
							}
						}else if( value instanceof Long){
							conditionNode.getExpressionChoice().setSelected("number");
							conditionNode.getConditionInput().getMetaworksContext().setHow("number");
							conditionNode.getConditionInput().setExpressionText(value.toString());
						}else if( value instanceof Date){
							conditionNode.getExpressionChoice().setSelected("date");
							conditionNode.getConditionInput().getMetaworksContext().setHow("date");
							conditionNode.getConditionInput().setExpressionDate((Date)value);
						}else{
							conditionNode.getExpressionChoice().setSelected("null");
							conditionNode.getConditionInput().getMetaworksContext().setHow("null");
						}	
					}else if( cd instanceof Or){
						makeChildTreeNode(treeNode , cd);
					}else if( cd instanceof And){
						conditionNode = makeConditionNode(treeNode , cd);
					}
				}
			}
		}
		
		return conditionNode;
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
