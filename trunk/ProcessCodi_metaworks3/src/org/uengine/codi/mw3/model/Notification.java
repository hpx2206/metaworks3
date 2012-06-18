package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.AbstractGenericDAO;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.springframework.beans.factory.annotation.Autowired;

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

	INotification list(Session session) throws Exception{
		INotification noti =  sql("select * from bpm_noti where userId = ?userId order by inputdate desc ");
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
		
		instanceViewContent.load(instance);
		
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
