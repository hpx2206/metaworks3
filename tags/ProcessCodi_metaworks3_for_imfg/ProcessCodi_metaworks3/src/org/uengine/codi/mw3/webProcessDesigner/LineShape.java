package org.uengine.codi.mw3.webProcessDesigner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.uengine.kernel.And;
import org.uengine.kernel.Condition;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Or;

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
		public Condition makeCondition(){
			
			if(this.getData() != null){
				Or condition = new Or();
				String data = ProcessDesignerWebContentPanel.unescape(this.getData());
				JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data);
				if( jsonArray != null && jsonArray.size() > 0){
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String operand = jsonObject.getString("operandChoice");					// And, Or
						String expressionType 	= jsonObject.getString("expressionChoice");	// Text, Number
						String expressionText 	= jsonObject.getString("expressionText");		// Text Val , Number Val
						String sign = jsonObject.getString("signChoice");								// == , =>
						String valiable = jsonObject.getString("valiableChoice");					// processValiable
						
						Object exppObject = new Object();
						if( expressionType.equals("Text") || expressionType.equals("Yes or No")){
							exppObject = expressionText;
						}else if( expressionType.equals("Number")){
							exppObject = new Integer(expressionText);
						}else if( expressionType.equals("Date")){
						}else if( expressionType.equals("File")){
						}else if( expressionType.equals("Activity Selection")){
						}else if( expressionType.equals("Complex Type")){
						}else if( expressionType.equals("Html Form")){
						}
						
						if( operand.equals("And")){
							Evaluate eval = new Evaluate(valiable, sign, exppObject);
							And andCondition = new And(new Condition[]{eval});
							condition.addCondition(andCondition);
						}else if( operand.equals("Or")){
							Or orCondition = new Or();
							Evaluate eval = new Evaluate(valiable, sign, exppObject);
							And andCondition = new And(new Condition[]{eval});
							orCondition.addCondition(andCondition);
							condition.addCondition(orCondition);
						}else{	//Otherwise
							
						}
					}
				}
				return condition;
			}
			
			return null;
		}
}
