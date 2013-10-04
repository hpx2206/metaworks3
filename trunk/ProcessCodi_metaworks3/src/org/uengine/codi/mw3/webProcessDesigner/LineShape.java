package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.uengine.contexts.TextContext;
import org.uengine.kernel.And;
import org.uengine.kernel.Condition;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Or;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.RoleExist;

public class LineShape extends CanvasDTO {
	
	public LineShape(){
		this.shapeType = "EDGE";
	}
	
	public LineShape(CanvasDTO cv){
		this.shapeId = cv.shapeId;
		this.shapeType = "EDGE";
		this.data = cv.data;
	}
		
	String customData;
		public String getCustomData() {
			return customData;
		}
		public void setCustomData(String customData) {
			this.customData = customData;
		}
	Condition lineCondition;
		public Condition getLineCondition() {
			return lineCondition;
		}
	
		public void setLineCondition(Condition lineCondition) {
			this.lineCondition = lineCondition;
		}
	
	public Condition makeCondition(ConditionTreeNode rootNode){
		if( rootNode != null){
			ArrayList<ConditionTreeNode> child = rootNode.getChild();
			Or condition = new Or();
			for( int i=0; i < child.size(); i++){
				ConditionTreeNode childNode = child.get(i);
				ConditionNode conditionNode = childNode.getConditionNode();
				String conditionType = conditionNode.getConditionType();
				TextContext condiName = new TextContext();
				condiName.setText(childNode.getName());
				if( conditionType != null && ( conditionType.equals(ConditionTreeNode.CONDITION_AND) || conditionType.equals(ConditionTreeNode.CONDITION_OR) )){
					
					String expressionChoice 	= childNode.getConditionNode().getExpressionChoice().getSelected();	// Text, Number
					String sign = childNode.getConditionNode().getSignChoice().getSelected();								// == , =>
					String valiable = childNode.getConditionNode().getValiableChoice().getSelected();					// processValiable
					
					ConditionInput expressionInput 	= childNode.getConditionNode().getConditionInput();			// Text , Number , Date ..
					
					Object exppObject = new Object();
					if( expressionChoice.equalsIgnoreCase("Text") ){
						exppObject = expressionInput.getExpressionText();
					}else if( expressionChoice.equalsIgnoreCase("Number")){
						exppObject = expressionInput.getExpressionText();
					}else if( expressionChoice.equalsIgnoreCase("Yes or No")){
						exppObject = expressionInput.getYesNo();
					}else if( expressionChoice.equalsIgnoreCase("Date")){
						// TODO 컨버팅 에러로 인하여 잠시 보류
//						exppObject = expressionInput.getExpressionDate();
//					}else if( expressionChoice.equals("File")){
					}else if( expressionChoice.equals("variable")){
						exppObject = expressionInput.getValiableChoice().getSelected();
//					}else if( expressionChoice.equals("Activity Selection")){
//					}else if( expressionChoice.equals("Complex Type")){
//					}else if( expressionChoice.equals("Html Form")){
					}
					
					if( conditionType != null && conditionType.equals(ConditionTreeNode.CONDITION_OR)){
						Or orCondition = new Or();
						Evaluate eval = new Evaluate(valiable, sign, exppObject);
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
						condition.addCondition(orCondition);
					}else if( conditionType != null && conditionType.equals(ConditionTreeNode.CONDITION_AND)){
						Evaluate eval = new Evaluate(valiable, sign, exppObject);
						And andCondition = new And(new Condition[]{eval});
						// 자식이 또 있는 경우 재귀호출
						if( childNode.getChild() != null &&  childNode.getChild().size() > 0){
							Condition childcond = makeCondition(childNode);
							if( childcond != null ){
								andCondition.addCondition(childcond);
							}
						}
						andCondition.setDescription(condiName);
						condition.addCondition(andCondition);
					}
				}else if( conditionType != null && conditionType.equals(ConditionTreeNode.CONDITION_OTHERWISE) ){
					Otherwise otherwise = new Otherwise();
					otherwise.setDescription(condiName);
					condition.addCondition(otherwise);
				}
				
			}
			return condition;
		}
		return null;
	}
	
}