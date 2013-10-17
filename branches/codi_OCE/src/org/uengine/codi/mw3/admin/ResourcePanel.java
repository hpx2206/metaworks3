package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.IProcessDefinition;
import org.uengine.codi.mw3.model.NewChildWindow;
import org.uengine.codi.mw3.model.NewFolder;
import org.uengine.codi.mw3.model.ResourceFile;
import org.uengine.codi.mw3.model.Session;

public class ResourcePanel {
	@AutowiredFromClient
	public Session session;
	
	public ResourcePanel() throws Exception {
//		init();
	}
	
	public void init() throws Exception {
		resourceFile = new ResourceFile();
		
		resourceFile.setMetaworksContext(new MetaworksContext());	
		resourceFile.getMetaworksContext().setWhere("design");
		resourceFile.getMetaworksContext().setWhen("design");
		resourceFile.session = session;
				
				
		
		resourceFile.setObjType("folder");
		resourceFile.setFolder(true);
		resourceFile.setAlias("");
		resourceFile.setName("classes");
		resourceFile.drillDown();
	
		
		libraryFile = new ResourceFile();
		
		libraryFile.setMetaworksContext(new MetaworksContext());	
		libraryFile.getMetaworksContext().setWhere("design");
		libraryFile.getMetaworksContext().setWhen("design");

		libraryFile.session = session;
		libraryFile.setObjType("folder");
		libraryFile.setFolder(true);
		libraryFile.setAlias("__lib");
		libraryFile.setName("libraries");
		libraryFile.drillDown();
	

		webResourceFile = new ResourceFile();

		webResourceFile.setMetaworksContext(new MetaworksContext());	
		webResourceFile.getMetaworksContext().setWhere("design");
		webResourceFile.getMetaworksContext().setWhen("design");

		webResourceFile.session = session;
		webResourceFile.setObjType("folder");
		webResourceFile.setFolder(true);
		webResourceFile.setAlias("__web");
		webResourceFile.setName("web");
		webResourceFile.drillDown();
		
		resourceSearchBox = new ResourceSearchBox();
	
	}
	
	
	@ServiceMethod(inContextMenu=true)
	public void refresh() throws Exception {
		init();
		
		MetaworksRemoteService.getInstance().clearMetaworksType("*");
		
		CodiClassLoader.refreshClassLoader(null);
	}
	
	@ServiceMethod
	public NewChildWindow newFolder() throws Exception {
		NewFolder nf = new NewFolder();

		NewChildWindow newChildWindow = new NewChildWindow();
		newChildWindow.setPanel(nf);
		newChildWindow.setNewChildContentPanel(null);
				
		return newChildWindow;

	}
	
	IProcessDefinition processDefinitions;	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}
	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
	
	ResourceSearchBox resourceSearchBox;
		@Face(options={"enterSearch"}, values={"true"})
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
		
	ResourceFile libraryFile;
		public ResourceFile getLibraryFile() {
			return libraryFile;
		}
		public void setLibraryFile(ResourceFile libraryFile) {
			this.libraryFile = libraryFile;
		}
		
	ResourceFile webResourceFile;
		public ResourceFile getWebResourceFile() {
			return webResourceFile;
		}
	
		public void setWebResourceFile(ResourceFile webResourceFile) {
			this.webResourceFile = webResourceFile;
		}

}
