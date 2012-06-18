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

	@ServiceMethod(target="popup")
	public Object[] showList() throws Exception{
		Popup popup = new Popup();
		popup.setName("Notification");
		Notification notiList = new Notification();
		popup.setPanel(	notiList.list(session));
		setNewItemCount(notiList.count(session));
		
		return new Object[]{popup, new Refresh(this)};
	}
	
	@AutowiredFromClient
	public Session session;
	
}
