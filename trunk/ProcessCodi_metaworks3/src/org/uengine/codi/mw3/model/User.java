package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class User extends Database<IUser> implements IUser {
	
	String name;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}

	String userId;
		public String getUserId() {
			return userId;
		}	
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	@AutowiredFromClient
	public Session session;

	@Override
	public Popup pickUp() throws Exception {
		Popup popup = new Popup();
		
		
		AddFollowerPanel userPicker = new AddFollowerPanel(fromHttpSession(), null);
		userPicker.setMetaworksContext(getMetaworksContext()); // propagate context
		
		popup.setPanel(userPicker);
		popup.setName("AddFollowerPanel");
		
		return popup;
	}
	
	public static User fromHttpSession(){
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession();
		
		User login = new User();
		
		if(session!=null){
			login.setUserId((String) session.getAttribute("userId"));
			
			return login;
		}
		
		return null;
	
	}

	@Override
	public Popup openRoleUserPicker() throws Exception {
		Popup popup = new Popup();
		
		RoleUserPickerPanel roleUserPicker = new RoleUserPickerPanel(session.getUser());
		popup.setPanel(roleUserPicker);
		popup.setName("Role User Pickup Panel");
		return popup;
	}
	
//	String  instanceId;
//	
//	@Override
//	public String getInstanceId() {
//		return instanceId;
//	}
//	
//	@Override
//	public void setInstanceId(String instanceId) {
//		this.instanceId = instanceId;
//	}
	
	@Override
	public Popup detail() throws Exception {
		this.getMetaworksContext().setHow("info");
		
		String when = this.getMetaworksContext().getWhen();
		
		if(when != null && when.equals(Followers.CONTEXT_WHERE_INFOLLOWERS)){
			String instId = follwers.getInstanceId();
			
			RoleMapping roleMapping = new RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
			if(!roleMapping.confirmFollower()){
				this.getMetaworksContext().setWhen("participant");
			}
		}
		
		Popup popup = new Popup(400,203);
		popup.setName("Friend Info");
		popup.setPanel(this);

		return popup;
	}
	
	@Override
	public UnstructuredProcessInstanceStarter chat() throws Exception{
		this.getMetaworksContext().setWhen("chat");
		
		UnstructuredProcessInstanceStarter instanceStarter = new UnstructuredProcessInstanceStarter();
		
		instanceStarter.getMetaworksContext().setWhen("chat");
		instanceStarter.setFriend(this);
		
		return instanceStarter;
	}

	@Autowired
	ProcessManagerRemote processManager;

	@AutowiredFromClient
	public Followers follwers;
	
	public Object[] addFollower() throws Exception {
		String instId = follwers.getInstanceId();
		
		processManager.putRoleMapping(instId, RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
		processManager.applyChanges();
		
		Followers followers = new Followers();
		followers.setInstanceId(instId);
		followers.load();
		
		return new Object[]{followers, new Popup()};
	}

	public Object[] removeFollower() throws Exception {
				
		String instId = follwers.getInstanceId();
		
//		if(whereText.startsWith(Followers.CONTEXT_WHERE_INFOLLOWERS)){
			// TODO: initEp 문제 해결후 권한 체크 활성화
/*			if(!session.getUser().getUserId().trim().equals(getUserId().trim())) {
				Instance instanceObj = new Instance();
				instanceObj.setInstId(new Long(instId));
				String initEp = instanceObj.databaseMe().getInitEp();
				if(!session.getUser().getUserId().trim().equals(initEp.trim())) {
					System.out.println("팔로워를 삭제할 권한이 없습니다. : (current logged userId :" + session.getUser().getUserId() +", initEp : " + initEp +")");
//					Popup popup = new Popup("팔로워를 삭제할 권한이 없습니다.");
//					return new Object[] {popup};
					throw new Exception("팔로워를 삭제할 권한이 없습니다.");
				}
			}
*///		} else {
//			Popup popup = new Popup("프로세스 참여자는 삭제할 수 없습니다.");
//			System.out.println("프로세스 참여자는 삭제할 수 없습니다.");
//			return new Object[] {popup};
//			throw new Exception("프로세스 참여자는 삭제할 수 없습니다.");
		//}
		
		Followers followers = new Followers();
			
		//TODO delete rolemapping
//		processManager.removeRoleMapping(instId, "follower_" + getName(), getUserId());
//		processManager.applyChanges();

		RoleMapping roleMapping = new RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
		if(roleMapping.deleteByInfo(session)) {
			followers.setInstanceId(instId);
			followers.load();

			System.out.println("delete follower done.");
			return new Object[] {followers, new Popup()};
		} else {
//			Popup popup = new Popup("프로세스 참여자는 삭제할 수 없습니다.");
//			System.out.println("프로세스 참여자는 삭제할 수 없습니다.");
//			return new Object[] {popup};
			throw new Exception("삭제 실패");
		}
		
	}
	
	public ContactList addContact() throws Exception {
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList();
		cp.load(session.getUser().getUserId());
		
		return cp;
	}	
	
	public Object[] removeContact() throws Exception {
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.removeContact();
		
		ContactList contactList = new ContactList();
		contactList.load(session.getUser().getUserId());
		
		return new Object[] {contactList, new Popup(this)};
		
	}
	
}
