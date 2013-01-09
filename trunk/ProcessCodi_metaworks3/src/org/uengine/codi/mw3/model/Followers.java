package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class Followers implements ContextAware {
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
	
	public void put(IUser user) throws Exception {
		if(followers == null)
			followers = (IUser) MetaworksDAO.createDAOImpl(IUser.class);
		
		followers.moveToInsertRow();
		followers.getImplementationObject().copyFrom(user);		
	}
	
	//, loader="auto", loadOnce=true
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup addFollowers() throws Exception{		
		Popup popup = new Popup(400,400);
		
		String type = "addInstanceFollower";
		if("topic".equals(this.getInstanceId())){
			type = "addTopicFollower";
		}else if("etc".equals(this.getInstanceId())){
			type = "addEtcFollower";
		}
		AddFollowerPanel panel = new AddFollowerPanel(session, getInstanceId(), type);

		popup.setPanel(panel);
		popup.setName("AddFollowerPanel");
		
		return popup;		
	}
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target="popup", mouseBinding="drop")
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
			
			if("topic".equals(this.getInstanceId())){
				TopicFollowers addFollower = new TopicFollowers();
				addFollower.setInstanceId(getInstanceId());
				newFollowUser.topicFollowers = addFollower;
				newFollowUser.getMetaworksContext().setWhen("addTopicFollower");
			}else{
				InstanceFollowers addFollower = new InstanceFollowers();
				addFollower.setInstanceId(getInstanceId());
				newFollowUser.instanceFollowers = addFollower;
				newFollowUser.getMetaworksContext().setWhen("addInstanceFollower");
			}
			newFollowUser.processManager = processManager;
			newFollowUser.session = session;
			return newFollowUser.addFollower();
		}
		session.setClipboard(null);
		return null;
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@AutowiredFromClient(onDrop=true)
	public IUser user;
}
