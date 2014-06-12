package org.uengine.codi.mw3.model;

import org.metaworks.dao.Database;

public class RoleUser extends Database<IRoleUser> implements IRoleUser {
	String roleCode;
		@Override
		public String getRoleCode() {
			return roleCode;
		}
		@Override
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

	String empCode;
		@Override
		public String getEmpCode() {
			return empCode;
		}
		@Override
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		
	public int removeMe() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from roleusertable");
		sb.append(" WHERE roleCode=?roleCode");
		sb.append("   AND empCode=?empCode");
		
		IRoleUser dao = (IRoleUser) sql(IRoleUser.class, sb.toString());
		
       	dao.set("roleCode", this.getRoleCode());
       	dao.set("empCode", this.getEmpCode());
        return dao.update();
	}
}
