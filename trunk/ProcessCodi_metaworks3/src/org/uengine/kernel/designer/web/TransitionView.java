package org.uengine.kernel.designer.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.ConditionInput;
import org.uengine.codi.mw3.webProcessDesigner.ConditionNode;
import org.uengine.codi.mw3.webProcessDesigner.ConditionPanel;
import org.uengine.codi.mw3.webProcessDesigner.ConditionTreeNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel;
import org.uengine.codi.mw3.webProcessDesigner.RolePanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.And;
import org.uengine.kernel.Condition;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Or;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.graph.Transition;

public class TransitionView extends CanvasDTO{
	transient Transition transition;
		public Transition getTransition() {
			return transition;
		}
		public void setTransition(Transition transition) {
			this.transition = transition;
		}
	boolean drawByCanvas;
		public boolean isDrawByCanvas() {
			return drawByCanvas;
		}
		public void setDrawByCanvas(boolean drawByCanvas) {
			this.drawByCanvas = drawByCanvas;
		}
		
	Object element;
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
	
	@AutowiredFromClient
	transient public RolePanel rolePanel;
	
	@AutowiredFromClient
	transient public ProcessVariablePanel processVariablePanel;
	
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow gateCondition() throws Exception{
		
		ArrayList<Role>	 roleList = rolePanel.getRoleList();
        ArrayList<ProcessVariable> variableList = processVariablePanel.getVariableList();
        
		ConditionPanel conditionPanel = new ConditionPanel();
		conditionPanel.setMetaworksContext(new MetaworksContext());
		conditionPanel.getMetaworksContext().setWhen("edit");
		
		conditionPanel.setTransition(transition);
		conditionPanel.setConditionId(this.getId());
		conditionPanel.setConditionLabel(transition.getTransitionName() == null ? "" : transition.getTransitionName() );
		conditionPanel.setRoleList(roleList);
		conditionPanel.setVariableList(variableList);
		conditionPanel.load();
		return new ModalWindow(conditionPanel , 800, 600,  "조건편집" );
	}
	
	public Condition makeCondition(ConditionTreeNode rootNode){
		if( rootNode != null){
			ArrayList<ConditionTreeNode> child = rootNode.getChild();
			Condition condition;
			if( rootNode.getConditionType() == null || "".equals(rootNode.getConditionType())){	// 최상위 root Node
				condition = new And();
				TextContext condiName = new TextContext();
				condiName.setText(ConditionTreeNode.CONDITION_AND);
				condition.setDescription(condiName);
			}else{
				String rootConditionType = rootNode.getConditionType();
				if( rootConditionType.equals(ConditionTreeNode.CONDITION_AND) ){
					condition = new And();
					TextContext condiName = new TextContext();
					condiName.setText(ConditionTreeNode.CONDITION_AND);
					condition.setDescription(condiName);
				}else{
					condition = new Or();
					TextContext condiName = new TextContext();
					condiName.setText(ConditionTreeNode.CONDITION_OR);
					condition.setDescription(condiName);
				}
			}
			for( int i=0; i < child.size(); i++){
				ConditionTreeNode childNode = child.get(i);
				String conditionType = childNode.getConditionType();
				TextContext condiName = new TextContext();
				condiName.setText(childNode.getName());
				if( childNode.isFolder() ){
					Condition childcond = makeCondition(childNode);
					((And)condition).addCondition(childcond);
				}else{
					ConditionNode conditionNode = childNode.getConditionNode();
					if( conditionType != null && conditionType.equals(ConditionTreeNode.CONDITION_OTHERWISE) ){
						condition = new Or();
						TextContext aa = new TextContext();
						condiName.setText(ConditionTreeNode.CONDITION_OR);
						condition.setDescription(aa);
						
						Otherwise otherwise = new Otherwise();
						otherwise.setDescription(condiName);
						((Or)condition).addCondition(otherwise);
					}else if( conditionNode != null && conditionNode.getConditionType().equals(ConditionTreeNode.CONDITION_AND) || conditionNode.getConditionType().equals(ConditionTreeNode.CONDITION_OR) ){
					
						String expressionChoice 	= conditionNode.getExpressionChoice().getSelected();	// Text, Number
						String sign = conditionNode.getSignChoice().getSelected();								// == , =>
						String valiable = conditionNode.getValiableChoice().getSelected();					// processValiable
						
						ConditionInput expressionInput 	= conditionNode.getConditionInput();			// Text , Number , Date ..
						
						Object exppObject = new Object();
						if( expressionChoice.equalsIgnoreCase("Text") ){
							exppObject = expressionInput.getExpressionText();
						}else if( expressionChoice.equalsIgnoreCase("Number")){
							exppObject = expressionInput.getExpressionText();
						}else if( expressionChoice.equalsIgnoreCase("Yes or No")){
							exppObject = expressionInput.getYesNo();
						}else if( expressionChoice.equalsIgnoreCase("Date")){
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							exppObject = df.format(expressionInput.getExpressionDate());
						}else if( expressionChoice.equals("variable")){
							exppObject = expressionInput.getValiableChoice().getSelected();
						}else{
							exppObject = "";
						}
						
						if( conditionNode.getConditionType().equals(ConditionTreeNode.CONDITION_OR)){
							Or orCondition = new Or();
							Evaluate eval = new Evaluate(valiable, sign, exppObject);
							eval.setType(expressionChoice);
							And andCondition = new And(new Condition[]{eval});
							// 자식이 또 있는 경우 재귀호출
							if( childNode.getChild() != null &&  childNode.getChild().size() > 0){
								Condition childcond = makeCondition(childNode);
								if( childcond != null ){
									andCondition.addCondition(childcond);
								}
							}
							orCondition.setDescription(condiName);
							orCondition.addCondition(andCondition);
							((Or)condition).addCondition(orCondition);
						}else if( conditionNode.getConditionType().equals(ConditionTreeNode.CONDITION_AND)){
							Evaluate eval = new Evaluate(valiable, sign, exppObject);
							eval.setType(expressionChoice);
							And andCondition = new And(new Condition[]{eval});
							// 자식이 또 있는 경우 재귀호출
							if( childNode.getChild() != null &&  childNode.getChild().size() > 0){
								Condition childcond = makeCondition(childNode);
								if( childcond != null ){
									andCondition.addCondition(childcond);
								}
							}
							andCondition.setDescription(condiName);
							((And)condition).addCondition(andCondition);
						}
					}
				}
			}
			return condition;
		}
		return null;
	}
}
