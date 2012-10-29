package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceTooltip {
	
	Long instanceId;
		@Id
		public Long getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}
	
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	public InstanceView instanceView;			
		public InstanceView getInstanceView() {
			return instanceView;
		}
		public void setInstanceView(InstanceView instanceView) {
			this.instanceView = instanceView;
		}
		
	public InstanceDrag instanceDrag;
		public InstanceDrag getInstanceDrag() {
			return instanceDrag;
		}
		public void setInstanceDrag(InstanceDrag instanceDrag) {
			this.instanceDrag = instanceDrag;
		}
		
	public InstanceTooltip() throws Exception{
	}
	
	@ServiceMethod(target="popup", loader="auto")
	public Popup schedule() throws Exception{
		instanceViewNullCheck();
		return instanceView.schedule();
	}
	
	@ServiceMethod(needToConfirm=true)
	public Object[] remove() throws Exception{
		instanceViewNullCheck();
		return instanceView.remove();
	}
	
	@ServiceMethod(callByContent=true)
	public void complete() throws Exception{
		instanceViewNullCheck();
		instanceView.complete();
	}
	
	public void instanceViewNullCheck()  throws Exception{
		if( instanceView == null){
			instanceView = new InstanceView();
			instanceView.session = session;
			instanceView.setInstanceId(instanceId.toString());
			instanceView.setStatus(status);
			instanceView.processManager = processManager;
		}
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;	
}
