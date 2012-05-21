package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.model.Popup;

public class Employee extends Database<IEmployee> implements IEmployee {
	
	String empCode;
	String password;
	String empName;
	boolean isAdmin;
	String jikName;
	// IDept dept;
	// ICompany company;
	String partCode;

	transient String partName;

	String globalCom;
	String isDeleted;
	String mobileNo;
	String email;
	String locale;
	String faxNo;
	String email2;
	String postNo;
	String address1;
	String address2;
	String officeName;
	String officeTel;
	String officeExtTel;
	String position;
	String jobtitle;
	String webpage;
	String memo;
	String priority;
	String sensitivity;
	String etc1;
	String etc2;
	String ipcCode;
	String techCode;
	String tel;
	String customComCode;
	String customComName;
	String ipcCode1;
	String ipcCode2;
	String ipcCode3;
	String customerType;
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public boolean getIsAdmin() {
		return isAdmin;
	}

	@Override
	public void setIsAdmin(boolean value) {
		this.isAdmin = value;
	}

	public String getJikName() {
		return jikName;
	}

	public void setJikName(String jikName) {
		this.jikName = jikName;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	@Override
	public String getPartName() {
		return partName;
	}

	@Override
	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getGlobalCom() {
		return globalCom;
	}

	public void setGlobalCom(String globalCom) {
		this.globalCom = globalCom;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String deleted) {
		this.isDeleted = deleted;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeExtTel() {
		return officeExtTel;
	}

	public void setOfficeExtTel(String officeExtTel) {
		this.officeExtTel = officeExtTel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}

	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public String getEtc2() {
		return etc2;
	}

	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}

	public String getIpcCode() {
		return ipcCode;
	}

	public void setIpcCode(String ipcCode) {
		this.ipcCode = ipcCode;
	}

	public String getTechCode() {
		return techCode;
	}

	public void setTechCode(String techCode) {
		this.techCode = techCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCustomComCode() {
		return customComCode;
	}

	public void setCustomComCode(String customComCode) {
		this.customComCode = customComCode;
	}
	
	public String getCustomComName() {
		return customComName;
	}

	public void setCustomComName(String customComName) {
		this.customComName = customComName;
	}

	public String getIpcCode1() {
		return ipcCode1;
	}

	public void setIpcCode1(String ipcCode1) {
		this.ipcCode1 = ipcCode1;
	}
	
	public String getIpcCode2() {
		return ipcCode2;
	}

	public void setIpcCode2(String ipcCode2) {
		this.ipcCode2 = ipcCode2;
	}
	
	public String getIpcCode3() {
		return ipcCode3;
	}

	public void setIpcCode3(String ipcCode3) {
		this.ipcCode3 = ipcCode3;
	}
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	PortraitImageFile imageFile;
	
	public PortraitImageFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(PortraitImageFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public IEmployee load() throws Exception {
		String errorMessage;
		if (getEmpCode() != null) {
			IEmployee emp = (IEmployee) findMe();
			if (getPassword().equals(emp.getPassword())) {
				emp = findMe();

				// emp = databaseMe();

				getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				return emp;
			} else {
				errorMessage = "Password not match !!!";
			}
		} else {
			errorMessage = "There is no user id !!!";
		}

		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		throw new Exception(errorMessage);
	}

	@Override
	public IEmployee findMe() throws Exception {
		Employee employee = new Employee();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select EMPTABLE.*, PARTTABLE.PARTNAME from ");
		sb.append("EMPTABLE LEFT OUTER JOIN PARTTABLE on EMPTABLE.partcode=PARTTABLE.partcode ");
		sb.append("where EMPTABLE.EMPCODE=?empCode ");
		
		IEmployee findEmployee = (IEmployee) sql(sb.toString());
		findEmployee.set("empCode", getEmpCode());
		findEmployee.select();
		if (findEmployee.next()) {
			employee.copyFrom(findEmployee);
		}
		return employee;

	}

	@Override
	public IEmployee findByDept(Dept dept) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select EMPTABLE.*, PARTTABLE.PARTNAME from ");
		sb.append("EMPTABLE LEFT OUTER JOIN PARTTABLE on EMPTABLE.partcode=PARTTABLE.partcode ");
		if (dept.getPartCode() != null) {
			sb.append("where EMPTABLE.partcode=?partCode ");
		} else {
			sb.append("where EMPTABLE.partcode is null ");
		}
		sb.append("and EMPTABLE.globalcom=?globalCom ");
		IEmployee deptEmployee = sql(sb.toString());
		if (dept.getPartCode() != null) {
			deptEmployee.setPartCode(dept.getPartCode());
		}
		deptEmployee.setGlobalCom(dept.getGlobalCom());
		deptEmployee.select();
		return deptEmployee;
	}

	@AutowiredFromClient
	public Session session;

//	@Override
//	public Object[] loadOrganization() throws Exception {
//		return Perspective.loadInstanceListPanel(session, "organization",
//				getEmpCode());
//	}

	@Override
	public Object[] pickup() throws Exception {
		
		// TODO add Remover to return values
		IEmployee selectedEmployee = findMe();
		selectedEmployee.getMetaworksContext().setWhere("pickerCaller");
		return new Object[] {new ToOpener(selectedEmployee), new Remover(new Popup())};
	}
	
	@Override
	public Popup openPicker() {
		EmployeePicker employeePicker = new EmployeePicker(session.getCompany().getComCode());
		employeePicker.getMetaworksContext().setWhere("picker");
		return new Popup(employeePicker);
	}

	@Override
	public Object[] saveEmployeeInfo() throws Exception {
		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			createDatabaseMe();
		} else {
			// if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_EDIT))
			syncToDatabaseMe();
		}
		flushDatabaseMe();
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		if(session.getEmployee().getEmpCode().equals(getEmpCode())) {
			session.setEmployee(findMe());
			return new Object[] {findMe(), session};
		}
		return new Object[] {findMe()};
	}
//
//	@Override
//	public Object showDetail() throws Exception {
//		IEmployee employee = findMe();
//		employee.setMetaworksContext(getMetaworksContext());
//		employee.getMetaworksContext().setHow("detail");
//		
//		if (getMetaworksContext().getWhere().equals("inSession")) {
//			employee.getMetaworksContext().setWhere("inDetailView");
//			employee.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
//			EastRightPanel empInfoPanel = new EastRightPanel();
//			empInfoPanel.setContent(employee);
//			return empInfoPanel;
//		} else if (getMetaworksContext().getWhere().equals("admin")) {
//			AdminEastPanel newDeptPanel = new AdminEastPanel();
//			newDeptPanel.setContent(employee);
//			return newDeptPanel;
//		} else {
//			EastRightPanel empInfoPanel = new EastRightPanel();
//			empInfoPanel.setContent(employee);
//			return empInfoPanel;
//		}
//	}

	@Override
	public ContactList addContact() throws Exception {
		Contact contact = new Contact();
		IUser friendUser = new User();
		friendUser.setUserId(getEmpCode());
		friendUser.setName(getEmpName());
		contact.setFriends(friendUser);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList(session.getUser());
		return cp;
	}

}
