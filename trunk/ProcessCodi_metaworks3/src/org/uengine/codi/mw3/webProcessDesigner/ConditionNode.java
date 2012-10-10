package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class ConditionNode  implements ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

	String conditionName;
		public String getConditionName() {
			return conditionName;
		}
		public void setConditionName(String conditionName) {
			this.conditionName = conditionName;
		}
	
	String conditionType;	// and, or, expresion ..
		public String getConditionType() {
			return conditionType;
		}
		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}
	String nameNext;
		public String getNameNext() {
			return nameNext;
		}
		public void setNameNext(String nameNext) {
			this.nameNext = nameNext;
		}
		
	String typeNext;
	
		public String getTypeNext() {
			return typeNext;
		}
		public void setTypeNext(String typeNext) {
			this.typeNext = typeNext;
		}

		
	Condition conditionObj;
		public Condition getConditionObj() {
			return conditionObj;
		}
		public void setConditionObj(Condition conditionObj) {
			this.conditionObj = conditionObj;
		}

	
	private ArrayList<ConditionNode> childNode;
		public ArrayList<ConditionNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<ConditionNode> childNode) {
			this.childNode = childNode;
		}
		
	public ConditionNode(){
		setChildNode( new ArrayList<ConditionNode>() );
		metaworksContext = new MetaworksContext();
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void addChildNode(ConditionNode ConditionNode){
		getChildNode().add(ConditionNode);
	}
	
	@AutowiredFromClient
	public Session session;
	
//	@ServiceMethod(callByContent=true, mouseBinding="drag")
//	public Session drag() {
//		session.setClipboard(this);
//		return session;
//	}
	
	@ServiceMethod(callByContent=true)
	public Object[] addChildNode() throws Exception{
		// 현재 노드 찾기..
		// 아이디만 가지고... parent 와 현재 노드가 어디 위치인지 어떻게 찾지????? xml 으로 생성이 되어야하나??
		
		// expression 이 아니면 하위노드로 만들어 주고, 맞다면 parent를 찾은 후에  
		if( typeNext != null){
			ConditionNode newNode = new ConditionNode();
			newNode.setConditionType(getTypeNext());
			newNode.setConditionName(getNameNext());
			newNode.setParentId(getParentId());
			Condition condition = null;
			if( typeNext.equalsIgnoreCase("Node")){
			}else if( typeNext.equalsIgnoreCase("And")){
				condition = new And();
				condition.setMetaworksContext(new MetaworksContext());
				condition.getMetaworksContext().setHow("and");
				condition.getMetaworksContext().setWhen("expression");
			}else if( typeNext.equalsIgnoreCase("Or")){ 
				condition = new Or();
				condition.setMetaworksContext(new MetaworksContext());
				condition.getMetaworksContext().setHow("or");
				condition.getMetaworksContext().setWhen("expression");
			}else if( typeNext.equalsIgnoreCase("Eval")){
				condition = new Evaluate();
				condition.setMetaworksContext(new MetaworksContext());
				condition.getMetaworksContext().setHow("eval");
				condition.getMetaworksContext().setWhen("expression");
			}
			newNode.setConditionObj(condition);
			
			addChildNode(newNode);
		}
		return new Object[]{this};
	}
	@ServiceMethod(callByContent=true, mouseBinding="drop")
	public Object[] drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ConditionNode){
			
			System.out.println("ConditionNode");
		}else if(clipboard instanceof And){
			System.out.println("and");
		}else if(clipboard instanceof Or){
			System.out.println("or");
			
		}
		return null;
	}
	
	public ConditionNode getNode(String findId){
		
		ConditionNode resultNode = null;
		
		if(getId().equals(findId)){
			resultNode = this;
		}else{
			for(int i =0; i<getChildNode().size(); i++){
				resultNode = getChildNode().get(i).getNode(findId);
				
				if(resultNode != null)
					break;				
			}
		}
		return resultNode;			
	}
	
	@AutowiredFromClient
	public ConditionPanel conditionPanel;
}
