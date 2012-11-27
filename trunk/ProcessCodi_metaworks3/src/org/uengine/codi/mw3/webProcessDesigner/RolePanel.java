package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class RolePanel implements ContextAware {
	
	
	public RolePanel() throws Exception{
		roles = new ArrayList<Role>();
		
		newRole = new Role();
		newRole.setMetaworksContext(new MetaworksContext());
		newRole.getMetaworksContext().setWhen("edit");
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public ArrayList<Role> roles;
		public ArrayList<Role> getRoles() {
			return roles;
		}
		public void setRoles(ArrayList<Role> roles) {
			this.roles = roles;
		}

	Role newRole;
		public Role getNewRole() {
			return newRole;
		}
		public void setNewRole(Role newRole) {
			this.newRole = newRole;
		}
	
}
