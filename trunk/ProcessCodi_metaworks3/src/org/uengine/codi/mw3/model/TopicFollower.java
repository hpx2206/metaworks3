package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.knowledge.TopicMapping;

public class TopicFollower extends Follower {

	public TopicFollower(){
		this.setParentType(TYPE_TOPIC);
	}

	@Override
	public void put() throws Exception {
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(this.getParentId());
		tm.setUserId(user.getUserId());
		tm.setUserName(user.getName());
		
		// not exist save topicmapping
		if( !tm.findByUser().next() ){
			tm.saveMe();
			this.addPushListener();
		}
	}

	@Override
	public void delegate() throws Exception {
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(this.getParentId());
		tm.setUserId(user.getUserId());
		tm.setUserName(user.getName());
		
		tm.removeMe();
		this.push();
	}
	
	@Override
	public IFollower findFollowers() throws Exception {
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(this.getParentId());

		IFollower follower = tm.findFollowers();
		follower.getMetaworksContext().setWhere(WHERE_FOLLOWER);
		
		return follower;
	}
	
	@Override
	public IContact findContacts(String keyword) throws Exception {
		return Contact.findContactsForTopic(this.getParentId(), session.getUser(), keyword);
	}

}
