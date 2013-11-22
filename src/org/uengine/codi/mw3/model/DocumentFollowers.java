package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.knowledge.TopicMapping;

public class DocumentFollowers extends Followers{
	static final String CONTEXT_WHERE_DOCUMENTFOLLOWERS = "documentFollowers";
	
	public DocumentFollowers(){
		setInstanceId("document");
	}
	
	@Override
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(session.getLastSelectedItem());
		
		
		IUser users = tm.findUser();
		users.getMetaworksContext().setWhen(CONTEXT_WHERE_DOCUMENTFOLLOWERS);
		setFollowers(users);
		
		IDept dept = tm.findDept();
		
		dept.getMetaworksContext().setHow(CONTEXT_WHERE_DEPTFOLLOWERS);
		setDeptFollowers(dept);
		
	}
	
}
