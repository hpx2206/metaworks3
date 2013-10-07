package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;


public class NotificationBadge{ 
	
	int newItemCount;
	
		public int getNewItemCount() {
			return newItemCount;
		}
	
		public void setNewItemCount(int newItemCount) {
			this.newItemCount = newItemCount;
		}
		
	public NotificationBadge(){
		
	}
	
	@ServiceMethod
	public void refresh() throws Exception{
		Notification notiList = new Notification();
		setNewItemCount(notiList.count(session));
	}

	@ServiceMethod(target="popup", loader="org.uengine.codi.mw3.model.Popup")
	public Object[] showList() throws Exception{
		Popup popup = new Popup();
		popup.setName("Notification");
		Notification notiList = new Notification();
		popup.setPanel(	notiList.list(session));
		setNewItemCount(notiList.count(session));
		
		
		//한방에 다 클리어!
		Notification notificationType = new Notification();
		INotification notifictionsInSameInstance  = notificationType.sql("update bpm_noti set confirm=1 where userId=?userId");
		notifictionsInSameInstance.setConfirm(true);
		//notifictionsInSameInstance.setInstId(inst.getInstId());
		notifictionsInSameInstance.setUserId(session.getUser().getUserId());
		notifictionsInSameInstance.update();

		setNewItemCount(0);
		
		return new Object[]{popup, new Refresh(this)};
	}
	
	@AutowiredFromClient
	public Session session;
	
}
