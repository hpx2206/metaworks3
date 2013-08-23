package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.MetaworksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class DocumentListWindow extends ContentWindow implements ContextAware {
	
	//문서분류별 목록을 출력해주는 클래스
	
	final static int LIST_CNT = 5;
	
	@AutowiredFromClient
	public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String folderId;
		@Id
		public String getFolderId() {
			return folderId;
		}
		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}
		
	IWorkItem workItem;
		public IWorkItem getWorkItem() {
			return workItem;
		}
		public void setWorkItem(IWorkItem workItem) {
			this.workItem = workItem;
		}
	
	public int startIndex;
		public int getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}
		
	public int lastIndex;
		public int getLastIndex() {
			return lastIndex;
		}
		public void setLastIndex(int lastIndex) {
			this.lastIndex = lastIndex;
		}
	
	public String moreTitle;
		public String getMoreTitle() {
			return moreTitle;
		}
		public void setMoreTitle(String moreTitle) {
			this.moreTitle = moreTitle;
		}
		
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
	
	public DocumentListWindow(){
			setMetaworksContext(new MetaworksContext());
	}
	
	
	@ServiceMethod(payload={"folderId"})
	public void load(){
		try{
			this.load(this.getFolderId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void load(String folderId) throws Exception{
		String how = null;
		
		how = "normal";
		
		getMetaworksContext().setHow(how);
		IWorkItem thread = (IWorkItem)MetaworksDAO.createDAOImpl(IWorkItem.class);
		IWorkItem result = WorkItem.find(folderId, LIST_CNT);	
		
		boolean more = result.size() > 5;
		while(result.next()){
			thread.moveToInsertRow();
			thread.getImplementationObject().copyFrom(result);
			
			if(more) thread.setMore(more);
			
			more = false;
		}
		
//		result = WorkItem.findDocument(folderId);
		while(result.next()){
			thread.moveToInsertRow();
			thread.getImplementationObject().copyFrom(result);
		}
		
		setThread(thread);
		
		
	}
	
	@ServiceMethod(callByContent=true)
	public void more() throws Exception{
		setMore(true);
		setMoreTitle(null);
		load(folderId);
	}
	
	@Autowired
	public ProcessManagerRemote processManager;	
}
