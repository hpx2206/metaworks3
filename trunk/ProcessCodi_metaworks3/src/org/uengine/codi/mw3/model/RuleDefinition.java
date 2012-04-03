package org.uengine.codi.mw3.model;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.platform.Console;
import org.uengine.kernel.DefaultProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.SwitchActivity;

@Face(options={"hideNewBtn", "hideAddBtn"}, values={"true", "true"})   
public class RuleDefinition implements ContextAware {

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
	
	private DecisionTreeNode nodes;
		public DecisionTreeNode getNodes() {
			return nodes;
		}
		public void setNodes(DecisionTreeNode nodes) {
			this.nodes = nodes;
		}
	
		
	@AutowiredFromClient
	public RuleVariableDefinition ruleVariableDefinition;
		
	public RuleDefinition() {
				
	}
	
	public void init() {
		this.nodes = new DecisionTreeNode();
		this.nodes.setRoot(true);
		this.nodes.setMetaworksContext(new MetaworksContext());
		this.nodes.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.nodes.setValue("");
	}
	
	@ServiceMethod(callByContent=true)
	public void execute() {
		try {
			
			org.uengine.kernel.ProcessDefinition def = new org.uengine.kernel.ProcessDefinition();
			
			def.addChildActivity(getNodes().createActivity());
			def.registerToProcessDefinition(true, false);
	
			ProcessInstance pi = new DefaultProcessInstance();
			pi.setProcessDefinition(def);
	
			ArrayList<ClassField> variables = this.ruleVariableDefinition.getClassFields();
			ProcessVariable[] pvs = new ProcessVariable[variables.size()];
			for(int i=0;i<variables.size();i++) {
				
				ClassField variable = variables.get(i);
				
				String variableName = variable.getFieldName();
				pvs[i] = ProcessVariable.forName(variableName);
				
				pi.set(variableName, variable.getDefaultValue());
			}
			
			def.setProcessVariables(pvs);
					
			pi.execute();
			
			for(int i=0;i<variables.size();i++) {
				
				ClassField variable = variables.get(i);
				String variableName = variable.getFieldName();
				
				Console.addLog("<ul>");
				Console.addLog("<li>" + variableName + "=" + pi.get(variableName) + "<br>");
				Console.addLog("</ul>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
}
