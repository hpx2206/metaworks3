package org.uengine.codi.mw3.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.digester.SetRootRule;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.Database;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class WorkItem extends Database<IWorkItem> implements IWorkItem{
	
	
	public WorkItem(){}
	
	protected static IWorkItem find(String instanceId) throws Exception{
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, "select * from bpm_worklist where rootInstId=?instId");
		
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

	Long rootInstId;

		public Long getRootInstId() {
			return rootInstId;
		}
	
		public void setRootInstId(Long rootInstId) {
			this.rootInstId = rootInstId;
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
		
	Date startDate;
			
		public Date getStartDate() {
			return startDate;
		}
	
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

	Date endDate;
	
		public Date getEndDate() {
			return endDate;
		}
	
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		
	Date dueDate;
	
		public Date getDueDate() {
			return dueDate;
		}
	
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
		
	Date saveDate;

		public Date getSaveDate() {
			return saveDate;
		}
	
		public void setSaveDate(Date saveDate) {
			this.saveDate = saveDate;
		}


	String tool;
	@Hidden
		public String getTool() {
			return tool;
		}
		public void setTool(String tool) {
			this.tool = tool;
		}

	String status;	
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}

	public WorkItemHandler detail() throws Exception{

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
		
		workItemHandler.setMetaworksContext(new MetaworksContext());
		workItemHandler.getMetaworksContext().setWhen(getStatus());
		
		workItemHandler.load();
		
		
		return workItemHandler;
	}
	
	public ModalWindow workItemPopup() throws Exception{
		
		detail();
		
		ModalWindow workItemHandlerModal = new ModalWindow(workItemHandler, 800, 0, getTitle());

		return workItemHandlerModal;
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
		
		setRootInstId(getInstId());
		
		User loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());


		setWriter(loginUser);
		setTaskId(taskId);
		setStartDate(Calendar.getInstance().getTime());
		setEndDate(getStartDate());
////		
		createDatabaseMe();
		flushDatabaseMe();
		
		final Instance instance = new Instance();
		instance.setInstId(getInstId());
		
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
//		instanceViewContent.load(instance);
		
		getMetaworksContext().setWhen(WHEN_VIEW);
		
		WorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstId()));
		newItem.setTaskId(new Long(getInstId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		//newItem.setWriter(loginUser);

		//OLD WAY
//		//let the other's list updated
//		WebContext wctx = WebContextFactory.get();
//		String currentPage = wctx.getCurrentPage();
//
//		   Collection sessions = wctx.getScriptSessionsByPage(currentPage);
//
//		   Util utilAll = new Util(sessions);
//		   utilAll.addFunctionCall("mw3.getAutowiredObject('org.uengine.codi.mw3.model.InstanceView').activityStream");

		
		final InstanceView refreshedInstanceView = new InstanceView();
		refreshedInstanceView.processManager = processManager;
		refreshedInstanceView.load(instance);
		
		final IInstance refreshedInstance = instance.databaseMe();
		refreshedInstance.getMetaworksContext().setHow("blinking");
		
		//NEW WAY IS GOOD
		Browser.withAllSessions(new Runnable(){

			@Override
			public void run() {
				ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{new Object[]{new Refresh(refreshedInstanceView), new Refresh(refreshedInstance)}, null, "body"});
				ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});

			}
			
		});
		
		//TODO: IF YOU CAN NARROW THE RECEIVERS TO THE FOLLOWERS ONLY IS BEST APPROACH


		//makes new line and change existing div
		return new WorkItem[]{/*this*/newItem};
	}

	@Override
	public Popup newActivity() throws Exception {
		NewInstancePanel newSubInstancePanel = new NewInstancePanel();
		newSubInstancePanel.setParentInstanceId(getInstId().toString());
		
		newSubInstancePanel.load();

		//Good example :   customizing for specific usage - removing some parts
		newSubInstancePanel.setUnstructuredProcessInstanceStarter(null);
		
		Popup popup = new Popup();
		popup.setPanel(newSubInstancePanel);
		
		return popup;
	}

	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;

	@Autowired
	public InstanceViewContent instanceViewContent;
	

}
