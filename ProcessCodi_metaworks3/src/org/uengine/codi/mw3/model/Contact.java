package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;

import org.metaworks.dao.Database;


public class Contact extends Database<IContact> implements IContact{

	public IContact loadContacts() throws Exception{
		return loadContacts(null);
	}
	public IContact loadContacts(String keyword) throws Exception{
		IUser friend = new User();
		friend.setName(keyword + '%');
		
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*, e.mood")
		.append("  from contact a left outer join emptable e")
		.append("    on a.friendid = e.empcode")
		.append(" where a.userId=?userId")
		.append("   and a.network=?network");
		
		if(keyword != null && keyword.length() > 0)
			sb.append("   and a.friendName like ?friendName");
		
		IContact contacts = sql(sb.toString());
		contacts.setUserId(getUserId());
		contacts.setNetwork(getNetwork());
		contacts.setFriend(friend);		
		contacts.select();
		contacts.setMetaworksContext(getMetaworksContext());
		return contacts;
	}
	
	public IContact findContactsWithFriendName(String keyword) throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId and friendName like '%" + keyword + "%'");
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}
	
	public void addContact() throws Exception{
		if(!this.getUserId().equals(getFriend().getUserId())){
			IContact contact = sql("select * from contact where userid = ?userId and friendId = ?friendId");
			contact.setFriend(getFriend());
			contact.setUserId(getUserId());
			
			contact.select();
			
			if(contact.size() == 0){
				createDatabaseMe();
				flushDatabaseMe();
			}else{
				// TODO : 이미 추가된 사람이라는 오류 처리 ?
			}
		}else{
			// TODO : 본인 선택 했다는 오류 처리 ?
		}
	}
	
	public void removeContact() throws Exception{
		IContact contact = sql("delete from contact where userid = ?userId and friendId = ?friendId");
		contact.setFriend(getFriend());
		contact.setUserId(getUserId());

		contact.update();
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
		
	String network;
	
		public String getNetwork() {
			return network;
		}
		public void setNetwork(String network) {
			this.network = network;
		}
		
	String mood;
		
		public String getMood() {
			return mood;
		}
		public void setMood(String mood) {
			this.mood = mood;
		}
		
		
		
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
	
}

