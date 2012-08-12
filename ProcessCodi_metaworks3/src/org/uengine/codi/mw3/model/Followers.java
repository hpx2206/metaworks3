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
	static final String CONTEXT_WHERE_INFOLLOWERS = "followers";

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
		IUser users = (IUser) Database.sql(IUser.class, "select distinct a.endpoint userId, a.resname name, b.network from bpm_rolemapping a left join contact b on a.endpoint=b.friendId  where rootinstid=?instanceId");
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
		
		users.getMetaworksContext().setWhen(CONTEXT_WHERE_INFOLLOWERS);
		setFollowers(users);
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP, loader="auto", loadOnce=true)
	public Popup addFollowers() throws Exception{		
		Popup popup = new Popup(400,400);
		
		AddFollowerPanel panel = new AddFollowerPanel(session.user, getInstanceId());

		popup.setPanel(panel);
		popup.setName("AddFollowerPanel");
		
		return popup;		
	}
	
	@AutowiredFromClient
	public Session session;
	
}
