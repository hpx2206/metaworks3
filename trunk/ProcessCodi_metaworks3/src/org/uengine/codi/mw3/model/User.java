package org.uengine.codi.mw3.model;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.sql.RowSet;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.processmanager.ProcessManagerRemote;

public class User extends Database<IUser> implements IUser {
	
	public final static String FRIEND = "friend";
	
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
		
	boolean userChecked;
		public boolean isUserChecked() {
			return userChecked;
		}
		public void setUserChecked(boolean userChecked) {
			this.userChecked = userChecked;
		}
		
	int businessValue;
		public int getBusinessValue() {
			return businessValue;
		}
		public void setBusinessValue(int businessValue) {
			this.businessValue = businessValue;
		}
		
	int todoCount;
		public int getTodoCount() {
			return todoCount;
		}
		public void setTodoCount(int todoCount) {
			this.todoCount = todoCount;
		}
		
	boolean guest;
		public boolean isGuest() {
			return guest;
		}
		public void setGuest(boolean guest) {
			this.guest = guest;
		}
		
	String inviteUser;
		public String getInviteUser() {
			return inviteUser;
		}
		public void setInviteUser(String inviteUser) {
			this.inviteUser = inviteUser;
		}

	@AutowiredFromClient
	public Session session;

	@Override
	public Popup pickUp() throws Exception {
		Popup popup = new Popup();
		
		String type = "addPicker";
		ContactPanel contactPanel = new ContactPanel(session.getUser());
		contactPanel.getContactListPanel().setId(type);
		if(contactPanel.getContactListPanel().getLocalContactList() != null)
			contactPanel.getContactListPanel().getLocalContactList().getMetaworksContext().setWhen(type);		
		if(contactPanel.getContactListPanel().getSocialContactList() != null)
			contactPanel.getContactListPanel().getSocialContactList().getMetaworksContext().setWhen(type);
		contactPanel.getUser().getMetaworksContext().setWhen(type);
		
		/*AddFollowerPanel userPicker = new AddFollowerPanel( session , null , "addAskFollower" );
		userPicker.setMetaworksContext(new MetaworksContext()); // propagate context
		userPicker.getMetaworksContext().setWhen("userPicker");*/
		
		popup.setPanel(contactPanel);
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
		
		int with = 435;
		int height = 275;
		
		System.out.println("when : " + getMetaworksContext().getWhen());
		
		this.getMetaworksContext().setHow("info");
		
		String when = this.getMetaworksContext().getWhen();
		
		if(when != null && when.equals(Followers.CONTEXT_WHERE_INFOLLOWERS)){
			String instId = instanceFollowers.getInstanceId();
			
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
			setGuest(employee.databaseMe().isGuest());
			setInviteUser(employee.databaseMe().getInviteUser());			
			
			//선택된 유저의 business value를 보인다.
			int myBV = getBV(getUserId());
			setBusinessValue(myBV);
			
			//선택된 유저가 해야 할 일의 개수를 보인다.
			int todoCount = Instance.countTodo(getUserId(), employee.databaseMe().getGlobalCom());
			setTodoCount(todoCount);
			
			setNetwork("local");
			
			//선택한 유저 recentItem에 add
			RecentItem recentItem = new RecentItem();
			recentItem.setEmpCode(session.getEmployee().getEmpCode());
			recentItem.setItemId(this.getUserId());
			recentItem.setItemType(FRIEND);
			recentItem.setUpdateDate(Calendar.getInstance().getTime());
			
			recentItem.add();
			
		}catch(Exception e){
		//	e.printStackTrace();
		}
		
		Popup popup = new Popup(with, height);
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
	public Followers etcFollowers;
	
	@AutowiredFromClient
	public TopicFollowers topicFollowers;
	
	@AutowiredFromClient
	public DocumentFollowers documentFollowers;
	
	@AutowiredFromClient
	public InstanceFollowers instanceFollowers;
	
	@AutowiredFromClient
	public AddFollowerPanel addFollowerPanel;
	
	@AutowiredFromClient
	public InstanceListPanel instanceListPanel;
	

	public Object[] addFollower() throws Exception {
		
		if(addFollowerPanel!=null && "userPicker".equals(addFollowerPanel.getMetaworksContext().getWhen())){
			getMetaworksContext().setWhen("edit");
			getMetaworksContext().setHow("picker");
			
			return new Object[]{new Remover(new Popup()), new ToOpener(this)};
			
		}else if("addDocumentFollower".equals(this.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(session.getLastSelectedItem());
			tm.setUserId(this.getUserId());
			
			if( !tm.findByUser().next() ){
				tm.setUserName(this.getName());
				tm.saveMe();
				tm.flushDatabaseMe();
			}
			
			DocumentFollowers documentFollowers = new DocumentFollowers();
			documentFollowers.session = session;
			documentFollowers.load();
			
			return new Object[]{new Refresh(documentFollowers)};
		
		}else if("addTopicFollower".equals(this.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(session.getLastSelectedItem());
			tm.setUserId(this.getUserId());
			
			if( !tm.findByUser().next() ){
				tm.setUserName(this.getName());
				tm.saveMe();
				tm.flushDatabaseMe();
			}
			
			TopicFollowers topicFollowers = new TopicFollowers();
			topicFollowers.session = session;
			topicFollowers.load();
			
			ContactPanel contactPanel = new ContactPanel(session, Followers.ADD_TOPICFOLLOWERS);
			
			return new Object[]{new Refresh(topicFollowers), new Refresh(contactPanel)};
//		}else if("addInstanceFollower".equals(this.getMetaworksContext().getWhen())){
//			String instId = instanceFollowers.getInstanceId();
//			
//			Instance instance = new Instance();
//			instance.setInstId(new Long(instId));
//			
//			processManager.putRoleMapping(instId, RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getName(), getUserId());
//			processManager.applyChanges();
//			
//			InstanceFollowers followers = new InstanceFollowers();
//			followers.setInstanceId(instId);
//			followers.load();
//			
//			/// send notification to the follower 
//			
//			final boolean postByMe = getUserId().equals(session.getUser().getUserId());
//			if(!postByMe){ //ignore myself
//				Notification noti = new Notification();
//				
//				noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
//				noti.setUserId(getUserId());
//				noti.setActorId(session.getUser().getUserId());
//				noti.setConfirm(false);
//				noti.setInputDate(Calendar.getInstance().getTime());
//				//noti.setTaskId(getTaskId());
//				noti.setInstId(new Long(instId));
//				noti.setActAbstract(session.getUser().getName() + " added "  + getName()+ " to '" + instance.databaseMe().getName() + "'");
//	
//				noti.createDatabaseMe();
//				noti.flushDatabaseMe();
//			
//			
//				String followerSessionId = Login.getSessionIdWithUserId(getUserId());
//				
//				try{
//					//NEW WAY IS GOOD
//					Browser.withSession(followerSessionId, new Runnable(){
//		
//						@Override
//						public void run() {
//							//refresh notification badge
//							if(!postByMe)
//								ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
//							
//						}
//						
//					});
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//			////// end
//			
//			return new Object[]{new Refresh(followers)};
			// TODO 이 부분은 변경됨 FollowerSelectPanel.java 참조
		}else if("addInstanceFollower".equals(this.getMetaworksContext().getWhen())){			
			
			String instId = instanceFollowers.getInstanceId();

			Instance instance = new Instance();
			instance.setInstId(new Long(instId));
			
			processManager.putRoleMapping(instId, RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getUserId(), getUserId());
			processManager.applyChanges();
			
			InstanceFollowers followers = new InstanceFollowers();
			followers.setInstanceId(instId);
			followers.load();
			
			/// send notification to the follower 
			
			final boolean postByMe = getUserId().equals(session.getUser().getUserId());
			if(!postByMe){ //ignore myself
				Notification noti = new Notification();
				
				noti.session = session;
				noti.setActor(this);
				
				noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
				noti.setUserId(getUserId());
				noti.setActorId(session.getUser().getUserId());
				noti.setConfirm(false);
				noti.setInputDate(Calendar.getInstance().getTime());
				//noti.setTaskId(getTaskId());
				noti.setInstId(new Long(instId));
				noti.setActAbstract(session.getUser().getName() + " added "  + getName()+ " to '" + instance.databaseMe().getName() + "'");
	
				//워크아이템에서 노티를 추가할때와 동일한 로직을 수행하도록 변경
//				noti.createDatabaseMe();
//				noti.flushDatabaseMe();
				noti.add(instance.databaseMe());
			
				String followerSessionId = Login.getSessionIdWithUserId(getUserId());
				
				try{
					//NEW WAY IS GOOD
					Browser.withSession(followerSessionId, new Runnable(){
		
						@Override
						public void run() {
							//refresh notification badge
							if(!postByMe){
								ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
							}
						}
						
					});
				}catch(Exception e){
					e.printStackTrace();
				}
			}
//			////// end
			
			ContactPanel contactPanel = new ContactPanel(session, Followers.ADD_INSTANCEFOLLOWERS);
			
			return new Object[]{new Refresh(contactPanel), new Refresh(followers)};
			
			
			//TODO: restored by jjy. 
			
			//
		}else if("addEtcFollower".equals(this.getMetaworksContext().getWhen())){			
			etcFollowers.put(this);
			
			return new Object[]{new Refresh(etcFollowers)};
		}else if("addPicker".equals(this.getMetaworksContext().getWhen())){
			getMetaworksContext().setWhen("edit");
			getMetaworksContext().setHow("picker");
			
			return new Object[]{new Remover(new Popup()), new ToOpener(this)};			
		}
		
		return null;
	}
	@ServiceMethod(target=TARGET_APPEND)
	public Object[] removeFollower() throws Exception {
		if("topicFollowers".equals(this.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(session.getLastSelectedItem());
			tm.setUserId(this.getUserId());
			
			ITopicMapping rs = tm.findByUser();
			if( rs.next() ){
				tm.setTopicMappingId(rs.getTopicMappingId());
				tm.remove();
				
				TopicFollowers topicFollowers = new TopicFollowers();
				topicFollowers.session = session;
				topicFollowers.load();
				
				return new Object[]{new Refresh(topicFollowers)};
			}else{
				throw new Exception("삭제 실패");
			}
			
		}else if("documentFollowers".equals(this.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(session.getLastSelectedItem());
			tm.setUserId(this.getUserId());
			
			ITopicMapping rs = tm.findByUser();
			if(rs.next()){
				tm.setTopicMappingId(rs.getTopicMappingId());
				tm.remove();
				
				DocumentFollowers documentFollowers = new DocumentFollowers();
				documentFollowers.session = session;
				documentFollowers.load();
				
				return new Object[]{new Refresh(documentFollowers)};

			}else{
				throw new Exception("삭제 실패");
			}
			
		}else{
			String instId = instanceFollowers.getInstanceId();
			
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
			
			InstanceFollowers followers = new InstanceFollowers();
				
			//TODO delete rolemapping
	//		processManager.removeRoleMapping(instId, "follower_" + getName(), getUserId());
	//		processManager.applyChanges();
	
			RoleMapping roleMapping = new RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + getUserId(), getUserId());
			if(roleMapping.deleteByInfo(session)) {
				followers.setInstanceId(instId);
				followers.load();
	
				System.out.println("delete follower done.");
				return new Object[]{new Refresh(followers)};  // refresh로 새로 뿌려주기
				
			} else {
	//			Popup popup = new Popup("프로세스 참여자는 삭제할 수 없습니다.");
	//			System.out.println("프로세스 참여자는 삭제할 수 없습니다.");
	//			return new Object[] {popup};
				throw new Exception("Author can not be removed from follower list.");
			}
		}
	}
	
	public Refresh addContact() throws Exception {
		
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setFriendId(this.getUserId());
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
		
		return new Object[] {new Refresh(contactList , true)};
		
	}
	
//	@ServiceMethod(target="popup", payload={"userId", "network"})
	public Popup info() throws Exception{
		Popup infoWindow = new Popup(600, 425);
		
		Employee me = new Employee();
		me.setEmpCode(getUserId());
		
		IEmployee dbMe = me.databaseMe();	
		dbMe.getMetaworksContext().setWhen("view");		
		dbMe.getMetaworksContext().setHow("detail");
		dbMe.getMetaworksContext().setWhere("inDetailPopup");

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
	
	@Override
	public void drop() {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IUser){
			
			IUser user = (IUser) clipboard;
			
			try {
				if(!user.getUserId().equals(getUserId()))
					copyFrom(user);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}	
	
	}

	@Override
	public Object[] unsubscribe() throws Exception {
		
		IEmployee emp = new Employee();
		emp.setEmpCode(getUserId());
		Employee employee = (Employee)emp.findMe();	
		
		String inviteUser = employee.getInviteUser();
		
		if(getUserId().equals(session.getUser().getUserId()) || session.getEmployee().getIsAdmin() ||
				(inviteUser != null && inviteUser.equals(session.getUser().getUserId()))) {
			
			employee.databaseMe().setIsDeleted("1");		
			
			if(getUserId().equals(session.getUser().getUserId()))
				return new Object[]{session.logout(), new Remover(new ModalWindow())};
			else
				return new Object[]{new Remover(employee , true)};
		}
		else
			throw new Exception("$OnlyTheAdminAndWriterAndInviteUserCanUnsubscribe");
	}
	
	@Override
	public void guestToUser() throws Exception {	
		
		IEmployee emp = new Employee();
		emp.setEmpCode(getUserId());
		Employee employee = (Employee)emp.findMe();		
		
		String inviteUser = employee.getInviteUser();
		
		if((inviteUser != null && inviteUser.equals(session.getUser().getUserId()))
				|| session.getEmployee().getIsAdmin()) {
			
			employee.databaseMe().setGuest(false);
			this.setGuest(employee.databaseMe().isGuest());
		}
		else
			throw new Exception("$OnlyTheAdminAndInviteUserCanEdit");
	}
	
	@Override
	public void userToGuest() throws Exception {
		IEmployee emp = new Employee();
		emp.setEmpCode(getUserId());
		Employee employee = (Employee)emp.findMe();		
		
		String inviteUser = employee.getInviteUser();
		
		if((inviteUser != null && inviteUser.equals(session.getUser().getUserId()))
				|| session.getEmployee().getIsAdmin()) {
			
			employee.databaseMe().setGuest(true);
			this.setGuest(employee.databaseMe().isGuest());
		}
		else
			throw new Exception("$OnlyTheAdminAndInviteUserCanEdit");
	}

	@Override
	public void addAsAdmin() throws Exception {
		if(session.getEmployee().getIsAdmin()){
			
			Employee employeeType = new Employee();
			employeeType.setEmpCode(getUserId());
			employeeType.databaseMe().setIsAdmin(true);
			
		}else
			throw new Exception("$AdminOnly");
	}
	
	@Override
	public Object[] showWall() throws Exception{
		
		Employee employee = new Employee();
		employee.setEmpCode(getUserId());
		employee.copyFrom(employee.databaseMe());
		employee.session = session;
		
		// "organization" 팔로워기준 - 자기가 추가되어있으면 다보임
//		Object[] employeeList = employee.loadOrganization();
		
		// "request"  시작자기준 
		employee.session.setEmployee(employee);
		Object[] employeeList = Perspective.loadInstanceListPanel(session, "request", employee.getEmpCode(), "사원 : " + employee.getEmpName() + "(" + employee.getJikName() +")");
		
		return new Object[]{(InstanceListPanel) employeeList[1], new Remover(new Popup())};
	}
	
	@Override
	public Object[] showSchedule() throws Exception{
		InstanceListPanel instListPanel = new InstanceListPanel();
		
		ScheduleCalendar scheduleCalendar = new ScheduleCalendar();
		scheduleCalendar.session = session;
		scheduleCalendar.loadByUserId(getUserId());
		
		instListPanel.setPreloaded(true);
		instListPanel.setScheduleCalendar(scheduleCalendar);
		instListPanel.setTitle(getName() + " Schedule");
		
		return new Object[]{instListPanel, new Remover(new Popup())};
	}
	
	@ServiceMethod()
	public int getBV(String empCode) throws Exception{
		
		int totalBV = 0;
		
		String sql = "select sum(businessvalue) totalBV from inst_emp_perf where empcode=?empCode";
		
		IInstanceEmployeePerformance getBV = (IInstanceEmployeePerformance) Database.sql(IInstanceEmployeePerformance.class, sql);
		getBV.setEmpCode(empCode);
		getBV.select();
		
		RowSet rowset = getBV.getImplementationObject().getRowSet();
		
		if(rowset.next()){
			totalBV = rowset.getInt("totalBV");
		}
		
		return totalBV;
		
	}
	
	public void approvedSubscribe() throws Exception {
		if(!session.getEmployee().getIsAdmin()){
			throw new Exception("$AdminOnly");
		}
		
		Employee employee = new Employee();
		employee.setEmpCode(this.getUserId());
		employee.databaseMe().setApproved(true);
	}
	
	public Object showMenu() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setEmpCode(session.getEmployee().getEmpCode());
		
		employee.setMetaworksContext(new MetaworksContext());
		employee.getMetaworksContext().setWhere("user_menu_option");
		
		Popup menu = new Popup();
		menu.setHeight(98);
		menu.setWidth(200);
		menu.setPanel(employee);
		
		return menu;
	}
}
