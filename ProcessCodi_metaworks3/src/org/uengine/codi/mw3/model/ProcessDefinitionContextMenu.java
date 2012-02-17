package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
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

		String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");

		if(!userId.equals(def.databaseMe().getAuthor())){
			throw new Exception("최초 정의 생성자만 정의를 삭제할 수 있습니다.");
		}
		
		def.databaseMe().setIsDeleted(true);
		
		return def;
	}
	
	@ServiceMethod
	public NewFolder newFolder(){
		NewFolder newFolder = new NewFolder();
		newFolder.setParentFolderDefId(getDefId());
		
		return newFolder;
	}
	
	@ServiceMethod
	public void rename(){
		
	}
//	
//	@Autowired 
//	ProcessManagerRemote processManager;

}
