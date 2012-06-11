package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;


public class NewInstancePanel implements ContextAware {
	
	public NewInstancePanel(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load() throws Exception{
//		unstructuredProcessInstanceStarter = new UnstructuredProcessInstanceStarter();
//		
		processMapPanel = new ProcessMapPanel();		
		processMapPanel.setMetaworksContext(this.getMetaworksContext());
		processMapPanel.load();
		
		newInstantiator = new CommentWorkItem();
		newInstantiator.setWriter(session.getUser());
		newInstantiator.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		newInstantiator.setInstantiation(true);
	}
	
	@AutowiredFromClient
	public Session session;
	
	WorkItem newInstantiator;
		public WorkItem getNewInstantiator() {
			return newInstantiator;
		}
	
		public void setNewInstantiator(WorkItem newInstantiator) {
			this.newInstantiator = newInstantiator;
		}

	
//	UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter;		
//	public UnstructuredProcessInstanceStarter getUnstructuredProcessInstanceStarter() {
//		return unstructuredProcessInstanceStarter;
//	}
//
//	public void setUnstructuredProcessInstanceStarter(
//			UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter) {
//		this.unstructuredProcessInstanceStarter = unstructuredProcessInstanceStarter;
//	}
//	
	ProcessMapPanel processMapPanel;
		public ProcessMapPanel getProcessMapPanel() {
			return processMapPanel;
		}
		public void setProcessMapPanel(ProcessMapPanel processMapPanel) {
			this.processMapPanel = processMapPanel;
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
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	

}
