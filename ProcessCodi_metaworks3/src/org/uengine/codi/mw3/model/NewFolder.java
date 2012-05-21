package org.uengine.codi.mw3.model;

import java.io.File;
import java.rmi.RemoteException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.processmanager.ProcessManagerRemote;

public class NewFolder implements ContextAware{
	
	public NewFolder(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String folderName;
	
		public String getFolderName() {
			return folderName;
		}
	
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}

	String parentFolderDefId;
	@Hidden
		public String getParentFolderDefId() {
			return parentFolderDefId;
		}	
		public void setParentFolderDefId(String parentFolderDefId) {
			this.parentFolderDefId = parentFolderDefId;
		}

		
	@ServiceMethod(callByContent = true)
	public Object[] create() throws Exception{
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/";

		
		File file = new File(resourceBase + "/" + getParentFolderDefId() + "/" + getFolderName());
		file.mkdirs();
		
		ResourceFile parent = new ResourceFile();
		parent.setAlias(getParentFolderDefId());
		parent.drillDown();
			
		return new Object[]{parent, new Remover(this)};
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}
