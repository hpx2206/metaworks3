package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel;
import org.uengine.kernel.EJBProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceView {

	@Autowired
	public ProcessManagerRemote processManager;	
	
	public InstanceView() {
	}		
		
	public void load(IInstance instance) throws Exception{

		Instance inst = new Instance();	
		inst.setInstId(instance.getInstId());
		inst.copyFrom(inst.databaseMe());
		String secuopt = inst.getSecuopt();
		
		//if(session!=null){
			Notification notificationType = new Notification();
			INotification notifictionsInSameInstance  = notificationType.sql("update bpm_noti set confirm=1 where instid = ?instid and userId=?userId");
			notifictionsInSameInstance.setConfirm(true);
			notifictionsInSameInstance.setInstId(inst.getInstId());
			notifictionsInSameInstance.setUserId(session.getUser().getUserId());
			notifictionsInSameInstance.update();
		//}

		if(inst.databaseMe().getIsDeleted()){
			throw new Exception("Deleted Instance");
		}

		setInstanceId(instance.getInstId().toString());
		setStatus(inst.databaseMe().getStatus());
		setSecuopt(secuopt);
		
		inst.setMetaworksContext(getMetaworksContext());
		loadDefault(inst);
		
		if("1".equals(getSecuopt())){ //means secured conversation
			
			IUser followers = getFollowers().getFollowers();
			followers.beforeFirst();
			
			boolean iCanSee = false;
			while(followers.next()){
				if(session.getUser().getUserId().equals(followers.getUserId())){
					iCanSee = true;
					break;
				}
			}
			IDept deptFollower = getFollowers().getDeptFollowers();
			while( deptFollower.next() ){
				if(deptFollower.getPartCode().equals(session.getEmployee().getPartCode()) ){
					iCanSee = true;
					break;
				}
			}
			
			if(!iCanSee){
				throw new Exception("$NotPermittedToSee");
			}
			
			followers.beforeFirst();
			
		}
		
		setInstanceViewThreadPanel(activityStream());
		
	}
	
	/*
	 * 2013-01-21 jinwon
	 * InstanceViewThreadPanel 로 대체
	 * 
	@ServiceMethod(inContextMenu=true, callByContent=true, needToConfirm=true, target="popup", mouseBinding="drop")
	public Object[] drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IWfNode){
			WfNode draggedNode = (WfNode) clipboard;
			
			//setting the first workItem as wfNode referencer
			GenericWorkItem genericWI = new GenericWorkItem();
			
			genericWI.processManager = processManager;
			genericWI.session = session;
			genericWI.setTitle("Attaching Knowledge");//parent.getName());
			GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
			KnowledgeTool knolTool = new KnowledgeTool();
			knolTool.setNodeId(draggedNode.getId());
			genericWIH.setTool(knolTool);
			
			genericWI.setGenericWorkItemHandler(genericWIH);
			genericWI.setInstId(new Long(getInstanceId()));
			// TODO attach thread 
			return genericWI.add();
//		}else{
//		
//			Instance instance = new Instance();
//			instance.session = session;
//			instance.processManager = processManager;
//			instance.setInstId(new Long(getInstanceId()));
//			return instance.paste();
		}
		return null;
	}
	*/
	
	
	String status;
	
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}

	InstanceNameChanger instanceNameChanger;
		public InstanceNameChanger getInstanceNameChanger() {
			return instanceNameChanger;
		}
		public void setInstanceNameChanger(InstanceNameChanger instanceNameChanger) {
			this.instanceNameChanger = instanceNameChanger;
		}
		
	InstanceSecurityConfigurer instanceSecurityConfigurer;

		public InstanceSecurityConfigurer getInstanceSecurityConfigurer() {
			return instanceSecurityConfigurer;
		}

		public void setInstanceSecurityConfigurer(
				InstanceSecurityConfigurer instanceSecurityConfigurer) {
			this.instanceSecurityConfigurer = instanceSecurityConfigurer;
		}

	String instanceName;
		@Name
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}
	
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}	
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}

	@AutowiredFromClient
	public Session session;

	protected void loadDefault(Instance inst) throws Exception{
		ProcessInstance instance = processManager.getProcessInstance(getInstanceId());
		
		followers = new InstanceFollowers();
		followers.setInstanceId(instanceId);
		followers.load();
		
		newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.setTaskId(new Long(-1));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		//Session session = (Session) TransactionContext.getThreadLocalInstance().getSharedContext("codi_session");

		if(session==null){
			session = new Session();
			session.user = User.fromHttpSession();
		}
		
		newItem.setWriter(session.user);
		
		//ProcessInstanceMonitor flowChart = new ProcessInstanceMonitor();
		//flowChart.setInstanceId(instanceId);
		//setProcessInstanceMonitor(flowChart);
		
		setInstanceName(inst.getName());
		
		if(inst.getDefId() == null){
			crowdSourcer = new CrowdSourcer();
			crowdSourcer.setInstanceId(getInstanceId());
			crowdSourcer.setFollowers(this.followers);
			crowdSourcer.setMessage(getInstanceName());
			
			if(instance.getProperty("", "facebook_postIds") != null){
				String[] postIds = (String[])instance.getProperty("", "facebook_postIds");
				crowdSourcer.setPostIds(postIds);			
			}
			
			boolean isOpen = false;
			if(instance.getProperty("", "is_open") != null){
				isOpen = ((String)instance.getProperty("", "is_open")).equals("open") ? true : false;
			}
			
			System.out.println("isOpen :" + isOpen);
			crowdSourcer.setOpen(isOpen);
		
		
		
			//threadPosting = new PostingsWorkItem();
			
			externalFeedback = new ArrayList<FacebookFeedback>();
			
			if(instance.getProperty("", "facebook_postIds") != null){
				String[] postIds = (String[]) instance.getProperty("", "facebook_postIds");
				
				for(int i=0; i<postIds.length; i++){
					FacebookFeedback postingWorkItem = new FacebookFeedback();
					postingWorkItem.setPostId(postIds[i]);
					//postingWorkItem.setType("posting");
					
					externalFeedback.add(postingWorkItem);
				}			
			}	
			eventTriggerPanel = new EventTriggerPanel(instance);
		}
		
		
		instanceNameChanger = new InstanceNameChanger();
		instanceNameChanger.setInstanceId(this.getInstanceId());
		instanceNameChanger.setInstanceName(this.getInstanceName());
		
		setInstanceSecurityConfigurer(new InstanceSecurityConfigurer());
		getInstanceSecurityConfigurer().setInstanceId(instanceId);

	}
	
	ArrayList<FacebookFeedback> externalFeedback;
		
		public ArrayList<FacebookFeedback> getExternalFeedback() {
			return externalFeedback;
		}
	
		public void setExternalFeedback(ArrayList<FacebookFeedback> externalFeedback) {
			this.externalFeedback = externalFeedback;
		}


	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

	IWorkItem newItem;
		public IWorkItem getNewItem() {
			return newItem;
		}
		public void setNewItem(IWorkItem newItem) {
			this.newItem = newItem;
		}
		
//	NewInstancePanel newSubInstancePanel;
//		public NewInstancePanel getNewSubInstancePanel() {
//			return newSubInstancePanel;
//		}
//		public void setNewSubInstancePanel(NewInstancePanel newSubInstancePanel) {
//			this.newSubInstancePanel = newSubInstancePanel;
//		}
	
//	IWorkItem thread;
//		public IWorkItem getThread() {
//			return thread;
//		}
//		public void setThread(IWorkItem thread) {
//			this.thread = thread;
//		}	

	InstanceViewThreadPanel instanceViewThreadPanel;
		public InstanceViewThreadPanel getInstanceViewThreadPanel() {
			return instanceViewThreadPanel;
		}
		public void setInstanceViewThreadPanel(
				InstanceViewThreadPanel instanceViewThreadPanel) {
			this.instanceViewThreadPanel = instanceViewThreadPanel;
		}

//	IWorkItem threadPosting;
//		public IWorkItem getThreadPosting() {
//			return threadPosting;
//		}
//		public void setThreadPosting(IWorkItem threadPosting) {
//			this.threadPosting = threadPosting;
//		}

	public ProcessInstanceMonitorPanel processInstanceMonitor;
			
		public ProcessInstanceMonitorPanel getProcessInstanceMonitor() {
			return processInstanceMonitor;
		}
	
		public void setProcessInstanceMonitor(
				ProcessInstanceMonitorPanel processInstanceMonitor) {
			this.processInstanceMonitor = processInstanceMonitor;
		}

	EventTriggerPanel eventTriggerPanel;
				
		public EventTriggerPanel getEventTriggerPanel() {
			return eventTriggerPanel;
		}
	
		public void setEventTriggerPanel(EventTriggerPanel eventTrigger) {
			this.eventTriggerPanel = eventTrigger;
		}

	ScheduleEditor scheduleEditor;
		public ScheduleEditor getScheduleEditor() {
			return scheduleEditor;
		}
		public void setScheduleEditor(ScheduleEditor scheduleEditor) {
			this.scheduleEditor = scheduleEditor;
		}
		
	InstanceFollowers followers;
		public InstanceFollowers getFollowers() {
			return followers;
		}
		public void setFollowers(InstanceFollowers followers) {
			this.followers = followers;
		}
		
	CrowdSourcer crowdSourcer;			
		public CrowdSourcer getCrowdSourcer() {
			return crowdSourcer;
		}
	
		public void setCrowdSourcer(CrowdSourcer crowdSourcer) {
			this.crowdSourcer = crowdSourcer;
		}

	//TODO: Please remove this method after Fake
	@ServiceMethod 
	@Test(scenario="first", starter=true, instruction="댓글로 메모, 파일등을 입력할 수 있고 기타 동적인 업무를 추가할 수 있습니다. 여기서 기타활동을 클릭하십시오.", next="autowiredObject.org.uengine.codi.mw3.model.IProcessMap.initiate()")
	public void newActivity() {}
	
	
	@ServiceMethod(target="popup", loader="auto")
	public Popup schedule() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		 
		if(!instance.databaseMe().getInitEp().equals(session.getUser().getUserId())  && !(session.getEmployee()!=null && session.getEmployee().getIsAdmin())){
			throw new Exception("$OnlyInitiatorCanSetDueDate");
		}

		InstanceDueSetter ids = new InstanceDueSetter();
		ids.setInstId(new Long(getInstanceId()));
		ids.setDueDate(instance.databaseMe().getDueDate());
		ids.setOnlyInitiatorCanComplete(instance.databaseMe().isInitCmpl());
		ids.setProgress(instance.databaseMe().getProgress());
		if("sns".equals(session.getEmployee().getPreferUX()) ){
			ids.getMetaworksContext().setHow("instanceList");
			ids.getMetaworksContext().setWhere("sns");
		}
		ids.getMetaworksContext().setWhen("edit");
		
		/*return new Popup(350,200,ids);*/
		return new Popup(400,300,ids);
	}
	
	@ServiceMethod(payload={"instanceId"}, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] remove() throws Exception{

		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		 
		IInstance instanceRef = instance.databaseMe();
		
		if(!instanceRef.getInitEp().equals(session.getUser().getUserId())  && !(session.getEmployee()!=null && session.getEmployee().getIsAdmin())){
			throw new Exception("$OnlyInitiatorCanDeleteTheInstance");
		}
		
		processManager.stopProcessInstance(instanceId);

		instanceRef.setIsDeleted(true);
		instance.flushDatabaseMe();
		
		/* 내가 할일 카운트 다시 계산 */
		if(instanceRef.getDefId() != null || (instanceRef.getDefId() == null && instanceRef.getDueDate() != null)){
			TodoBadge todoBadge = new TodoBadge();
			todoBadge.session = session;
			todoBadge.refresh();

			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()),
					new Object[]{new Refresh(todoBadge)});			
		}
		
		if(!"sns".equals(session.getEmployee().getPreferUX())){
			NewInstancePanel instancePanel = new NewInstancePanel();
			instancePanel.load(session);
			
			return new Object[]{new Remover(instance), new Refresh(new ContentWindow(instancePanel))};
		}else{
			return new Object[]{new Remover(instance)};
		}
	}
	
	@ServiceMethod(payload={"instanceId", "status"})
	public void complete() throws Exception{

		//processManager.stopProcessInstance(instanceId);
		
		String tobe = (getStatus().equals("Completed") ? "Running" : "Completed");
		
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		IInstance instanceRef = instance.databaseMe();
				
		if(instance.isInitCmpl() && !session.getUser().getUserId().equals(instance.getInitEp())){
			throw new Exception("$OnlyInitiatorCanComplete");
		}
		
		// instance update flush
		instanceRef.setStatus(tobe);
		instance.flushDatabaseMe();

		this.load(instance);
		this.setStatus(tobe);
		
		MetaworksRemoteService.pushClientObjects(new Object[]{new InstanceListener(InstanceListener.COMMAND_REFRESH, instanceRef)});

		/* 내가 할일 카운트 다시 계산 */
		if(instanceRef.getDefId() != null || (instanceRef.getDefId() == null && instanceRef.getDueDate() != null)){
			TodoBadge todoBadge = new TodoBadge();
			todoBadge.session = session;
			todoBadge.refresh();

			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()),
					new Object[]{new Refresh(todoBadge)});			
		}
	}
		
	
	
	@ServiceMethod(target="popup", loader="auto")
//	@Test(scenario="first", starter=true, instruction="자유로운 대화와 정형화된 프로세스가 어우러진 프로세스를 모니터링 합니다.", next="autowiredObject.org.uengine.codi.mw3.admin.PageNavigator.goKnowledge()")
	@Test(scenario="first", starter=true, instruction="$first.Monitor", next="autowiredObject.org.uengine.codi.mw3.model.InstanceListPanel.switchToKnowledge()")

	public ModalWindow monitor() throws Exception{
		
		ModalWindow modal = new ModalWindow();
		
		modal.setTitle("Process Monitoring");
		modal.setWidth(700);
		modal.setHeight(800);
		ProcessInstanceMonitorPanel processInstanceMonitorPanel = new ProcessInstanceMonitorPanel();
		processInstanceMonitorPanel.processManager = processManager;
		processInstanceMonitorPanel.session = session;
		processInstanceMonitorPanel.load(instanceId);
		
		//setProcessInstanceMonitor(processInstanceMonitorPanel);
		
		modal.setPanel(processInstanceMonitorPanel);
		
		//loadDefault();
		
		return modal;
	}
	@ServiceMethod(target="popup", loader="auto")
	public ModalWindow showWFlow() throws Exception{
		
		ModalWindow modal = new ModalWindow();
		
		modal.setTitle("Process Monitoring");
		modal.setWidth(1024);
		modal.setHeight(768);
		InstanceMonitorPanel processInstanceMonitorPanel = new InstanceMonitorPanel();
		processInstanceMonitorPanel.processManager = processManager;
		processInstanceMonitorPanel.session = session;
		processInstanceMonitorPanel.load(instanceId);
		
		//setProcessInstanceMonitor(processInstanceMonitorPanel);
		
		modal.setPanel(processInstanceMonitorPanel);
		
		//loadDefault();
		
		return modal;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup newSubInstancePanel() throws Exception{
		
//		NewInstancePanel newSubInstancePanel = new NewInstancePanel();
//		newSubInstancePanel.setParentInstanceId(getInstanceId());
//		
//		newSubInstancePanel.load();

		//Good example :   customizing for specific usage - removing some parts
//		newSubInstancePanel.setUnstructuredProcessInstanceStarter(null);
		
		
//		ProcessMapPanel processMapPanel;
//		processMapPanel = new ProcessMapPanel();		
//		processMapPanel.setMetaworksContext(this.getMetaworksContext());
//		processMapPanel.load();

		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);
		processMapList.setParentInstanceId(new Long(getInstanceId()));
		
		Popup popup = new Popup();
		popup.setPanel(processMapList);
		
		return popup;
		
	}

	@ServiceMethod
	public InstanceViewThreadPanel activityStream() throws Exception{
		InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
		instanceViewThreadPanel.session = session;
		instanceViewThreadPanel.load(instanceId);
		
		return instanceViewThreadPanel;
	}

	
	@ServiceMethod(callByContent=true)
	public void toggleSecurityConversation() throws Exception{
		getInstanceSecurityConfigurer().toggleSecureConversation();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instanceId));
		String secuopt = instance.databaseMe().getSecuopt();
		
		setSecuopt(secuopt);
		
//		loadDefault();
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 	
		
	
	@AutowiredFromClient(onDrop = true)
	public IInstance dropInstance;
}
