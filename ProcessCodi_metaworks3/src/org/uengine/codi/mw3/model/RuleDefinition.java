package org.uengine.codi.mw3.model;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.kernel.DefaultProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.SwitchActivity;


public class RuleDefinition {

	// private Map variables;
	
	private Node nodes;
	
	public Node getNodes() {
		return nodes;
	}

	public void setNodes(Node nodes) {
		this.nodes = nodes;
	}

	RuleVariableDefinition variableDefinition;
		public RuleVariableDefinition getVariableDefinition() {
			return variableDefinition;
		}
		public void setVariableDefinition(RuleVariableDefinition variableDefinition) {
			this.variableDefinition = variableDefinition;
		}
	
//	public Map getVariables() {
//		return variables;
//	}
//
//	public void setVariables(Map variables) {
//		this.variables = variables;
//	}

	public RuleDefinition() {
		this.nodes = new Node("Root Node",true);
		this.nodes.setMetaworksContext(new MetaworksContext());
		this.nodes.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.nodes.setValue("");
		
		variableDefinition = new RuleVariableDefinition();
				
	}
	
	@ServiceMethod(callByContent=true)
	public void execute() {
		
		org.uengine.kernel.ProcessDefinition def = new org.uengine.kernel.ProcessDefinition();
		
//		ArrayList<ClassField> variables = this.variableDefinition.getClassFields();
//		for(int i=0;i<variables.size();i++) {
//			
//		}
		
		def.setProcessVariables(new ProcessVariable[]{ProcessVariable.forName("연령"), ProcessVariable.forName("성별")});
		
		SwitchActivity switchAct = nodes.createSwitchActivity();
		def.addChildActivity(switchAct);
		def.registerToProcessDefinition(true, true);
		
		try {
			
			ProcessInstance pi = new DefaultProcessInstance();
			pi.setProcessDefinition(def);
			
			pi.set("연령", 25);
			pi.set("성별", "남");
			pi.execute();
			
			System.out.println(pi.get("보험료"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		
//		switchAct.setConditions(value);
//		
//		
//		System.out.println("Rule Definition execute()");
				
//		Iterator<String> iterator = variables.keySet().iterator();
//	    while (iterator.hasNext()) {
//	        String key = (String) iterator.next();
//	        System.out.print("key="+key);
//	        System.out.println(" value="+variables.get(key));
//	    }
	    
//	    nodes.execute();
		
	}

	
}
