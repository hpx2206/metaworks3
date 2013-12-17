package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
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
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.calendar.ScheduleCalendarEvent;
import org.uengine.codi.mw3.filter.AllSessionFilter;
import org.uengine.codi.mw3.filter.OtherSessionFilter;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.TopicNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.search.solr.SolrData;
import org.uengine.webservices.worklist.DefaultWorkList;


public class WorkItem extends Database<IWorkItem> implements IWorkItem{
	
	public final static String USE_BBB = GlobalContext.getPropertyString("bbb.use", "0");
	
	public WorkItem(){
		this.getMetaworksContext().setWhen(WHEN_NEW);
		this.setUseBBB("1".equals(USE_BBB));
	}
	
	protected static IWorkItem find(String instanceId) throws Exception{
		
		//String sql = "select * from bpm_worklist where rootInstId=?instId and isdeleted!=?isDeleted order by taskId";
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where rootInstId=?instId");
		sql.append("   and isdeleted!=?isDeleted");
		sql.append(" order by taskId");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("instId",instanceId);
		workitem.set("isDeleted",1);
		
		//TODO: this expression should be work later instead of above.
		//IUser user = new User();
		//user.setEndpoint(login.getEmpCode());
		//workitem.setWriter(user);
		
		workitem.select();
		
		return workitem;
	}
	
	protected static IWorkItem findComment(String instanceId) throws Exception{
		
		StringBuffer sql = new StringBuffer();
				
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where rootInstId=?instId");
		sql.append("   and isdeleted!=?isDeleted");
		sql.append("   and type in ('ovryCmnt') ");
		sql.append(" order by taskId");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("instId", instanceId);
		workitem.set("isDeleted",1);
		
		
		//TODO: this expression should be work later instead of above.
		//IUser user = new User();
		//user.setEndpoint(login.getEmpCode());
		//workitem.setWriter(user);
		
		workitem.select();
		
		return workitem;
	}
	
	protected static IWorkItem findParentWorkItemByType(String taskId , String type) throws Exception{
		
		StringBuffer sql = new StringBuffer();
				
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where prtTskId = ?prtTskId");
		sql.append("   and isdeleted != ?isDeleted");
		sql.append("   and type = ?type ");
		sql.append(" order by taskId");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("type", type);
		workitem.set("prtTskId", taskId);
		workitem.set("isDeleted", 1);
		workitem.select();
		
		return workitem;
	}
	
	protected static IWorkItem find(String instanceId, int count) throws Exception{
		
		//String sql = "select * from bpm_worklist where rootInstId=?instId and isdeleted!=?isDeleted order by taskId";
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where rootInstId=?instId");
		sql.append("   and isdeleted!=?isDeleted");		
		sql.append("   and (type  not in ('ovryCmnt' , 'replyCmnt') or type is null)");
		sql.append(" order by taskId desc");
		sql.append(" limit " + (count + 1));
		sql.append(") worklist order by taskId ");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("instId", instanceId);
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
	
	@AutowiredFromClient
	public ScheduleCalendar scheduleCalendar;
	
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
	
	String folderId;
		public String getFolderId() {
			return folderId;
		}
	
		public void setFolderId(String folderId) {
			this.folderId = folderId;
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
		
	Preview preview;
		public Preview getPreview() {
			return preview;
		}
		public void setPreview(Preview preview) {
			this.preview = preview;
		}
	
	RemoteConferenceWorkItem remoteConf;
		public RemoteConferenceWorkItem getRemoteConf() {
			return remoteConf;
		}
		public void setRemoteConf(RemoteConferenceWorkItem remoteConf) {
			this.remoteConf = remoteConf;
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

	boolean more;
		public boolean isMore() {
			return more;
		}	
		public void setMore(boolean more) {
			this.more = more;
		}

	public DocumentDrag documentDrag;	
		public DocumentDrag getDocumentDrag() {
			return documentDrag;
		}
	
		public void setDocumentDrag(DocumentDrag documentDrag) {
			this.documentDrag = documentDrag;
		}

	public void like() throws Exception{
		
	}
	
	public void detail() throws Exception{

		IWorkItem workItem = databaseMe();
		
		Long instId = workItem.getInstId(); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		String tracingTag = (String) workItem.get("trcTag"); //since it knows metaworks IDAO will load all the field members from the table beyond the listed by setter/getter.
		
		//WorkItemHandler workItemHandler;
		
		String tool = workItem.getTool();
		if(tool != null && tool.startsWith("mw3.")){
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
			boolean existRoleMapping = false;
			
			if(DefaultWorkList.WORKITEM_STATUS_NEW.equals(getStatus()) || 
			   DefaultWorkList.WORKITEM_STATUS_DRAFT.equals(getStatus()) || 
			   DefaultWorkList.WORKITEM_STATUS_CONFIRMED.equals(getStatus())){
				
				ProcessInstance instance = processManager.getProcessInstance(this.getInstId().toString());			
				HumanActivity humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
				
				
				RoleMapping	roleMapping = humanActivity.getRole().getMapping(instance, tracingTag);
				
				if(roleMapping != null){
					existRoleMapping = true;
					
					roleMapping.beforeFirst();
					
					do{
						if(roleMapping.getEndpoint().equals(session.getEmployee().getEmpCode())){
							editable = true;
							
							break;
						}
					}while(roleMapping.next());
				}
			}
			
			if(editable){
				if(this.getDispatchOption() == 1){
					workItemHandler.getMetaworksContext().setWhen("compete");
				}else{
					workItemHandler.getMetaworksContext().setWhen(WHEN_EDIT);
				}
				
			}else if(!existRoleMapping && this.getDispatchOption() == 1){
				workItemHandler.getMetaworksContext().setWhen("compete");
			}else{
				workItemHandler.getMetaworksContext().setWhen(WHEN_VIEW);
			}
		
			workItemHandler.session = session;
			
			workItemHandler.load();
		}
		this.contentLoaded = true;
		
		//setWorkItemHandler(workItemHandler);
		
		
		//return workItemHandler;
	}
	
	
	public void loadContents() throws Exception {
		//only lazy loading needed workitems will use this method
		typedDatabaseMe().loadContents();
		setContentLoaded(true);
	}

	
	
	public ModalWindow workItemPopup() throws Exception{
		
		Object result = null;
		ModalWindow modalWindow = new ModalWindow();
		
		if("file".equals(this.getType())){
			String path = "preview/" +this.getTaskId() + ".pdf";
			IFrame iframe = new IFrame(path);
			iframe.setWidth("100%");
			iframe.setHeight("98%");
			
			result = iframe;
		}else if(this.getType() == null){
			this.workItemHandler = null;
			detail();			
			result = workItemHandler; 
		}else{
			throw new Exception("$CannotOpen");
		}
		
		modalWindow.setPanel(result);
		modalWindow.setWidth(1000);
		modalWindow.setHeight(800);
		modalWindow.setTitle(title);
		
		return modalWindow;//(result, 0, 0, getTitle());
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
		
	
	public IWorkItem newSchedule() {
		return new ScheduleWorkItem();
	}

	
	public IWorkItem newImage() {
		// TODO Auto-generated method stub
		return new ImageWorkItem();
	}

	
	public IWorkItem newMovie() {
		// TODO Auto-generated method stub
		return new MovieWorkItem();
	}


	
	public IWorkItem newComment() throws Exception {
		CommentWorkItem wi = new CommentWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	
	public IWorkItem newMemo() throws Exception {
		MemoWorkItem wi = new MemoWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	
	public IWorkItem newRemoteConference() throws Exception {
		RemoteConferenceWorkItem wi = new RemoteConferenceWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	
	public IWorkItem newFile() throws Exception {
		FileWorkItem wi = new FileWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
	public IWorkItem newDocument() throws Exception{
		DocWorkItem wi = new DocWorkItem();
		formatWorkItem(wi);
		
		return wi;
	}
	
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
		
	String folderName;
		public String getFolderName() {
			return folderName;
		}
	
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}

	Long prtTskId;
		public Long getPrtTskId() {
			return prtTskId;
		}
		public void setPrtTskId(Long prtTskId) {
			this.prtTskId = prtTskId;
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
	
	boolean useBBB;
		public boolean isUseBBB() {
			return useBBB;
		}
		public void setUseBBB(boolean useBBB) {
			this.useBBB = useBBB;
		}

	public IInstance save() throws Exception {
		
		boolean newInstance = false;
		IInstance instanceRef = null;		
		
		
		IUser writer = new User();
		if( this.getWriter() == null || (this.getWriter() != null && this.getWriter().getUserId() == null)){
			writer.setUserId(session.getUser().getUserId());
			writer.setName(session.getUser().getName());
			this.setWriter(writer);
		}
		
		
		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen()) || this instanceof FileWorkItem){
			// 인스턴스 발행
			if(this.getInstId() == null){
				newInstance = true;
				
				// 인스턴스 발행을 위한 ProcessMap 사용
				ProcessMap processMap = new ProcessMap();
				processMap.processManager = processManager;
				processMap.session = session;
				processMap.setDefId(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
				
				String instId = processMap.initializeProcess();
				processMap.executeProcess(instId);
				
				// WorkItem 의 InstId 할당
				this.setInstId(new Long(instId));
				
				// 기본 정보 설정  
				Instance instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();	

				instanceRef.setInitCmpl(false);										// 기본값 수정 시작자만 완료 가능하게
				instanceRef.setInitiator(this.getWriter());
				instanceRef.setInitComCd(session.getEmployee().getGlobalCom());		// 시작자의 회사
				instanceRef.setStatus(WORKITEM_STATUS_RUNNING);									// 처음 상태 Running
				instanceRef.setDueDate(getDueDate());
				instanceRef.setName(this.getTitle());
				if(this.getFolderId() != null){
					instanceRef.setTopicId(this.getFolderId());
				}
				
				if(WORKITEM_TYPE_FILE.equals(this.getType()) || WORKITEM_TYPE_DOCUMENT.equals(this.getType()) || WORKITEM_TYPE_GENERIC.equals(this.getType()))
					instanceRef.setIsFileAdded(true);
				
				instanceRef.setIsDocument(WorkItem.WORKITEM_TYPE_FILE.equals(this.getType()));
				
				
				
				afterInstantiation(instanceRef);				
			}else{
				Instance instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();
				
				if(this.getDueDate()!= null)
					instanceRef.setDueDate(this.getDueDate());
						
				// 덧글일 때 WorkItem 추가하는 사용자가 팔로워에 추가되어 있지 않다면 추가작업
				instance.fillFollower();

				IUser followers = instance.getFollowers().getFollowers();
				boolean existFollower = false;
				
				if(followers != null){
					followers.beforeFirst();
					
					while(followers.next()){
						if(followers.getUserId().equals(session.getUser().getUserId())){
							existFollower = true;
							
							break;
						}
					}
				}	
						
				if(!existFollower){
					RoleMapping newFollower = RoleMapping.create();
					newFollower.setName(Followers.CONTEXT_WHERE_INFOLLOWERS + session.getUser().getUserId());
					newFollower.setEndpoint(session.getUser().getUserId());
					
					processManager.putRoleMapping(getInstId().toString(), newFollower);
					processManager.applyChanges();
				}
			}
			// 덧글 상태일때 덧글에 입력된 사용자명 자동으로 follower 에 추가해주는 기능
			if(this instanceof CommentWorkItem){
				ArrayList<String> initialFollowers = ((CommentWorkItem)this).initialFollowers;
				if(initialFollowers!=null){
					for(String userId : initialFollowers){
						
						RoleMapping follower = RoleMapping.create();
						follower.setName(org.uengine.codi.mw3.model.RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + userId);
						follower.setEndpoint(userId);						

						processManager.putRoleMapping(this.getInstId().toString() , follower);
						processManager.applyChanges();
					}
				}
			}
			
			if(this instanceof RemoteConferenceWorkItem){
				ArrayList<String> initialFollowers = ((RemoteConferenceWorkItem)this).initialFollowers;
				if(initialFollowers!=null){
					for(String userId : initialFollowers){
						
						RoleMapping follower = RoleMapping.create();
						follower.setName(org.uengine.codi.mw3.model.RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + userId);
						follower.setEndpoint(userId);						

						processManager.putRoleMapping(this.getInstId().toString() , follower);
						processManager.applyChanges();
					}
				}
			}
			
			if(!newInstance){
				//마지막 워크아이템의 제목을 인스턴스의 적용
				String lastCmnt = instanceRef.getLastCmnt();
				// title 이 LASTCMT_LIMIT_SIZE 보다 크다면 사이즈를 조절함
				String cmntTitle = this.getTitle();
				if(cmntTitle.length() > LASTCMT_LIMIT_SIZE){
					cmntTitle = getTitle().substring(0, LASTCMT_LIMIT_SIZE - 5) + "..." ;
				}
				
				if(WORKITEM_TYPE_FILE.equals(this.getType()) || WORKITEM_TYPE_DOCUMENT.equals(this.getType()) || WORKITEM_TYPE_GENERIC.equals(this.getType()))
					instanceRef.setIsFileAdded(true);
				
				if(lastCmnt == null){
					instanceRef.setLastCmnt(cmntTitle);
					instanceRef.setLastCmntUser(session.getUser());
				}else{
					if(instanceRef.getLastCmnt2() == null){
						instanceRef.setLastCmnt2(cmntTitle);
						instanceRef.setLastCmnt2User(session.getUser());
					}else {
						instanceRef.setLastCmnt(instanceRef.getLastCmnt2());
						instanceRef.setLastCmntUser(instanceRef.getLastCmnt2User());
						
						instanceRef.setLastCmnt2(cmntTitle);
						instanceRef.setLastCmnt2User(session.getUser());
					}
				}				
			}
			instanceRef.setCurrentUser(this.getWriter());//may corrupt when the last actor is assigned from process execution.
								

			
			if(this.getTaskId() == null || this.getTaskId() == -1)
				this.setTaskId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()));
			
			
			
			if(this.getTitle() == null && WORKITEM_TYPE_FILE.equals(this.getType()) ||
					this.getTitle() == null && WORKITEM_TYPE_GENERIC.equals(this.getType()))
				this.setTitle(new String(this.getFile().getFilename()));
				
			//기존 date 추가 부분
			this.setStartDate(Calendar.getInstance().getTime());
			this.setEndDate(getStartDate());
			this.setStatus(WORKITEM_STATUS_FEED);
			this.setIsDeleted(false);			
			if(this.getRootInstId() == null)
				this.setRootInstId(this.getInstId());
			
			// 덧글 상태일때 덧글이 길면 메모로 변경해주는 기능
			if(this instanceof CommentWorkItem && !(this instanceof SystemWorkItem)){
				if(this.getTitle().length() > TITLE_LIMIT_SIZE){
					this.setType(WORKITEM_TYPE_MEMO);
					this.setContent(getTitle());
					this.setTitle(getTitle().substring(0, TITLE_LIMIT_SIZE) + "...");
				}
			}
			
			this.createDatabaseMe();
			Instance tempInstance = new Instance();
			tempInstance.setInstId(this.getInstId());
			tempInstance.databaseMe().setStatus(WORKITEM_STATUS_RUNNING);
		// 수정
		}else{
			Instance instance = new Instance();
			instance.setInstId(this.getInstId());
			
			instanceRef = instance.databaseMe();
			
//			this.copyFrom(databaseMe());
//			this.databaseMe();
			this.syncToDatabaseMe();
		}		
		
		this.flushDatabaseMe();
		
		SolrData SolrData = new SolrData();
		Instance inst = new Instance();
		inst.copyFrom(instanceRef);
		SolrData.insertWorkItem(this , inst);
		
		return instanceRef;
	}
	
	public Object[] makeReturn(Long prevInstId, IInstance instanceRef) throws Exception {
		
		Object[] returnObjects = null;
		String mood = null;
		
		if(session.getEmployee() != null)
			mood = session.getEmployee().getPreferUX();
		
		this.getMetaworksContext().setHow(mood);
		
		Instance instance = new Instance();
		instance.copyFrom(instanceRef);
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;
		instance.flushDatabaseMe();
		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen())){
			this.getMetaworksContext().setWhen(WHEN_VIEW);
			this.getWriter().setMetaworksContext(this.getMetaworksContext());
			if( session.getLastPerspecteType() != null && TopicNode.TOPIC.equals(session.getLastPerspecteType())){
				TopicNode topic = new TopicNode();
				topic.setId(session.getLastSelectedItem());
				topic.copyFrom(topic.databaseMe());
				instance.setTopicName(topic.getName());
			}
			final IWorkItem copyOfThis = this;
			final IInstance copyOfInstance = instance;
			copyOfInstance.fillFollower();
			InstanceFollowers instanceFollowers = copyOfInstance.getFollowers();
					
			copyOfInstance.getMetaworksContext().setWhen("blinking");
			
			// 인스턴스 발행
			if(prevInstId == null){
				Object detail = instance.detail();
				
				if("sns".equals(mood)){
					newInstancePanel = new NewInstancePanel();
					newInstancePanel.load(session);
					
					returnObjects = new Object[]{new ToPrepend(new InstanceList(), detail),new Refresh(newInstancePanel)};
				}/*else if("oce".equals(session.getUx())){
					newInstancePanel = new NewInstancePanel();
					newInstancePanel.getMetaworksContext().setHow("sns");
					newInstancePanel.load(session);
					
					returnObjects = new Object[]{new ToPrepend(new InstanceList(), detail),new Refresh(newInstancePanel)};
				}*/
				else{
				     CommingTodoPerspective commingTodoPerspective = new CommingTodoPerspective();
				     returnObjects = new Object[]{new ToPrepend(new InstanceList(), instance), new Refresh(detail), new Refresh(commingTodoPerspective)};
				    }							
			// 덧글
			}else{
				if("oce".equals(session.getUx())){
					this.getMetaworksContext().setHow("sns");
				}
				InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
				instanceViewThreadPanel.setInstanceId(this.getInstId().toString());
				
				if(this instanceof OverlayCommentWorkItem){
					WorkItem parentWorkItem = new WorkItem();
					parentWorkItem.setTaskId(getOverlayCommentOption().getParentTaskId());
					
					returnObjects = new Object[]{new ToAppend(parentWorkItem, this) , new Refresh(instanceFollowers)};
					
				}else if(this instanceof CommentWorkItem){		
					if("memo".equals(this.getType())){
						MemoWorkItem memo = new MemoWorkItem();						
						memo.copyFrom(this);
						memo.setMemo(new WebEditor(this.getContent()));

						returnObjects = new Object[]{new Refresh(memo, false, true) , new Refresh(instanceFollowers)};
					}else{
//						if("comment".equals(this.getType()) && ((CommentWorkItem)this).initialFollowers != null) {
//							
//							InstanceFollowers followers = new InstanceFollowers();
//							followers.setInstanceId(this.getInstId().toString());
//							followers.load();
//							
//							returnObjects = new Object[]{new Refresh(this, false, true), new Refresh(followers)};
//						}
//						else
						returnObjects = new Object[]{new Refresh(this, false, true), new Refresh(instanceFollowers)};	
					}
				}else if(this instanceof GenericWorkItem){
					InstanceViewThreadPanel genericWorkItem = new InstanceViewThreadPanel();
					genericWorkItem.session = session;
					genericWorkItem.session.setLastPerspecteType("normal");
					genericWorkItem.load(this.getInstId().toString());
					
//					CommentWorkItem commentWorkItem = new CommentWorkItem();
//					commentWorkItem.setInstId(this.getInstId());
//					commentWorkItem.setWriter(session.getUser());
//					commentWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
//					
//					DocumentTool documentTool = new DocumentTool();
//					documentTool.setInstId(this.getFileInstId().toString());
//					documentTool.onLoad();
					
					
					returnObjects = new Object[]{new ToAppend(instanceViewThreadPanel, this), new Refresh(genericWorkItem)  , new Refresh(instanceFollowers)};
				}else if(this instanceof FileWorkItem){
					DocWorkItem docWorkItem = new DocWorkItem();
					docWorkItem.setInstId(this.getInstId());
					docWorkItem.getMetaworksContext().setHow("document");
					
					returnObjects = new Object[]{new ToAppend(instanceViewThreadPanel, this), new Refresh(docWorkItem)  , new Refresh(instanceFollowers)};
				}else{
					CommentWorkItem commentWorkItem = new CommentWorkItem();
					commentWorkItem.setInstId(this.getInstId());
					commentWorkItem.setWriter(session.getUser());
					commentWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
					returnObjects = new Object[]{new ToAppend(instanceViewThreadPanel, this), new Refresh(commentWorkItem)  , new Refresh(instanceFollowers)};
				}
				
				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(copyOfInstance)});
			}
			
			// 팔로워들에게 알림처리
			// 부서로 추가된 팔로워들의 userId 를 가져온다.
			IDept deptFollower = instanceFollowers.getDeptFollowers();
			HashMap<String, String> notiUsers = new HashMap<String, String>();
			if(deptFollower != null){
				deptFollower.beforeFirst();
				while(deptFollower.next()){
					HashMap<String, String> deptSessionList = Login.getSessionIdWithDept(deptFollower.getPartCode());
					
					if(deptSessionList != null)
						notiUsers.putAll(deptSessionList);
				}
			}
			
			// 유저로 추가된 팔로워 알림 처리
			IUser followers = instanceFollowers.getFollowers();
			if(followers != null){
				followers.beforeFirst();
				
				while(followers.next()){
					if(session.getUser().getUserId().equals(followers.getUserId()))
						continue;
					
					notiUsers.put(followers.getUserId() , Login.getSessionIdWithUserId(followers.getUserId()));
					if(notiUsers.containsKey(followers.getUserId().toUpperCase())){
						notiUsers.remove(followers.getUserId().toUpperCase());
					}
					notiUsers.put(followers.getUserId() , Login.getSessionIdWithUserId(followers.getUserId()) );
				}
			}
			
			//같은 조직인 사람에게 알림 처리
//			IUser deptEmployee = deptFollowers.getFollowers();
//			if(deptEmployee != null){
//				deptEmployee.beforeFirst();
//				
//				while(deptEmployee.next()){
//					if(session.getUser().getUserId().equals(deptEmployee.getUserId()))
//						continue;
//					
//					notiUsers.put(deptEmployee.getUserId() , Login.getSessionIdWithUserId(deptEmployee.getUserId()));
//					if(notiUsers.containsKey(deptEmployee.getUserId().toUpperCase())){
//						notiUsers.remove(deptEmployee.getUserId().toUpperCase());
//					}
//					notiUsers.put(deptEmployee.getUserId() , Login.getSessionIdWithUserId(deptEmployee.getUserId()) );
//				}
//			}
//				
//			//주제에 추가된 팔로워 알림 처리		
//			IUser topicFollower = topicFollowers.getFollowers();
//			if(topicFollower != null){
//				topicFollower.beforeFirst();
//				
//				while(topicFollower.next()){
//					if(session.getUser().getUserId().equals(topicFollower.getUserId()))
//						continue;
//					
//					notiUsers.put(topicFollower.getUserId() , Login.getSessionIdWithUserId(topicFollower.getUserId()));
//					if(notiUsers.containsKey(topicFollower.getUserId().toUpperCase())){
//						notiUsers.remove(topicFollower.getUserId().toUpperCase());
//					}
//					notiUsers.put(topicFollower.getUserId() , Login.getSessionIdWithUserId(topicFollower.getUserId()) );
//				}
//			}
			
			// noti 저장
			Iterator<String> iterator = notiUsers.keySet().iterator();
			while(iterator.hasNext()){
				String followerUserId = (String)iterator.next();
				Notification noti = new Notification();
				INotiSetting notiSetting = new NotiSetting();
				INotiSetting findResult = notiSetting.findByUserId(followerUserId);
//				findResult.next();
				if(!findResult.next() || findResult.isWriteInstance()){
					noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
					noti.setUserId(followerUserId);
					noti.setActorId(session.getUser().getUserId());
					noti.setConfirm(false);
					noti.setInputDate(Calendar.getInstance().getTime());
					noti.setTaskId(getTaskId());
					noti.setInstId(getInstId());					
					noti.setActAbstract(session.getUser().getName() + " wrote : " + getTitle());
		
					noti.add(copyOfInstance);
				}
			}
			
			// noti 발송
			if(prevInstId == null && this.getDueDate() != null){
			/*	ScheduleCalendarEvent scEvent = new ScheduleCalendarEvent();
				scEvent.setTitle(this.getTitle());
				scEvent.setId(this.getInstId().toString());
				scEvent.setStart(this.getDueDate());
				scEvent.setEnd(this.getDueDate());
				scEvent.setAllDay(true);
				scEvent.setCallType(ScheduleCalendar.CALLTYPE_INSTANCE);
				scEvent.setComplete(Instance.INSTNACE_STATUS_COMPLETED.equals(this.getStatus()));
				
				MetaworksRemoteService.pushTargetScriptFiltered(new AllSessionFilter(notiUsers),
						"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
						new Object[]{scEvent}); //getTitle(), getInstId()+"", newInstancePanel.getDueDate()});
				
				MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(session.getUser().getUserId()),
						"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
						new Object[]{scEvent}); //instanceRef.getName(), instanceRef.getInstId().toString(), instanceRef.getDueDate() });
*/			}
			
			MetaworksRemoteService.pushTargetScriptFiltered(new AllSessionFilter(notiUsers),
					"mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh",
					new Object[]{});
			
			// 본인 이외에 다른 사용자에게 push -     뭐지?			
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()), session.getUser().getUserId().toUpperCase()),
					new Object[]{new InstanceListener(copyOfInstance), new WorkItemListener(copyOfThis)});			
			
		// 수정
		}else{		
			this.getMetaworksContext().setWhen(WHEN_VIEW);
			
			// 변경된 WorkItem 을 갱신
			returnObjects = new Object[]{new Refresh(this, true)};
			
			// 본인 이외에 다른 사용자에게 push
			final IWorkItem copyOfThis = this;
			
			//MetaworksRemoteService.pushOtherClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new WorkItemListener(copyOfThis)});
			
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()), session.getUser().getUserId().toUpperCase()),
					new Object[]{new WorkItemListener(copyOfThis)});	
		}		
		
		ScheduleCalendarEvent scEvent = new ScheduleCalendarEvent();
		scEvent.setTitle(instanceRef.getName());
		scEvent.setId(instanceRef.getInstId().toString());
		scEvent.setStart(instanceRef.getDueDate());
		
		if(instanceRef.getDueDate() != null){
			Calendar c = Calendar.getInstance();
			c.setTime(instanceRef.getDueDate());
	
			if(c.get(c.HOUR_OF_DAY) == 23 && c.get(c.MINUTE) == 59 && c.get(c.SECOND) == 59)
				scEvent.setAllDay(true);
			else
				scEvent.setAllDay(false);
		}
		
		scEvent.setCallType(ScheduleCalendar.CALLTYPE_INSTANCE);
		scEvent.setComplete(Instance.INSTNACE_STATUS_COMPLETED.equals(instanceRef.getStatus()));
		
		MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(session.getUser().getUserId()),
				"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
				new Object[]{scEvent}); //instanceRef.getName(), instanceRef.getInstId().toString(), instanceRef.getDueDate() });
		
		//NEW WAY IS GOOD
		Browser.withSession(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Runnable(){
			@Override
			public void run() {
				ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + TodoBadge.class.getName() + "').refresh", new Object[]{});					
			}
			
		});
		
		
		
		/*
		if(this.getDueDate() != null){
			ScheduleCalendarEvent scEvent = new ScheduleCalendarEvent();
			scEvent.setTitle(instanceRef.getName());
			scEvent.setId(this.getInstId().toString());
			scEvent.setStart(this.getDueDate());
			scEvent.setEnd(this.getDueDate());
			scEvent.setAllDay(true);
			scEvent.setCallType(ScheduleCalendar.CALLTYPE_INSTANCE);
			scEvent.setComplete(Instance.INSTNACE_STATUS_COMPLETED.equals(this.getStatus()));
			
			MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(session.getUser().getUserId()),
					"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
					new Object[]{scEvent}); //instanceRef.getName(), instanceRef.getInstId().toString(), instanceRef.getDueDate() });
		}
		*/
		
		
		return returnObjects;
	}
	
	
	@Test(scenario="first", starter=true, instruction="$Write", next="newActivity()")
	public Object[] add() throws Exception {
		Object[] returnObjects = null;
		
		Long prevInstId = this.getInstId();
		
		Instance instance = new Instance();
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;
		instance.copyFrom(this.save());
		instance.flushDatabaseMe();
		
		return makeReturn(prevInstId, instance);
	}
	
	public Object remove() throws Exception{
		if( this.getWorkItemHandler() != null && this.getWorkItemHandler().getTracingTag() != null && !this.getWorkItemHandler().getTracingTag().equals(null) ){
			throw new Exception("$CanNotDelete");
		}
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
		
		if(!"file".equals(this.getType())) {
			if( this.getWorkItemHandler() != null && this.getWorkItemHandler().getTracingTag() != null && !this.getWorkItemHandler().getTracingTag().equals(null) ){
				throw new Exception("$CanNotEdit");
			}
			if(!session.getUser().getUserId().equals(getWriter().getUserId())){
				throw new Exception("$OnlyTheWriterCanEdit");
			}
		}
		
		/*
		setDueDate(null);
		setStartDate(null);
		setEndDate(null);		
		*/
//		if("generic".equals(this.getType()) || "minimised".equals(this.getMetaworksContext().getHow())){
//			throw new MetaworksException("$CanNotDocumentEdit");
//		}else{
		getMetaworksContext().setWhen("edit");		
//		}
	}

	protected void afterInstantiation(IInstance instanceRef) throws Exception {
		
		if(newInstancePanel!=null){
			//instanceRef.databaseMe().setSecuopt(newInstancePanel.getSecurityLevel());
			
			if(newInstancePanel.getSecurityLevel().getSelected() != null)
				instanceRef.setSecuopt(newInstancePanel.getSecurityLevel().getSelected());			
			else
				instanceRef.setSecuopt("0");
			
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
		
		if( newInstancePanel != null && newInstancePanel.getDueDate() != null){
			processMapList.setDueDateSetting(true);
			// 새글을 쓰려는데 dueDate 가 이미 있는 경우
		}else if( newInstancePanel == null && instanceViewContent != null && instanceViewContent.getInstanceView() != null && instanceViewContent.getInstanceView().getDueDate() != null ){
			// 기존 글에서 프로세스를 실행시키려는데, 인스턴스에 이미 dueDate가 들어가 있는 경우
			processMapList.setDueDateSetting(true);
		}
		
		Popup popup = new Popup();
		popup.setPanel(processMapList);
		
		return popup;
	}
	

	
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
	
	public Object moreView() throws Exception {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where rootInstId=?instId");
		sql.append("   and taskId<=?taskId");
		sql.append("   and (type  not in ('ovryCmnt' , 'replyCmnt') or type is null)");
		sql.append("   and isdeleted!=?isDeleted");
		
		sql.append(" order by taskId");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("instId", this.getInstId());
		workitem.set("taskId", this.getTaskId());
		workitem.set("isDeleted",1);
		
		//TODO: this expression should be work later instead of above.
		//IUser user = new User();
		//user.setEndpoint(login.getEmpCode());
		//workitem.setWriter(user);
		
		workitem.select();
		
		System.out.println("size:" + workitem.size());
		
		return workitem;
		
	}

	public IWorkItem loadMajorVersionFile(String id) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from bpm_worklist where majorver in (select max(majorver) from bpm_worklist where instId=?instId)");
		sb.append(" and instId=?instId");
		
		IWorkItem workitem = (IWorkItem) sql(IWorkItem.class,sb.toString());
		
		workitem.set("instId",id);
		workitem.select();
		
		if(workitem.next()){
			return workitem;
		}else{
			return null;
		}
	}
	@Override
	public IWorkItem loadCurrentView() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from bpm_worklist where grpTaskId=?grpTaskId");
		sb.append(" and type=?type");
		IWorkItem workItem = (IWorkItem) sql(IWorkItem.class,sb.toString());
		
		workItem.set("grpTaskId",this.getGrpTaskId());
		workItem.set("type",this.getType());
		workItem.select();
		
		if(workItem.next()){
//			this.setType("file");
//			this.getMetaworksContext().setHow(MetaworksContext.HOW_MINIMISED);
			return workItem;
		}else{
			return null;
		}
	}
	
	@Override
	public IWorkItem loadChooseView() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from bpm_worklist where grpTaskId=?grpTaskId");
		sb.append(" and type=?type");
		sb.append(" and taskId=?taskId");
		IWorkItem workItem = (IWorkItem) sql(IWorkItem.class,sb.toString());
		
		workItem.set("grpTaskId",this.getGrpTaskId());
		workItem.set("type",this.getType());
		workItem.set("taskId",this.getTaskId());
		workItem.select();
		
		if(workItem.next()){
			workItem.getMetaworksContext().setHow(MetaworksContext.HOW_MINIMISED);
			return workItem;
		}else{
			return null;
		}
	}
	
	public IWorkItem findGenericWorkItem() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from bpm_worklist where grpTaskId=?grpTaskId");
		sb.append(" and type=?type");
		
		IWorkItem workItem = (IWorkItem)sql(IWorkItem.class,sb.toString());
		
		workItem.set("grpTaskId",this.getGrpTaskId());
		workItem.set("type",this.getType());
		workItem.select();
		
		if(workItem.next()){
			return workItem;
		}else{
			return null;
		}
	}
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

//	@AutowiredFromClient
//	public TopicFollowers topicFollowers;
//	
//	@AutowiredFromClient
//	public DeptFollowers deptFollowers;
	
	
}
