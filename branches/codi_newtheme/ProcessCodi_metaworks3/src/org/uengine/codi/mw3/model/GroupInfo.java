package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.codi.mw3.knowledge.WfNode;

public class GroupInfo extends PerspectiveInfo{
	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}

	
	@Override
	public void followersLoad() throws Exception {
		this.followers =  new TopicFollowers();
		this.followers.session = session;
		this.followers.load();
	}
	
	public void convertSecuopt() throws Exception{
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getId());
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(wfNode.getSecuopt())){
			wfNode.setSecuopt("1");
		}else{
			wfNode.setSecuopt("0");
		}
		wfNode.syncToDatabaseMe();
		
	}
	@Override
	public void settingJoined() throws Exception {
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(session.getLastSelectedItem());
		tm.setUserId(session.getEmployee().getEmpCode());
		ITopicMapping rs = tm.findByUser();
		if( rs.next() )
			this.setJoined(true);
		else
			this.setJoined(false);
			
	}
	@Override
	public Object[] unSubscribe() throws Exception {
		
		User user = new User();
		user.setUserId(session.getEmployee().getEmpCode());
		user.setName(session.getEmployee().getEmpName());
		user.session = this.session;
		user.setMetaworksContext(new MetaworksContext());
		user.getMetaworksContext().setWhen("topicFollowers");
		
		followers.removeFollower(user);

		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
	}
	@Override
	public Object[] subscribe() throws Exception {
		
		User user = new User();
		user.setUserId(session.getEmployee().getEmpCode());
		user.setName(session.getEmployee().getEmpName());
		user.session = this.session;
		user.setMetaworksContext(new MetaworksContext());
		user.getMetaworksContext().setHow("follower");
		user.getMetaworksContext().setWhen(Followers.ADD_TOPICFOLLOWERS);
		
		followers.addFollower(user);
		
		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CHANGE)};
	}
	
	
}
