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
import org.metaworks.website.MetaworksFile;

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
	
	public String getUrl();
	public void setUrl(String url);
	
	public String getThumbnail();
	public void setThumbnail(String thumbnail);
	
	@NonLoadable
	@NonSavable
	public MetaworksFile getLogoFile();
	public void setLogoFile(MetaworksFile logoFile);
	
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
	// 역활 부분에서 리스트 이름이 가려 졌는데 payload에 descr을 추가한다.
	@ServiceMethod(target="self", payload={"roleCode", "selected" ,"descr"})
	public void drillDown() throws Exception;

	@Face(displayName="$role.Subscribe")
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, needToConfirm=true)
	public Object subscribe() throws Exception;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, needToConfirm=true)
	@Face(displayName="$Remove")
	public Object[] removeMe() throws Exception;

	@Face(displayName="$Edit")
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup editPopup() throws Exception;

	@Face(displayName="$Save")
	@ServiceMethod(callByContent=true, except={"employeeList"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] saveMe() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] loadRole() throws Exception;
	
	// function
	public IRole findByGlobalCom() throws Exception;
	
	public IRole findByEmployee(IEmployee employee) throws Exception;
		
}
