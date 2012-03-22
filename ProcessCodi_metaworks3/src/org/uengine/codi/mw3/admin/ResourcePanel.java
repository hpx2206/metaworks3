package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.IProcessDefinition;
import org.uengine.codi.mw3.model.ProcessDefinition;
import org.uengine.codi.mw3.model.ResourceFile;
import org.uengine.codi.mw3.model.SelectedProcessDefinition;

public class ResourcePanel {
	
	@ServiceMethod
	public void refresh() throws Exception {
		
		resourceFile = new ResourceFile();
		resourceFile.setMetaworksContext(new MetaworksContext());	
		resourceFile.getMetaworksContext().setWhere("design");
		resourceFile.getMetaworksContext().setWhen("design");

		resourceFile.setFolder(true);
		resourceFile.setAlias("");
		resourceFile.setName("/");
		resourceFile.drillDown();
		
		resourceSearchBox = new ResourceSearchBox();
		
		MetaworksRemoteService.getInstance().clearMetaworksType("*");
	}
	
	ResourceSearchBox resourceSearchBox;
		public ResourceSearchBox getResourceSearchBox() {
			return resourceSearchBox;
		}
		public void setResourceSearchBox(ResourceSearchBox resourceSearchBox) {
			this.resourceSearchBox = resourceSearchBox;
		}

	ResourceFile resourceFile;


		public ResourceFile getResourceFile() {
			return resourceFile;
		}
	
	
		public void setResourceFile(ResourceFile resourceFile) {
			this.resourceFile = resourceFile;
		}

}
