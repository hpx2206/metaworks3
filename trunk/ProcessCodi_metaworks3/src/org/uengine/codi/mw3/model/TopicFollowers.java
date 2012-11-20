package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.knowledge.TopicMapping;

public class TopicFollowers extends Followers{
	
	static final String CONTEXT_WHERE_TOPICFOLLOWERS = "topicFollowers";
	
	public TopicFollowers(){
		setInstanceId("topic");
	}
	
	@Override
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(session.getLastSelectedItem());
		
		IUser users = tm.findUser();
		
		users.getMetaworksContext().setWhen(CONTEXT_WHERE_TOPICFOLLOWERS);
		setFollowers(users);
	}
}
