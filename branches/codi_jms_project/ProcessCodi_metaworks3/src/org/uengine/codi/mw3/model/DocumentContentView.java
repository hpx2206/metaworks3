package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.MetaworksDAO;



public class DocumentContentView  implements ContextAware {

	
	final static int LIST_CNT = 1;
	
	@AutowiredFromClient
	public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		

	IWorkItem workitem;
		public IWorkItem getWorkitem() {
			return workitem;
		}
	
		public void setWorkitem(IWorkItem workitem) {
			this.workitem = workitem;
		}
		
	IWorkItem thread;
		public IWorkItem getThread() {
			return thread;
		}
		public void setThread(IWorkItem thread) {
			this.thread = thread;
		}

	int startIndex;
		public int getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}

	boolean more;
		public boolean isMore() {
			return more;
		}
		public void setMore(boolean more) {
			this.more = more;
		}

	String moreTitle;
		public String getMoreTitle() {
			return moreTitle;
		}
		public void setMoreTitle(String moreTitle) {
			this.moreTitle = moreTitle;
		}	
	
	Long taskId;
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}

	int lastIndex;
		public int getLastIndex() {
			return lastIndex;
		}
		public void setLastIndex(int lastIndex) {
			this.lastIndex = lastIndex;
		}
		
	public DocumentContentView(){
		setMetaworksContext(new MetaworksContext());
	}
	
	@ServiceMethod(payload={"instanceId","taskId"})
	public void load() {
		try {
			this.load(this.getInstanceId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@ServiceMethod(callByContent=true)
	public void load(String instId) throws Exception{
		
		IWorkItem thread = (IWorkItem)MetaworksDAO.createDAOImpl(IWorkItem.class);
		IWorkItem result =WorkItem.find(instId,LIST_CNT);
//		IWorkItem workitem = WorkItem.findDocumentBytaskId(this.getTaskId());
//		
		boolean more = result.size() > 1;
		
		while(result.next()){
			thread.moveToInsertRow();
			thread.getImplementationObject().copyFrom(result);
			
			if(more)
				thread.setMore(more);
			
			more = false;
		}
		result = WorkItem.findComment(instId);
		while(result.next()){
			thread.moveToInsertRow();
			thread.getImplementationObject().copyFrom(result);
		}
		
		setThread(thread);
//		setThread(workitem);
		CommentWorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newItem.setWriter(session.getUser());

		setWorkitem(newItem);
	}
	
	@ServiceMethod(callByContent = true)
	public void more() throws Exception{
		setMore(true);
		setMoreTitle(null);
		
		load(instanceId);
	}
}
