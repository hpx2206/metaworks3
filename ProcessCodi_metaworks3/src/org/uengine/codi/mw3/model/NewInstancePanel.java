package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

public class NewInstancePanel extends ContentPanel{
	
	public NewInstancePanel() throws Exception{
		processDefinitions = new ProcessDefinition();
		processDefinitions.setParentFolder(new Long(-1));
		processDefinitions = processDefinitions.findAll();
	}
	
	IProcessDefinition processDefinitions;	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
}
