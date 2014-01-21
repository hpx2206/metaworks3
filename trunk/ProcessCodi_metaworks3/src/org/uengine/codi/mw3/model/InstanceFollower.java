package org.uengine.codi.mw3.model;

import org.uengine.kernel.Role;


public class InstanceFollower extends Follower {

	public InstanceFollower(){
		this.setParentType(TYPE_INSTANCE);
	}
	
	public InstanceFollower(String instanceId){
		this();
		
		this.setParentId(instanceId);
	}
	
	public RoleMapping makeRoleMapping() throws Exception {
		Long instId = Long.parseLong(this.getParentId());
		
		RoleMapping rm = new RoleMapping();
		rm.setRootInstId(instId);
		rm.setInstId(instId);
		
		if(Role.ASSIGNTYPE_USER == this.getAssigntype()){
			rm.setEndpoint(user.getUserId());
			rm.setResName(user.getName());
		}
		
		rm.setAssignType(this.getAssigntype());
		rm.setRoleName(RoleMapping.ROLEMAPPING_FOLLOWER_ROLENAME);
		
		return rm;
	}
	
	@Override
	public IFollower find() throws Exception {
		
		RoleMapping rm = this.makeRoleMapping();
		IFollower follower = rm.findFollower();
		
		// not exist save rolemapping
		if( follower.next() ){
			return follower;
		}else{
			return null;
		}
	}
	
	@Override
	public void put() throws Exception {
		// not exist save rolemapping
		Object findObj = find();
		if( findObj == null ){
			RoleMapping rm = this.makeRoleMapping();
			rm.saveMe();
			
			this.addPushListener();
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
		this.push();

	}
	
	@Override
	public IFollower findFollowers() throws Exception {
		RoleMapping roleMapping = new RoleMapping();
		roleMapping.setRootInstId(Long.parseLong(this.getParentId()));

		IFollower follower = roleMapping.findFollowers();
		follower.getMetaworksContext().setWhere(WHERE_FOLLOWER);
		
		System.out.println("follower : " + follower.size());
		return follower;
	}
	
	@Override
	public IContact findContacts(String keyword) throws Exception {
		return Contact.findContactsForInstance(this.getParentId(), session.getUser(), keyword);
	}
}
