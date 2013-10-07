package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class OrganizationPerspectiveDept extends Perspective {

	public OrganizationPerspectiveDept() {
		setLabel("Organization");
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
		
	@Override
	protected void loadChildren() throws Exception {
		System.out.println("loadChildren");
		
		IDept dept = new Dept();
		dept.getMetaworksContext().setHow("tree");
		dept.setGlobalCom(session.getEmployee().getGlobalCom());
		
		DeptList deptList = new DeptList();
		deptList.setId("/ROOT/");		
		deptList.setDept(dept.findByGlobalCom());
		
		setDeptList(deptList);
		
		
		IEmployee employee = new Employee();
		employee.getMetaworksContext().setWhere("navigator");
		employee.getMetaworksContext().setHow("tree");
		employee.setGlobalCom(session.getEmployee().getGlobalCom());
		
		EmployeeList employeeList = new EmployeeList();			
		employeeList.getMetaworksContext().setWhen("navigator");
		employeeList.setEmployee(employee.findByDeptOther());
		
		setDeptEmployee(employeeList);		
	}

	@Override
	protected void unloadChildren() throws Exception {		
		DeptList deptList = new DeptList();
		deptList.setId("/ROOT/");				
		setDeptList(deptList);
		
		EmployeeList employeeList = new EmployeeList();
		employeeList.setId("/ROOT/");
		setDeptEmployee(employeeList);				
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup addDept() {
		IDept dept = new Dept();
		dept.getMetaworksContext().setHow("info");
		dept.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		Popup popup = new Popup();
		popup.setPanel(dept);
		
		return popup;
	}
}
