package org.uengine.codi.mw3.ide.libraries;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.CollapsePerspective;
import org.uengine.codi.mw3.model.IRole;
import org.uengine.codi.mw3.model.Role;
import org.uengine.codi.mw3.model.Session;

public class RolePerspective extends CollapsePerspective {

	public RolePerspective() {
		setLabel("Role");
	}
	
	ArrayList<OrganizationRole> organizationRoleList;
		public ArrayList<OrganizationRole> getOrganizationRoleList() {
			return organizationRoleList;
		}
	
		public void setOrganizationRoleList(
				ArrayList<OrganizationRole> organizationRoleList) {
			this.organizationRoleList = organizationRoleList;
		}

	@Override
	protected void loadChildren() throws Exception {
		organizationRoleList = new ArrayList<OrganizationRole>();
		Role role = new Role();
		role.getMetaworksContext().setWhere("navigatorIDE");
		role.setComCode(session.getEmployee().getGlobalCom());
		IRole iRole = role.findByGlobalCom();
		while(iRole.next()){
			OrganizationRole organizationRole = new OrganizationRole();
			organizationRole.copyFrom(iRole);
			organizationRoleList.add(organizationRole);
		}
		
	}

	@Override
	protected void unloadChildren() throws Exception {
		organizationRoleList = null;
	}
	
	@AutowiredFromClient
	public Session session;
	
}
