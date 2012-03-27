package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", displayName="New InstancePanel", options={"hideLabels"}, values={"true"})
public class NewInstancePanel extends ContentWindow{
	
	public NewInstancePanel() throws Exception{
//		processDefinitions = new ProcessDefinition();
//		processDefinitions.setParentFolder("");
//		processDefinitions.getMetaworksContext().setWhen("newInstance");
//
//		processDefinitions = processDefinitions.findAll();
//		processDefinitions.getMetaworksContext().setWhen("newInstance");

		
		processDefinitions = new ResourceFile();
		
		processDefinitions.setMetaworksContext(new MetaworksContext());	
		processDefinitions.getMetaworksContext().setWhen("newInstance");

		processDefinitions.setFolder(true);
		processDefinitions.setAlias("");
		processDefinitions.setName("/");
		processDefinitions.drillDown();
		

	}
	
//	IProcessDefinition processDefinitions;	
//		public IProcessDefinition getProcessDefinitions() {
//			return processDefinitions;
//		}	
//		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
//			this.processDefinitions = processDefinitions;
//		}
	
	ResourceFile processDefinitions;
		public ResourceFile getProcessDefinitions() {
			return processDefinitions;
		}
		public void setProcessDefinitions(ResourceFile processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
		
	String knowledgeNodeId;
		public String getKnowledgeNodeId() {
			return knowledgeNodeId;
		}
		public void setKnowledgeNodeId(String knowledgeNodeId) {
			this.knowledgeNodeId = knowledgeNodeId;
		}
		
}
