package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;

public class RoleFollowers extends Followers{
	
static final String CONTEXT_WHERE_ROLEFOLLOWERS = "roleFollowers";
	
	public RoleFollowers(){
		setInstanceId("role");
	}
	
	@Override
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);

		String roleCode = session.getLastSelectedItem();
		
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(roleCode);
		
		IUser users = roleUser.findByRole();
		
		
		users.getMetaworksContext().setWhen(CONTEXT_WHERE_ROLEFOLLOWERS);
		setFollowers(users);
		
		
		
	}
}
