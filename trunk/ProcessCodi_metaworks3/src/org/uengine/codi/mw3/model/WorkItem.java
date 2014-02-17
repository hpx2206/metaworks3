package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.search.solr.SolrData;
import org.uengine.webservices.worklist.DefaultWorkList;


public class WorkItem extends Database<IWorkItem> implements IWorkItem{

	private Logger logger = LoggerFactory.getLogger(WorkItem.class);
	
	public final static String USE_BBB = GlobalContext.getPropertyString("bbb.use", "1");
	
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
	
	protected static IWorkItem findCommentByTaskId(String taskId) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append("select *");
		sql.append("  from bpm_worklist");
		sql.append(" where prtTskId=?taskId");
		sql.append("   and isdeleted!=?isDeleted");
		sql.append("   and type in ('ovryCmnt') ");
		sql.append(" order by taskId");
		
		IWorkItem workitem = (IWorkItem) Database.sql(IWorkItem.class, sql.toString());
		
		workitem.set("taskId", taskId);
		workitem.set("isDeleted",1);
		
		workitem.select();
		
		System.out.println("comment size : " + workitem.size());
		
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
	
	IFrame conference;
		public IFrame getConference() {
			return conference;
		}
		public void setConference(IFrame conference) {
			this.conference = conference;
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

	String activityAppAlias;
		public String getActivityAppAlias() {
			return activityAppAlias;
		}
		public void setActivityAppAlias(String activityAppAlias) {
			this.activityAppAlias = activityAppAlias;
		}
		
	ArrayList<String> initialFollowers;
		public ArrayList<String> getInitialFollowers() {
			return initialFollowers;
		}
		public void setInitialFollowers(ArrayList<String> initialFollowers) {
			this.initialFollowers = initialFollowers;
		}
		
	ArrayList<ParameterValue> parameters;
		public ArrayList<ParameterValue> getParameters() {
			return parameters;
		}
		public void setParameters(ArrayList<ParameterValue> parameters) {
			this.parameters = parameters;
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
	}
	
	public void loadContents() throws Exception {
		throw new Exception("not defined loadContents method");
	}
	/*
	public void loadContents() throws Exception {
		//only lazy loading needed workitems will use this method
		loadContents();
		setContentLoaded(true);
	}
	*/
	
	
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
		wi.setDueDate(this.getDueDate());
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
		
	boolean notReturn;
		public boolean isNotReturn() {
			return notReturn;
		}
		public void setNotReturn(boolean notReturn) {
			this.notReturn = notReturn;
		}

		
	private boolean changeFollower = false;
	
	public IInstance save() throws Exception {
		
		boolean isNewInstance = false;
		
		Instance instance = null;
		IInstance instanceRef = null;		
		
		if( this.getWriter() == null || (this.getWriter() != null && this.getWriter().getUserId() == null)){
			IUser writer = new User();
			writer.setUserId(session.getUser().getUserId());
			writer.setName(session.getUser().getName());
			
			this.setWriter(writer);
		}
		
		String title = this.getTitle();
		if(title.length() > TITLE_LIMIT_SIZE){
			title = title.substring(0, TITLE_LIMIT_SIZE) + "...";
		}

		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen()) || this instanceof FileWorkItem){
			// 인스턴스 발행
			if(this.getInstId() == null){
				isNewInstance = true;
				
				String instId = null;
				
				if(getActivityAppAlias()!=null){
					instId = processManager.initializeProcess(getActivityAppAlias());
					
					StringTokenizer tokenizer = new StringTokenizer(this.getTitle());
					String connector = null;
					
					if(getParameters()!=null){
						StringBuffer generatedTitle = new StringBuffer();
						
						int substringDelimiter = 0;
						for(ParameterValue pv : getParameters()){
							connector = tokenizer.nextToken("$").substring(substringDelimiter);
							tokenizer.nextToken(">");
							
							generatedTitle.append(connector).append(pv.getValueObject());
							
							substringDelimiter=1;
						}
					
						for(ParameterValue pv : getParameters()){
							Serializable valueObject = (Serializable)pv.getValueObject();
							
							processManager.setProcessVariable(instId, "", pv.getVariableName(), valueObject);
						}

						this.setTitle(generatedTitle.append(tokenizer.nextElement()).toString());
					}
					
					RoleMapping rm = RoleMapping.create();
					if(session.getUser() != null){
						rm.setName("Initiator");
						rm.setEndpoint(session.getUser().getUserId());
						
						processManager.putRoleMapping(instId, rm);
					}
					
					processManager.setLoggedRoleMapping(rm);
					processManager.executeProcess(instId);
					processManager.applyChanges();
				}else{
					// 인스턴스 발행을 위한 ProcessMap 사용
					ProcessMap processMap = new ProcessMap();
					processMap.processManager = processManager;
					processMap.session = session;
					processMap.setDefId(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
					
					instId = processMap.initializeProcess();
					processMap.executeProcess(instId);
				}
				
				// WorkItem 의 InstId 할당
				this.setInstId(new Long(instId));
				
				// 기본 정보 설정  
				instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();	

				instanceRef.setInitCmpl(false);										// 기본값 수정 시작자만 완료 가능하게
				instanceRef.setInitiator(this.getWriter());
				instanceRef.setInitComCd(session.getEmployee().getGlobalCom());		// 시작자의 회사
				instanceRef.setDueDate(getDueDate());
				instanceRef.setStatus(WORKITEM_STATUS_RUNNING); // 처음 상태 Running
				instanceRef.setName(title);
				if(this.getFolderId() != null){
					instanceRef.setTopicId(this.getFolderId());
				}
				
				if(WORKITEM_TYPE_FILE.equals(this.getType()) || WORKITEM_TYPE_DOCUMENT.equals(this.getType()) || WORKITEM_TYPE_GENERIC.equals(this.getType()))
					instanceRef.setIsFileAdded(true);
				
				instanceRef.setIsDocument(WorkItem.WORKITEM_TYPE_FILE.equals(this.getType()));
				
				afterInstantiation(instanceRef);				
			}else{
				if( this.getRootInstId() != null && this.getRootInstId() != this.getInstId() ){
					setInstId(this.getRootInstId());
				}
				instance = new Instance();
				instance.setInstId(this.getInstId());
				
				instanceRef = instance.databaseMe();
				
				if(this.getDueDate()!= null)
					instanceRef.setDueDate(this.getDueDate());
			}

			instanceRef.setCurrentUser(this.getWriter());//may corrupt when the last actor is assigned from process execution.
			
			
			if(this.getTaskId() == null || this.getTaskId() == -1)
				this.setTaskId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()));

			if(!isNewInstance){
				if(WORKITEM_TYPE_FILE.equals(this.getType()) || WORKITEM_TYPE_DOCUMENT.equals(this.getType()) || WORKITEM_TYPE_GENERIC.equals(this.getType()))
					instanceRef.setIsFileAdded(true);

				//마지막 워크아이템의 제목을 인스턴스의 적용
				if(instanceRef.getLastCmnt() == null){
					instanceRef.setLastCmnt(title);
					instanceRef.setLastCmntUser(session.getUser());
					instanceRef.setLastcmntTaskId(this.getTaskId());
				}else{
					if(instanceRef.getLastCmnt2() == null){
						instanceRef.setLastCmnt2(title);
						instanceRef.setLastCmnt2User(session.getUser());
						instanceRef.setLastcmnt2TaskId(this.getTaskId());
					}else {
						instanceRef.setLastCmnt(instanceRef.getLastCmnt2());
						instanceRef.setLastCmntUser(instanceRef.getLastCmnt2User());
						instanceRef.setLastcmntTaskId(instanceRef.getLastcmnt2TaskId());
						
						instanceRef.setLastCmnt2(title);
						instanceRef.setLastCmnt2User(session.getUser());
						instanceRef.setLastcmnt2TaskId(this.getTaskId());
					}
				}				
			}
			// TODO 날자 저장시에 특정 시간이 없을 시에 하루의 가장 마지막 시간으로 설정
			if( instanceRef.getDueDate() != null ){
				Calendar c = Calendar.getInstance ();
				c.setTime(instanceRef.getDueDate());
				if( c.get(c.HOUR_OF_DAY) == 0 && c.get(c.MINUTE) == 0){
					c.set ( c.HOUR_OF_DAY  , +23);
					c.set ( c.MINUTE  , +59);
					c.set ( c.SECOND  , +59);
					instanceRef.setDueDate(c.getTime());
				}
			}
			/*
			 * 사용자 제한 부분.
			 */
			this.permittedCheck(instanceRef);
			
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
			
			this.createDatabaseMe();
			
			
		// 수정
		}else{
			instance = new Instance();
			instance.setInstId(this.getInstId());
			
			instanceRef = instance.databaseMe();
			
			/*
			 * 사용자 제한 부분.
			 */
			this.permittedCheck(instanceRef);
			
			if( instanceRef.getLastcmntTaskId() != null && instanceRef.getLastcmntTaskId().equals(this.getTaskId()) ){
				instanceRef.setLastCmnt(title);
			}else if( instanceRef.getLastcmnt2TaskId() != null && instanceRef.getLastcmnt2TaskId().equals(this.getTaskId()) ){
				instanceRef.setLastCmnt2(title);
			}
			
			this.syncToDatabaseMe();
		}		
		
		// 팔로워 추가(추천 및 게시)
		boolean isAddFollwer = false;
		
		InstanceFollower findFollower = new InstanceFollower(instance.getInstId().toString());
		findFollower.session = session;
		findFollower.setEnablePush(false);
		
		IFollower follower = findFollower.findFollowers();
		
		if(initialFollowers == null){
			initialFollowers = new ArrayList<String>();	
		}
		
		// 게시 사용자도 팔로워에 추가되게
		initialFollowers.add(this.getWriter().getUserId());
		
		for(String userId : initialFollowers){
			follower.beforeFirst();
			
			boolean isExist = false;
			
			while(follower.next()){
				if(follower.getEndpoint().equals(userId)){
					isExist = true;
					
					break;
				}
			}
			
			if(!isExist){
				Employee addUser = new Employee();
				addUser.setEmpCode(userId);
				addUser.copyFrom(addUser.findMe());
				
				findFollower.setUser(addUser.getUser());
				findFollower.put();
				
				isAddFollwer = true;
			}
		}
		
		if(isAddFollwer){
			findFollower.setEnablePush(true);
			findFollower.push();
		}
		
		// this code here must be for transaction problem 
		this.flushDatabaseMe();
		
		SolrData SolrData = new SolrData();
		Instance inst = new Instance();
		inst.copyFrom(instanceRef);
		SolrData.insertWorkItem(this , inst);
		
		return instanceRef;
	}
	
	public Object[] makeReturn(Long prevInstId, IInstance instanceRef) throws Exception {
		
		Object[] returnObjects = null;
		
		Instance instance = new Instance();
		instance.copyFrom(instanceRef);
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;
		instance.flushDatabaseMe();
		
		boolean securityPush = false;
		HashMap<String, String> pushUserMap = new HashMap<String, String>();
		String currentUserId = session.getUser().getUserId();
		// 주제 제목 설정
		if(instance.getTopicId() != null){
			TopicNode topic = new TopicNode();
			topic.setId(instance.getTopicId());
			
			try{
				topic.copyFrom(topic.databaseMe());
				
				instance.setTopicName(topic.getName());
				String topicSecuopt = topic.getSecuopt();
				
				if( "1".equals( topicSecuopt )){
					securityPush = true;
					Notification notification = new Notification();
					notification.session = session;
					pushUserMap = notification.findTopicNotiUser(topic.getId());
				}
			}catch(Exception e){
				logger.info("topicId 는 존재하나 해당  Topic 이 존재하지 않음. (" + instance.getTopicId() +")");
			}
		}
		
		instance.fillFollower();
		
		/**
		 *  ==== returnObjects 생성 ====
		 *  새로 쓴 글을 제외하고는 workItem 만 컨트롤을 한다.
		 */
		// 추가
		if(WHEN_NEW.equals(getMetaworksContext().getWhen())){
			// 인스턴스 발행
			if(prevInstId == null){
				Object detail = instance.detail();
				
			     UpcommingTodoPerspective upcommingTodoPerspective = new UpcommingTodoPerspective();
			     returnObjects = new Object[]{new ToPrepend(new InstanceList(), instance),
			    		 				      new Refresh(detail),
			    		 				      new Refresh(upcommingTodoPerspective)};
			}else{
				// 댓글
				InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
				instanceViewThreadPanel.setInstanceId(this.getInstId().toString());
				
				CommentWorkItem commentWorkItem = new CommentWorkItem();
				commentWorkItem.setInstId(this.getInstId());
				commentWorkItem.setWriter(session.getUser());
				commentWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
				returnObjects = new Object[]{new ToAppend(instanceViewThreadPanel, this), new Refresh(commentWorkItem)};
			}
			
		// 수정
		}else{		
			// 변경된 WorkItem 을 갱신
			returnObjects = new Object[]{new Refresh(this, false, true)};
		}
		
		/**
		 *  ===  push 부분 ===
		 */
		this.getMetaworksContext().setWhere(WorkItem.WHERE_WORKLIST);
		instance.getMetaworksContext().setWhere(Instance.WHERE_INSTANCELIST);
		
		final IWorkItem copyOfThis = this;
		final IInstance copyOfInstance = instance;
		
		if(this instanceof OverlayCommentWorkItem || this instanceof FileWorkItem)
			copyOfInstance.setInstId(this.getRootInstId());
		
		/**
		 *  === noti push 부분 ===
		 *  위쪽에서 topic notiuser를 구하였지만 noti를 보내는 사람을 구하는 로직은 다를수 있으니 다시한번 구한다.
		 */
		HashMap<String, String> notiUsers = new HashMap<String, String>();
		Notification notification = new Notification();
		notification.session = session;
		notiUsers = notification.findInstanceNotiUser(instanceRef.getInstId().toString());
		if(instance.getTopicId() != null && "0".equals(instance.getSecuopt())){
			HashMap<String, String> topicNotiUsers = notification.findTopicNotiUser(instance.getTopicId());
			Iterator<String> iterator = topicNotiUsers.keySet().iterator();
			while(iterator.hasNext()){
				String followerUserId = (String)iterator.next();
				notiUsers.put(followerUserId, topicNotiUsers.get(followerUserId));
			}
		}
		
		pushUserMap.putAll(notiUsers);
		
		// noti 저장
		Iterator<String> iterator = notiUsers.keySet().iterator();
		while(iterator.hasNext()){
			String followerUserId = (String)iterator.next();
			Notification noti = new Notification();
			INotiSetting notiSetting = new NotiSetting();
			INotiSetting findResult = notiSetting.findByUserId(followerUserId);
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
		
		Notification.addPushListener(notiUsers);
		
		/**
		 *  === instance push 부분 ===
		 *  위쪽에서 topic notiuser를 구하였지만 noti를 보내는 사람을 구하는 로직은 다를수 있으니 다시한번 구한다.
		 */
		if( !securityPush ){
			HashMap<String, String> companyUsers = Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom());
			pushUserMap.putAll(companyUsers);	// 다른 테넌트의 follower 가 있을수도 있으니 추가를 해줌
		}
		
		if(prevInstId == null){
			// 인스턴스가 새로글을 쓸 경우에는 달력과 다른사람에게 instance 푸쉬만 되어야함
			// 새글이 아닐 경우에는 달력 푸쉬는 필요가 없다. 
			// 관련된 사용자에게 일정 입력 시 달력에 push
			if(instanceRef.getDueDate() != null){
				ScheduleCalendarEvent scEvent = new ScheduleCalendarEvent();
				scEvent.setTitle(instanceRef.getName());
				scEvent.setId(instanceRef.getInstId().toString());
				scEvent.setStart(instanceRef.getDueDate());
				
				Calendar c = Calendar.getInstance();
				c.setTime(instanceRef.getDueDate());
		
				// TODO : 현재는 무조건 종일로 설정
				scEvent.setAllDay(true);
				scEvent.setCallType(ScheduleCalendar.CALLTYPE_INSTANCE);
				scEvent.setComplete(Instance.INSTNACE_STATUS_COMPLETED.equals(instanceRef.getStatus()));
				
				MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(currentUserId),
						"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
						new Object[]{scEvent});
				
				TodoBadge todoBadge = new TodoBadge();
				todoBadge.loader = true;
				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(currentUserId), new Object[]{new Refresh(todoBadge, true)});
			}
			// 새 글일 경우에는 다른사람에게만 push를 하여준다.
			// 본인 이외에 다른 사용자에게 push
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(pushUserMap, currentUserId.toUpperCase()),
					new Object[]{new InstanceListener(InstanceListener.COMMAND_APPEND, copyOfInstance)});	
			
		}else{
			if(WHEN_NEW.equals(getMetaworksContext().getWhen())){
				// 덧글은 append 로 붙어야 하고,
				// 자기 자신의 인스턴스 리스트를 새로고침
				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(currentUserId), new Object[]{new InstanceListener(InstanceListener.COMMAND_APPEND, copyOfInstance)});
				
				// 본인 이외에 다른 사용자에게 push
				MetaworksRemoteService.pushClientObjectsFiltered(
						new OtherSessionFilter(pushUserMap, currentUserId.toUpperCase()),
						new Object[]{new InstanceListener(InstanceListener.COMMAND_APPEND,copyOfInstance), new WorkItemListener(WorkItemListener.COMMAND_APPEND , copyOfThis)});	
				
			}else if(WHEN_EDIT.equals(getMetaworksContext().getWhen())){
				// edit는 refresh 로 붙어야함
				// 자기 자신의 인스턴스 리스트를 새로고침
				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(currentUserId), new Object[]{new InstanceListener(InstanceListener.COMMAND_REFRESH, copyOfInstance)});
				
				// 본인 이외에 다른 사용자에게 push
				MetaworksRemoteService.pushClientObjectsFiltered(
						new OtherSessionFilter(pushUserMap, currentUserId.toUpperCase()),
						new Object[]{new InstanceListener(WorkItemListener.COMMAND_REFRESH, copyOfInstance), new WorkItemListener(WorkItemListener.COMMAND_REFRESH , copyOfThis)});	
				
			}
		}
		
		/**
		 *  === MetaworksContext 처리 ===
		 */

		this.getMetaworksContext().setWhen(WHEN_VIEW);
		this.getWriter().setMetaworksContext(this.getMetaworksContext());
		
		return returnObjects;
	}
	
	
	@Test(scenario="first", starter=true, instruction="$Write", next="newActivity()")
	public Object[] add() throws Exception {
		Long prevInstId = this.getInstId();
		
		Instance instance = new Instance();
		instance.session = session;
		instance.instanceViewContent = instanceViewContent;
		instance.copyFrom(this.save());
		instance.flushDatabaseMe();
		
		if( notReturn ){
			return null;
		}else{
			return makeReturn(prevInstId, instance);
		}
	}
	
	public Object remove() throws Exception{
		if( this.getWorkItemHandler() != null && this.getWorkItemHandler().getTracingTag() != null && !this.getWorkItemHandler().getTracingTag().equals(null) ){
			throw new Exception("$CanNotDelete");
		}else if( this.getTool() != null && this.getTrcTag() != null && this.getType() == null ){
			// 프로세스의 workItem 이 닫혀있을 경우 이렇게 들어오는 경우가 있어서 삭제를 막는다.
			throw new Exception("$CanNotDelete");
		}
		Instance theInstance = new Instance();
		theInstance.setInstId(getInstId());
		theInstance.copyFrom(theInstance.databaseMe());
		
		if(session.getUser().getUserId().equals(getWriter().getUserId()) || (session.getEmployee()!=null && session.getEmployee().getIsAdmin()) || theInstance.getInitiator().getUserId().equals(session.getUser().getUserId())){
			if( this.getTaskId().equals(theInstance.getLastcmntTaskId()) ){
				if( theInstance.getLastcmnt2TaskId() != null ){
					// 두번째 댓글이 있다면 두번째 댓글을 첫번째로 옴기고 두번째 댓글을 지운다
					theInstance.databaseMe().setLastCmnt(theInstance.getLastCmnt2());
					theInstance.databaseMe().setLastCmntUser(theInstance.getLastCmnt2User());
					theInstance.databaseMe().setLastcmntTaskId(theInstance.getLastcmnt2TaskId());
					
					theInstance.databaseMe().setLastCmnt2(null);
					theInstance.databaseMe().setLastCmnt2User(null);
					theInstance.databaseMe().setLastcmnt2TaskId(null);
				}else{
					// 두번째 댓글이 없다면 댓글을 지운다.
					theInstance.databaseMe().setLastCmnt(null);
					theInstance.databaseMe().setLastCmntUser(null);
					theInstance.databaseMe().setLastcmntTaskId(null);
				}
				
			}else if(this.getTaskId().equals(theInstance.getLastcmnt2TaskId()) ){
				theInstance.databaseMe().setLastCmnt2(null);
				theInstance.databaseMe().setLastCmnt2User(null);
				theInstance.databaseMe().setLastcmnt2TaskId(null);
			}
			
			deleteDatabaseMe();
			
			final IWorkItem copyOfThis = this;
			final IInstance copyOfInstance = theInstance;
			// 자기 자신의 인스턴스 리스트를 새로고침
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(InstanceListener.COMMAND_REFRESH, copyOfInstance)});
			
			// 본인 이외에 다른 사용자에게 push
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()), session.getUser().getUserId().toUpperCase()),
					new Object[]{new InstanceListener(InstanceListener.COMMAND_REFRESH, copyOfInstance), new WorkItemListener(WorkItemListener.COMMAND_REMOVE , copyOfThis)});	
			
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
	
	protected void permittedCheck(IInstance instanceRef) throws Exception{
		if( instanceRef.getIsDeleted() ){
			throw new MetaworksException("$alreadyDeletedPost");
		}
		if( "1".equals(instanceRef.getSecuopt())){
			// 비공개 글일 경우 본건의 관련자가 아니면 글쓰기를 막음.
			InstanceFollower findFollower = new InstanceFollower(instanceRef.getInstId().toString());
			IFollower follower = findFollower.findFollowers();
			boolean isExist = false;
			while(follower.next()){
				if(Role.ASSIGNTYPE_USER == follower.getAssigntype()){
					if(follower.getEndpoint().equals(session.getEmployee().getEmpCode())){
						isExist = true;
						break;
					}
				}else if(Role.ASSIGNTYPE_DEPT == follower.getAssigntype()){
					if(follower.getEndpoint().equals(session.getEmployee().getPartCode())){
						isExist = true;
						break;
					}
				}
			}
			if( !isExist ){
				throw new MetaworksException("$NotPermittedToWork");
			}
		}
	}

	protected void afterInstantiation(IInstance instanceRef) throws Exception {
		
		if(newInstancePanel!=null){
			//instanceRef.databaseMe().setSecuopt(newInstancePanel.getSecurityLevel());
			
			if(newInstancePanel.getSecurityLevel().getSelected() != null)
				instanceRef.setSecuopt(newInstancePanel.getSecurityLevel().getSelected());			
			else
				instanceRef.setSecuopt("0");
			
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
		
		if( this.getDueDate() != null){
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
		overlayCommentWorkItem.setRootInstId(getRootInstId());
		
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
