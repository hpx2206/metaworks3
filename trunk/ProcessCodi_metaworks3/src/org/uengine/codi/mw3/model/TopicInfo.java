package org.uengine.codi.mw3.model;


public class TopicInfo extends GroupInfo{

	public final static String HTML = "html";

	public TopicInfo(){
		
	}
	
	public TopicInfo(Session session, String topicId) throws Exception {
		super(session, topicId);
	}
	
}
