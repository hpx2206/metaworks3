package org.uengine.codi.mw3.model;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.Login;
import org.uengine.processmanager.ProcessManagerRemote;

public class UnstructuredProcessInstanceStarter {

	String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
		
		
	String friendId;
		
		public String getFriendId() {
			return friendId;
		}
	
		public void setFriendId(String friendId) {
			this.friendId = friendId;
		}

	@ServiceMethod(callByContent = true)
	public Object[] start() throws Exception{
		
		//good example: fully reused code are below:
		
		ResourceFile unstructuredProcessDefinition = new ResourceFile();
		unstructuredProcessDefinition.processManager = processManager;
		unstructuredProcessDefinition.session = session;
		unstructuredProcessDefinition.instanceViewContent = instanceViewContent;
		unstructuredProcessDefinition.setAlias(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
		
		Object[] instanceViewAndInstanceList = unstructuredProcessDefinition.initiate();
		
		final InstanceViewContent instanceView = (InstanceViewContent) instanceViewAndInstanceList[0];
		
		instanceView.getInstanceView().getInstanceNameChanger().setInstanceName(getTitle());
		instanceView.getInstanceView().getInstanceNameChanger().change();
		
		if(friendId!=null){

			Browser.withSession(Login.getSessionIdWithUserId(getFriendId()), new Runnable(){

				@Override
				public void run() {
					ScriptSessions.addFunctionCall("mw3.getAutowiredObject('org.uengine.codi.mw3.model.Tray').__getFaceHelper().addTray", new Object[]{session.getUser().getName(), instanceView.getInstanceView().getInstanceId()});
				}
				
			});
		}
		
		
		return instanceViewAndInstanceList;
		
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
}
