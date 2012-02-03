package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(options={"hideNewBtn", "hideTitle", "hideViewBox", "hideLabels", "hideEditBtn"},
       values={"true", "true", "true", "true", "true"})
public class InstanceViewPublic {

	public InstanceViewPublic(){				
	}
	
	Session session;
		
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

	@Autowired
	InstanceViewContent instanceViewContent;
		@Autowired
		public InstanceViewContent getInstanceViewContent() {
			return instanceViewContent;
		}	
		public void setInstanceViewContent(InstanceViewContent instanceViewContent) {
			this.instanceViewContent = instanceViewContent;
		}
		
	@Autowired
	ProcessManagerRemote processManager;	

	Long instanceId;
		@Id
		@Hidden
		public Long getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}

	boolean open;
		public boolean isOpen() {
			return open;
		}
		public void setOpen(boolean open) {
			this.open = open;
		}
		
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@ServiceMethod(when="hidden")
	public void load() throws Exception {
		if(getSession() == null)
			throw new Exception("session error");
		
		ProcessInstance instance = processManager.getProcessInstance(String.valueOf(getInstanceId()));
		
		boolean isOpen = false;
		if(instance.getProperty("", "is_open") != null){
			isOpen = ((String)instance.getProperty("", "is_open")).equals("open") ? true : false;
		}
		
		setOpen(isOpen);
		
		if(isOpen){
			IInstance instances = (IInstance) Database.sql(IInstance.class, 
					  "select * from bpm_procinst where instId=?instId"
				);
			
			instances.setInstId(getInstanceId());
			instances.select();
			
			if(instances.next()){		
				getInstanceViewContent().load(instances);
				getInstanceViewContent().getInstanceView().getMetaworksContext().setWhen("public");
				
				setMetaworksContext(new MetaworksContext());
				getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			}else{
				throw new Exception("Instance where ID=[" + getInstanceId() + "] is not found.");
			}
		}
	}
}
