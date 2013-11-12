package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.dao.MetaworksDAO;

public class DeptFollowers extends Followers{
	
	static final String CONTEXT_WHERE_DEPTFOLLOWERS = "deptFollowers";
	
	@Override
	public void load() throws Exception{
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		String partCode = session.getLastSelectedItem();
		String globalCom = session.getEmployee().getGlobalCom();
		
		Dept dept = new Dept();
		dept.setPartCode(partCode);
		dept.setGlobalCom(globalCom);
		IDept refDept = dept.findChildren();
		refDept.getMetaworksContext().setHow(CONTEXT_WHERE_DEPTFOLLOWERS);
		setDeptFollowers(refDept);
		
		
		IEmployee employee = new Employee();
		employee.getMetaworksContext().setWhere("navigator");
		employee.getMetaworksContext().setHow("tree");
		employee.setGlobalCom(session.getEmployee().getGlobalCom());
		IEmployee refEmployee = employee.findByDept(dept);
		
		IUser users =  (IUser)MetaworksDAO.createDAOImpl(IUser.class);
		while(refEmployee.next()){
			User user = new User();
			user.setUserId(refEmployee.getEmpCode());
			user.setName(refEmployee.getEmpName());
			users.moveToInsertRow();
			users.getImplementationObject().copyFrom(user);
		}
		setFollowers(users);
		
	}

}
