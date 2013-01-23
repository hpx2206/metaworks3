package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.ToPrepend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Test;
import org.metaworks.dao.Database;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.IFrame;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.webservices.worklist.DefaultWorkList;


public class WorkItem extends Database<IWorkItem> implements IWorkItem{
	
	public WorkItem(){
		this.getMetaworksContext().setWhen(WHEN_NEW);
	}
	
	protected static IWorkItem find(String instanceId) throws Exception{
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, "select * from bpm_worklist where rootInstId=?instId and isdeleted!=?isDeleted");
		
		workitem.set("instId",instanceId);
		workitem.set("isDeleted",1);
		
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
		
	String trcTag;
		public String getTrcTag() {
			return trcTag;
		}
		public void setTrcTag(String trcTag) {
			this.trcTag = trcTag;
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
		
	OverlayCommentOption overlayCommentOption;
		public OverlayCommentOption getOverlayCommentOption() {
			return overlayCommentOption;
		}
		public void setOverlayCommentOption(OverlayCommentOption overlayCommentOption) {
			this.overlayCommentOption = overlayCommentOption;
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
		
	WorkItemVersionChooser workItemVersionChooser;
		public WorkItemVersionChooser getWorkItemVersionChooser() {
			return workItemVersionChooser;
		}
		public void setWorkItemVersionChooser(
				WorkItemVersionChooser workItemVersionChooser) {
			this.workItemVersionChooser = workItemVersionChooser;
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
		
	boolean contentLoaded;
		public boolean isContentLoaded() {
			return contentLoaded;
		}
		public void setContentLoaded(boolean contentLoaded) {
			this.contentLoaded = contentLoaded;
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
		
	int dispatchOption;
		public int getDispatchOption() {
			return dispatchOption;
		}
		public void setDispatchOption(int dispatchOption) {
			this.dispatchOption = dispatchOption;
		}

	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

	String ext1;
		public String getExt1() {
			return ext1;
		}
		public void setExt1(String ext1) {
			this.ext1 = ext1;
		}
		
	String ext2;
		public String getExt2() {
			return ext2;
		}
		public void setExt2(String ext2) {
			this.ext2 = ext2;
		}
		
	String ext3;
		public String getExt3() {
			return ext3;
		}
		public void setExt3(String ext3) {
			this.ext3 = ext3;
		}
		
	String ext4;
		public String getExt4() {
			return ext4;
		}
		public void setExt4(String ext4) {
			this.ext4 = ext4;
		}
		
	String ext5;
		public String getExt5() {
			return ext5;
		}
		public void setExt5(String ext5) {
			this.ext5 = ext5;
		}
		
	String ext6;
		public String getExt6() {
			return ext6;
		}
		public void setExt6(String ext6) {
			this.ext6 = ext6;
		}
		
	String ext7;
		public String getExt7() {
			return ext7;
		}
		public void setExt7(String ext7) {
			this.ext7 = ext7;
		}
		
	String ext8;
		public String getExt8() {
			return ext8;
		}
		public void setExt8(String ext8) {
			this.ext8 = ext8;
		}
		
	String ext9;
		public String getExt9() {
			return ext9;
		}
		public void setExt9(String ext9) {
			this.ext9 = ext9;
		}
		
	String ext10;
		public String getExt10() {
			return ext10;
		}
		public void setExt10(String ext10) {
			this.ext10 = ext10;
		}

	public void like() throws Exception{
		
	}
		
	public void detail() throws Exception{

		IWorkItem workItem = databaseMe();
		
		Long instId = workItem.getInstId(); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		String tracingTag = (String) workItem.get("trcTag"); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		
		//WorkItemHandler workItemHandler;
		
		String tool = workItem.getTool();
		if(tool.startsWith("mw3.")){
			String metaworksHandler = 
				tool.substring(4);
			
			workItemHandler = (WorkItemHandler) Thread.currentThread().getContextClassLoader().loadClass(metaworksHandler).newInstance();
		}else{			
			if(workItemHandler != null && workItemHandler.getTaskId() != null){
				workItemHandler = null;
			}else{
				workItemHandler = new WorkItemHandler();
			}
		}
		
		if(workItemHandler != null){
			workItemHandler.processManager = codiPmSVC;
			workItemHandler.setInstanceId(instId.toString());
			workItemHandler.setTaskId(getTaskId());
			workItemHandler.setTracingTag(tracingTag);
			workItemHandler.setRootInstId(this.getRootInstId());
			
			workItemHandler.setMetaworksContext(new MetaworksContext());
			
			boolean editable = false;
			

				
			if(DefaultWorkList.WORKITEM_STATUS_NEW.equals(getStatus()) || 
			   DefaultWorkList.WORKITEM_STATUS_DRAFT.equals(getStatus()) || 
			   DefaultWorkList.WORKITEM_STATUS_CONFIRMED.equals(getStatus())){
				
				ProcessInstance instance = processManager.getProcessInstance(this.getInstId().toString());			
				HumanActivity humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
				
				
				RoleMapping	roleMapping = humanActivity.getRole().getMapping(instance, tracingTag);
				roleMapping.beforeFirst();
				
				do{
					if(roleMapping.getEndpoint().equals(session.getEmployee().getEmpCode())){
						editable = true;
						
						break;
					}
				}while(roleMapping.next());
			}
			
			if(editable){
				if(this.getDispatchOption() == 1){
					workItemHandler.getMetaworksContext().setWhen("compete");
				}else{
					workItemHandler.getMetaworksContext().setWhen(WHEN_EDIT);
				}
				
			}else{
				workItemHandler.getMetaworksContext().setWhen(WHEN_VIEW);
			}
		
			workItemHandler.session = session;
			
			workItemHandler.load();
		}
		
		
		//setWorkItemHandler(workItemHandler);
		
		
		//return workItemHandler;
	}
	
	@Override
	public void loadContents() throws Exception {
		//only lazy loading needed workitems will use this method
		typedDatabaseMe().loadContents();
		setContentLoaded(true);
	}

	
	
	public ModalWindow workItemPopup() throws Exception{
		

		
		Object result = null;
		
		if("file".equals(this.getType())){
			String path = "images/pdf/" +this.getGrpTaskId() + "_" + String.valueOf(this.getMajorVer()) + "_" + String.valueOf(this.getMinorVer()) + ".pdf";
			IFrame iframe = new IFrame(path);
			iframe.setWidth("100%");
			iframe.setHeight("98%");
			
			result = iframe;
		}else{
			this.workItemHandler = null;
			detail();			
			result = workItemHandler; 
		}
		
		return new ModalWindow(result, 0, 0, getTitle());
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
	

	private void formatWorkItem(WorkItem wi) {
		wi.setTitle(this.getTitle());
		wi.setInstId(getInstId());
		wi.setEndpoint(session.getUser().getUserId());
		wi.setWriter(getWriter());
		wi.setMetaworksContext(this.getMetaworksContext());
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
	public IWorkItem newComment() throws Exception {
		CommentWorkItem wi = new CommentWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	@Override
	public IWorkItem newMemo() throws Exception {
		MemoWorkItem wi = new MemoWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	@Override
	public IWorkItem newFile() throws Exception {
		FileWorkItem wi = new FileWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	@Override
	public IWorkItem newSourceCode() throws Exception {
		SourceCodeWorkItem wi = new SourceCodeWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}

/*	private void formatWorkItem(WorkItem wi) {
		wi.setInstId(getInstId());
		wi.setEndpoint(session.getUser().getUserId());
		wi.setWriter(getWriter());
		wi.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		wi.getMetaworksContext().setWhere(this.getMetaworksContext().getWhere());		
		wi.getMetaworksContext().setHow(this.getMetaworksContext().getHow());
		wi.setInstantiation(isInstantiation());
	}
*/
	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	int majorVer;
		public int getMajorVer() {
			return majorVer;
		}
	
		public void setMajorVer(int majorVer) {
			this.majorVer = majorVer;
		}

		
	int minorVer;
		public int getMinorVer() {
			return minorVer;
		}
	
		public void setMinorVer(int minorVer) {
			this.minorVer = minorVer;
		}
		
		
	Long grpTaskId;
		
		public Long getGrpTaskId() {
			return grpTaskId;
		}
	
		public void setGrpTaskId(Long grpTaskId) {
			this.grpTaskId = grpTaskId;
		}

		
	boolean isDeleted;	
		
		public boolean getIsDeleted() {
			return isDeleted;
		}
	
		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

	public IInstance save() throws Exception {
		
		IInstance instanceRef = null;		
		
		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen()) || this instanceof FileWorkItem){
			// 인스턴스 발행
			if(this.getInstId() == null){
				// 인스턴스 발행을 위한 ProcessMap 사용
				ProcessMap processMap = new ProcessMap();
				processMap.processManager = processManager;
				processMap.session = session;
				processMap.setDefId(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
				
				String instId = processMap.initializeProcess();
				
				// 덧글 상태일때 덧글에 입력된 사용자명 자동으로 follower 에 추가해주는 기능
				if(this instanceof CommentWorkItem){
					ArrayList<String> initialFollowers = ((CommentWorkItem)this).initialFollowers;
					if(initialFollowers!=null){
						for(String userId : initialFollowers){
							
							RoleMapping follower = RoleMapping.create();
							follower.setEndpoint(userId);
							follower.setName("fol_" + userId);

							processManager.putRoleMapping(instId, follower);
						}
					}
				}

				// WorkItem 의 InstId 할당
				this.setInstId(new Long(instId));
				
				// 기본 정보 설정  
				Instance instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();				
				
				instanceRef.setInitCmpl(true);										// 기본값 수정 시작자만 완료 가능하게
				instanceRef.setInitiator(session.getUser());						// 시작자는 실행한 사람
				instanceRef.setInitComCd(session.getEmployee().getGlobalCom());		// 시작자의 회사
				instanceRef.setStatus("Running");									// 처음 상태 Running
				instanceRef.setDueDate(getDueDate());
				instanceRef.setName(this.getTitle());	
				
				afterInstantiation(instanceRef);				
			}else{
				Instance instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();
			}
			
			// 마지막 워크아이템의 제목을 인스턴스의 적용
			instanceRef.setLastCmnt(getTitle());
			instanceRef.setCurrentUser(session.getUser());//may corrupt when the last actor is assigned from process execution.
								
			IUser writer = new User();
			writer.setUserId(session.getUser().getUserId());
			writer.setName(session.getUser().getName());
			
			if(this.getTaskId() == null)
				this.setTaskId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()));
			
			this.setWriter(writer);
			this.setStartDate(Calendar.getInstance().getTime());
			this.setEndDate(getStartDate());
			this.setStatus(WORKITEM_STATUS_FEED);
			this.setIsDeleted(false);			

			if(this.getRootInstId() == null)
				this.setRootInstId(this.getInstId());
			
			// 덧글 상태일때 덧글이 길면 메모로 변경해주는 기능
			if(this instanceof CommentWorkItem){
				if(this.getTitle().length() > TITLE_LIMIT_SIZE){
					this.setType(WORKITEM_TYPE_MEMO);
					this.setContent(getTitle());
					this.setTitle(getTitle().substring(0, TITLE_LIMIT_SIZE) + "...");
				}
			}
			
			this.createDatabaseMe();
			
		// 수정
		}else{		
			this.syncToDatabaseMe();
		}		
		
		this.flushDatabaseMe();
		
		return instanceRef;
	}
	
	public Object[] makeReturn(Long prevInstId, IInstance instanceRef) throws Exception {
		
		Object[] returnObjects = null;
		String mood = session.getEmployee().getPreferUX();
		
		this.getMetaworksContext().setHow(mood);
		
		Instance instance = new Instance();
		instance.copyFrom(instanceRef);
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;

		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen())){
			this.getMetaworksContext().setWhen(WHEN_VIEW);
			
			final IWorkItem copyOfThis = this;
			final IInstance copyOfInstance = instance;
			copyOfInstance.getMetaworksContext().setWhen("blinking");
			
			// 인스턴스 발행
			if(prevInstId == null){
				Object detail = instance.detail();
				
				if("sns".equals(mood)){
					NewInstancePanel panel = new NewInstancePanel();
					panel.load(session);
					
					returnObjects = new Object[]{new ToPrepend(new InstanceList(), detail), new Refresh(panel)};
				}else
					returnObjects = new Object[]{new ToPrepend(new InstanceList(), instance), new Refresh(detail)};								
			// 덧글
			}else{
				InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
				instanceViewThreadPanel.setInstanceId(this.getInstId().toString());
				
				if(this instanceof OverlayCommentWorkItem){
					WorkItem parentWorkItem = new WorkItem();
					parentWorkItem.setTaskId(getOverlayCommentOption().getParentTaskId());
					
					returnObjects = new Object[]{new ToAppend(parentWorkItem, this)};
				}else if(this instanceof CommentWorkItem){		
					if("memo".equals(this.getType())){
						MemoWorkItem memo = new MemoWorkItem();						
						memo.copyFrom(this);
						memo.setMemo(new WebEditor(this.getContent()));

						returnObjects = new Object[]{new Refresh(memo, false, true)};
					}else{
						returnObjects = new Object[]{new Refresh(this, false, true)};	
					}
					
				}else{
					returnObjects = new Object[]{new ToAppend(instanceViewThreadPanel, this)};
				}
				
				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(copyOfInstance)});
			}
			
			// 본인 이외에 다른 사용자에게 push			
			MetaworksRemoteService.pushOtherClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(copyOfInstance), new WorkItemListener(copyOfThis)});
			
		// 수정
		}else{		
			this.getMetaworksContext().setWhen(WHEN_VIEW);
			
			// 변경된 WorkItem 을 갱신
			returnObjects = new Object[]{new Refresh(this, true)};
			
			// 본인 이외에 다른 사용자에게 push
			final IWorkItem copyOfThis = this;
			
			MetaworksRemoteService.pushOtherClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new WorkItemListener(copyOfThis)});
		}		
		
		return returnObjects;
	}
	
	@Override
	@Test(scenario="first", starter=true, instruction="$Write", next="newActivity()")
	public Object[] add() throws Exception {
		Object[] returnObjects = null;
		
		Long prevInstId = this.getInstId();
		
		Instance instance = new Instance();
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;
		instance.copyFrom(this.save());
		
		return makeReturn(prevInstId, instance);
	}
	
	public Object remove() throws Exception{
		
		if(session.getUser().getUserId().equals(getWriter().getUserId()) || (session.getEmployee()!=null && session.getEmployee().getIsAdmin())){
			deleteDatabaseMe();
			return new Remover(this);
		}

		Instance theInstance = new Instance();
		theInstance.setInstId(getInstId());
		
		if(theInstance.databaseMe().getInitiator().getUserId().equals(session.getUser().getUserId())){
			deleteDatabaseMe();
			return new Remover(this);
			
		}
				
		throw new Exception("$OnlyTheWriterCanDelete");
		
	}

	public void edit() throws Exception{
		if(!session.getUser().getUserId().equals(getWriter().getUserId())){
			throw new Exception("$OnlyTheWriterCanEdit");
		}
		
		setDueDate(null);
		setStartDate(null);
		setEndDate(null);		
	
		getMetaworksContext().setWhen("edit");		
	}

	protected void afterInstantiation(IInstance instanceRef) throws Exception {
		
		if(newInstancePanel!=null){
			//instanceRef.databaseMe().setSecuopt(newInstancePanel.getSecurityLevel());
			instanceRef.setSecuopt(newInstancePanel.getSecurityLevel().getSelected());			
			
			if(newInstancePanel.getDueDate() != null){
				instanceRef.setDueDate(newInstancePanel.getDueDate());
			}
			
			if(newInstancePanel.getTopicNodeId() != null){
				instanceRef.setTopicId(newInstancePanel.getTopicNodeId());
			}
			
			if(newInstancePanel.getKnowledgeNodeId() != null){			
				WfNode parent = new WfNode();
				parent.setLoadDepth(WfNode.LOAD_DEPTH - 1);
				parent.load(newInstancePanel.getKnowledgeNodeId());
				
				instanceRef.setName(parent.getName());
			
				parent.setLinkedInstId(instId);
				parent.save();
				
				//setting the first workItem as wfNode referencer
				GenericWorkItem genericWI = new GenericWorkItem();
				
				genericWI.processManager = processManager;
				genericWI.session = session;
				genericWI.setTitle("Attaching Knowledge");//parent.getName());
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

	@Override
	public Popup newActivity() throws Exception {
//		NewInstancePanel newSubInstancePanel = new NewInstancePanel();
//		newSubInstancePanel.setParentInstanceId(getInstId().toString());
//		
//		newSubInstancePanel.load();

		//Good example :   customizing for specific usage - removing some parts
		//newSubInstancePanel.setUnstructuredProcessInstanceStarter(null);

		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);
		processMapList.setParentInstanceId(getInstId());
		processMapList.setTitle(getTitle());
		
		Popup popup = new Popup();
		popup.setPanel(processMapList);
		
		return popup;
	}
	

	@Override
	public OverlayCommentWorkItem comment() throws Exception {
		
		OverlayCommentOption overlayCommentOption = new OverlayCommentOption();
		
		overlayCommentOption.setParentTaskId(getTaskId());
		overlayCommentOption.setX("1"); 
		overlayCommentOption.setY("1");
		
		OverlayCommentWorkItem overlayCommentWorkItem = new OverlayCommentWorkItem();
		overlayCommentWorkItem.setOverlayCommentOption(overlayCommentOption);
		overlayCommentWorkItem.setInstId(getInstId());
		
//		overlayCommentWorkItem.session = session;
//		overlayCommentWorkItem.processManager = processManager;
//		
//		overlayCommentWorkItem.add();
//		
		
		//return new slot 
		
		return overlayCommentWorkItem; 
	}

	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
}
