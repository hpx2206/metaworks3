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
		
		/*
		IUser users_ = (IUser) MetaworksDAO.createDAOImpl(IUser.class);

		
		//TODO due to mysql - jdbc alias problem
		while(users.next()){
			users_.moveToInsertRow();
			users_.setName((String) users.get("resname"));
			users_.setUserId((String) users.get("endpoint"));

		}
		
		users_.beforeFirst();
		setFollowers(users_);
		*/
		
		setFollowers(users);
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup addFollowers() throws Exception{		
		Popup popup = new Popup();
		
		AddFollowerPanel panel = new AddFollowerPanel(session.user, getInstanceId());
		panel.getMetaworksContext().setWhen("addFollower");

		popup.setPanel(panel);
		

		popup.setName("AddFollowerPanel");
		
		return popup;		
	}
	
	@AutowiredFromClient
	public Session session;
	
}
