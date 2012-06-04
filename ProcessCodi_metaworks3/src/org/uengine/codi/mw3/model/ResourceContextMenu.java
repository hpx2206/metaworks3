package org.uengine.codi.mw3.model;


import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;

public class ResourceContextMenu {
	
	String resourceAlias;
	@Id
		public String getResourceAlias() {
			return resourceAlias;
		}
	
		public void setResourceAlias(String resourceAlias) {
			this.resourceAlias = resourceAlias;
		}


	@ServiceMethod(needToConfirm=true)
	public ResourceFile delete() throws Exception{
//		processManager.removeProcessDefinition(defId);
	
		
		ResourceFile def = new ResourceFile();
		def.setAlias(getResourceAlias());

//		String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");

//		if(!userId.equals(def.databaseMe().getAuthor())){
//			throw new Exception("최초 정의 생성자만 정의를 삭제할 수 있습니다.");
//		}
		
//		def.databaseMe().setIsDeleted(true);
	
		
		
//		return def.delete();
		return null;
	}
	
	@ServiceMethod
	public NewFolder newFolder(){
		NewFolder newFolder = new NewFolder();
//		newFolder.setParentFolder(getDefId());
		
		return newFolder;
	}
	
	@ServiceMethod
	public void rename(){
		
	}
//	
//	@Autowired 
//	ProcessManagerRemote processManager;

}
