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
		
	public IRoleUser findUserByRoleCode() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from roleusertable");
		sb.append(" WHERE roleCode=?roleCode ");
		
		IRoleUser dao = (IRoleUser) sql(IRoleUser.class, sb.toString());
		
       	dao.setRoleCode(this.getRoleCode());
       	
       	dao.select();
       	
       	return dao;
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
	
	public IUser findByRole() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select emptable.empcode as userId, emptable.empname as name ");
		sb.append(" from emptable left outer join roleusertable ");
		sb.append("   on emptable.empcode=roleusertable.EMPCODE ");
		sb.append(" where rolecode=?rolecode");
		
		IUser dao = (IUser) sql(IUser.class, sb.toString());
		
       	dao.set("roleCode", this.getRoleCode());
       	dao.select();
       	return dao;
	}
	
	public IRoleUser findMe() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from roleUserTable ");
		sb.append("  where roleCode=?roleCode ");	
		sb.append("   AND empCode=?empCode ");
		
		IRoleUser dao = null;
		
		try {
			dao = sql(sb.toString());
			dao.setRoleCode(this.getRoleCode());
			dao.setEmpCode(this.getEmpCode());
			dao.select();
			
			if(!dao.next())
				dao = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao;
	}
}
