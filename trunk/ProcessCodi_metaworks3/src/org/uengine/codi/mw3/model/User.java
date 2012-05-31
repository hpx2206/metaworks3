package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
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
	
	public ContactList addContact() throws Exception {
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList();
		cp.load(session.getUser().getUserId());
		
		return cp;		
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
		Popup popup = new Popup();
		
		this.getMetaworksContext().setWhen("info");
		
		popup.setName("Friend Info");
		popup.setPanel(this);

		return popup;
	}
	
	@Override
	public UnstructuredProcessInstanceStarter chat() throws Exception{		
		UnstructuredProcessInstanceStarter instanceStarter = new UnstructuredProcessInstanceStarter();
		
		instanceStarter.getMetaworksContext().setHow("chat");
		instanceStarter.setFriend(this);
		
		return instanceStarter;
	}
	
//	@Autowired
//	ProcessManagerRemote processManager;
	
	public Object[] removeFollower() throws Exception {
		String whereText = getMetaworksContext().getWhere();
		boolean isDeleted = false;
		String instId = "";
		
		if(whereText.startsWith(Followers.CONTEXT_WHERE_INFOLLOWERS)){
			instId = whereText.substring(whereText.indexOf(":")+1);
			
			if(!session.getUser().getUserId().trim().equals(getUserId().trim())) {
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
		} else {
//			Popup popup = new Popup("프로세스 참여자는 삭제할 수 없습니다.");
//			System.out.println("프로세스 참여자는 삭제할 수 없습니다.");
//			return new Object[] {popup};
			throw new Exception("프로세스 참여자는 삭제할 수 없습니다.");
		}
		
		Followers followers = new Followers();
			
		//TODO delete rolemapping
//		processManager.removeRoleMapping(instId, "follower_" + getName(), getUserId());
//		processManager.applyChanges();

		RoleMapping roleMapping = new RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
		isDeleted = roleMapping.deleteByInfo(session);
			
		followers.setInstanceId(instId);
		followers.load();
		
		if(isDeleted) {
			System.out.println("delete follower done.");
			return new Object[] {followers};
		} else {
//			Popup popup = new Popup("프로세스 참여자는 삭제할 수 없습니다.");
//			System.out.println("프로세스 참여자는 삭제할 수 없습니다.");
//			return new Object[] {popup};
			throw new Exception("프로세스 참여자는 삭제할 수 없습니다.");
		}
		
	}
	
}
