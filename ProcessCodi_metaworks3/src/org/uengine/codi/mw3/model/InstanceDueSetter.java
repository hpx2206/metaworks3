package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;

@Face(displayName="$InstanceDueSetter")
public class InstanceDueSetter implements ContextAware{
	
	MetaworksContext metaworksContext;
		
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	Long instId;
	@Hidden
		public Long getInstId() {
			return instId;
		}
	
		public void setInstId(Long instId) {
			this.instId = instId;
		}
		
	java.util.Date dueDate;
	@Face(displayName="$Due")
		public java.util.Date getDueDate() {
			return dueDate;
		}
	
		public void setDueDate(java.util.Date dueDate) {
			this.dueDate = dueDate;
		}
		
	boolean onlyInitiatorCanComplete;
	@Face(displayName="$OnlyInitiatorCanComplete")
		public boolean isOnlyInitiatorCanComplete() {
			return onlyInitiatorCanComplete;
		}
		public void setOnlyInitiatorCanComplete(boolean onlyInitiatorCanComplete) {
			this.onlyInitiatorCanComplete = onlyInitiatorCanComplete;
		}
	
	String progress;
	@Face(displayName="$progress")
		public String getProgress() {
			return progress;
		}
		public void setProgress(String progress) {
			this.progress = progress;
		}
		
//	IUser assignee;
//
//		public IUser getAssignee() {
//			return assignee;
//		}
//	
//		public void setAssignee(IUser assignee) {
//			this.assignee = assignee;
//		}
		



	@ServiceMethod(callByContent=true)
	public void apply() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(getInstId());
		instance.databaseMe().setDueDate(getDueDate());
		instance.databaseMe().setInitCmpl(isOnlyInitiatorCanComplete());
		instance.databaseMe().setProgress(getProgress());
		
		instance.flushDatabaseMe();
		IInstance iInstance = instance.databaseMe();
		iInstance.setMetaworksContext(getMetaworksContext());
		iInstance.getMetaworksContext().setWhen("view");
		
		MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(iInstance)});
		
//		instance.databaseMe().set
	}

	
	public InstanceDueSetter(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		setDueDate(new Date());
	}
}
