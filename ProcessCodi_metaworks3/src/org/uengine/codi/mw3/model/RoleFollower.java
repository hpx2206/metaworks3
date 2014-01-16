package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.knowledge.TopicMapping;

public class RoleFollower extends Follower {

	public RoleFollower(){
		this.setParentType(TYPE_TOPIC);
	}

	@Override
	public void put() throws Exception {
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getParentId());
		roleUser.setEmpCode(this.getUser().getUserId());
		
		roleUser.createDatabaseMe();
		roleUser.flushDatabaseMe();
	}

	@Override
	public void delegate() throws Exception {
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getParentId());
		roleUser.setEmpCode(this.getUser().getUserId());
		
		roleUser.removeUser();
	}
	
	@Override
	public IFollower findFollowers() throws Exception {
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getParentId());

		IFollower follower = roleUser.findFollowers();
		follower.getMetaworksContext().setWhere(WHERE_FOLLOWER);
		
		return follower;
	}
	
	@Override
	public IContact findContacts(String keyword) throws Exception {
		return Contact.findContactsForRole(this.getParentId(), session.getUser(), keyword);
	}

}
