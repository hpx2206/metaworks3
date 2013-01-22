package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceViewThreadPanel implements ContextAware {
	
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
	protected InstanceViewThreadPanel(String instanceId) throws Exception{
		this();
		
		load(instanceId);		

	}
	
	public void load(String instanceId) throws Exception {
		String how = null;
		
		if("sns".equals(session.getEmployee().getPreferUX()))
			how = "sns";
		else
			how = "normal";
		
		getMetaworksContext().setHow(how);
		setInstanceId(instanceId);
				
		IWorkItem result = WorkItem.find(instanceId);
		result.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		result.getMetaworksContext().setHow(how);
		
		setThread(result);
		
		CommentWorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newItem.getMetaworksContext().setHow(how);
		newItem.setWriter(session.getUser());

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
		
	@ServiceMethod(callByContent=true, needToConfirm=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IWfNode){
			WfNode draggedNode = (WfNode) clipboard;

			KnowledgeTool knolTool = new KnowledgeTool();
			knolTool.setNodeId(draggedNode.getId());

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
			
			// TODO attach thread 
			return genericWI.add();
		}else{
			return null;
		}
	}
	
	@Autowired
	public ProcessManagerRemote processManager;	
}
