package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.ResourceFile;

public class ResourcePanel {
	
	public ResourcePanel(IUser user) {
	
		resourceFile = new ResourceFile();
		
		resourceSearchBox = new ResourceSearchBox(user);
	}
	
	@ServiceMethod
	public void refresh() throws Exception {
		resourceFile.setMetaworksContext(new MetaworksContext());	
		resourceFile.getMetaworksContext().setWhere("design");
		resourceFile.getMetaworksContext().setWhen("design");

		resourceFile.setFolder(true);
		resourceFile.setAlias("");
		resourceFile.setName("/");
		resourceFile.drillDown();
		
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
