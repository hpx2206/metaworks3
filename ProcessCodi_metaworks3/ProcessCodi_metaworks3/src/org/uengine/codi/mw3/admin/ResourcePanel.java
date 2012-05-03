package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.IProcessDefinition;
import org.uengine.codi.mw3.model.ProcessDefinition;

public class ResourcePanel {
	
	public ResourcePanel(IUser user) throws Exception {
		
		init();
		
		resourceSearchBox = new ResourceSearchBox(user);
	}

	public ResourcePanel(){
	}
	
	public void init() throws Exception {
		
		ProcessDefinition root = new ProcessDefinition();
		root.setParentFolder("-1");
		processDefinitions = root.findAll();
		
		processDefinitions.getMetaworksContext().setWhere("design");
		
	}

	@ServiceMethod
	public void refresh() throws Exception {
		
		init();
		
		MetaworksRemoteService.getInstance().clearMetaworksType("*");
	}
	
	IProcessDefinition processDefinitions;
	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}
	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
		
	
	ResourceSearchBox resourceSearchBox;
		public ResourceSearchBox getResourceSearchBox() {
			return resourceSearchBox;
		}
		public void setResourceSearchBox(ResourceSearchBox resourceSearchBox) {
			this.resourceSearchBox = resourceSearchBox;
		}

}
