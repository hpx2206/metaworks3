package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.dao.TransactionContext;


public class InstanceViewThreadPanel implements ContextAware {

	public Session session;
	
	public InstanceViewThreadPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	IWorkItem newItem;
		public IWorkItem getNewItem() {
			return newItem;
		}
		public void setNewItem(IWorkItem newItem) {
			this.newItem = newItem;
		}
	protected InstanceViewThreadPanel(String instanceId) throws Exception{
		this();
		
		load(instanceId);		

	}
	
	public void load(String instanceId) throws Exception {
		setInstanceId(instanceId);
		
		IWorkItem result = WorkItem.find(instanceId);
		result.getMetaworksContext().setHow("instanceList");
		setThread(result);
		
		CommentWorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.setTaskId(new Long(-1));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		newItem.setWriter(session.user);

		setNewItem(newItem);
		
	}

	String instanceId;
	@Id
		public String getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
//
//	ArrayList<IDAO> thread;
//		public ArrayList<IDAO> getThread() {
//			return thread;
//		}
//	
//		public void setThread(ArrayList<IDAO> thread) {
//			this.thread = thread;
//		}
	
	IWorkItem thread;
		public IWorkItem getThread() {
			return thread;
		}
	
		public void setThread(IWorkItem thread) {
			this.thread = thread;
		}
		
	boolean more;
		public boolean isMore() {
			return more;
		}
		public void setMore(boolean more) {
			this.more = more;
		}
}
