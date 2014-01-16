package org.uengine.codi.mw3.model;

import org.uengine.kernel.Role;


public class InstanceFollower extends Follower {

	public InstanceFollower(){
		this.setParentType(TYPE_INSTANCE);
	}

	@Override
	public void put() throws Exception {
		Long instId = Long.parseLong(this.getParentId());
		
		// find root instnace id
		Instance instance = new Instance();
		instance.setInstId(Long.parseLong(this.getParentId()));
		Long rootInstId = instance.databaseMe().getRootInstId();
		
		RoleMapping rm = new RoleMapping();
		rm.setRootInstId(rootInstId);
		rm.setInstId(instId);
		
		if(Role.ASSIGNTYPE_USER == this.getAssigntype()){
			rm.setEndpoint(user.getUserId());
			rm.setResName(user.getName());
		}
		
		rm.setAssignType(this.getAssigntype());
		rm.setRoleName(RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME);
		
		// not exist save rolemapping
		if( !rm.findMe().next() ){
			rm.saveMe();
		}
	}

	@Override
	public void delegate() throws Exception {
		RoleMapping rm = new RoleMapping();
		rm.setInstId(Long.parseLong(this.getParentId()));
		rm.setEndpoint(this.getUser().getUserId());
		rm.setAssignType(this.getAssigntype());
		rm.setRoleName(RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME);
		
		rm.removeMe();

	}
	
	@Override
	public IFollower findFollowers() throws Exception {
		RoleMapping roleMapping = new RoleMapping();
		roleMapping.setRootInstId(Long.parseLong(this.getParentId()));

		IFollower follower = roleMapping.findFollowers();
		follower.getMetaworksContext().setWhere(WHERE_FOLLOWER);
		
		return follower;
	}
	
	@Override
	public IContact findContacts(String keyword) throws Exception {
		return Contact.findContactsForInstance(this.getParentId(), session.getUser(), keyword);
	}
}
