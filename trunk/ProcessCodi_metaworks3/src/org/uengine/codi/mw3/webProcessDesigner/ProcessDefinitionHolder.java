package org.uengine.codi.mw3.webProcessDesigner;

import org.uengine.contexts.TextContext;

public class ProcessDefinitionHolder { 
	
	String processDefinitionAlias;
		public String getProcessDefinitionAlias() {
			return processDefinitionAlias;
		}
		public void setProcessDefinitionAlias(String processDefinitionAlias) {
			this.processDefinitionAlias = processDefinitionAlias;
		}
	String childType;
		public String getChildType() {
			return childType;
		}
		public void setChildType(String childType) {
			this.childType = childType;
		}
	TextContext name;
		public TextContext getName() {
			return name;
		}
		public void setName(TextContext name) {
			this.name = name;
		}
	
}
