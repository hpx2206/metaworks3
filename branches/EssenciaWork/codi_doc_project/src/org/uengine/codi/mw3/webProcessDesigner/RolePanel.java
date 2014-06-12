package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.uengine.kernel.Role;

public class RolePanel {

	ArrayList<Role> roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	public RolePanel(){
		roleList = new ArrayList<Role>();
	}
}
