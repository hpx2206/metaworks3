package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.uengine.contexts.TextContext;

public class ProcessDefinitionHolder { 
	
	/*
	 * ChildProcessDefinition, 
	 * +ProcessDeifinitionFolder
	 **/
	String processDefinitionAlias;
		public String getProcessDefinitionAlias() {
			return processDefinitionAlias;
		}
		public void setProcessDefinitionAlias(String processDefinitionAlias) {
			this.processDefinitionAlias = processDefinitionAlias;
		}
		
	ArrayList<ProcessDefinitionHolder> childs;
		public ArrayList<ProcessDefinitionHolder> getChilds() {
			return childs;
		}
		public void setChilds(ArrayList<ProcessDefinitionHolder> childs) {
			this.childs = childs;
		}

	TextContext name;
		public TextContext getName() {
			return name;
		}
		public void setName(TextContext name) {
			this.name = name;
		}
	
}
