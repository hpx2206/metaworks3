package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.processmanager.ProcessManagerRemote;

public class Contact extends Database<IContact> implements IContact{
	
	public IContact loadContacts() throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId");
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}

	public IContact findContactsWithFriendName(String keyword) throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId and friendName like '%" + keyword + "%'");
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}
	
	public void addContact() throws Exception{
		createDatabaseMe();
		flushDatabaseMe();
	}
	
	IUser friend;
		public IUser getFriend() {
			return friend;
		}
	
		public void setFriend(IUser friend) {
			this.friend = friend;
		}
			
		
	String userId;		
		public String getUserId() {
			return userId;
		}
	
		public void setUserId(String userId) {
			this.userId = userId;
		}
	


	@AutowiredFromClient
	public AddFollowerPanel addFollowerPanel;

	public Followers addFollower() throws Exception {
		processManager.putRoleMapping(addFollowerPanel.getInstanceId(), "follower_" + getFriend().getName(), getFriend().getUserId());
		processManager.applyChanges();
		
		Followers followers = new Followers();
		followers.setInstanceId(addFollowerPanel.getInstanceId());
		followers.load();
		
		return followers;
	}

	
	@Autowired
	ProcessManagerRemote processManager;
	
	
//	@AutowiredFromClient
//	public User user;

	public User pickUp() throws RemoteException, Exception {
		//User user = new User(); //this should have error - more than the @Id, the objectId is the closest one.
		
		User user = new User();
		user.copyFrom(this.getFriend());
		user.getMetaworksContext().setWhen("pickUp"); //keep the context 
		
		return user;
	}

	@Override
	public User roleUserPickUp() throws RemoteException, Exception {
		User user = new User(); //this should have error - more than the @Id, the objectId is the closest one.
		user.copyFrom(this.getFriend());
		user.getMetaworksContext().setWhere(IUser.MW3_WHERE_ROLEUSER_PICKER_CALLER); //keep the context 
		
		return user;
	}
	
	public Popup chat() throws Exception{


		//ScriptSession ss = wctx.getScriptSessionById();

//	   Util utilAll = new Util(ss);
//	   utilAll.addFunctionCall("alert");

		//ss.addScript(script)
		
		Popup chatStarter = new Popup();
		UnstructuredProcessInstanceStarter instanceStarter = new UnstructuredProcessInstanceStarter();
		instanceStarter.setFriendId(getFriend().getUserId());
		
		chatStarter.setPanel(instanceStarter);
		
		return chatStarter;
	}
	
}

