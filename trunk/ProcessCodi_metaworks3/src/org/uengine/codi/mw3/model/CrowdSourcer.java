package org.uengine.codi.mw3.model;


import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;

public class CrowdSourcer {
	
	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
			
	String[] postIds;
		public String[] getPostIds() {
			return postIds;
		}
		public void setPostIds(String[] postIds) {
			this.postIds = postIds;
		}

	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE, callByContent=true)
	public void crowdSourcing() throws Exception{
		try{
			ProcessInstance instance = processManager.getProcessInstance(getInstanceId());
		
			instance.setProperty("", "facebook_postIds", getPostIds());
			processManager.applyChanges();
			
		}catch(Exception e){
			throw e;
		}finally{		
			processManager.remove();
		}
		
	}		
	
	Followers followers;
	public Followers getFollowers() {
		return followers;
	}
	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	@Autowired
	ProcessManagerRemote processManager;
	
}
