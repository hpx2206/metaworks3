package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
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

	IDept children;

	@Override
	public IDept getChildren() throws Exception {
		return children;
	}

	@Override
	public void setChildren(IDept children) throws Exception {
		this.children = children;
	}

	IEmployee deptEmployee;

	@Override
	public IEmployee getDeptEmployee() throws Exception {
		return deptEmployee;
	}

	@Override
	public void setDeptEmployee(IEmployee deptEmployee) throws Exception {
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
	public IDept findByGlobalCom(String globalCom) throws Exception {
		String sql = "select * from parttable where globalcom=?globalCom";
		IDept deptList = sql(sql);
		deptList.setGlobalCom(globalCom);
		deptList.select();
		return deptList;
	}

	@Override
	public IDept findTreeByGlobalCom(String globalCom) throws Exception {
		String sql = "select * from parttable where globalcom=?globalCom ";
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
			children = null;
			deptEmployee = null;
		} else {
			setChildren(findChildren());
			getChildren().setMetaworksContext(getMetaworksContext());
			IEmployee deptEmployee = new Employee();
			setDeptEmployee(deptEmployee.findByDept(this));
			getDeptEmployee().setMetaworksContext(getMetaworksContext());
			getDeptEmployee().getMetaworksContext().setHow("tree");
		}
	}

	@Override
	public AdminEastPanel editDeptInfo() throws Exception {
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		AdminEastPanel newDeptPanel = new AdminEastPanel();
		newDeptPanel.setContent(this);
		return newDeptPanel;
	}

	@AutowiredFromClient
	public Session session;

	@Override
	public Object[] saveDeptInfo() throws Exception {
		IDept returnValueDept = null;

		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			this.setGlobalCom(session.getCompany().getComCode());
			createDatabaseMe();
			syncToDatabaseMe();

			returnValueDept = findRefreshableParentDept();
		} else { // when== "edit"
			// if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_EDIT))
			syncToDatabaseMe();
			flushDatabaseMe();
			returnValueDept = databaseMe();
		}
		// TODO execute drillDown()
		// returnValueDept.drillDown();
		
		returnValueDept.getMetaworksContext().setWhen(
				MetaworksContext.WHEN_VIEW);

		AdminEastPanel resultSaveDeptPanel = new AdminEastPanel();
		resultSaveDeptPanel.setContent("save successfully.");
		return new Object[] { returnValueDept, resultSaveDeptPanel };
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
		setIsDeleted("1");
		return saveDeleteFlag();
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
	public AdminEastPanel addNewChildDept() throws Exception {
		IDept newDept = new Dept();
		newDept.setParent_PartCode(getPartCode());
		newDept.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newDept.getMetaworksContext().setWhere("admin");

		AdminEastPanel newDeptPanel = new AdminEastPanel();
		newDeptPanel.setContent(newDept);
		return newDeptPanel;
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

}
