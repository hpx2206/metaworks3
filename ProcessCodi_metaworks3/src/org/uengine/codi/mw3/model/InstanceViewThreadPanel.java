package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.MetaworksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceViewThreadPanel implements ContextAware {
	
	final static int LIST_CNT = 5;

	@AutowiredFromClient
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
	
		
	protected InstanceViewThreadPanel(String instanceId) throws Exception{
		this();
		
		load(instanceId);		

	}
	
	@ServiceMethod(payload={"instanceId"})
	public void load(){
		try {
			this.load(this.getInstanceId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(String instanceId) throws Exception {
//		String how = null;
//		
//		if(session.getEmployee() != null && "sns".equals(session.getEmployee().getPreferUX()))
//			how = "sns";
//		/*else if("oce".equals(session.getUx()))	
//			how = "sns";*/
//		else if("document".equals(session.getLastPerspecteType()) || "UnlabeledDocument".equals(session.getLastPerspecteType()))
//			how = "document";
//		else
//			how = "normal";
//		
//		getMetaworksContext().setHow(how);
		
		setInstanceId(instanceId);
		
		IWorkItem thread = (IWorkItem)MetaworksDAO.createDAOImpl(IWorkItem.class);
		IWorkItem result = WorkItem.find(instanceId, LIST_CNT);		
		boolean more = result.size() > 5;
		while(result.next()){
			thread.moveToInsertRow();
			
			if(more){
				thread.setTaskId(result.getTaskId());
				thread.setInstId(result.getInstId());
				thread.setMore(true);
			}else{
				thread.getImplementationObject().copyFrom(result);
			}
			
			more = false;
		}
		
		result = WorkItem.findComment(instanceId);
		while(result.next()){
			thread.moveToInsertRow();
			thread.getImplementationObject().copyFrom(result);
		}
		thread.getMetaworksContext().setWhere(IWorkItem.WHERE_WORKLIST);
		setThread(thread);
		
		User writer = new User();
		writer.copyFrom(session.getUser());
		writer.setMetaworksContext(new MetaworksContext());

		IWorkItem newItem = null;
		if("document".equals(session.getLastPerspecteType())|| "UnlabeledDocument".equals(session.getLastPerspecteType())){
			newItem = new DocWorkItem();
		}else{
			newItem = new CommentWorkItem();
		}
		
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newItem.getMetaworksContext().setWhere(MetaworksContext.WHERE_EVER);
		newItem.setInstId(new Long(getInstanceId()));
		newItem.setWriter(writer);
		
		setNewItem(newItem);
	}
	
	@ServiceMethod(callByContent = true)
	public void more() throws Exception {
		setMore(true);
		setMoreTitle(null);
		
		load(instanceId);
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
		
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IWfNode){
			WfNode draggedNode = (WfNode) clipboard;

			KnowledgeTool knolTool = new KnowledgeTool();
			knolTool.setNodeId(draggedNode.getId());
			knolTool.onLoad();
			
			GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
			genericWIH.setTool(knolTool);

			//setting the first workItem as wfNode referencer
			GenericWorkItem genericWI = new GenericWorkItem();
			genericWI.setMetaworksContext(new MetaworksContext());
			genericWI.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			genericWI.processManager = processManager;
			genericWI.session = session;
			genericWI.setTitle("Attaching Knowledge");//parent.getName());

			genericWI.setGenericWorkItemHandler(genericWIH);
			genericWI.setInstId(new Long(getInstanceId()));
//			genericWI.setTitle(draggedNode.getName());	//title duplicate
			
			// TODO attach thread 
			return genericWI.add();
		}else{
			return null;
		}
	}
	
	@Autowired
	public ProcessManagerRemote processManager;	
}
