package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", displayName="New InstancePanel", options={"hideLabels"}, values={"true"})
public class NewInstancePanel extends ContentWindow{
	
	public NewInstancePanel() throws Exception{
		processDefinitions = new ProcessDefinition();
		processDefinitions.setParentFolder(new Long(-1));
		processDefinitions = processDefinitions.findAll();
		
		processDefinitions.getMetaworksContext().setWhen("newInstance");
	}
	
	IProcessDefinition processDefinitions;	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
}
