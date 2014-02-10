package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksException;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class InstanceDrag {
	Long instanceId;
		@Id
		public Long getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}
	
	@ServiceMethod(mouseBinding="drag")
	public Session cut() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(this.instanceId);
		if(instance.databaseMe().getIsDeleted()){
			throw new MetaworksException("$alreadyDeletedPost");
		}
		session.setClipboard(this);
		return session;
	}
	
	@AutowiredFromClient
	public Session session;
}
