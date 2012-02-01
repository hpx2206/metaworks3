
package org.uengine.codi.mw3.admin;

import java.util.Vector;

import org.uengine.kernel.Condition;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.Or;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.SwitchActivity;

public class RuleDefinition extends AbstractDefinition{

	
	
	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return "rule";
	}
	
	Vector<Vector<RuleColumn>> rows;
		public Vector<Vector<RuleColumn>> getRows() {
			return rows;
		}
		public void setRows(Vector<Vector<RuleColumn>> rows) {
			this.rows = rows;
		}
	

	@Override
	public String getData() throws Exception {

		ProcessDefinition procDef = new ProcessDefinition();
		
		SwitchActivity switchAct = new SwitchActivity();
		
		Condition cond = new Or();
//		
//		
//		HashMap map = new HashMap();
//		Or ors = new Or();
//		for(int i=0; i<rows.size(); i++){
//			Vector<RuleColumn> cols = rows.get(i);
//	//		String key = (String)cols.get(1);
//			
//			boolean isOtherwise = false;
//			
//	//		if(key==null)continue;
//			if(!map.containsKey(key)){
//				Or or = new Or();
//				And and = new And();
//				for(int j=0;j<cols.size();j++){
//					if(cols.get(j).getCondition() instanceof Otherwise){
//						isOtherwise = true;
//						break;
//					}else if(cols.get(j).getCondition() instanceof RoleExist){
//						RoleExist role = (RoleExist)cols.get(j).getCondition();
//						if(role!=null){
//							and.addCondition(role);
//						}
//					}else{
//						Evaluate eval = (Evaluate)cols.get(j).getCondition();
//						if(eval!=null){
//							and.addCondition(eval);
//						}
//					}
//				}
//				if(!isOtherwise && and.getConditionsVt().size()>0){
//					or.addCondition(and);
//				}
//				
//				if(isOtherwise){
//					Otherwise otherwise = new Otherwise();
//					map.put(key, otherwise);
//					otherwise.getDescription().setText(key);
//					ors.addCondition(otherwise);
//				}else{
//					map.put(key, or);
//					or.getDescription().setText(key);
//					ors.addCondition(or);
//				}
//
//			}else{
//				And and = new And();
//				for(int j=0;j<cols.size();j++){
//					if(cols.get(j) instanceof Evaluate){
//						Evaluate eval = (Evaluate)cols.get(j);
//						if(eval!=null){
//							and.addCondition(eval);
//						}
//					}else{
//						RoleExist role = (RoleExist)cols.get(j);
//						if(role!=null){
//							and.addCondition(role);
//						}
//					}
//				}
//				if(and.getConditionsVt().size()>0){
//					((Or)map.get(key)).addCondition(and);
//				}
//				
//			}
//			
//		}
//		
		
		switchAct.setConditions(new Condition[]{new Or()});
		
		
		return GlobalContext.serialize(procDef, ProcessDefinition.class);
	}


	
}
