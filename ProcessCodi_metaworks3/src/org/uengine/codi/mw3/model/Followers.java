package org.uengine.codi.mw3.model;

import java.util.Calendar;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.processmanager.ProcessManagerRemote;

import org.uengine.kernel.Role;

public class Followers implements ContextAware {
	static final String CONTEXT_WHERE_INFOLLOWERS = "followers";
	static final String CONTEXT_WHERE_DEPTFOLLOWERS = "deptFollower";
	
	static final String ADD_INSTANCEFOLLOWERS = "addInstanceFollower";
	static final String ADD_TOPICFOLLOWERS = "addTopicFollower";
	static final String ADD_ROLEFOLLOWERS = "addRoleFollower";
	static final String ADD_DEPTFOLLOWERS = "addDeptFollower";
	static final String ADD_DOCUMENTFOLLOWERS = "addDocumentFollower";
	static final String ADD_ETCFOLLOWERS = "addEtcFollower";

	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
	IUser followers;
		@Face(ejsPath="genericfaces/ArrayFace.ejs", options={"alignment"}, values={"horizontal"})
		public IUser getFollowers() {
			return followers;
		}
		public void setFollowers(IUser followers) {
			this.followers = followers;
		}
		
	IDept deptFollowers;
		@Face(ejsPath="genericfaces/ArrayFace.ejs", options={"alignment"}, values={"horizontal"})
		public IDept getDeptFollowers() {
			return deptFollowers;
		}
		public void setDeptFollowers(IDept deptFollowers) {
			this.deptFollowers = deptFollowers;
		}
		
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public Followers(){
		setMetaworksContext(new MetaworksContext());
	}
		
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		IUser users = (IUser) Database.sql(IUser.class, "select distinct a.endpoint userId, a.resname name, b.network from bpm_rolemapping a left join contact b on a.endpoint=b.friendId where rootinstid=?instanceId and assigntype = 0 ");
		users.set("instanceId", instanceId);
		users.select();
		
		users.getMetaworksContext().setWhen(CONTEXT_WHERE_INFOLLOWERS);
		setFollowers(users);

		IDept dept = (IDept) Database.sql(IDept.class, "select distinct endpoint PARTCODE, rolename PARTNAME from bpm_rolemapping where rootinstid=?instanceId and assigntype = 2 ");
		dept.set("instanceId", instanceId);
		dept.select();
		
		dept.getMetaworksContext().setHow(CONTEXT_WHERE_DEPTFOLLOWERS);
		setDeptFollowers(dept);
	}
	
	public void put(IUser user) throws Exception {
		if(followers == null)
			followers = (IUser) MetaworksDAO.createDAOImpl(IUser.class);
		
		followers.moveToInsertRow();
		followers.getImplementationObject().copyFrom(user);		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Object[] popupAddFollowers() throws Exception{		
		Popup popup = new Popup(400,400);
		popup.setWidth(400);
		popup.setHeight(400);

		session.setLastInstanceId(this.getInstanceId());
		
		FollowerSelectTab followerSelectTab = new FollowerSelectTab();
		followerSelectTab.setMetaworksContext(new MetaworksContext());
		
		String type = ADD_INSTANCEFOLLOWERS;
		followerSelectTab.getMetaworksContext().setWhere("userDeptTab");
		if("topic".equals(this.getInstanceId())){
			type = ADD_TOPICFOLLOWERS;
			followerSelectTab.getMetaworksContext().setWhere("userDeptTab");
		}else if("dept".equals(this.getInstanceId())){
			type = ADD_DEPTFOLLOWERS;
			followerSelectTab.getMetaworksContext().setWhere("userTab");
		}else if("role".equals(this.getInstanceId())){
			type = ADD_ROLEFOLLOWERS;
			followerSelectTab.getMetaworksContext().setWhere("userTab");
		}else if("etc".equals(this.getInstanceId())){
			type = ADD_ETCFOLLOWERS;
			followerSelectTab.getMetaworksContext().setWhere("userDeptTab");
		}else if("document".equals(this.getInstanceId())){
			followerSelectTab.getMetaworksContext().setWhere("userDeptTab");
			type = ADD_DOCUMENTFOLLOWERS;
		}
		
		followerSelectTab.load(session, type);

		popup.setPanel(followerSelectTab);
		
		return new Object[]{new Refresh(session) , popup};		
	}
	
	public Object[] addFollower(DeptTreeNode dept) throws Exception {
		
		if( "topic".equals(dept.session.getLastPerspecteType()) ){
			TreeNode node = dept;
			TopicMapping tm = new TopicMapping();
			tm.processManager = dept.processManager;
			
			tm.setTopicId( dept.session.getLastSelectedItem() );
			tm.setUserId(node.getId());
			tm.setUserName(node.getName());
			
			if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
				tm.setAssigntype(2);
			}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
				tm.setAssigntype(0);
			}
			tm.saveMe();
			tm.flushDatabaseMe();
			

		}else if( "document".equals(dept.session.getLastPerspecteType()) ){
			TreeNode node = dept;
			TopicMapping tm = new TopicMapping();
			tm.processManager = dept.processManager;
			
			tm.setTopicId( dept.session.getLastSelectedItem() );
			tm.setUserId(node.getId());
			tm.setUserName(node.getName());
			
			if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
				tm.setAssigntype(2);
			}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
				tm.setAssigntype(0);
			}
			tm.saveMe();
			tm.flushDatabaseMe();
			

		}else{
			String instId = dept.session.getLastInstanceId();
			// 현재 부서트리에서는 사용자를 체크 안하게 되어있지만 확장성을 위하여 employee쪽도 넣는 부분을 구현해 놓음
			TreeNode node = dept;
			org.uengine.kernel.RoleMapping roleMap = org.uengine.kernel.RoleMapping.create();
			roleMap.setName(node.getName());
			roleMap.setEndpoint(node.getId());
			roleMap.setResourceName(node.getId());
			
			if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
				roleMap.setAssignType(Role.ASSIGNTYPE_DEPT);
			}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
				roleMap.setAssignType(Role.ASSIGNTYPE_USER);
			}
			dept.processManager.putRoleMapping(instId, roleMap);
			dept.processManager.applyChanges();
		}
				
		return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(dept)};

		
	}
	
	
	public Object[] addFollower(User user) throws Exception {
		
		if(ADD_DOCUMENTFOLLOWERS.equals(user.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(user.session.getLastSelectedItem());
			tm.setUserId(user.getUserId());
			
			if( !tm.findByUser().next() ){
				tm.setUserName(user.getName());
				tm.saveMe();
				tm.flushDatabaseMe();
			}
			
			return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(user)};
		
		}else if(ADD_TOPICFOLLOWERS.equals(user.getMetaworksContext().getWhen())){
			
			// TODO: add instance
			
			// TODO: send noti
			Instance instance = new Instance();
			instance.setInstId(new Long(user.session.getLastSelectedItem()));
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(user.session.getLastSelectedItem());
			tm.setUserId(user.getUserId());

			if( !tm.findByUser().next() ){
				tm.setUserName(user.getName());
				tm.saveMe();
				tm.flushDatabaseMe();
			
				Notification noti = new Notification();
				INotiSetting notiSetting = new NotiSetting();
				noti.session = user.session;
				
				INotiSetting findNotiSetting;
				Employee codi = new Employee();
				codi.setEmpCode("0");
				
				findNotiSetting = notiSetting.findByUserId(user.getUserId());
				if(findNotiSetting.next()){
					if(findNotiSetting.isModiTopic()){
						noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
						noti.setUserId(user.getUserId());
						noti.setActorId(session.getEmployee().getEmpName());
						noti.setConfirm(false);
						noti.setInstId(instance.getInstId());
						noti.setInputDate(Calendar.getInstance().getTime());
						noti.setActAbstract(session.getUser().getName() + " 님이 " + user.getUserId() + "님을 주제에 초대 했습니다.");
			
						//워크아이템에서 노티를 추가할때와 동일한 로직을 수행하도록 변경
						//noti.createDatabaseMe();
						//noti.flushDatabaseMe();
						
						noti.add(instance);
					
						String followerSessionId = Login.getSessionIdWithUserId(user.getUserId());
						
						try{
							//NEW WAY IS GOOD
							Browser.withSession(followerSessionId, new Runnable(){
				
								@Override
								public void run() {
									//refresh notification badge
									ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
								}
								
							});
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
						
			return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(user)};

			// TODO 이 부분은 변경됨 FollowerSelectPanel.java 참조
		}else if(ADD_INSTANCEFOLLOWERS.equals(user.getMetaworksContext().getWhen())){			
			
			String instId = user.session.getLastInstanceId();

			Instance instance = new Instance();
			instance.setInstId(new Long(instId));
			instance.copyFrom(instance.databaseMe());
			user.processManager.putRoleMapping(instId, RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + user.getUserId(), user.getUserId());
			user.processManager.applyChanges();
			
			/// send notification to the follower 
			
			final boolean postByMe = user.getUserId().equals(user.session.getUser().getUserId());
			if(!postByMe){ //ignore myself
				Notification noti = new Notification();
				
				noti.session = user.session;
				noti.setActor(user);
				
				noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
				noti.setUserId(user.getUserId());
				noti.setActorId(user.session.getUser().getUserId());
				noti.setConfirm(false);
				noti.setInputDate(Calendar.getInstance().getTime());
				//noti.setTaskId(getTaskId());
				noti.setInstId(new Long(instId));
				noti.setActAbstract(user.session.getUser().getName() + " added "  + user.getName()+ " to '" + instance.databaseMe().getName() + "'");
	
				//워크아이템에서 노티를 추가할때와 동일한 로직을 수행하도록 변경
				//noti.createDatabaseMe();
				//noti.flushDatabaseMe();
				noti.add(instance.databaseMe());
			
				String followerSessionId = Login.getSessionIdWithUserId(user.getUserId());
				
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
			
			return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(user)};
			
			//TODO: restored by jjy. 
		}else if(ADD_DEPTFOLLOWERS.equals(user.getMetaworksContext().getWhen())){
			IEmployee findEmp = new Employee();
			findEmp.setEmpCode(user.getUserId());
			
			Employee emp = new Employee();
			emp.copyFrom(findEmp.findMe());
			emp.setPartCode(user.session.getLastSelectedItem());
			emp.syncToDatabaseMe();
			emp.flushDatabaseMe();
			
			//Instance instance = new Instance();
			//instance.setInstId(new Long(session.getLastSelectedItem()));
			
			Notification noti = new Notification();
			INotiSetting notiSetting = new NotiSetting();
			noti.session = user.session;
			
			INotiSetting findNotiSetting;
			Employee codi = new Employee();
			codi.setEmpCode("0");
			
			findNotiSetting = notiSetting.findByUserId(user.getUserId());
			if(findNotiSetting.next()){
				if(findNotiSetting.isModiOrgan()){
					noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
					noti.setUserId(user.getUserId());
					noti.setActorId(user.session.getEmployee().getEmpName());
					noti.setConfirm(false);
					//noti.setInstId(instance.getInstId());
					noti.setInputDate(Calendar.getInstance().getTime());
					noti.setActAbstract(user.session.getUser().getName() + " 님이 " + user.getUserId() + "님을 그룹에 초대 했습니다.");
		
					//워크아이템에서 노티를 추가할때와 동일한 로직을 수행하도록 변경
					//noti.createDatabaseMe();
					//noti.flushDatabaseMe();
					
					noti.add(null);
				
					String followerSessionId = Login.getSessionIdWithUserId(user.getUserId());
					
					try{
						//NEW WAY IS GOOD
						Browser.withSession(followerSessionId, new Runnable(){
			
							@Override
							public void run() {
								//refresh notification badge
								ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
							}
							
						});
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(user)};

		}else if(ADD_ROLEFOLLOWERS.equals(user.getMetaworksContext().getWhen())){
			
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleCode(user.session.getLastSelectedItem());
			roleUser.setEmpCode(user.getUserId());
			IRoleUser findRoleUser = roleUser.findMe();
			
			if(findRoleUser != null){
				throw new Exception("$AlreadyExistingRole");
			}
			
			roleUser.createDatabaseMe();
			roleUser.flushDatabaseMe();
			
			return new Object[]{new ToEvent(this, EventContext.EVENT_CHANGE), new Remover(user)};
			
		}
		
		return null;
	}
	
	public Object[] removeFollower(DeptTreeNode dept) throws Exception {
		return null;
	}
	public Object[] removeFollower(User user) throws Exception {
		if("topicFollowers".equals(user.getMetaworksContext().getWhen())){
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(user.session.getLastSelectedItem());
			tm.setUserId(user.getUserId());
			
			ITopicMapping rs = tm.findByUser();
			if( rs.next() ){
				tm.setTopicMappingId(rs.getTopicMappingId());
				tm.remove();
				
				
				
				return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
			}else{
				throw new Exception("삭제 실패");
			}
			
		}else if("deptFollowers".equals(user.getMetaworksContext().getWhen())){
			
			Employee employee = new Employee();
			employee.setEmpCode(user.session.getEmployee().getEmpCode());
			employee.copyFrom(employee.databaseMe());
						
			employee.setPartCode("");
			employee.syncToDatabaseMe();
			employee.flushDatabaseMe();
			
			return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
			
		}else if("roleFollowers".equals(user.getMetaworksContext().getWhen())){
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleCode(user.session.getLastSelectedItem());
			roleUser.setEmpCode(user.session.getEmployee().getEmpCode());

			roleUser.removeUser();
			
			return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
			
		}else{
			String instId = session.getLastInstanceId();
			
			InstanceFollowers followers = new InstanceFollowers();
				
			//TODO delete rolemapping
	
			org.uengine.codi.mw3.model.RoleMapping roleMapping = new org.uengine.codi.mw3.model.RoleMapping(new Long(instId), RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX + user.getUserId(), user.getUserId());
			if(roleMapping.deleteByInfo(session)) {
				followers.setInstanceId(instId);
				followers.load();
	
				System.out.println("delete follower done.");
				return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
				
			} else {
				throw new Exception("Author can not be removed from follower list.");
			}
		}
	}
	
	
	@ServiceMethod(callByContent=true, target="popup", mouseBinding="drop")
	public Object[] drop() throws Exception{
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IUser){
			User newFollowUser = (User)clipboard;
			
			followers.beforeFirst();
			while(followers.next()){
				if( newFollowUser.getUserId().equals( followers.getUserId()) ) {
					return null; //ignores same follower
				}
			}
			
			return this.addFollower(newFollowUser);
			
			/*if("topic".equals(this.getInstanceId())){
				TopicFollowers addFollower = new TopicFollowers();
				addFollower.setInstanceId(getInstanceId());
				newFollowUser.topicFollowers = addFollower;
				newFollowUser.getMetaworksContext().setWhen(ADD_TOPICFOLLOWERS);
			}else if("document".equals(this.getInstanceId())){
				DocumentFollowers documentFollower = new DocumentFollowers();
				documentFollower.setInstanceId(getInstanceId());
				newFollowUser.documentFollowers = documentFollower;
//				newFollowUser.setName(session.getUser().getName());
				newFollowUser.getMetaworksContext().setWhen(ADD_DOCUMENTFOLLOWERS);
			}else if("role".equals(this.getInstanceId())){
				RoleFollowers roleFollower = new RoleFollowers();
				roleFollower.setInstanceId(getInstanceId());
				newFollowUser.roleFollowers = roleFollower;
				newFollowUser.getMetaworksContext().setWhen(ADD_ROLEFOLLOWERS);
			}else if("dept".equals(this.getInstanceId())){
				DeptFollowers deptFollower = new DeptFollowers();
				deptFollower.setInstanceId(getInstanceId());
				newFollowUser.deptFollowers = deptFollower;
				newFollowUser.getMetaworksContext().setWhen(ADD_DEPTFOLLOWERS);
			}
			else{	
				InstanceFollowers addFollower = new InstanceFollowers();
				addFollower.setInstanceId(getInstanceId());
				newFollowUser.instanceFollowers = addFollower;
				newFollowUser.getMetaworksContext().setWhen(ADD_INSTANCEFOLLOWERS);
			}
			newFollowUser.processManager = processManager;
			newFollowUser.session = session;
			return newFollowUser.addFollower();*/
		}
		
		session.setClipboard(null);
		
		return new Object[]{ session };
	}
	
	@Hidden
	@ServiceMethod(eventBinding=EventContext.EVENT_CHANGE, bindingHidden=true)
	public void refresh() throws Exception {
		this.load();
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@AutowiredFromClient(onDrop=true)
	public IUser user;
}
