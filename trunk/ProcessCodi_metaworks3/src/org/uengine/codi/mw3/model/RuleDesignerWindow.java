package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "Rule Designer", options = { "hideLabels" }, values = { "true" })
public class RuleDesignerWindow extends ContentWindow {

	RuleDesignerContentPanel ruleDesignerContentPanel;
	
public RuleDesignerContentPanel getRuleDesignerContentPanel() {
		return ruleDesignerContentPanel;
	}

	public void setRuleDesignerContentPanel(
			RuleDesignerContentPanel ruleDesignerContentPanel) {
		this.ruleDesignerContentPanel = ruleDesignerContentPanel;
	}

public void newRule(String parentFolder) throws Exception {
	ruleDesignerContentPanel = new RuleDesignerContentPanel();
	ruleDesignerContentPanel.newRule(parentFolder);
}

public void load(String defId) throws Exception {
	ruleDesignerContentPanel = new RuleDesignerContentPanel();
	//ruleDesignerContentPanel.
	//entityDesignerContentPanel.load(defId);		
	
	
//	String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
//	String resource = processManager.getResource(defVerId);
	
	
//	RuleDefinition ruleDefinition = (RuleDefinition) GlobalContext.deserialize(resource, EntityDefinition.class);
//	ruleDefinition.init();
//	
//	ruleDesignerContentPanel.setEntityDefinition(ruleDefinition);
//	ruleDesignerContentPanel.setEntityQuery(new EntityQuery());
}

//@Autowired
//ProcessManagerRemote processManager;
}
