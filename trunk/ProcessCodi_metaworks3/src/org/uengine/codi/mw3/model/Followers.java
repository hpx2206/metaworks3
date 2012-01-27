package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;

public class Followers {

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
		
	public void load() throws Exception{
		IUser users = (IUser) Database.sql(IUser.class, "select endpoint userId, resname name from bpm_rolemapping where rootinstid=?instanceId");
		users.set("instanceId", instanceId);
		users.select();
		
		IUser users_ = (IUser) MetaworksDAO.createDAOImpl(IUser.class);

		
		//TODO due to mysql - jdbc alias problem
		while(users.next()){
			users_.moveToInsertRow();
			users_.setName((String) users.get("resname"));
			users_.setUserId((String) users.get("endpoint"));

		}
		
		users_.beforeFirst();
		
		setFollowers(users_);
		
	}
	
	int pageX;
		@Hidden
		public int getPageX() {
			return pageX;
		}	
		public void setPageX(int pageX) {
			this.pageX = pageX;
		}
	
	int pageY;
		@Hidden
		public int getPageY() {
			return pageY;
		}	
		public void setPageY(int pageY) {
			this.pageY = pageY;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup addFollowers() throws Exception{
		System.out.println("this.pageX : " + this.pageX);
		System.out.println("this.pageY : " + this.pageY);
		
		Popup popup = new Popup();
		
		popup.setPageX(this.pageX);
		popup.setPageY(this.pageY);
		
		popup.setPanel(new AddFollowerPanel(session.login, getInstanceId()));
		popup.setName("AddFollowerPanel");
		
		return popup;		
	}
	
	@AutowiredFromClient
	public Session session;
	
}
