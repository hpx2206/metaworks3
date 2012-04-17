package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.kernel.ProcessVariable;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath = "genericfaces/Tab.ejs", options = { "hideLabels" }, values = { "true" })
public class RuleDesignerContentPanel extends ContentWindow {

	public RuleDesignerContentPanel() {
		setMetaworksContext(new MetaworksContext());
	}
	
	
	RuleDefinitionInfo ruleDefinitionInfo;
		public RuleDefinitionInfo getRuleDefinitionInfo() {
			return ruleDefinitionInfo;
		}
		public void setRuleDefinitionInfo(RuleDefinitionInfo ruleDefinitionInfo) {
			this.ruleDefinitionInfo = ruleDefinitionInfo;
		}

	RuleVariableDefinition ruleVariableDefinition;
		public RuleVariableDefinition getRuleVariableDefinition() {
			return ruleVariableDefinition;
		}
		public void setRuleVariableDefinition(
				RuleVariableDefinition ruleVariableDefinition) {
			this.ruleVariableDefinition = ruleVariableDefinition;
		}
	
	RuleDefinition ruleDefinition;
		public RuleDefinition getRuleDefinition() {
			return ruleDefinition;
		}
		public void setRuleDefinition(RuleDefinition ruleDefinition) {
			this.ruleDefinition = ruleDefinition;
		}

	public void newRule(String parentFolder) throws Exception {
		
		ruleVariableDefinition = new RuleVariableDefinition();
		ruleDefinitionInfo = new RuleDefinitionInfo();
		ruleDefinitionInfo.setParentFolder(parentFolder);
		
		ruleDefinition = new RuleDefinition();
		ruleDefinition.init();

	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		
		org.uengine.kernel.ProcessDefinition pd = processManager.getProcessDefinition(defVerId);
		
		org.uengine.kernel.ProcessDefinition def = new org.uengine.kernel.ProcessDefinition();
		
		//TODO: revert these logic
//		def.addChildActivity(getNodes().createActivity());
//		def.registerToProcessDefinition(true, false);
//
//		ArrayList<ClassField> variables = this.ruleVariableDefinition.getClassFields();
//		ProcessVariable[] pvs = pd.getProcessVariables();
//		for(int i=0;i<pvs.size();i++) {
//			
//			pvs[i]
//			
//			ClassField variable = variables.get(i);
//			
//			String variableName = variable.getFieldName();
//			pvs[i] = ProcessVariable.forName(variableName);
//			
//		}
//		
//		def.setProcessVariables(pvs);
//
//		return def;
	}
	
	@Autowired public ProcessManagerRemote processManager;
	

	transient MetaworksContext metaworksContext;

		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
