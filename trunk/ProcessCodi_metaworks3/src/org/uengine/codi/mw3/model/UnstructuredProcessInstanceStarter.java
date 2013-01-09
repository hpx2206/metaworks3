package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
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
		//@Face(ejsPath="genericfaces/richText.ejs", options={"rows"}, values={"5"})
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

	@ServiceMethod(callByContent = true, keyBinding="enter", target=ServiceMethodContext.TARGET_APPEND)
	@Test(scenario="first", starter=true, instruction="지금 생각하고 계신 것을 간략히 입력하고 클릭합니다.", next="autowiredObject.org.uengine.codi.mw3.model.InstanceView.newActivity()")
	public Object[] start() throws Exception{
		
		//good example: fully reused code are below:
		
		ResourceFile unstructuredProcessDefinition = new ResourceFile();
		unstructuredProcessDefinition.processManager = processManager;
		unstructuredProcessDefinition.session = session;
		unstructuredProcessDefinition.instanceViewContent = instanceViewContent;
		unstructuredProcessDefinition.setAlias(CodiProcessDefinitionFactory.unstructuredProcessDefinitionLocation);
		
		ArrayList<IUser> friends = new ArrayList<IUser>();
		friends.add(friend);
		unstructuredProcessDefinition.friends = friends;
		
		Object[] instanceViewAndInstanceList = unstructuredProcessDefinition.initiate();
		
		final InstanceViewContent instanceView = (InstanceViewContent) instanceViewAndInstanceList[0];
		
		instanceView.getInstanceView().getInstanceNameChanger().setInstanceName(getTitle());
		instanceView.getInstanceView().getInstanceNameChanger().change();

		
		Instance instance = new Instance();
		instance.setInstId(new Long
				(instanceView.getInstanceView().instanceId));
		User initUser = new User();
		initUser.setUserId(session.getUser().getUserId());
		initUser.setName(session.getUser().getName());
		instance.databaseMe().setInitiator(initUser);
//		instance.databaseMe().setInitEp(session.user.getUserId());
		instance.databaseMe().setDueDate(null);
		
		if(session.getEmployee() != null)
			instance.databaseMe().setInitComCd(session.getEmployee().getGlobalCom());
		
			
		if("chat".equals(getMetaworksContext().getWhen()))
			instance.databaseMe().setSecuopt("1"); //채팅이면 보안 대화로 
		else
			instance.databaseMe().setSecuopt("0"); // 아니면 전체 공개  
			
		
		CommentWorkItem comment = (CommentWorkItem) instanceView.getInstanceView().getNewItem();
		comment.processManager = processManager;
		comment.session = session;
		comment.setTitle(getTitle());
		comment.add();
		
		WorkItem newItem = new CommentWorkItem();
		newItem.setInstId(new Long(instanceView.getInstanceView().getInstanceId()));
		newItem.setTaskId(new Long(instanceView.getInstanceView().getInstanceId()));
		newItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		instanceView.getInstanceView().setNewItem(newItem);
	
		Browser.withSession(Login.getSessionIdWithUserId(session.getEmployee().getEmpCode()), new Runnable(){
			@Override
			public void run() {
				ScriptSessions.addFunctionCall("mw3.getAutowiredObject('org.uengine.codi.mw3.model.Tray').__getFaceHelper().addTray", new Object[]{session.getUser().getName(), instanceView.getInstanceView().getInstanceId()});
			}
			
		});
		
		if(getFriend() != null && getFriend().getUserId() != null){
			Browser.withSession(Login.getSessionIdWithUserId(getFriend().getUserId()), new Runnable(){

				@Override
				public void run() {
					ScriptSessions.addFunctionCall("mw3.getAutowiredObject('org.uengine.codi.mw3.model.Tray').__getFaceHelper().addTray", new Object[]{session.getUser().getName(), instanceView.getInstanceView().getInstanceId(), true});
				}
				
			});
		}
		
		/*
		if(newInstancePanel!=null && newInstancePanel.getKnowledgeNodeId() != null){
						
			WfNode parent = new WfNode();
			parent.load(newInstancePanel.getKnowledgeNodeId());
			
			WfNode child = new WfNode();		
			child.setName(getTitle());
			child.setLinkedInstId(Long.parseLong(instanceView.getInstanceView().getInstanceId()));
			parent.addChildNode(child);
			
			child.createMe();

			return new Object[]{instanceView, parent};

		}
		*/
		
		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{new Remover(new Popup())};
		}else
			return new Object[]{instanceViewAndInstanceList[0], instanceViewAndInstanceList[1], new Remover(new Popup())};
	}
	
	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

	@AutowiredFromClient
	public Tray tray;

}
