package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="genericfaces/Window.ejs", displayName="New InstancePanel", options={"hideLabels"}, values={"true"})
public class NewInstancePanel extends ContentWindow{
	
	public NewInstancePanel() throws Exception{

	}
	
	public void load() throws Exception{
		
		unstructuredProcessInstanceStarter = new UnstructuredProcessInstanceStarter();
		
		processMapPanel = new ProcessMapPanel();
		processMapPanel.load();
		
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
	
	ProcessMapPanel processMapPanel;
		public ProcessMapPanel getProcessMapPanel() {
			return processMapPanel;
		}
		public void setProcessMapPanel(ProcessMapPanel processMapPanel) {
			this.processMapPanel = processMapPanel;
		}

	UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter;
		
		public UnstructuredProcessInstanceStarter getUnstructuredProcessInstanceStarter() {
			return unstructuredProcessInstanceStarter;
		}
	
		public void setUnstructuredProcessInstanceStarter(
				UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter) {
			this.unstructuredProcessInstanceStarter = unstructuredProcessInstanceStarter;
		}

	ResourceFile processDefinitions;
		public ResourceFile getProcessDefinitions() {
			return processDefinitions;
		}
		public void setProcessDefinitions(ResourceFile processDefinitions) {
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
		
		
	String parentInstanceId;
	@Hidden
		public String getParentInstanceId() {
			return parentInstanceId;
		}
		public void setParentInstanceId(String parentInstanceId) {
			this.parentInstanceId = parentInstanceId;
		}

}
