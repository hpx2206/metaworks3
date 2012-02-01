package org.uengine.codi.mw3.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.RuleDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class RuleDesignerContentPanel {

	RuleDefinition definition;
		public RuleDefinition getDefinition() {
			return definition;
		}
		public void setDefinition(RuleDefinition definition) {
			this.definition = definition;
		}
	
	public void newDefinition(String parentFoler) throws Exception{
		definition = new RuleDefinition();
		definition.setParentFolder(parentFoler);
	}

	public void loadDefinition(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		definition = (RuleDefinition) GlobalContext.deserialize(resource, RuleDefinition.class);
		definition.setDefId(defId);
		
	}

	@Autowired ProcessManagerRemote processManager;

}
