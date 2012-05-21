package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.model.Popup;

@Table(name = "EMPTABLE")
public interface IEmployee extends IDAO {

	@Id
	public String getEmpCode();

	public void setEmpCode(String empCode);

	@Hidden(when = "view")
	@Face(options="type", values="password")
	public String getPassword();

	public void setPassword(String password);

	@Name
	public String getEmpName();

	public void setEmpName(String empName);

	public String getJikName();

	public void setJikName(String jikName);

	public String getPartCode();
	
	public void setPartCode(String partCode);
	
	@NonSavable
	public String getPartName();
	
	public void setPartName(String partName);
	
	public String getGlobalCom();
	
	public void setGlobalCom(String comCode);
	
	@Hidden
	public String getIsDeleted();
	
	public void setIsDeleted(String deleted);

	public String getMobileNo();

	public void setMobileNo(String mobileNo);
	
	public String getEmail();

	public void setEmail(String email);

	public String getLocale();

	public void setLocale(String locale);
	
	public String getEmail2();
	
	public void setEmail2(String email2);
	
	public String getPostNo();
	
	public void setPostNo(String postNo);
	
	public String getAddress1();
	
	public void setAddress1(String address1);
	
	public String getAddress2();
	
	public void setAddress2(String address2);
	
	public String getOfficeName();
	
	public void setOfficeName(String officeName);
	
	public String getOfficeTel();
	
	public void setOfficeTel(String officeTel);
	
	public String getOfficeExtTel();
	
	public void setOfficeExtTel(String officeExtTel);
	
	public String getPosition();
	
	public void setPosition(String position);
	
	public String getJobtitle();
	
	public void setJobtitle(String jobtitle);
	
	public String getWebpage();
	
	public void setWebpage(String webpage);
	
	public String getMemo();
	
	public void setMemo(String memo);
	
	public String getPriority();
	
	public void setPriority(String priority);
	
	public String getSensitivity();
	
	public void setSensitivity(String sensitivity);
	
	public String getEtc1();
	
	public void setEtc1(String etc1);
	
	public String getEtc2();
	
	public void setEtc2(String etc2);
	
	public String getIpcCode();
	
	public void setIpcCode(String ipcCode);
	
	public String getTechCode();
	
	public void setTechCode(String techCode);
	
	public String getTel();
	
	public void setTel(String tel);
	
	public String getCustomComCode();

	public void setCustomComCode(String customComCode);
	
	public String getCustomComName();

	public void setCustomComName(String customComName);
	
	public String getIpcCode1();
	
	public void setIpcCode1(String ipcCode1);
	
	public String getIpcCode2();
	
	public void setIpcCode2(String ipcCode2);
	
	public String getIpcCode3();
	
	public void setIpcCode3(String ipcCode3);
	
	public String getCustomerType();

	public void setCustomerType(String customerType);
	
	@NonLoadable
	@NonSavable
	public PortraitImageFile getImageFile();

	public void setImageFile(PortraitImageFile imageFile);
	
	@ServiceMethod(callByContent = true)
	public IEmployee load() throws Exception;

	public abstract boolean getIsAdmin();
	public abstract void setIsAdmin(boolean value);
	
	public IEmployee findMe() throws Exception;
	
	public IEmployee findByDept(Dept dept) throws Exception;
	
//	@ServiceMethod(where="navigation")
//	public Object[] loadOrganization() throws Exception;
	
	@ServiceMethod(target="append", where="picker")
	public Object pickup() throws Exception;
	
	@ServiceMethod(target="popup")
	public Popup openPicker();
	
	@ServiceMethod(callByContent=true)
	public Object saveEmployeeInfo() throws Exception;
	
//	@ServiceMethod(callByContent=true)
//	public Object showDetail() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public ContactList addContact() throws Exception;
}
