package org.uengine.codi.mw3.model;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.processmanager.ProcessManagerRemote;

import com.sun.tools.xjc.model.CNonElement;

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

	@AutowiredFromClient
	public Session session;

	@Override
	public Popup pickUp() throws Exception {
		Popup popup = new Popup();
		
		
		AddFollowerPanel userPicker = new AddFollowerPanel(fromHttpSession(), null);
		userPicker.setMetaworksContext(new MetaworksContext()); // propagate context
		userPicker.getMetaworksContext().setWhen("userPicker");
		
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
	
	@Override
	public IEmployee loadEmployee() throws Exception{
		Employee emp = new Employee();
		emp.setEmpCode(getUserId());
		
		return emp.databaseMe();
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
		System.out.println("when : " + getMetaworksContext().getWhen());
		
		this.getMetaworksContext().setHow("info");
		
		String when = this.getMetaworksContext().getWhen();
		
		if(when != null && when.equals(Followers.CONTEXT_WHERE_INFOLLOWERS)){
			String instId = followers.getInstanceId();
			
			RoleMapping roleMapping = new RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
			if(!roleMapping.confirmFollower()){
				this.getMetaworksContext().setWhen("participant");
			}
		}
		
		try{
			Employee employee = new Employee();
			employee.setEmpCode(getUserId());
			setMood(employee.databaseMe().getMood());
			setName(employee.databaseMe().getEmpName());
			setNetwork("local");
		}catch(Exception e){
		//	e.printStackTrace();
		}
		
		Popup popup = new Popup(400,203);
		popup.setName("Info");
		popup.setPanel(this);

		return popup;
	}
	
	public Window friends() throws Exception{
		
		ContentWindow contactWindow = new ContentWindow();
		contactWindow.setTitle(getName() + "'s friends");
		ContactPanel contactPanel = new ContactPanel(this);
		contactWindow.setPanel(contactPanel);
		
		return contactWindow;
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
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public Followers followers;
	
	@AutowiredFromClient
	public AddFollowerPanel addFollowerPanel;
	
	public Object[] addFollower() throws Exception {
		
		if("userPicker".equals(addFollowerPanel.getMetaworksContext().getWhen())){
			getMetaworksContext().setWhen("edit");
			return new Object[]{new Remover(new Popup()), new ToOpener(this)};
			
		}else{
		
			
			String instId = followers.getInstanceId();
			
			Instance instance = new Instance();
			instance.setInstId(new Long(instId));
			
			processManager.putRoleMapping(instId, RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
			processManager.applyChanges();
			
			Followers followers = new Followers();
			followers.setInstanceId(instId);
			followers.load();
			
			/// send notification to the follower 
			
			final boolean postByMe = getUserId().equals(session.getUser().getUserId());
			if(!postByMe){ //ignore myself
				Notification noti = new Notification();
				
				noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
				noti.setUserId(getUserId());
				noti.setActorId(session.getUser().getUserId());
				noti.setConfirm(false);
				noti.setInputDate(Calendar.getInstance().getTime());
				//noti.setTaskId(getTaskId());
				noti.setInstId(new Long(instId));
				noti.setActAbstract(session.getUser().getName() + " added "  + getName()+ " to '" + instance.databaseMe().getName() + "'");
	
				noti.createDatabaseMe();
				noti.flushDatabaseMe();
			
			
				String followerSessionId = Login.getSessionIdWithUserId(getUserId());
				
				try{
					//NEW WAY IS GOOD
					Browser.withSession(followerSessionId, new Runnable(){
		
						@Override
						public void run() {
							//refresh notification badge
							if(!postByMe)
								ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
							
						}
						
					});
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			////// end
			
			return new Object[]{new Refresh(followers)};
		}
	}

	public Object[] removeFollower() throws Exception {
				
		String instId = followers.getInstanceId();
		
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
	
	public Refresh addContact() throws Exception {
		
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList contactList = new ContactList();
		contactList.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		
		if(this.getNetwork() != null && this.getNetwork().equals("fb"))
			contactList.getMetaworksContext().setWhere(ContactList.FACEBOOK);
		else
			contactList.getMetaworksContext().setWhere(ContactList.LOCAL);
		
		contactList.load(session.getUser().getUserId());		
		
		return new Refresh(contactList);
	}	
	
	public Object[] removeContact() throws Exception {
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.removeContact();
		
		ContactList contactList = new ContactList();
		contactList.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		contactList.getMetaworksContext().setWhere(this.getMetaworksContext().getWhere());
		contactList.load(session.getUser().getUserId());
		
		return new Object[] {contactList, new Popup()};
		
	}
	
//	@ServiceMethod(target="popup", payload={"userId", "network"})
	public Popup info() throws Exception{
		Popup infoWindow = new Popup();	
		Employee me = new Employee();
		me.setEmpCode(getUserId());
		
		IEmployee dbMe = me.databaseMe();	
		dbMe.getMetaworksContext().setWhen("view");		
		dbMe.getMetaworksContext().setHow("detail");
		dbMe.getMetaworksContext().setWhere("inDetailView");

		infoWindow.setPanel(dbMe);		
		
		return infoWindow;
	}

	@Override
	public void changeMood() throws Exception {

		try{
			Employee employee = new Employee();
			employee.setEmpCode(getUserId());
			employee.databaseMe().setMood(getMood());
			setNetwork("local");
		}catch(Exception e){
		//	e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public Session drag() {
		session.setClipboard(this);
		return session;
	}
	
	
	
}
