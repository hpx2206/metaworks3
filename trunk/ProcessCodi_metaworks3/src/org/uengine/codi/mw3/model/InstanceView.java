package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceView {

	@Autowired
	public ProcessManagerRemote processManager;	
	
	public InstanceView() {
	}		
		
	public void load(IInstance instance) throws Exception{
		setMetaworksContext(new MetaworksContext());
		
		setInstanceId(instance.getInstId().toString());
		
		loadDefault();
		setInstanceViewThreadPanel(activityStream());
		
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
		
		
		
		
	@AutowiredFromClient
	public Session session;

	protected void loadDefault() throws Exception{
		ProcessInstance instance = processManager.getProcessInstance(getInstanceId());
		
		
		followers = new Followers();
		followers.setInstanceId(instanceId);
		followers.load();
		
		newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.setTaskId(new Long(getInstanceId()));
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
		
		setInstanceName(instance.getName());

		
		crowdSourcer = new CrowdSourcer();
		crowdSourcer.setInstanceId(getInstanceId());
		crowdSourcer.followers = this.followers;
		crowdSourcer.setMessage("'" + instance.getName() + "' 프로세스에 참여자로 등록했습니다: ");
		
		
		
		
		if(instance.getProperty("", "facebook_postIds") != null){
			String[] postIds = (String[])instance.getProperty("", "facebook_postIds");
			crowdSourcer.setPostIds(postIds);			
		}
		
		boolean isOpen = false;
		if(instance.getProperty("", "is_open") != null){
			isOpen = ((String)instance.getProperty("", "is_open")).equals("open") ? true : false;
		}
		
		crowdSourcer.setOpen(isOpen);
		
		
		
		threadPosting = new PostingsWorkItem();
		/*
		postingsWorkItem = new ArrayList<IWorkItem>();
		
		if(instance.getProperty("", "facebook_postIds") != null){
			
			
			for(int i=0; i<postIds.length; i++){
				PostingsWorkItem postingWorkItem = new PostingsWorkItem();
				postingWorkItem.setPostId(postIds[i]);
				postingWorkItem.setType("posting");
				
				facebookPostings.add(postingWorkItem);
			}			
		}	
		*/
		
		eventTriggerPanel = new EventTriggerPanel(instance);
		
		instanceNameChanger = new InstanceNameChanger();
		instanceNameChanger.setInstanceId(instanceId);
		instanceNameChanger.setInstanceName(instanceName);
		
		setInstanceSecurityConfigurer(new InstanceSecurityConfigurer());
		getInstanceSecurityConfigurer().setInstanceId(instanceId);

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

	IWorkItem threadPosting;
		public IWorkItem getThreadPosting() {
			return threadPosting;
		}
		public void setThreadPosting(IWorkItem threadPosting) {
			this.threadPosting = threadPosting;
		}

//	public ProcessInstanceMonitor processInstanceMonitor;
//
//	public ProcessInstanceMonitor getProcessInstanceMonitor() {
//		return processInstanceMonitor;
//	}
//
//	public void setProcessInstanceMonitor(
//			ProcessInstanceMonitor processInstanceMonitor) {
//		this.processInstanceMonitor = processInstanceMonitor;
//	}
		
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
		
	Followers followers;
		public Followers getFollowers() {
			return followers;
		}
		public void setFollowers(Followers followers) {
			this.followers = followers;
		}
		
	CrowdSourcer crowdSourcer;			
		public CrowdSourcer getCrowdSourcer() {
			return crowdSourcer;
		}
	
		public void setCrowdSourcer(CrowdSourcer crowdSourcer) {
			this.crowdSourcer = crowdSourcer;
		}

	@ServiceMethod
	public ScheduleEditor schedule() throws Exception{
		scheduleEditor = new ScheduleEditor();
		scheduleEditor.setInstanceId(instanceId);
		scheduleEditor.load(processManager);
		//loadDefault();

		return scheduleEditor;
	}
	
	@ServiceMethod(target="popup")
	public ModalWindow monitor() throws Exception{
		
		ModalWindow modal = new ModalWindow();
		
		ProcessInstanceMonitorPanel processInstanceMonitorPanel = new ProcessInstanceMonitorPanel();
		processInstanceMonitorPanel.processManager = processManager;
		processInstanceMonitorPanel.load(instanceId);
		
		modal.setPanel(processInstanceMonitorPanel);
		
		//loadDefault();
		
		return modal;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup newSubInstancePanel() throws Exception{
		
		NewInstancePanel newSubInstancePanel = new NewInstancePanel();
		newSubInstancePanel.setParentInstanceId(getInstanceId());
		newSubInstancePanel.load();
		
		Popup popup = new Popup();
		popup.setPanel(newSubInstancePanel);
		
		return popup;
		
	}

	@ServiceMethod
	public InstanceViewThreadPanel activityStream() throws Exception{
		
		return new InstanceViewThreadPanel(instanceId);
		
		
	}

	@ServiceMethod(needToConfirm = true)
	public void delete() throws RemoteException{
		processManager.stopProcessInstance(instanceId);
		processManager.applyChanges();
	}
	
	@ServiceMethod(callByContent=true)
	public void toggleSecurityConversation() throws Exception{
		getInstanceSecurityConfigurer().toggleSecureConversation();
//		loadDefault();
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 	
}
