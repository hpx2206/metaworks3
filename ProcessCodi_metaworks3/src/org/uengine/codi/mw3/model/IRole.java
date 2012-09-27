package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="roletable")
public interface IRole extends IDAO {
	@Id
	public String getRoleCode();
	public void setRoleCode(String roleCode);

	public String getComCode();
	public void setComCode(String comCode);

	public String getDescr();
	public void setDescr(String descr);

	public String getIsDeleted();
	public void setIsDeleted(String isDeleted);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isSelected();
	public void setSelected(boolean selected);
	
	@NonLoadable
	@NonSavable
	public EmployeeList getEmployeeList();
	public void setEmployeeList(EmployeeList employeeList);

	// service method
	@ServiceMethod(target="self", payload={"roleCode", "selected"})
	public void drillDown() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true, needToConfirm=true)
	public Object subscribe() throws Exception;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true, needToConfirm=true)
//	@Face(displayName="Unsubscribe")
	public Object[] removeMe() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, inContextMenu=true)
	public Popup editPopup() throws Exception;

	@ServiceMethod(callByContent=true, except={"employeeList"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] saveMe() throws Exception;
	
	
	
	// function
	public IRole findByGlobalCom() throws Exception;
	
	public IRole findByEmployee(IEmployee employee) throws Exception;
		
}
