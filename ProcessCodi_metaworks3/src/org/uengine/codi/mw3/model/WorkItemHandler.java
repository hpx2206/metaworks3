package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.filter.AllSessionFilter;
import org.uengine.codi.mw3.filter.OtherSessionFilter;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityInstanceContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(ejsPath="faces/org/metaworks/widget/Window.ejs", options={"hideLabels"}, values={"true"}, displayName="업무 처리 화면")
public class WorkItemHandler implements ContextAware{
	
	public WorkItemHandler(){} //to be spring bean without argument, this is required.
	
	public void load() throws Exception{
		
		Long instanceId = new Long(getInstanceId());
		Long taskId = getTaskId();
		String tracingTag = getTracingTag();
		
		instance = processManager.getProcessInstance(instanceId.toString());

		if(humanActivity==null && instanceId!=null && tracingTag!=null){
			humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		}
		
		if(humanActivity != null && humanActivity.getParameters()!=null){
			// load map for ITool
			loadMapForITool((Map<String, Object>)makeMapForITool());
			
			//creates work item handler
				parameters = new ParameterValue[humanActivity.getParameters().length];
				for(int i=0; i<humanActivity.getParameters().length; i++){
					ParameterContext pc = humanActivity.getParameters()[i];
					
					parameters[i] = new ParameterValue();
					
					ParameterValue pv = parameters[i];
					pv.setVariableName(pc.getVariable().getName());
					pv.setArgument(pc.getArgument().getText(session!=null && session.getEmployee()!=null ? session.getEmployee().getLocale() : null));
										
					String when = this.getMetaworksContext().getWhen();
					
					MetaworksContext mc = new MetaworksContext();
					
					if(MetaworksContext.WHEN_EDIT.equals(when)){
						if(ParameterContext.DIRECTION_IN.equals(pc.getDirection()))						
							when = MetaworksContext.WHEN_VIEW;
					}
					
					mc.setWhen(when);					
					pv.setMetaworksContext(mc);
					
					
					Serializable processVariableValue = pc.getVariable().get(instance, "");
					Class variableType = pc.getVariable().getType();
					if( variableType == null && pc.getVariable().getTypeInputter() != null ){
						variableType = Class.forName(pc.getVariable().getTypeInputter());
					}
//				
//					if(variableType == String.class){
//						parameters[i].setValueString((String) processVariableValue);
////					}else if(Long.class.isAssignableFrom(variableType)){
////						parameters[i].setValueNumber((Number) processVariableValue);
////					}else if(Calendar.class.isAssignableFrom(variableType)){
////						parameters[i].setValueCalendar((Calendar) processVariableValue);
//					}else 
					
					if(variableType == ComplexType.class){
						if(processVariableValue instanceof ComplexType){
							ComplexType complexType = (ComplexType) processVariableValue;
							complexType.setDesignerMode(false);
							processVariableValue = (Serializable) complexType.getTypeClass().newInstance();
							
							if(processVariableValue instanceof ContextAware){
								((ContextAware)processVariableValue).setMetaworksContext(mc);
							}
							
							if(processVariableValue instanceof NeedArrangementToSerialize){
								((NeedArrangementToSerialize)processVariableValue).afterDeserialization();
							}
							
							if(processVariableValue instanceof ITool){
								((ITool)processVariableValue).onLoad();
							}
						} else if (processVariableValue instanceof ITool) {
							if(processVariableValue instanceof ContextAware){
								((ContextAware)processVariableValue).setMetaworksContext(mc);
							}
							
							// for completed ComplexType Object implements ITool
							((ITool) processVariableValue).onLoad();
						}
						
					}else{
						
						if(variableType==Boolean.class){
							processVariableValue = new Boolean(false);
						}else if(variableType==Number.class){
							processVariableValue = new Integer(0);
						}else if(variableType==String.class){
							if( processVariableValue == null ){
								processVariableValue = new String();
							}
						}else
							processVariableValue = (Serializable) variableType.newInstance();
					}
					
					pv.setValueObject(processVariableValue);
				}
				
				releaseMapForITool();
		}
		
		setInstanceId(instanceId.toString());
		setTracingTag(humanActivity.getTracingTag());
		setTaskId(taskId);
		
		// 댓글이 있는지 찾는다.
		if( taskId != -1 && taskId != 0){
			WorkItem item = new WorkItem();
			
			workItem = item.findParentWorkItemByType(taskId+"" , "replyCmnt");
		}
	}
	
	transient IWorkItem workItem;
		public IWorkItem getWorkItem() {
			return workItem;
		}
		public void setWorkItem(IWorkItem workItem) {
			this.workItem = workItem;
		}

	transient protected HumanActivity humanActivity;
//	@NonLoadable
//	@NonSavable
//	@Hidden
//		public HumanActivity getHumanActivity() {
//			return humanActivity;
//		}
//		public void setHumanActivity(HumanActivity humanActivity) {
//			this.humanActivity = humanActivity;
//		}
		
	transient protected ProcessInstance instance;
	
	//// in old manners, we should carry all the following parameters by passing query string or json something:
	


	ParameterValue[] parameters;
		@Valid
		public ParameterValue[] getParameters() {
			return parameters;
		}
		public void setParameters(ParameterValue[] parameters) {
			this.parameters = parameters;
		}
		
	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}


	String tracingTag;
	@Hidden
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}


	Long taskId;
		@Id
		@Hidden
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	Long rootInstId;
		@Hidden
		public Long getRootInstId() {
			return rootInstId;
		}
		public void setRootInstId(Long rootInstId) {
			this.rootInstId = rootInstId;
		}
		
	String replyFieldName;
		@Hidden
		public String getReplyFieldName() {
			return replyFieldName;
		}
		public void setReplyFieldName(String replyFieldName) {
			this.replyFieldName = replyFieldName;
		}
	String replyTitle;
		@Hidden
		public String getReplyTitle() {
			return replyTitle;
		}
		public void setReplyTitle(String replyTitle) {
			this.replyTitle = replyTitle;
		}

	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
	public Object[] cancel() throws Exception{
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);

//		WorkList worklist = instance.getWorkList();
//		worklist.cancelWorkItem(getTaskId().toString(), new KeyedParameter[]{}, instance.getProcessTransactionContext());
//
//		humanActivity.setStatus(instance, Activity.ACTIVITY_STOPPED)

		humanActivity.stop(instance);
		
		processManager.applyChanges();
		
		CommentWorkItem cancelledHistory = new CommentWorkItem();
		cancelledHistory.processManager = processManager;
		cancelledHistory.session = session;
		cancelledHistory.setInstId(new Long(getInstanceId()));
		
		cancelledHistory.setTitle(humanActivity.getName().getText() + " task has been cancelled by me.");
		cancelledHistory.setWriter(session.getUser());
		cancelledHistory.add();
		Instance instance = new Instance();
		instance.setInstId(this.getRootInstId());
		
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		if("oce".equals(session.getUx())){
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("instanceList");
			panel.getMetaworksContext().setWhere("sns");
			panel.session = session;
			panel.load(this.getRootInstId().toString());
			
			return new Object[]{panel, new Remover(new ModalWindow() , true )};
		}else {
			return new Object[]{instanceViewContent, new Remover(new ModalWindow(), true)};
//			if("sns".equals(session.getEmployee().getPreferUX())){
//				return new Remover(new ModalWindow());
//			}else{
//				return this;
//			}
		}
	}

	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
	public Object[] skip() throws Exception{
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = (HumanActivity) instance.getProcessDefinition()
					.getActivity(tracingTag);

//		WorkList worklist = instance.getWorkList();
//		worklist.cancelWorkItem(getTaskId().toString(), new KeyedParameter[]{}, instance.getProcessTransactionContext());
//
//		humanActivity.setStatus(instance, Activity.ACTIVITY_STOPPED)

		humanActivity.skip(instance);

		List activities = humanActivity.getPropagatedActivities(instance);
		
		//resume the process
//		Vector activityInstances =  instance.getCurrentRunningActivitiesDeeply();
		for(int i=0; i<activities.size(); i++){
			Activity nextAct = (Activity) activities.get(i);
			nextAct.resume(instance);
		}
		
		processManager.applyChanges();
		
		CommentWorkItem cancelledHistory = new CommentWorkItem();
		cancelledHistory.processManager = processManager;
		cancelledHistory.session = session;
		cancelledHistory.setInstId(new Long(getInstanceId()));
		
		cancelledHistory.setTitle(humanActivity.getName().getText() + " has been skipped by me.");
		cancelledHistory.setWriter(session.getUser());
		cancelledHistory.add();
		
		Instance instance = new Instance();
		instance.setInstId(this.getRootInstId());
		
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
			
			
		if("oce".equals(session.getUx())){
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("instanceList");
			panel.getMetaworksContext().setWhere("sns");
			panel.session = session;
			panel.load(this.getRootInstId().toString());
			
			return new Object[]{panel, new Remover(new ModalWindow() , true )};
		}else{
			return new Object[]{instanceViewContent, new Remover(new ModalWindow(), true)};
		}
	}

	@AutowiredFromClient
	public Session session;
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT, validate=true, target=ServiceMethodContext.TARGET_APPEND)
//	@Available(when={"NEW"})
	public Object[] complete() throws RemoteException, ClassNotFoundException, Exception{
						
		instance = processManager.getProcessInstance(instanceId);
		
		humanActivity = null;
		if (instanceId != null && tracingTag != null) {
			humanActivity = (HumanActivity) instance.getProcessDefinition()
					.getActivity(tracingTag);
		}

		if (!humanActivity.getActualMapping(instance).getEndpoint()
				.equals(session.getUser().getUserId())) {
			throw new Exception("$NotPermittedToComplete");

		}

		// load map for ITool
		loadMapForITool((Map<String, Object>)makeMapForITool());
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			
			String variableTypeName = parameters[i].getVariableType();
			//Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;

			processVariableValue = (Serializable) parameters[i].getValueObject();
		
//				if(variableType == String.class){
//				}else if(Long.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueNumber();
//				}else if(Calendar.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueCalendar();
//				}

			if(processVariableValue instanceof ITool){
				((ITool)processVariableValue).beforeComplete();
			}
			
			rp.setProcessVariableChange(new KeyedParameter(pv.getVariableName(), processVariableValue));
		}
		
		processManager.completeWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp);
		
		// 변경된 액티비티 들만 찾기
		String[] executedTaskIds = executedActivityTaskIds(instance);
        
		processManager.applyChanges();
		
		if(parameters!=null){
			for(int i=0; i<parameters.length; i++){				
				Serializable processVariableValue = null;
				processVariableValue = (Serializable) parameters[i].getValueObject();
				
				if(processVariableValue instanceof ITool){
					((ITool)processVariableValue).afterComplete();
				}
			}
		}
		
		releaseMapForITool();
		
		ArrayList<WorkItem> newlyAddedWorkItems = new ArrayList<WorkItem>();
		
		for(String taskId : executedTaskIds){
			ProcessWorkItem newlyAppendedWorkItem = new ProcessWorkItem();
			newlyAppendedWorkItem.setTaskId(new Long(taskId));
			newlyAppendedWorkItem.copyFrom(newlyAppendedWorkItem.databaseMe());
			
			newlyAddedWorkItems.add(newlyAppendedWorkItem);
		}
		
		// TODO pushTargetClientObjects 를 하고 나면 copyOfInstance 가 변경이 되는 상황이 발생하여 새로운 객체를 생성하여줌
		Instance inst = new Instance();
		inst.setInstId(this.getRootInstId());
		inst.copyFrom(inst.databaseMe());
		
		this.saveLastComent(inst);
		inst.flushDatabaseMe();
		
		WorkItem workItemMe = new WorkItem();
		workItemMe.setTaskId(this.getTaskId());
		workItemMe.copyFrom(workItemMe.databaseMe());
		workItemMe.setMetaworksContext(new MetaworksContext());
		/**
		 *  === noti push 부분 ===
		 *  위쪽에서 topic notiuser를 구하였지만 noti를 보내는 사람을 구하는 로직은 다를수 있으니 다시한번 구한다.
		 */
		HashMap<String, String> notiUsers = new HashMap<String, String>();
		Notification notification = new Notification();
		notification.session = session;
		notiUsers = notification.findInstanceNotiUser(inst.getInstId().toString());
		if(inst.getTopicId() != null){
			HashMap<String, String> topicNotiUsers = notification.findTopicNotiUser(inst.getTopicId());
			Iterator<String> iterator = topicNotiUsers.keySet().iterator();
			while(iterator.hasNext()){
				String followerUserId = (String)iterator.next();
				notiUsers.put(followerUserId, topicNotiUsers.get(followerUserId));
			}
		}
		
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
				noti.setInstId(new Long(getInstanceId()));					
				noti.setActAbstract(session.getUser().getName() + " completed workItem : " + inst.getName());
				noti.add(inst);
			}
		}
		
		MetaworksRemoteService.pushTargetScriptFiltered(new AllSessionFilter(notiUsers),
				"mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh",
				new Object[]{});
		
		/**
		 *  === instance push 부분 ===
		 */
		notiUsers.putAll(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()));	
		
//		if( this.getRootInstId() != null && this.getRootInstId() != new Long(this.getInstanceId())){
//			// 상위 인스턴스가 있는 경우
//		}else{
			inst.getMetaworksContext().setWhere("instancelist");
			// 본인의 instanceList 에 push
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(inst)});
			
			// 본인 이외에 다른 사용자에게 push			
			// 새로 추가되는 workItem이 있는 경우 - 1. 새로추가된 workItem은 append를 하고 2.완료시킨 워크아이템은 리프레쉬를 시킨다
			if( newlyAddedWorkItems.size() > 0 ){
				for(int j=0; j < newlyAddedWorkItems.size(); j++){
					ProcessWorkItem wt = (ProcessWorkItem)newlyAddedWorkItems.get(j);
					wt.setMetaworksContext(new MetaworksContext());
					MetaworksRemoteService.pushClientObjectsFiltered(
							new OtherSessionFilter(notiUsers , session.getUser().getUserId().toUpperCase()),
							new Object[]{new WorkItemListener(WorkItemListener.COMMAND_APPEND , wt)});
				}
			}
			// 새로 추가되는 workItem이 없는 경우 1. 완료시킨 워크아이템은 리프레쉬를 시킨다
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(notiUsers , session.getUser().getUserId().toUpperCase()),
					new Object[]{new InstanceListener(inst) ,  new WorkItemListener(WorkItemListener.COMMAND_REFRESH , workItemMe)});			
			
//		}
		//refreshes the instanceview so that the next workitem can be show up
		if("oce".equals(session.getUx()) || "sns".equals(session.getEmployee().getPreferUX())){
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("instanceList");
			panel.getMetaworksContext().setWhere("sns");
			panel.session = session;
			panel.load(this.getRootInstId().toString());
			
			return new Object[]{panel, new Remover(new ModalWindow() , true )};
		}else{
			/*
			 * 2013/12/03 cjw
			 * 프로세스 완료시에 수정된 워크아이템만 부분 갱신되게 수정
			 */
			InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
			instanceViewThreadPanel.setInstanceId(this.getInstanceId());
			
			return new Object[]{new ToAppend(instanceViewThreadPanel, newlyAddedWorkItems), new Refresh(workItemMe)};

		}
	}
	
	private IInstance saveLastComent(Instance instanceRef) throws Exception{
		String title = humanActivity.getDescription() != null ? humanActivity.getDescription().getText() : null;
		
		//마지막 워크아이템의 제목을 인스턴스의 적용
		if( title != null && !"".equals(title)){
			if(instanceRef.getLastCmnt() == null){
				instanceRef.setLastCmnt(title);
				instanceRef.setLastCmntUser(session.getUser());
				instanceRef.setLastcmntTaskId(this.getTaskId());
				// database update
				instanceRef.databaseMe().setLastCmnt(title);
				instanceRef.databaseMe().setLastCmntUser(session.getUser());
				instanceRef.databaseMe().setLastcmntTaskId(this.getTaskId());
			}else{
				if(instanceRef.getLastCmnt2() == null){
					instanceRef.setLastCmnt2(title);
					instanceRef.setLastCmnt2User(session.getUser());
					instanceRef.setLastcmnt2TaskId(this.getTaskId());
					// database update
					instanceRef.databaseMe().setLastCmnt2(title);
					instanceRef.databaseMe().setLastCmnt2User(session.getUser());
					instanceRef.databaseMe().setLastcmnt2TaskId(this.getTaskId());
				}else {
					instanceRef.setLastCmnt(instanceRef.getLastCmnt2());
					instanceRef.setLastCmntUser(instanceRef.getLastCmnt2User());
					instanceRef.setLastcmntTaskId(instanceRef.getLastcmnt2TaskId());
					
					instanceRef.setLastCmnt2(title);
					instanceRef.setLastCmnt2User(session.getUser());
					instanceRef.setLastcmnt2TaskId(this.getTaskId());
					// database update
					instanceRef.databaseMe().setLastCmnt(instanceRef.getLastCmnt2());
					instanceRef.databaseMe().setLastCmntUser(instanceRef.getLastCmnt2User());
					instanceRef.databaseMe().setLastcmntTaskId(instanceRef.getLastcmnt2TaskId());
					
					instanceRef.databaseMe().setLastCmnt2(title);
					instanceRef.databaseMe().setLastCmnt2User(session.getUser());
					instanceRef.databaseMe().setLastcmnt2TaskId(this.getTaskId());
				}
			}
		}
		return instanceRef;
	}
	@Autowired
	public InstanceViewContent instanceViewContent;
	
			
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT )
	public void save() throws RemoteException, ClassNotFoundException, Exception{
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			String variableTypeName = parameters[i].getVariableType();
			//Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;

			processVariableValue = (Serializable) parameters[i].getValueObject();
		
//				if(variableType == String.class){
//				}else if(Long.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueNumber();
//				}else if(Calendar.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueCalendar();
//				}

			
			rp.setProcessVariableChange(new KeyedParameter(pv.getVariableName(), processVariableValue));
		}
		
		processManager.saveWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp );
//			processManager.applyChanges(); //you may call this. since you can ensure this service method is the service itself
	}
	
	@ServiceMethod(callByContent=true, when="compete")
	public Object[]  accept() throws Exception{
		instance = processManager.getProcessInstance(instanceId.toString());
		
		humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		
		
		RoleMapping roleMapping = RoleMapping.create();
		roleMapping.setName(humanActivity.getRole().getName());
		roleMapping.setEndpoint(session.getEmployee().getEmpCode());		
		
		processManager.delegateWorkitem(this.getInstanceId(), this.getTracingTag(), roleMapping);
		processManager.applyChanges();
		
		//refreshes the instanceview so that the next workitem can be show up
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		instance.copyFrom(instance.databaseMe());
		
		instanceViewContent.load(instance);
		
		if("oce".equals(session.getUx()) || "sns".equals(session.getEmployee().getPreferUX())){
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("instanceList");
			panel.getMetaworksContext().setWhere("sns");
			panel.session = session;
			panel.load(this.getRootInstId().toString());
			
			return new Object[]{panel, new Remover(new ModalWindow() , true )};
		
		}else {
			return new Object[]{instanceViewContent, new Remover(new ModalWindow(), true)};
			
		}
		
	}
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT , target=ServiceMethodContext.TARGET_APPEND)
	public ReplyOverlayCommentWorkItem comment() throws Exception{
		
		OverlayCommentOption overlayCommentOption = new OverlayCommentOption();
		overlayCommentOption.setParentTaskId(getTaskId());
		
		ReplyOverlayCommentWorkItem replyOverlayCommentWorkItem = new ReplyOverlayCommentWorkItem();
		replyOverlayCommentWorkItem.session = session;
		replyOverlayCommentWorkItem.processManager = processManager;
		replyOverlayCommentWorkItem.setOverlayCommentOption(overlayCommentOption);
		replyOverlayCommentWorkItem.setInstId(new Long(getInstanceId()));
		replyOverlayCommentWorkItem.setTitle(replyTitle);
		replyOverlayCommentWorkItem.setExt1(replyFieldName);
		replyOverlayCommentWorkItem.setPrtTskId(getTaskId());
		replyOverlayCommentWorkItem.setEndpoint(session.getUser().getUserId());
		replyOverlayCommentWorkItem.setRootInstId(this.getRootInstId());
		replyOverlayCommentWorkItem.add();
		
		replyOverlayCommentWorkItem.getMetaworksContext().setWhen("edit");
		return replyOverlayCommentWorkItem;
	}
			
	@Autowired
	transient public ProcessManagerRemote processManager;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	protected Map<String, Object> makeMapForITool()
			throws Exception {
		Map<String, Object> mapForITool = new HashMap<String, Object>();

		mapForITool.put(ITool.ITOOL_INSTANCEID_KEY, getInstanceId());
		mapForITool.put(ITool.ITOOL_TRACINGTAG_KEY, getTracingTag());
		mapForITool.put(ITool.ITOOL_TASKID_KEY, getTaskId().toString());
		mapForITool.put(ITool.ITOOL_SESSION_KEY, session);
		mapForITool.put(ITool.ITOOL_PROCESS_MANAGER_KEY, processManager);
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT1_KEY, humanActivity.getExtValue1());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT2_KEY, humanActivity.getExtValue2());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT3_KEY, humanActivity.getExtValue3());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT4_KEY, humanActivity.getExtValue4());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT5_KEY, humanActivity.getExtValue5());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT6_KEY, humanActivity.getExtValue6());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT7_KEY, humanActivity.getExtValue7());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT8_KEY, humanActivity.getExtValue8());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT9_KEY, humanActivity.getExtValue9());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT10_KEY, humanActivity.getExtValue10());
		
		
		
		return mapForITool;
	}
	
	private void loadMapForITool(Map<String, Object> map) {
		TransactionContext.getThreadLocalInstance().setSharedContext(
				ITool.ITOOL_MAP_KEY, map);
	}

	private void releaseMapForITool() {
		TransactionContext.getThreadLocalInstance().setSharedContext(
				ITool.ITOOL_MAP_KEY, null);
	}		
	
	
	
	public static String[] executedActivityTaskIds(ProcessManagerRemote processManager, String instanceId) throws Exception {
		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		
		return executedActivityTaskIds(instance);
	}
	
	public static String[] executedActivityTaskIds(ProcessInstance instance) throws Exception {
        List executedActivityCtxs = instance.getProcessTransactionContext().getExecutedActivityInstanceContextsInTransaction();
        
		List<String> newlyAddedWorkItems = new ArrayList<String>();
        if(executedActivityCtxs!=null && executedActivityCtxs.size() > 0){
            for(int i=executedActivityCtxs.size()-1; i>-1; i--){
                    ActivityInstanceContext lastActCtx = (ActivityInstanceContext)executedActivityCtxs.get(i);
                    Activity lastAct = lastActCtx.getActivity();
                    
                    if(lastAct instanceof HumanActivity && lastActCtx.getInstance().isRunning(lastAct.getTracingTag())){
                        HumanActivity newlyStartedAct = (HumanActivity)lastAct;
						String[] taskIds = newlyStartedAct.getTaskIds(instance);
                        
						for(String taskId : taskIds){
                            newlyAddedWorkItems.add(taskId);
						}							
                    }
            }
        }		
        
        return newlyAddedWorkItems.toArray(new String[newlyAddedWorkItems.size()]);
	}
}
