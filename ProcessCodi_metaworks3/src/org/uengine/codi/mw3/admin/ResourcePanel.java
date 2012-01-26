package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.IProcessDefinition;
import org.uengine.codi.mw3.model.ProcessDefinition;
import org.uengine.codi.mw3.model.SelectedProcessDefinition;

public class ResourcePanel {

	@ServiceMethod
	public void refresh() throws Exception {
		
		ProcessDefinition root = new ProcessDefinition();
		root.setParentFolder(new Long(-1));
		processDefinitions = root.findAll();
		
		processDefinitions.getMetaworksContext().setWhere("design");
	}
	
	IProcessDefinition processDefinitions;
	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}
	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
		
	

}
