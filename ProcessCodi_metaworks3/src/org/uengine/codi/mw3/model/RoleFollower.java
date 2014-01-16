package org.uengine.codi.mw3.model;


public class RoleFollower extends Follower {

	public RoleFollower(){
		this.setParentType(TYPE_TOPIC);
	}

	public RoleUser makeRoleUser(){
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getParentId());
		roleUser.setEmpCode(this.getUser().getUserId());
		
		return roleUser;
	}
	
	@Override
	public IFollower find() throws Exception {
		RoleUser roleUser = this.makeRoleUser();
		IFollower follower = roleUser.findFollower();
		if(follower.next())
			return follower;
		else
			return null;
	}
	
	@Override
	public void put() throws Exception {
		
		if(this.find() == null){
			RoleUser roleUser = this.makeRoleUser();
			roleUser.createDatabaseMe();
			roleUser.flushDatabaseMe();
			
			this.push();
		}
	}

	@Override
	public void delegate() throws Exception {
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getParentId());
		roleUser.setEmpCode(this.getUser().getUserId());
		
		roleUser.removeUser();
		
		this.push();
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
