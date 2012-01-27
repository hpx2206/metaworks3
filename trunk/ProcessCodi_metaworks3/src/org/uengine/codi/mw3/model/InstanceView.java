package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceView {

	@Autowired
	ProcessManagerRemote processManager;	
	
	public InstanceView() {
	}		
	
	@Override
	protected void finalize() throws Throwable {
		processManager.remove();
	}
	
	public void load(IInstance instance) throws Exception{
		
		
		setInstanceId(instance.getInstId().toString());
		
		loadDefault();
		activityStream();
		
	}
	
	protected void loadDefault() throws Exception{
		
		followers = new Followers();
		followers.setInstanceId(instanceId);
		followers.load();
		
		newItem = new CommentWorkItem();
		newItem.setInstId(new Long(getInstanceId()));
		newItem.setTaskId(new Long(getInstanceId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);		
		
		crowdSourcer = new CrowdSourcer();
		crowdSourcer.setInstanceId(getInstanceId());
		crowdSourcer.followers = this.followers;
		
		ProcessInstance instance = processManager.getProcessInstance(getInstanceId());
		
		if(instance.getProperty("", "facebook_postIds") != null){
			String[] postIds = (String[])instance.getProperty("", "facebook_postIds");
			
			crowdSourcer.setPostIds(postIds);
		}
		
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

	IWorkItem thread;
		public IWorkItem getThread() {
			return thread;
		}
		public void setThread(IWorkItem thread) {
			this.thread = thread;
		}	
	
	IWorkItem threadPosting;
		public IWorkItem getThreadPosting() {
			return threadPosting;
		}
		public void setThreadPosting(IWorkItem threadPosting) {
			this.threadPosting = threadPosting;
		}

	ProcessInstanceMonitor processInstanceMonitor;
		public ProcessInstanceMonitor getProcessInstanceMonitor() {
			return processInstanceMonitor;
		}
		public void setProcessInstanceMonitor(
				ProcessInstanceMonitor processInstanceMonitor) {
			this.processInstanceMonitor = processInstanceMonitor;
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
	public void schedule() throws Exception{
		scheduleEditor = new ScheduleEditor();
		scheduleEditor.setInstanceId(instanceId);
		
		loadDefault();
	}
	
	@ServiceMethod 
	public void monitor() throws Exception{
		processInstanceMonitor = new ProcessInstanceMonitor();
		processInstanceMonitor.setInstanceId(instanceId);

		loadDefault();
	}

	@ServiceMethod
	public void activityStream() throws Exception{
		setThread(WorkItem.find(instanceId));
	}

	@ServiceMethod(needToConfirm = true)
	public void delete() throws RemoteException{
		processManager.stopProcessInstance(instanceId);
		processManager.applyChanges();
	}	
}
