package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessDefinitionContextMenu {
	
	Long defId;
	@Id
		public Long getDefId() {
			return defId;
		}
		public void setDefId(Long defId) {
			this.defId = defId;
		}

	@ServiceMethod(needToConfirm=true)
	public ProcessDefinition delete() throws Exception{
//		processManager.removeProcessDefinition(defId);
		
		ProcessDefinition def = new ProcessDefinition();
		def.setDefId(getDefId());
		def.databaseMe().setIsDeleted(true);
		
		return def;
	}
	
	@ServiceMethod
	public void rename(){
		
	}
//	
//	@Autowired 
//	ProcessManagerRemote processManager;

}
