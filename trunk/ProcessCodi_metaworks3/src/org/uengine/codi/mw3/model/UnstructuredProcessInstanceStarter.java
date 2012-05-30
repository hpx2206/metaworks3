package org.uengine.codi.mw3.model;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.CodiProcessDefinitionFactory;
import org.uengine.codi.mw3.Login;
import org.uengine.processmanager.ProcessManagerRemote;

public class UnstructuredProcessInstanceStarter implements ContextAware {

	public UnstructuredProcessInstanceStarter() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
	}
	
	String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}		

	IUser friend;
		public IUser getFriend() {
			return friend;
		}
		public void setFriend(IUser friend) {
			this.friend = friend;
		}

	MetaworksContext metaworksContext;	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
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
		
		if(getFriend() != null && getFriend().getUserId() != null){

			Browser.withSession(Login.getSessionIdWithUserId(getFriend().getUserId()), new Runnable(){

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
