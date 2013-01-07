package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.admin.AdminEastPanel;

@Table(name = "PARTTABLE")
public interface IDept extends IDAO {
	public static final String CUSTOMER_DEPT_PARTCODE = "CUSTOMER_DEP";

	@Id
	public String getPartCode();

	public void setPartCode(String partCode);

	@Name
	public String getPartName();

	public void setPartName(String partName);

	public String getParent_PartCode();

	public void setParent_PartCode(String parentPartCode);

	@Hidden
	public String getIsDeleted();

	public void setIsDeleted(String deleted);

	public String getDescription();

	public void setDescription(String description);

//	public String getComCode();
//
//	public void setComCode(String comCode);
	
	public String getGlobalCom();

	public void setGlobalCom(String globalCom);

	@Hidden
	@NonSavable
	@NonLoadable
	public boolean getSelected() throws Exception;

	public void setSelected(boolean value) throws Exception;

	@NonSavable
	@NonLoadable
	public DeptList getChildren() throws Exception;
	public void setChildren(DeptList children) throws Exception;

	@NonSavable
	@NonLoadable
	public EmployeeList getDeptEmployee() throws Exception;
	public void setDeptEmployee(EmployeeList deptEmployee) throws Exception;

	// methods
	public IDept load() throws Exception;

//	public IDept findByComCode(String comcode) throws Exception;
//
//	public IDept findTreeByComCode(String comcode) throws Exception;
	
	public IDept findByGlobalCom() throws Exception;

	public IDept findTreeByGlobalCom(String globalCom) throws Exception;


	public IDept findChildren() throws Exception;
	
//	public IDept findRootDeptByComCode(String comcode) throws Exception;
	
	public IDept findRootDeptByGlobalCom(String globalCom) throws Exception;


	// service methods
	@ServiceMethod(target="self", callByContent = true, payload = { "partCode", "selected" })
	public void drillDown() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true)
	public Object editDeptInfo() throws Exception;

	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] saveDeptInfo() throws Exception;

	@ServiceMethod(inContextMenu=true, needToConfirm=true)
	public Object[] deleteDept() throws Exception;
	
	@ServiceMethod(callByContent = true)
	public Object[] restoreDept() throws Exception;
			
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true)
	public Object addNewChildDept() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true, needToConfirm=true)
	public Object[] subscribe() throws Exception;

	
	@ServiceMethod
	public AdminEastPanel addNewEmployee() throws Exception;
	
	@ServiceMethod(target="popup")
	public Popup openPicker() throws Exception;
	
	@ServiceMethod(target="append", where="picker")
	public Object pickup() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop() throws Exception;

	@ServiceMethod
	public Object[] loadDeptList() throws Exception;
	
}
