package org.uengine.codi.mw3.model;


import java.rmi.RemoteException;
import java.util.StringTokenizer;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.knowledge.ProjectCommitter;
import org.uengine.codi.mw3.knowledge.SvnUser;
import org.uengine.kernel.GlobalContext;


public class Contact extends Database<IContact> implements IContact{
	
	public final static String DEFAULT_TOPIC_COUNT = "9";
	public final static String TOPIC = "topic";
	public final static String ETC = "etc";

	public IContact loadContacts(boolean isSelected) throws Exception{
		IUser friend = new User();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct c.userId, c.friendId, ifnull(e.empname, c.friendName) friendName, e.mood, item.updatedate")
		  .append("  from contact c ")
		  .append("  	left join emptable e")
		  .append("    		on c.friendid = e.empcode")
		  .append("  	left join recentItem item ")
		  .append("    		on item.itemId = e.empcode and item.empcode = c.userId and item.itemType=?itemType")
		  .append(" where c.userId=?userId")
		  .append("   and e.isDeleted=?isDeleted");
		
		if(this.getFriend() != null && this.getFriend().getName() != null){
			sb.append("   and c.friendName like ?friendName");
			sb.append("   and c.network=?network");
		}
		
		if(this.getMetaworksContext().getHow() != null && this.getMetaworksContext().getHow().equals("follower")) {			
			if(TOPIC.equals(session.getLastInstanceId())) {
				sb.append(" and not exists")
				  .append(" (select t.userid")
				  .append(" from bpm_topicmapping t")
				  .append(" where topicid='" + session.getLastSelectedItem() + "' and assigntype=0 and t.userid=c.friendId)");
			}else if(ETC.equals(session.getLastInstanceId())) {
			}else {
				sb.append(" and not exists")
				  .append(" (select distinct r.endpoint")
				  .append(" from bpm_rolemapping r")
				  .append(" where rootinstid='" + session.getLastInstanceId() +"' and assigntype = 0 and r.endpoint = c.friendId)");
			}
		}
		
		sb.append(" order by updatedate desc ");
		
		if(!isSelected) {
			sb.append("   limit " + GlobalContext.getPropertyString("contact.more.count", DEFAULT_TOPIC_COUNT));
		}
		
 		IContact contacts = sql(sb.toString());
		contacts.setUserId(getUserId());		
		if(this.getFriend() != null && this.getFriend().getName() != null){
			contacts.set("friendName", this.getFriend().getName() + "%");
			contacts.set("network", this.getFriend().getNetwork());
		}
		contacts.set("itemType", User.FRIEND);
		contacts.set("isDeleted", "0");
		contacts.select();
		contacts.setMetaworksContext(getMetaworksContext());
		return contacts;
	}
	
	public IContact findContactsWithFriendName(String keyword) throws Exception{
		IContact contacts = sql("select * from contact where userId=?userId and friendName like '%" + keyword + "%'");
		
		contacts.set("friendName", keyword);
		contacts.setUserId(getUserId());
		contacts.select();
		
		return contacts;
	}
	
	public void addContact() throws Exception{
		if(!this.getUserId().equals(getFriend().getUserId())){
			IContact contact = sql("select * from contact where userid = ?userId and friendId = ?friendId");
			contact.set("friendId", this.getFriend().getUserId());
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
		contact.set("friendId", getFriend().getUserId());
		contact.setUserId(getUserId());

		contact.update();
	}

	String friendId;
		public String getFriendId() {
			return friendId;
		}
		public void setFriendId(String friendId) {
			this.friendId = friendId;
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
		
	boolean checked;
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@AutowiredFromClient
	public Session session;
		
	public User pickUp() throws RemoteException, Exception {
		//User user = new User(); //this should have error - more than the @Id, the objectId is the closest one.
		
		User user = new User();
		user.copyFrom(this.getFriend());
		user.getMetaworksContext().setWhen("pickUp"); //keep the context 
		
		return user;
	}

	public User roleUserPickUp() throws RemoteException, Exception {
		User user = new User(); //this should have error - more than the @Id, the objectId is the closest one.
		user.copyFrom(this.getFriend());
		user.getMetaworksContext().setWhere(IUser.MW3_WHERE_ROLEUSER_PICKER_CALLER); //keep the context 
		
		return user;
	}
	
	public void check(){
		this.setChecked(!this.isChecked());
	}
	
}

