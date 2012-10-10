package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

public class ConditionPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	public ConditionPanel(){
		this("");
	}
	public ConditionPanel(String conditionLabel){
		and = new And();
		or = new Or();
		evaluate = new Evaluate();
		
		conditionNode =  new ConditionNode();
		conditionNode.setConditionType("Root");
		conditionNode.setConditionName("Or");
		
		ConditionNode conditionNode1 =  new ConditionNode();
		conditionNode1.setConditionName("And");
		ConditionNode conditionNode2 =  new ConditionNode();
		conditionNode2.setConditionName("aaaaaaa");
		conditionNode.addChildNode(conditionNode1);
		conditionNode.addChildNode(conditionNode2);
		
		setConditionLabel(conditionLabel);
	}
	
	String conditionLabel;
		public String getConditionLabel() {
			return conditionLabel;
		}
		public void setConditionLabel(String conditionLabel) {
			this.conditionLabel = conditionLabel;
		}
	And and;
		public And getAnd() {
			return and;
		}
		public void setAnd(And and) {
			this.and = and;
		}
	Or or;
		public Or getOr() {
			return or;
		}
		public void setOr(Or or) {
			this.or = or;
		}
	Evaluate evaluate;
		public Evaluate getEvaluate() {
			return evaluate;
		}
		public void setEvaluate(Evaluate evaluate) {
			this.evaluate = evaluate;
		}

	
	private ConditionNode conditionNode;
		public ConditionNode getConditionNode() {
			return conditionNode;
		}
		public void setConditionNode(ConditionNode conditionNode) {
			this.conditionNode = conditionNode;
		}
		
	String dragClassName;
		public String getDragClassName() {
			return dragClassName;
		}
		public void setDragClassName(String dragClassName) {
			this.dragClassName = dragClassName;
		}
		
	@ServiceMethod(callByContent=true)
	public Object[] saveCondition() throws Exception{
		System.out.println("conditionLabel = " + conditionLabel);
		return new Object[]{ new Remover(new ModalWindow())};
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] drawCondition() throws Exception{
		if(dragClassName != null && dragClassName.equalsIgnoreCase("And")){
			And and = new And();
		}else if(dragClassName != null && dragClassName.equalsIgnoreCase("Or")){
			System.out.println( "  =========  여기로 옴 ======" );
			Or or = new Or();
			ConditionNode orCondition =  new ConditionNode();
			orCondition.setConditionType("Or");
			orCondition.setConditionName("Or");
			conditionNode.addChildNode(orCondition);
		}
		return new Object[]{this};
	}
	
	

	

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
}
