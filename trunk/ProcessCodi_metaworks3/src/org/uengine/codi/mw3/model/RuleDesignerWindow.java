package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.codi.mw3.admin.EntityQuery;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "RuleDewigner Window", options = { "hideLabels" }, values = { "true" })
public class RuleDesignerWindow extends ContentWindow {

	RuleDesignerContentPanel ruleDesignerContentPanel;
	
public RuleDesignerContentPanel getRuleDesignerContentPanel() {
		return ruleDesignerContentPanel;
	}

	public void setRuleDesignerContentPanel(
			RuleDesignerContentPanel ruleDesignerContentPanel) {
		this.ruleDesignerContentPanel = ruleDesignerContentPanel;
	}

public void newRule() throws Exception {
	ruleDesignerContentPanel = new RuleDesignerContentPanel();
	ruleDesignerContentPanel.newRule();
}

public void load(String defId) throws Exception {
	ruleDesignerContentPanel = new RuleDesignerContentPanel();
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
