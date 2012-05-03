package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="genericfaces/WindowTab.ejs", displayName="New InstancePanel", options={"hideLabels"}, values={"true"})
public class NewInstancePanel extends ContentWindow{
	
	public NewInstancePanel() throws Exception{
		processDefinitions = new ProcessDefinition();
		processDefinitions.setParentFolder("-1");
		processDefinitions.getMetaworksContext().setWhen("newInstance");

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
		
	String knowledgeNodeId;
		@Hidden
		public String getKnowledgeNodeId() {
			return knowledgeNodeId;
		}
		public void setKnowledgeNodeId(String knowledgeNodeId) {
			this.knowledgeNodeId = knowledgeNodeId;
		}

}
