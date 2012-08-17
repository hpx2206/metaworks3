package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

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
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target="popup", mouseBinding="drop", keyBinding="Ctrl+V")
	public Object[] drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IUser){
			User newFollowUser = (User)clipboard;
			if(newFollowUser.getUserId().equals(session.getUser().getUserId())){
				return null; //ignores drag n drop same object 
			}
			followers.beforeFirst();
			while(followers.next()){
				if( newFollowUser.getUserId().equals( followers.getUserId()) ) {
					return null; //ignores same follower
				}
			}
			
			Followers addFollower = new Followers();
			addFollower.setInstanceId(getInstanceId());
			newFollowUser.followers = addFollower;
			newFollowUser.processManager = processManager;
			newFollowUser.session = session;
			return newFollowUser.addFollower();
		}
		return null;
	}
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
}
