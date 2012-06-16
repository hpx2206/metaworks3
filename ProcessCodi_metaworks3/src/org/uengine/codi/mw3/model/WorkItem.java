package org.uengine.codi.mw3.model;

import java.util.ArrayList;
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
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.ToPrepend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Test;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
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
		public WorkItemHandler getWorkItemHandler() {
			return workItemHandler;
		}
		public void setWorkItemHandler(WorkItemHandler workItemHandler) {
			this.workItemHandler = workItemHandler;
		}
		
	GenericWorkItemHandler genericWorkItemHandler;
			
		public GenericWorkItemHandler getGenericWorkItemHandler() {
			return genericWorkItemHandler;
		}
	
		public void setGenericWorkItemHandler(
				GenericWorkItemHandler genericWorkItemHandler) {
			this.genericWorkItemHandler = genericWorkItemHandler;
		}


	WebEditor memo;
		
		
		public WebEditor getMemo() {
			return memo;
		}
	
		public void setMemo(WebEditor memo) {
			this.memo = memo;
		}
	

	String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	String extFile;
			
		public String getExtFile() {
			return extFile;
		}
	
		public void setExtFile(String extFile) {
			this.extFile = extFile;
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

	public void detail() throws Exception{

		Long instId = databaseMe().getInstId(); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		String tracingTag = (String) databaseMe().get("trcTag"); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		
		//WorkItemHandler workItemHandler;
		
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
		
		setWorkItemHandler(workItemHandler);
		
		
		//return workItemHandler;
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
	public IWorkItem newFile() throws Exception {
		// TODO Auto-generated method stub
		FileWorkItem fwi = new FileWorkItem();

		formatWorkItem(fwi);
		
		return fwi;
	}

	@Override
	public IWorkItem newMemo() throws Exception {
		// TODO Auto-generated method stub
		MemoWorkItem wi = new MemoWorkItem();

		formatWorkItem(wi);

		
		return wi;
	}

	private void formatWorkItem(WorkItem wi) {
		wi.setInstId(getInstId());
		wi.setEndpoint(session.getUser().getUserId());
		wi.setWriter(getWriter());
		wi.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		wi.setInstantiation(isInstantiation());
	}


	@Override
	public IWorkItem newComment() throws Exception {
		// TODO Auto-generated method stub
		CommentWorkItem wi = new CommentWorkItem();
		
		String type = wi.getType();
		wi.copyFrom(this);
		wi.setType(type);
		
		return wi;
	}
	
	@Override
	public IWorkItem newSourceCode() throws Exception {
		// TODO Auto-generated method stub
		SourceCodeWorkItem wi = new SourceCodeWorkItem();
		String type = wi.getType();
		wi.copyFrom(this);
		wi.setType(type);
		wi.setSourceCode(new SourceCode());
		
		return wi;
	}
	
	
	boolean instantiation;
		
		public boolean isInstantiation() {
			return instantiation;
		}
	
		public void setInstantiation(boolean instantiation) {
			this.instantiation = instantiation;
		}

		
	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
		
	@Override
	public Object[] add() throws Exception {
		Long taskId = UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext());

		InstanceViewContent instantiatedViewContent = null;
		WfNode parent = null;
		if(instantiation){
			ResourceFile unstructuredProcessDefinition = new ResourceFile();
			unstructuredProcessDefinition.processManager = processManager;
			unstructuredProcessDefinition.session = session;
			unstructuredProcessDefinition.instanceViewContent = instanceViewContent;
			unstructuredProcessDefinition.setAlias(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
						
			Object[] instanceViewAndInstanceList = unstructuredProcessDefinition.initiate();

			instantiatedViewContent = (InstanceViewContent)instanceViewAndInstanceList[0];
			setInstId(new Long(instantiatedViewContent.getInstanceView().getInstanceId()));
			
			//이름 변경  
			instantiatedViewContent.getInstanceView().getInstanceNameChanger().setInstanceName(getTitle());
			instantiatedViewContent.getInstanceView().getInstanceNameChanger().change();
			instantiatedViewContent.getInstanceView().setInstanceName(getTitle());

			Instance instanceRef = new Instance();
			instanceRef.setInstId(new Long
					(instantiatedViewContent.getInstanceView().instanceId));
			instanceRef.databaseMe().setInitEp(session.user.getUserId());
			
			if(session.getEmployee() != null)
				instanceRef.databaseMe().setInitComCd(session.getEmployee().getGlobalCom());
			
		
			if(newInstancePanel!=null){
				
				instanceRef.databaseMe().setSecuopt(newInstancePanel.getSecurityLevel());
				instanceRef.flushDatabaseMe();
				
				if(newInstancePanel.getKnowledgeNodeId() != null){
				
					parent = new WfNode();
					parent.load(newInstancePanel.getKnowledgeNodeId());

					instantiatedViewContent.instanceView.instanceNameChanger.setInstanceName(parent.getName());
					instantiatedViewContent.instanceView.instanceNameChanger.change();

				
					parent.setLinkedInstId(instId);
					parent.save();
					
					//setting the first workItem as wfNode referencer
					GenericWorkItem genericWI = new GenericWorkItem();
					
					genericWI.processManager = processManager;
					genericWI.session = session;
					genericWI.setTitle("지식조각을 첨부합니다");//parent.getName());
					GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
					KnowledgeTool knolTool = new KnowledgeTool();
					knolTool.setNodeId(parent.getId());
					genericWIH.setTool(knolTool);
					
					genericWI.setGenericWorkItemHandler(genericWIH);
					genericWI.setInstId(new Long(instId));
					genericWI.add();
							
				}
			}

		}
		
		setRootInstId(getInstId());
		
		User loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());


		setWriter(loginUser);
		setTaskId(taskId);
		setStartDate(Calendar.getInstance().getTime());
		setEndDate(getStartDate());
		
		if(getTitle().length() > 190){
			setType(new MemoWorkItem().getType());
			setContent(getTitle());
			setTitle(getTitle().substring(0, 190) + "...");
		}

		createDatabaseMe();
		flushDatabaseMe();
		
		final Instance instance = new Instance();
		instance.setInstId(getInstId());
		
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
//		instanceViewContent.load(instance);
		
		getMetaworksContext().setWhen(WHEN_VIEW);
		
		WorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstId()));
		newItem.setTaskId(new Long(-1));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		if(instantiation){
			instantiatedViewContent.getInstanceView().setNewItem(newItem);
			InstanceViewThreadPanel threadPanel = new InstanceViewThreadPanel();
			threadPanel.setInstanceId(getInstId().toString());
			
//			ArrayList<IDAO> workItemArr = new ArrayList<IDAO>();
//			workItemArr.add(this);
//			threadPanel.setThread(workItemArr);
			
			threadPanel.setThread(this);
			
			instantiatedViewContent.getInstanceView().setInstanceViewThreadPanel(threadPanel);
			
			return new Object[]{new Refresh(instantiatedViewContent), new Refresh(parent)};
		}

		
		newItem.setWriter(loginUser);

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
		refreshedInstanceView.session = session;
		
		final IInstance refreshedInstance = instance.databaseMe();
		refreshedInstance.getMetaworksContext().setHow("blinking");
		

		
		//TODO: IF YOU CAN NARROW THE RECEIVERS TO THE FOLLOWERS ONLY IS BEST APPROACH

		//WorkItem copyMe = new WorkItem();
		//copyMe.setTaskId(getTaskId());
		final IWorkItem copyOfThis = this;//copyMe.databaseMe();

		//final IWorkItem copyOfThis = /*new WorkItem();
		//copyOfThis.copyFrom(*/databaseMe();//);
		
		final InstanceViewThreadPanel threadPanelOfThis = new InstanceViewThreadPanel();
		threadPanelOfThis.setInstanceId(getInstId().toString());

		//Add notifications to the followers
		IUser followers = refreshedInstanceView.getFollowers().getFollowers();
		followers.beforeFirst();
		
		ArrayList<String> followerIds = new ArrayList<String>();
		while(followers.next()){
			followerIds.add(followers.getUserId());
		}
		
		boolean iAmParticipating = false;
		for(String followerId : followerIds){
		
			final boolean postByMe = followerId.equals(session.getUser().getUserId());
			if(postByMe){ //ignore myself
				iAmParticipating = true;
			}else{
				Notification noti = new Notification();
				
				noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
				noti.setUserId(followerId);
				noti.setActorId(session.getUser().getUserId());
				noti.setConfirm(false);
				noti.setInputDate(Calendar.getInstance().getTime());
				noti.setTaskId(getTaskId());
				noti.setInstId(getInstId());
				
				
				noti.setActAbstract(session.getUser().getName() + "님이 댓글(활동)을 남겼습니다: " + getTitle());
	
				noti.createDatabaseMe();
				noti.flushDatabaseMe();

			
				String followerSessionId = Login.getSessionIdWithUserId(followerId);
				
				String device = Login.getDeviceWithUserId(followerId);
				
				if("desktop".equals(device) || device==null){
					try{
						//NEW WAY IS GOOD
						Browser.withSession(followerSessionId, new Runnable(){
			
							@Override
							public void run() {
								
								
								ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{new Object[]{new ToAppend(threadPanelOfThis, copyOfThis), new Remover(refreshedInstance), new Remover(refreshedInstance), new ToPrepend(new InstanceList(), refreshedInstance)}, null, "body"});
								
								//refresh notification badge
								if(!postByMe)
									ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
								
								ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});
							}
							
						});
					}catch(Exception e){
						e.printStackTrace(); //may stops due to error occurs when the follower isn't online.
					}
				}else{
					Session.pushMessage(followerId, new ToAppend(threadPanelOfThis, copyOfThis));
					
					NotificationBadge notiBadge = new NotificationBadge();
					notiBadge.setNewItemCount(-1);
					
					Session.pushMessage(followerId, new Refresh(notiBadge));
				}
				
			}
		}
		
		if(!iAmParticipating){
			org.uengine.kernel.RoleMapping newFollower = org.uengine.kernel.RoleMapping.create();
			newFollower.setName("_follower_" + session.getUser().getUserId());
			newFollower.setEndpoint(session.getUser().getUserId());
			
			processManager.putRoleMapping(getInstId().toString(), newFollower);
			
			//refreshedInstanceView.getFollowers().getFollowers().getImplementationObject().moveToInsertRow(session.getUser());
			//refreshedInstanceView.getFollowers().getFollowers()
			
		}
		
		//makes new line and change existing div
		return new Object[]{new Refresh(newItem), new ToAppend(threadPanelOfThis, copyOfThis)};
	}

	@Override
	public Popup newActivity() throws Exception {
//		NewInstancePanel newSubInstancePanel = new NewInstancePanel();
//		newSubInstancePanel.setParentInstanceId(getInstId().toString());
//		
//		newSubInstancePanel.load();

		//Good example :   customizing for specific usage - removing some parts
		//newSubInstancePanel.setUnstructuredProcessInstanceStarter(null);

		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load();
		processMapList.setParentInstanceId(getInstId());
		processMapList.setTitle(getTitle());
		
		Popup popup = new Popup();
		popup.setPanel(processMapList);
		
		return popup;
	}

	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;

	@Autowired
	public InstanceViewContent instanceViewContent;
	

}
