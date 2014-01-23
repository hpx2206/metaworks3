package org.uengine.codi.mw3.model;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.metaworks.EventContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.admin.AdminEastPanel;

@Table(name = "PARTTABLE")
public interface IDept extends IDAO {
	public static final String CUSTOMER_DEPT_PARTCODE = "CUSTOMER_DEP";

	@Id
//	@Pattern(regexp="/[*a-z][*0-9]/", message="$deptcode.space.error.message")
	@GeneratedValue
	public String getPartCode();
	public void setPartCode(String partCode);

	@Name
	@NotNull(message="부서 이름을 입력해주세요.")
	public String getPartName();
	public void setPartName(String partName);

	public String getParent_PartCode();

	public void setParent_PartCode(String parentPartCode);

	@Hidden
	public String getIsDeleted();

	public void setIsDeleted(String deleted);

	public String getDescription();

	public void setDescription(String description);

	public String getUrl();
	public void setUrl(String url);

	public String getThumbnail();
	public void setThumbnail(String thumbnail);

	@NonSavable
	@NonLoadable
	public MetaworksFile getLogoFile();
	public void setLogoFile(MetaworksFile logoFile);
	
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
	public boolean isFollowed();
	public void setFollowed(boolean followed);

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

	@Available(where=IUser.WHERE_ADDFOLLOWER)
	@ServiceMethod(callByContent=true, target=TARGET_APPEND, eventBinding=EventContext.EVENT_CLICK)
	public Object[] addFollower() throws Exception;
	
	@Available(where=IUser.WHERE_FOLLOWERS)
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] removeFollower() throws Exception;

	// service methods
	@ServiceMethod(target="self", callByContent = true, payload = { "partCode", "selected" })
	public void drillDown() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object editDeptInfo() throws Exception;

	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND, validate=true) 
	public Object[] saveDeptInfo() throws Exception;

	@ServiceMethod(needToConfirm=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] deleteDept() throws Exception;
	
	@ServiceMethod(callByContent = true)
	public Object[] restoreDept() throws Exception;
			
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object addNewChildDept() throws Exception;

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, needToConfirm=true)
	public Object[] subscribe() throws Exception;

	
	@ServiceMethod
	public AdminEastPanel addNewEmployee() throws Exception;
	
	@ServiceMethod(target="popup")
	public Popup openPicker() throws Exception;
	
	@ServiceMethod(target="append", where="picker")
	public Object pickup() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop() throws Exception;

	@ServiceMethod(payload={"partCode", "partName"})
	public Object[] loadDeptList() throws Exception;
	
}
