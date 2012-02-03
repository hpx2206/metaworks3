package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Rule Designer", options={"hideLabels"}, values={"true"})
public class RuleDesignerContentPanel extends ContentWindow {

//	RuleDefinition definition;
//		public RuleDefinition getDefinition() {
//			return definition;
//		}
//		public void setDefinition(RuleDefinition definition) {
//			this.definition = definition;
//		}
//	
//	public void newDefinition(String parentFoler) throws Exception{
//		definition = new RuleDefinition();
//		definition.setParentFolder(parentFoler);
//	}

//	public void loadDefinition(String defId) throws Exception{
//		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
//		String resource = processManager.getResource(defVerId);
//		definition = (RuleDefinition) GlobalContext.deserialize(resource, RuleDefinition.class);
//		definition.setDefId(defId);
//		
//	}

	RuleDefinition ruleDefinition;
	
	public RuleDefinition getRuleDefinition() {
		return ruleDefinition;
	}

	public void setRuleDefinition(RuleDefinition ruleDefinition) {
		this.ruleDefinition = ruleDefinition;
	}
	
	public void newRule() {
		ruleDefinition = new RuleDefinition();
	}
	
//	public ProcessManagerRemote getProcessManager() {
//		return processManager;
//	}
//
//	public void setProcessManager(ProcessManagerRemote processManager) {
//		this.processManager = processManager;
//	}
//
//	@Autowired ProcessManagerRemote processManager;

}
