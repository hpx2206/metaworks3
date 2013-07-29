package org.uengine.codi.mw3.webProcessDesigner.jms;

import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;

public class DefinitionMonitor {

	String definitionId;
		public String getDefinitionId() {
			return definitionId;
		}
		public void setDefinitionId(String definitionId) {
			this.definitionId = definitionId;
		}
		
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}
		
	public void load(){
		
	}
}
