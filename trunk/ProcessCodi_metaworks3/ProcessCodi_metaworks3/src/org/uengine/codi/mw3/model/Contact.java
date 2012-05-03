package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ORMapping;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class Contact extends Database<IContact> implements IContact{
	
	public IContact loadContacts() throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId");
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}

	public IContact findContactsWithFriendName() throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId and friendName like '%" + getFriendName() + "%'");
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}
	
	public void addContact() throws Exception{
		createDatabaseMe();
		flushDatabaseMe();
	}
	
	IUser friends;
		public IUser getFriends() {
			return friends;
		}
	
		public void setFriends(IUser friends) {
			this.friends = friends;
		}
			
		
	String userId;
		
		public String getUserId() {
			return userId;
		}
	
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	String friendId;

		public String getFriendId() {
			return friendId;
		}
	
		public void setFriendId(String friendId) {
			this.friendId = friendId;
		}
		
	String friendName;
		public String getFriendName() {
			return friendName;
		}
	
		public void setFriendName(String friendName) {
			this.friendName = friendName;
		}

	@AutowiredFromClient
	public AddFollowerPanel addFollowerPanel;

	public Followers addFollower() throws Exception {
		processManager.putRoleMapping(addFollowerPanel.getInstanceId(), "follower_" + getFriendName(), getFriendId());
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
		User user = new User(); //this should have error - more than the @Id, the objectId is the closest one.
		user.setUserId(this.getFriendId());
		user.setName(this.getFriendName());
		user.getMetaworksContext().setWhen("pickUp"); //keep the context 
		
		return user;
	}
	
}

