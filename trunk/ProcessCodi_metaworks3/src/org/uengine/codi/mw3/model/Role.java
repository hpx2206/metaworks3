package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;

public class Role extends Database<IRole> implements IRole {
	@AutowiredFromClient
	public Session session;
	
	String roleCode;
		@Override
		public String getRoleCode() {
			return roleCode;
		}
		@Override
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

	String comCode;
		@Override
		public String getComCode() {
			return comCode;
		}
		@Override
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}

	String descr;
		public String getDescr() {
			return descr;
		}
		public void setDescr(String descr) {
			this.descr = descr;
		}	

	String isDeleted;
		@Override
		public String getIsDeleted() {
			return isDeleted;
		}
		@Override
		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}
		
	boolean selected;	
		@Override
		public boolean isSelected() {
			return selected;
		}
		@Override
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
	EmployeeList employeeList;
		@Override
		public EmployeeList getEmployeeList() {
			return employeeList;
		}
		@Override
		public void setEmployeeList(EmployeeList employeeList) {
			this.employeeList = employeeList;
		}
		
	@Override
	public void drillDown() throws Exception {
		this.setSelected(!this.isSelected());
		
		EmployeeList employeeList = new EmployeeList();
		employeeList.setId("role_" + this.getRoleCode());
		employeeList.setMetaworksContext(this.getMetaworksContext());
		
		if (this.isSelected()) {
			IEmployee employee = new Employee();
			employee.setMetaworksContext(this.getMetaworksContext());
			employee.getMetaworksContext().setHow("tree");
			
			employeeList.setEmployee(employee.findByRole(this));
			
			setEmployeeList(employeeList);
		} else {
			setEmployeeList(employeeList);
		}
	}
		
	@Override
	public IRole findByGlobalCom() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM roleTable");
		sb.append(" WHERE comCode=?comCode");
		sb.append("   AND isDeleted=?isDeleted");
		
		IRole role = sql(sb.toString());
		role.setComCode(this.getComCode());
		role.setIsDeleted("0");
		role.select();
		role.setMetaworksContext(this.getMetaworksContext());
		
		return role;
	}
	
	@Override
	public Object subscribe() throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM roleUserTable");
		sb.append(" WHERE roleCode=?roleCode");		
		sb.append("   AND empCode=?empCode");
		
		IRoleUser roleUser = (IRoleUser)sql(IRoleUser.class, sb.toString());
		roleUser.setRoleCode(this.getRoleCode());
		roleUser.setEmpCode(session.getEmployee().getEmpCode());
		roleUser.select();
		
		if(roleUser.size() == 0){
			RoleUser insertRoleUser = new RoleUser();
			insertRoleUser.setRoleCode(this.getRoleCode());
			insertRoleUser.setEmpCode(session.getEmployee().getEmpCode());
			insertRoleUser.createDatabaseMe();
			insertRoleUser.flushDatabaseMe();
			
			Employee employee = new Employee();
			employee.setEmpCode(session.getEmployee().getEmpCode());
			employee.copyFrom(employee.databaseMe());
			employee.setMetaworksContext(this.getMetaworksContext());
			employee.getMetaworksContext().setHow("tree");
			
			EmployeeList employeeList = new EmployeeList();
			employeeList.setId("role_" + this.getRoleCode());
			
			return new ToAppend(employeeList, employee);
		}else{
			return null;
		}
	}
	
	@Override
	public Object[] saveMe() throws Exception {
		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			// 생성
			this.setComCode(session.getCompany().getComCode());
			this.setIsDeleted("0");
			
			this.getMetaworksContext().setWhere("navigator");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);				
			
			createDatabaseMe();
			syncToDatabaseMe();
			
			RoleList roleList = new RoleList();
			roleList.setId("/ROOT/");
			
			return new Object[]{new Remover(new Popup()), new ToAppend(roleList, this)};
		} else {
			syncToDatabaseMe();
			flushDatabaseMe();
			
			this.getMetaworksContext().setWhere("navigator");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);				
			
			return new Object[]{new Remover(new Popup()), new Refresh(this)};
		}
	}

	@Override
	public Object[] removeMe() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getRoleCode());		
		role.copyFrom(role.databaseMe());
		role.setIsDeleted("1");
		
		role.syncToDatabaseMe();
		role.flushDatabaseMe();
		
		return new Object[]{new Remover(role)};				
	}
	
	@Override
	public Popup editPopup() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getRoleCode());
		role.copyFrom(role.databaseMe());
		
		role.getMetaworksContext().setWhere("admin");
		role.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		Popup popup = new Popup();
		popup.setPanel(role);
		
		return popup;		
	}
	
	public IRole findByEmployee(IEmployee employee) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.*");
		sb.append("  FROM roleTable a, roleUserTable b");
		sb.append(" WHERE a.rolecode = b.rolecode");
		sb.append("   AND a.isDeleted=?isDeleted");
		sb.append("   AND b.empCode=?empCode");
		
		IRole role = sql(sb.toString());
		role.set("empCode", employee.getEmpCode());
		role.setIsDeleted("0");
		role.select();
		role.setMetaworksContext(this.getMetaworksContext());
		
		return role;		
	}
}
