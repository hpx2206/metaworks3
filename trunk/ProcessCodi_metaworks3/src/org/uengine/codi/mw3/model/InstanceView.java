package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.facebook.api.Facebook;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceView{
	

	
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
		@Face(displayName=" ")
		public IWorkItem getThread() {
			return thread;
		}
		public void setThread(IWorkItem thread) {
			this.thread = thread;
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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE)
	public void crowdSourcing() throws Exception{
		  String access_token = "d71fb40f35d4296e308bd16654269bd8";
		  String myappkey = "296566710367686";
		  
		  String fb_message = "contents here";
		  String facebookName = "crowd sourcing test";
		  String facebookURL = "http://www.uengine.org";
		  
		  Map<String, String> data = new HashMap<String, String>();  
		  data.put("message", fb_message);  
		  data.put("name", facebookName);  
		  data.put("link", facebookURL);   
		  data.put("picture", "http://cfile25.uf.tistory.com/image/165BBF484E4BBD8F3402C0");   
		  
		  Followers followers = new Followers();
		  followers.setInstanceId(getInstanceId());
		  followers.load();
		  
		  IUser followerUsers = followers.getFollowers();
		  while(followerUsers.next()){
			  String friendkey = followerUsers.getUserId();
			  Facebook graph_api = new Facebook(friendkey, access_token);  
			  JSONObject result = graph_api.sendPostToMyFriend(data); 
			  
			  result.toString();
		  }
	}

	@Autowired
	ProcessManagerRemote processManager;
			

}
