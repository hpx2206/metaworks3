package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;



public class DocumentContentView  {

	Long taskId;
		@Id
		@Hidden
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	IWorkItem workitem;
		
		public IWorkItem getWorkitem() {
			return workitem;
		}
	
		public void setWorkitem(IWorkItem workitem) {
			this.workitem = workitem;
		}

	public DocumentContentView(){
	}
	
	@ServiceMethod(payload={"taskid"})
	public void load() {
		try {
			this.load(this.getTaskId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void load(Long id) throws Exception{
		
		IWorkItem workitem = WorkItem.findDocumentByFolderId(id);
		setWorkitem(workitem);
	}
}
