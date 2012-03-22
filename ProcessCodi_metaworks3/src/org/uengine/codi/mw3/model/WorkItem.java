package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.persistence.dao.DAOFactory;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class WorkItem extends Database<IWorkItem> implements IWorkItem{
	
	
	public WorkItem(){}
	
	protected static IWorkItem find(String instanceId) throws Exception{
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, "select * from bpm_worklist where instId=?instId");
		
		workitem.set("instId",instanceId);
		
		//TODO: this expression should be work later instead of above.
		//IUser user = new User();
		//user.setEndpoint(login.getEmpCode());
		//workitem.setWriter(user);
		
		workitem.select();
		
		return workitem;
	}
	
	public IWorkItem find() throws Exception{
		
		return find(getInstId().toString());
	}


	Long instId;
	@Hidden
		public Long getInstId() {
			return instId;
		}
		public void setInstId(Long instId) {
			this.instId = instId;
		}


	IUser writer;
		public IUser getWriter() {
			return writer;
		}
		public void setWriter(IUser writer) {
			this.writer = writer;
		}


	String title;
	@Hidden
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}


	boolean like;
	@Hidden
		public boolean isLike() {
			return like;
		}
		public void setLike(boolean like) {
			this.like = like;
		}
		
	Long taskId;
	@Hidden
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	String endpoint;	
		
	@Hidden
		public String getEndpoint() {
			return endpoint;
		}
	
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}
		
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}

	public void like() throws Exception{
		
	}

	//normally it is null, but the 'detail' button pressed, it should be activated (by value set)
	WorkItemHandler workItemHandler;
	@Hidden
		public WorkItemHandler getWorkItemHandler() {
			return workItemHandler;
		}
		public void setWorkItemHandler(WorkItemHandler workItemHandler) {
			this.workItemHandler = workItemHandler;
		}
		
	String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	SourceCode sourceCode;
		public SourceCode getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(SourceCode sourceCode) {
			this.sourceCode = sourceCode;
		}
		
	String tool;
	@Hidden
		public String getTool() {
			return tool;
		}
		public void setTool(String tool) {
			this.tool = tool;
		}

	public Window detail() throws Exception{

		Long instId = databaseMe().getInstId(); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		String tracingTag = (String) databaseMe().get("trcTag"); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		
		WorkItemHandler workItemHandler;
		
		String tool = databaseMe().getTool();
		if(tool.startsWith("mw3.")){
			String metaworksHandler = 
				tool.substring(4);
			
			workItemHandler = (WorkItemHandler) Thread.currentThread().getContextClassLoader().loadClass(metaworksHandler).newInstance();
		}else{
			workItemHandler = new WorkItemHandler();
		}
		
		workItemHandler.processManager = codiPmSVC;
		workItemHandler.setInstanceId(instId.toString());
		workItemHandler.setTaskId(getTaskId());
		workItemHandler.setTracingTag(tracingTag);
		
		workItemHandler.load();
		
		return new Window(workItemHandler);
	}
	
	@Autowired
	public ProcessManagerRemote codiPmSVC; //this is needed in order to give to WorkItemHandler due to Spring's propagated injection with Autowired is not working now

	
	String type;
		public String getType() {
			return type;
		}
	
		public void setType(String type) {
			this.type = type;
		}

	@Override
	public IWorkItem newSchedule() {
		return new ScheduleWorkItem();
	}

	@Override
	public IWorkItem newImage() {
		// TODO Auto-generated method stub
		return new ImageWorkItem();
	}

	@Override
	public IWorkItem newMovie() {
		// TODO Auto-generated method stub
		return new MovieWorkItem();
	}

	@Override
	public IWorkItem newFile() {
		// TODO Auto-generated method stub
		FileWorkItem fwi = new FileWorkItem();
		fwi.setInstId(getInstId());
		fwi.setEndpoint(session.getUser().getUserId());
		fwi.setWriter(getWriter());
		fwi.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);

		return fwi;
	}


	@Override
	public IWorkItem newComment() {
		// TODO Auto-generated method stub
		CommentWorkItem wi = new CommentWorkItem();
		wi.setInstId(getInstId());
		wi.setEndpoint(session.getUser().getUserId());
		wi.setWriter(getWriter());
		wi.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		return wi;
	}
	

	@Override
	public IWorkItem newSourceCode() {
		// TODO Auto-generated method stub
		SourceCodeWorkItem wi = new SourceCodeWorkItem();
		wi.setInstId(getInstId());
		wi.setEndpoint(session.getUser().getUserId());
		wi.setWriter(getWriter());
		wi.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		return wi;
	}
	
	@Override
	public WorkItem[] add() throws Exception {
		Long taskId = UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext());
		
		setTaskId(taskId);
		
		createDatabaseMe();
		flushDatabaseMe();
		
		Instance instance = new Instance();
		instance.setInstId(getInstId());
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
//		instanceViewContent.load(instance);
		
		getMetaworksContext().setWhen(WHEN_VIEW);
		
		WorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstId()));
		newItem.setTaskId(new Long(getInstId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		User loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());

		newItem.setWriter(loginUser);

		//makes new line and change existing div
		return new WorkItem[]{this, newItem};
	}

	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;

	@Autowired
	public InstanceViewContent instanceViewContent;

}
