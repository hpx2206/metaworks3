package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;



public class DocumentContentView   implements ContextAware {

	Long taskId;
		@Id
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	IWorkItem workitem;
		public IWorkItem getWorkitem() {
			return workitem;
		}
	
		public void setWorkitem(IWorkItem workitem) {
			this.workitem = workitem;
		}

	public DocumentContentView(){
		setMetaworksContext(new MetaworksContext());
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
		setTaskId(id);
		
		IWorkItem workitem = WorkItem.findDocumentByFolderId(id);
		setWorkitem(workitem);
	}
}
