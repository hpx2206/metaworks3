package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.admin.AdminEastPanel;

public class Dept extends Database<IDept> implements IDept {
	String partCode;
	String partName;
	String parentPartCode;
	String isDeleted;
	String description;
//	String comCode;

	public Dept(){
		setIsDeleted("0");
		
		setChildren(new DeptList());
		setDeptEmployee(new EmployeeList());
	}
	
	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getParent_PartCode() {
		return parentPartCode;
	}

	public void setParent_PartCode(String parentPartCode) {
		this.parentPartCode = parentPartCode;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@Override
//	public String getComCode() {
//		// TODO Auto-generated method stub
//		return this.comCode;
//	}
//
//	@Override
//	public void setComCode(String comCode) {
//		this.comCode = comCode;
//	}
	
	String globalCom;
	public String getGlobalCom() {
		return globalCom;
	}

	public void setGlobalCom(String globalCom){
		this.globalCom = globalCom;
	}

	boolean selected;

	@Override
	public boolean getSelected() throws Exception {
		return selected;
	}

	@Override
	public void setSelected(boolean value) throws Exception {
		selected = value;
	}

	DeptList children;

	@Override
	public DeptList getChildren() {
		return children;
	}

	@Override
	public void setChildren(DeptList children) {
		this.children = children;
	}

	EmployeeList deptEmployee;

	@Override
	public EmployeeList getDeptEmployee() {
		return deptEmployee;
	}

	@Override
	public void setDeptEmployee(EmployeeList deptEmployee) {
		this.deptEmployee = deptEmployee;
	}

	@Override
	public IDept load() throws Exception {
		if (getPartCode() != null) {
			IDept dept = (IDept) databaseMe();
			return dept;
		}
		return null;
	}

//	@Override
//	public IDept findByComCode(String comcode) throws Exception {
//		String sql = "select * from parttable where comcode=?comCode";
//		IDept deptList = sql(sql);
//		deptList.setComCode(comcode);
//		deptList.select();
//		return deptList;
//	}
//
//	@Override
//	public IDept findTreeByComCode(String comcode) throws Exception {
//		String sql = "select * from parttable where comcode=?comCode ";
//		IDept deptList = sql(sql);
//		deptList.setComCode(comcode);
//		deptList.select();
//		deptList.setMetaworksContext(new MetaworksContext());
//		deptList.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
//		if (deptList.size() == 0) {
//			deptList = new Dept();
//			deptList.setMetaworksContext(new MetaworksContext());
//			deptList.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
//		}
//		return deptList;
//	}
	
	@Override
	public IDept findByGlobalCom() throws Exception {
		String sql = "select * from parttable where globalcom=?globalCom and parent_partCode is null and isDeleted='0' ";
		IDept deptList = sql(sql);
		deptList.setGlobalCom(this.getGlobalCom());
		deptList.select();
		deptList.setMetaworksContext(this.getMetaworksContext());
		
		return deptList;
	}

	@Override
	public IDept findTreeByGlobalCom(String globalCom) throws Exception {
		String sql = "select * from parttable where globalcom=?globalCom and isDeleted='0' ";
		IDept deptList = sql(sql);
		deptList.setGlobalCom(globalCom);
		deptList.select();
		deptList.setMetaworksContext(new MetaworksContext());
		deptList.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		if (deptList.size() == 0) {
			deptList = new Dept();
			deptList.setMetaworksContext(new MetaworksContext());
			deptList.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		}
		return deptList;
	}

	@Override
	public IDept findChildren() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from parttable ");
		sb.append("where globalcom=?globalCom ");
		sb.append("  and isDeleted='0' ");
		
		if(getPartCode() != null) {
			sb.append("and parent_partcode=?parent_PartCode ");
		} else {
			sb.append("and parent_partcode is null ");
		}
		IDept childDeptList = sql(sb.toString());
		childDeptList.setGlobalCom(getGlobalCom());
		
		if(getPartCode() != null) {
			childDeptList.setParent_PartCode(getPartCode());
		}
		childDeptList.select();
		childDeptList.setMetaworksContext(this.getMetaworksContext());
		
		System.out.println(sb.toString());
		
		return childDeptList;
	}
	
//	@Override
//	public IDept findRootDeptByComCode(String comcode) throws Exception{
//		setComCode(comcode);
//		setPartName(comcode);
//		setPartCode(null);
//		drillDown();
//		return this;
//	}
	
	@Override
	public IDept findRootDeptByGlobalCom(String globalCom) throws Exception{
		setGlobalCom(globalCom);
		setPartName(globalCom);
		setPartCode(null);
		drillDown();
		return this;
	}

	@Override
	public void drillDown() throws Exception {
		setSelected(!getSelected());
		if (!selected) {
			DeptList deptList = new DeptList();
			deptList.setId(this.getPartCode());
			
			setChildren(deptList);
			
			EmployeeList employeeList = new EmployeeList();
			employeeList.setId(this.getPartCode());
			
			setDeptEmployee(employeeList);
		} else {
			DeptList deptList = new DeptList();
			deptList.setMetaworksContext(this.getMetaworksContext());
			deptList.setId(this.getPartCode());
			deptList.setDept(this.findChildren());			
			setChildren(deptList);			
			
			if(!("deptPicker".equals(this.getMetaworksContext().getWhere()))){
				IEmployee employee = new Employee();
				employee.setMetaworksContext(this.getMetaworksContext());
				employee.getMetaworksContext().setHow("tree");
				
				EmployeeList employeeList = new EmployeeList();			
				employeeList.setMetaworksContext(this.getMetaworksContext());
				employeeList.setId(this.getPartCode());
				employeeList.setEmployee(employee.findByDept(this));
				
				setDeptEmployee(employeeList);
			}
		}
	}

	@Override
	public Popup editDeptInfo() throws Exception {
		Dept dept = new Dept();
		dept.setPartCode(this.getPartCode());
		dept.copyFrom(dept.databaseMe());
		
		dept.getMetaworksContext().setWhere("admin");
		dept.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		Popup popup = new Popup();
		popup.setPanel(dept);
		
		return popup;		
	}

	@AutowiredFromClient
	public Session session;

	@Override
	public Object[] saveDeptInfo() throws Exception {

		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			// 생성
			this.setGlobalCom(session.getCompany().getComCode());
			this.setIsDeleted("0");
			this.getMetaworksContext().setWhere("navigator");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);				
			
			createDatabaseMe();
			syncToDatabaseMe();
			

			DeptList deptList = new DeptList();			
			if(this.getParent_PartCode() == null)				
				deptList.setId("/ROOT/");
			else
				deptList.setId(this.getParent_PartCode());
			
			return new Object[]{new Remover(new Popup()), new ToAppend(deptList, this)};
			
			//drillDown
			
			//return new Object[] { returnValueDept, resultSaveDeptPanel };
			
			//returnValueDept = findRefreshableParentDept();
		} else {
			// if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_EDIT))
			syncToDatabaseMe();
			flushDatabaseMe();
			
			this.getMetaworksContext().setWhere("navigator");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);				
			
			return new Object[]{new Remover(new Popup()), new Refresh(this)};
		}
		//}
		// TODO execute drillDown()
		// returnValueDept.drillDown();
		
		//return new Object[] { returnValueDept, resultSaveDeptPanel };
	}

	private IDept findRefreshableParentDept() throws Exception {
		IDept returnValueDept;
		Dept parentDept = new Dept();
		if (getParent_PartCode() != null) {
			parentDept.setPartCode(getParent_PartCode());
			returnValueDept = parentDept.databaseMe();
		} else {
			returnValueDept = parentDept.findRootDeptByGlobalCom(getGlobalCom());
		}
		returnValueDept.setMetaworksContext(getMetaworksContext());
		return returnValueDept;
	}

	@Override
	public Object[] deleteDept() throws Exception {
		Dept dept = new Dept();
		dept.setPartCode(this.getPartCode());		
		dept.copyFrom(dept.databaseMe());
		dept.setIsDeleted("1");
		
		
/*		if( (this.getChildren() != null && this.getChildren().size() > 0) || (this.getDeptEmployee() != null && this.getDeptEmployee().size() > 0))
			throw new Exception("하위 노드가 존재하면 삭제할 수 없습니다.");*/
				
		dept.syncToDatabaseMe();
		dept.flushDatabaseMe();
		
		return new Object[]{new Remover(dept)};		
	}

	@Override
	public Object[] restoreDept() throws Exception {
		setIsDeleted("0");
		return saveDeleteFlag();
	}

	private Object[] saveDeleteFlag() throws Exception {
		syncToDatabaseMe();
		IDept returnValueDept = findRefreshableParentDept();

		AdminEastPanel resultSaveDeptPanel = new AdminEastPanel();
		resultSaveDeptPanel.setContent("save successfully.");
		return new Object[] { returnValueDept, resultSaveDeptPanel };
	}

	@Override
	public Object addNewChildDept() throws Exception {
		IDept newDept = new Dept();
		newDept.setParent_PartCode(getPartCode());
		newDept.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newDept.getMetaworksContext().setWhere("admin");

		Popup popup = new Popup();
		popup.setPanel(newDept);
		
		return popup;
	}

	@Override
	public AdminEastPanel addNewEmployee() throws Exception {
		IEmployee newEmployee = new Employee();
		newEmployee.setMetaworksContext(getMetaworksContext());
		newEmployee.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newEmployee.getMetaworksContext().setHow("detail");
		newEmployee.setPartCode(getPartCode());

		AdminEastPanel addNewEmployeePanel = new AdminEastPanel();
		addNewEmployeePanel.setContent(newEmployee);
		return addNewEmployeePanel;
	}

	public Object[] subscribe() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmpCode(session.getEmployee().getEmpCode());
		employee.copyFrom(employee.databaseMe());
		
		if(employee.getPartCode() != null && employee.getPartCode().equals(this.getPartCode())){
			return null;
		}else{
			String prevPartCode = employee.getPartCode();
								
			employee.setPartCode(this.getPartCode());
			employee.syncToDatabaseMe();
			employee.flushDatabaseMe();
			employee.setMetaworksContext(this.getMetaworksContext());
			employee.getMetaworksContext().setHow("tree");
			
			EmployeeList employeeList = new EmployeeList();
			employeeList.setId(this.getPartCode());

			Dept findDept = null;
			try{
				findDept = new Dept();			
				findDept.setPartCode(prevPartCode);			
				findDept.copyFrom(findDept.databaseMe());
				findDept.setMetaworksContext(this.getMetaworksContext());
				findDept.drillDown();
			}catch(Exception e){}

			if(findDept!=null){				
				return new Object[]{new Refresh(findDept), new Refresh(session), new ToAppend(employeeList, employee)};
			}else{
				return new Object[]{new Refresh(session), new ToAppend(employeeList, employee)};
				
			}
		}
		
	}

	@Override
	public Popup openPicker() throws Exception {
		DeptPicker deptPicker = new DeptPicker(session.getCompany().getComCode());
		deptPicker.load();
		
		return new Popup(deptPicker);
	}
	
	@Override
	public Object[] pickup() throws Exception {
		Dept dept = new Dept();
		dept.setPartCode(this.getPartCode());
		dept.copyFrom(dept.databaseMe());
		
		dept.getMetaworksContext().setWhere("inDetailView");
		dept.getMetaworksContext().setWhen("edit");
		
		return new Object[] {new ToOpener(dept), new Remover(new Popup())};
	}
	
}
