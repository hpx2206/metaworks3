package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.dao.MetaworksDAO;


public class InstanceViewThreadPanel implements ContextAware {
	
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

	protected InstanceViewThreadPanel(String instanceId) throws Exception{
		this();
		
		load(instanceId);		

	}
	
	public void load(String instanceId) throws Exception {
		setInstanceId(instanceId);
		
		IWorkItem result = WorkItem.find(instanceId);
		IWorkItem thread; 
		
		if("instanceList".equals(this.getMetaworksContext().getHow())){
/*			thread = (IWorkItem)MetaworksDAO.createDAOImpl(IWorkItem.class);
			int cnt = 1;
			int limit = 5;
			
			while(result.next()){
				cnt++;
				
				thread.moveToInsertRow();
				thread.getImplementationObject().copyFrom(result);
				
				if(cnt > limit){
					setMore(true);
					
					break;
				}
			}*/		
			thread = result;
			
			
			thread.getMetaworksContext().setHow("instanceList");
		}else{
			thread = result;
		}
		
		setThread(thread);//.getImplementationObject().toArrayList());		
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
