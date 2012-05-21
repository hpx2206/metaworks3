package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.processmanager.ProcessManagerRemote;

public class UnstructuredProcessInstanceStarter {

	String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
		
	@ServiceMethod(callByContent = true)
	public Object[] start() throws Exception{
		
		//good example: fully reused code are below:
		
		ResourceFile unstructuredProcessDefinition = new ResourceFile();
		unstructuredProcessDefinition.processManager = processManager;
		unstructuredProcessDefinition.session = session;
		unstructuredProcessDefinition.instanceViewContent = instanceViewContent;
		unstructuredProcessDefinition.setAlias(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
		
		Object[] instanceViewAndInstanceList = unstructuredProcessDefinition.initiate();
		
		InstanceViewContent instanceView = (InstanceViewContent) instanceViewAndInstanceList[0];
		
		instanceView.getInstanceView().getInstanceNameChanger().setInstanceName(getTitle());
		instanceView.getInstanceView().getInstanceNameChanger().change();
		
		return instanceViewAndInstanceList;
		
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
}
