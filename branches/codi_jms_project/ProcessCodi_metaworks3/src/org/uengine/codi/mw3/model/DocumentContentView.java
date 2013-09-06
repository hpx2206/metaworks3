package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;



public class DocumentContentView   implements ContextAware {

	String id;
		@Id
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
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
	
	@ServiceMethod(payload={"id"})
	public void load() {
		try {
			this.load(this.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void load(String id) throws Exception{
		setId(id);
		
		IWorkItem workitem = WorkItem.findDocumentByFolderId(id);
		setWorkitem(workitem);
	}
}
