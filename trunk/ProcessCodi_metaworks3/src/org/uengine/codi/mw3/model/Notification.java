package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequestWrapper;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.util.CodiStringUtil;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.Role;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;

public class Notification extends Database<INotification> implements INotification{

	Long notiId;
		public Long getNotiId() {
			return notiId;
		}
		public void setNotiId(Long notiId) {
			this.notiId = notiId;
		}
		
	Date inputDate;
		public Date getInputDate() {
			return inputDate;
		}
		public void setInputDate(Date inputDate) {
			this.inputDate = inputDate;
		}

	String userId;
		public String getUserId() {
			return userId;
		}
	
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String actorId;
		public String getActorId() {
			return actorId;
		}
		public void setActorId(String actorId) {
			this.actorId = actorId;
		}
		
	IUser actor;
		public IUser getActor() {
			return actor;
		}
		public void setActor(IUser actor) {
			this.actor = actor;
		}

	Long instId;
		public Long getInstId() {
			return instId;
		}
		public void setInstId(Long instId) {
			this.instId = instId;
		}
		
	Long taskId;
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	String actAbstract;
		public String getActAbstract() {
			return actAbstract;
		}
		public void setActAbstract(String actAbstract) {
			
			if(actAbstract.length() > 97){
				actAbstract = actAbstract.substring(0, 97) + "...";
			}
			
			this.actAbstract = actAbstract;
		}
		
	boolean confirm;
		public boolean isConfirm() {
			return confirm;
		}
		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}

	public String url;
	
	public void add(final IInstance instance) throws Exception{
		createDatabaseMe();
		flushDatabaseMe();
		
		final Employee userInfo = new Employee();
		userInfo.setEmpCode(getUserId());

		IEmployee userInfoTemp;
		try{
			userInfoTemp = userInfo.databaseMe();
		}catch(Exception e){
			userInfoTemp = new Employee();
			userInfoTemp.setFacebookId(userInfo.getEmpCode());
		}
		

		/**
		 * TODO: e-mail nofitication
		 * 
		 * 성능 이슈가 야기됨.
		 * follower마다 Emptable에서 데이터를 읽어 오기 때문에 ...(actorUserInfo.databaseMe()) 차후 수정
		 * 
		 */
		if(userInfo.databaseMe().isMailNoti()){
			final Employee userInfoDB = new Employee();
			userInfoDB.copyFrom(userInfoTemp);
			
			final String realPath = CodiStringUtil.lastLastFileSeparatorChar(new HttpServletRequestWrapper(TransactionContext.getThreadLocalInstance().getRequest()).getRealPath(""));
			final String url = TenantContext.getURL();
			
			/*
			final Employee actorUserInfo = new Employee();
			actorUserInfo.setEmpCode(getActorId());
			
			final IEmployee actorUserInfoDB = actorUserInfo.databaseMe();
			*/
			
			try{
		       	
		       	
			}catch(Exception e){
				e.printStackTrace();
			}
			
			new Thread(){

				@Override
				public void run() {
					try {
						// facebook
						/*
						if(userInfoDB.getEmpCode()==null && userInfoDB.getFacebookId()!=null){
							//TODO: this case should be chosen by more deterministic option (e.g. User.network=='facebook') 
							FacebookClient facebookClient = new DefaultFacebookClient();
							String facebookId = getUserId();
							String query = "SELECT uid, name, email, locale FROM user WHERE uid=" + facebookId;
							
							List<com.restfb.types.User> users = facebookClient.executeQuery(query, com.restfb.types.User.class);
							if (users.size() == 0) {
								throw new UEngineException("There's no such user [" + facebookId + "]");
							} else {
								com.restfb.types.User user = users.get(0);
								userInfoDB.setEmail(user.getEmail());
							}
						}
						*/
						
						String content = "";
						
						String path = realPath + "/resources/mail/notiMail.html";
						FileInputStream is;
						try {
							is = new FileInputStream(path);
							InputStreamReader isr = new InputStreamReader(is,"UTF-8");
							BufferedReader br = new BufferedReader(isr);
							
							int data;
							while((data = br.read()) != -1){
								content += (char)data;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						content = this.replaceString(content,"user.name", userInfoDB.getEmpName());
						content = this.replaceString(content, "notimail.date", getInputDate().toString());
						content = this.replaceString(content, "notimail.image", url + "/portrait/" + userInfoDB.getEmpCode() + ".thumnail");
						
						content = this.replaceString(content, "notimail.content", getActAbstract());
						
						String from = "help@opencloudengine.org";
						String title = "프로세스 코디의 알림이 도착했습니다.";
						
						// TODO 상대방 이메일을 userInfoDB에서 가져오질 못함
						(new EMailServerSoapBindingImpl()).sendMail(from, userInfoDB.getEmail(), title, content);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				public String replaceString(String lineString, String from, String to){
					String returnValue = "";
					returnValue = lineString.replaceAll(from, to);
					return returnValue;
				}
			}.start();
		}
	}
	
	public HashMap<String, String> findInstanceNotiUser(String instanceId) throws Exception{
		HashMap<String, String> notiUsers = new HashMap<String, String>();
		
		InstanceFollower findFollower = new InstanceFollower(instanceId);
		findFollower.session = session;
		findFollower.setEnablePush(false);
		
		IFollower follower = findFollower.findFollowers();
		while(follower.next()){
			int assigntype = follower.getAssigntype();
			if( assigntype == Role.ASSIGNTYPE_USER ){
				String followerUserId = follower.getEndpoint();
				if( session.getUser().getUserId().equals(followerUserId)){	// 자기 자신은 제외
					continue;
				}
				notiUsers.put(followerUserId, Login.getSessionIdWithUserId(followerUserId));
				
			}else if( assigntype == Role.ASSIGNTYPE_DEPT ){
				String partcode = follower.getEndpoint();
				Dept dept = new Dept();
				dept.setGlobalCom(session.getCompany().getComCode());
				dept.setPartCode(partcode);
				
				IEmployee employee = new Employee();
				employee.setMetaworksContext(this.getMetaworksContext());
				employee = employee.findByDept(dept);
				if( employee != null ){
					while(employee.next()){
						String followerUserId = employee.getEmpCode();
						if( session.getUser().getUserId().equals(followerUserId)){	// 자기 자신은 제외
							continue;
						}
						notiUsers.put(followerUserId, Login.getSessionIdWithUserId(followerUserId));
					}
				}
			}
		}
		
		return notiUsers;
	}
	
	public HashMap<String, String> findTopicNotiUser(String topicId) throws Exception{
		HashMap<String, String> notiUsers = new HashMap<String, String>();
		
		TopicFollower topicFollower = new TopicFollower();
		topicFollower.setParentId(topicId);
		
		IFollower topicFollowers = topicFollower.findFollowers();
		while(topicFollowers.next()){
			int assigntype = topicFollowers.getAssigntype();
			if( assigntype == Role.ASSIGNTYPE_USER ){
				String followerUserId = topicFollowers.getEndpoint();
				if( session.getUser().getUserId().equals(followerUserId)){	// 자기 자신은 제외
					continue;
				}
				notiUsers.put(followerUserId, Login.getSessionIdWithUserId(followerUserId));
				
			}else if( assigntype == Role.ASSIGNTYPE_DEPT ){
				String partcode = topicFollowers.getEndpoint();
				Dept dept = new Dept();
				dept.setGlobalCom(session.getCompany().getComCode());
				dept.setPartCode(partcode);
				
				IEmployee employee = new Employee();
				employee.setMetaworksContext(this.getMetaworksContext());
				employee = employee.findByDept(dept);
				if( employee != null ){
					while(employee.next()){
						String followerUserId = employee.getEmpCode();
						if( session.getUser().getUserId().equals(followerUserId)){	// 자기 자신은 제외
							continue;
						}
						notiUsers.put(followerUserId, Login.getSessionIdWithUserId(followerUserId));
					}
				}
			}
		}
		return notiUsers;
	}
		
	INotification list(Session session) throws Exception{
		INotification noti =  sql("select * from bpm_noti where userId = ?userId order by inputdate desc limit 30");
		noti.setUserId(session.user.getUserId());
		noti.select();
		
		return noti;
	}
	
	int count(Session session) throws Exception{
		INotification noti =  sql("select count(1) CNT from bpm_noti where userId = ?userId and confirm=0");
		noti.setUserId(session.user.getUserId());
		noti.select();
		noti.next();
		return (Integer) noti.getInt("CNT");
	}
	
	public Object[] see() throws Exception{
		
		// 확인 표시
		this.setConfirm(true);
		
		Instance instance = new Instance();
		instance.setInstId(getInstId());
		instance.session = session;

		return new Object[]{instance.detail(), this};
		
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
}
