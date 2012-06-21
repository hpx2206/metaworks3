package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;

public class AddLocalContactPanel {

	
	public AddLocalContactPanel() {
		
	}
	
	public AddLocalContactPanel(Session session) throws Exception {
		IDept dept = new Dept();
		dept.getMetaworksContext().setHow("tree");
		dept.setGlobalCom(session.getEmployee().getGlobalCom());
		
		DeptList deptList = new DeptList();
		deptList.setId("/ROOT/");		
		deptList.setDept(dept.findByGlobalCom());
		
		setDeptList(deptList);
		
		IEmployee employee = new Employee();
		employee.getMetaworksContext().setWhere("addContact");
		employee.getMetaworksContext().setHow("tree");
		employee.setGlobalCom(session.getEmployee().getGlobalCom());
		
		EmployeeList employeeList = new EmployeeList();			
		employeeList.setEmployee(employee.findByDeptOther());
		
		setDeptEmployee(employeeList);				
	}

	DeptList deptList;
		public DeptList getDeptList() {
			return deptList;
		}
		public void setDeptList(DeptList deptList) {
			this.deptList = deptList;
		}
		
	EmployeeList deptEmployee;
		public EmployeeList getDeptEmployee() {
			return deptEmployee;
		}
		public void setDeptEmployee(EmployeeList deptEmployee) {
			this.deptEmployee = deptEmployee;
		}			

	@AutowiredFromClient
	public Session session;

}
