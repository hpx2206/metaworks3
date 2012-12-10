package org.uengine.codi.mw3.model;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.UEngineException;
import org.uengine.util.UEngineUtil;
import org.uengine.webservices.emailserver.impl.EMailServerSoapBindingImpl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

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
		
		final IEmployee userInfoDB = userInfoTemp;
		
		final Employee actorUserInfo = new Employee();
		actorUserInfo.setEmpCode(getActorId());
		final IEmployee actorUserInfoDB = actorUserInfo.databaseMe();
		
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
        URL urlURL = new java.net.URL(base);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	String protocol = urlURL.getProtocol();

		final String url = protocol + "://" + host + (port == 80 ? "" : ":"+port) + TransactionContext.getThreadLocalInstance().getRequest().getContextPath();
//
//		new Thread(){
//
//			@Override
//			public void run() {
//				try {
//					
//					if(userInfoDB.getEmpCode()==null && userInfoDB.getFacebookId()!=null){
//						//TODO: this case should be chosen by more deterministic option (e.g. User.network=='facebook') 
//						FacebookClient facebookClient = new DefaultFacebookClient();
//						String facebookId = getUserId();
//						String query = "SELECT uid, name, email, locale FROM user WHERE uid=" + facebookId;
//						
//						List<com.restfb.types.User> users = facebookClient.executeQuery(query, com.restfb.types.User.class);
//						if (users.size() == 0) {
//							throw new UEngineException("There's no such user [" + facebookId + "]");
//						} else {
//							com.restfb.types.User user = users.get(0);
//							userInfoDB.setEmail(user.getEmail());
//						}
//					}
//					
//					(new EMailServerSoapBindingImpl()).sendMail(
//							actorUserInfoDB.getEmail(), 
//							actorUserInfoDB.getEmpName(),
//							userInfoDB.getEmail(), 
//							"[ProcessCodi] " + instance.getName(), 
//							getActAbstract() + "<p><a href='" + url + "'>Connect to Process Codi for details.</a>", 
//							null, 
//							null,
//							"UTF-8"
//					);
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			
//		}.start();

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
		
		Instance instance = new Instance();
		instance.setInstId(getInstId());
		
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		instanceViewContent.instanceView.getInstanceViewThreadPanel().getThread().getMetaworksContext().setHow("instance");
		instanceViewContent.instanceView.getInstanceViewThreadPanel().getNewItem().getMetaworksContext().setHow("instance");
		
		setConfirm(true);
		//flushDatabaseMe();
		
		NotificationBadge notiBadge = new NotificationBadge();
		notiBadge.session = session;
		notiBadge.refresh();
		
		return new Object[]{instanceViewContent, new Refresh(notiBadge), this};
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

}
