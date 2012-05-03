package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
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
		processManager.addFolder(getFolderName(), getParentFolderDefId().toString());
		ProcessDefinition parentForRefresh = new ProcessDefinition();
		
		parentForRefresh.setDefId(new Long(getParentFolderDefId()));
		parentForRefresh.drillDown();
		parentForRefresh.setName(parentForRefresh.databaseMe().getName());
		parentForRefresh.setObjType("folder");
		
		getMetaworksContext().setWhen("view");
		
		return new Object[]{parentForRefresh, this};
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}
