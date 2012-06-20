package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.Login;

public class Employee extends Database<IEmployee> implements IEmployee {
	
	String empCode;	

	String password;
	String confirmPassword;
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
	String preferUX;
	String preferMob;
	String mood;
	

	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	
	public String getPreferMob() {
		return preferMob;
	}
	public void setPreferMob(String preferMob) {
		this.preferMob = preferMob;
	}
	public String getPreferUX() {
		return preferUX;
	}
	public void setPreferUX(String preferUX) {
		this.preferUX = preferUX;
	}
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
				errorMessage = "<font color=blue>Wrong User or Password! forgot?</font>";				
			}
		} else {
			errorMessage = "<font color=blue>There's no such ID. Please subscribe.</font>";
		}
		
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
		deptEmployee.setMetaworksContext(this.getMetaworksContext());
		
		return deptEmployee;
	}

	@Override
	public IEmployee findByRole(Role role) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.*");
		sb.append("  FROM empTable a, roleUserTable b");
		sb.append(" WHERE a.empCode = b.empCode");
		sb.append("   AND a.isDeleted=?isDeleted");
		sb.append("   AND b.roleCode=?roleCode");
		
		IEmployee employee = sql(sb.toString());
		employee.setIsDeleted("0");
		employee.set("roleCode", role.getRoleCode());
		employee.select();
		employee.setMetaworksContext(this.getMetaworksContext());
		
		return employee;
	}
	
	
	@AutowiredFromClient
	public Session session;

	@Override
	public Object[] loadOrganization() throws Exception {
		return Perspective.loadInstanceListPanel(session, "organization",
				getEmpCode());
	}

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
	public Object editEmployeeInfo() throws Exception {
		IEmployee employee = findMe();
		
		employee.getMetaworksContext().setHow("detail");
		employee.getMetaworksContext().setWhen(WHEN_EDIT);
		employee.getMetaworksContext().setWhere("inDetailView");

		employee.setImageFile(new PortraitImageFile());
		employee.getImageFile().getMetaworksContext().setWhen(WHEN_EDIT);
		
		return new EmployeeInfo(employee);
	}	
	
	@Override
	public Object[] saveEmployeeInfo() throws Exception {		
		if (getMetaworksContext().getWhen().startsWith(MetaworksContext.WHEN_NEW)) {
			this.setIsDeleted("0");
			this.setGlobalCom("uEngine");
			
			createDatabaseMe();
		} else {
			// if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_EDIT))
			syncToDatabaseMe();
		}
		flushDatabaseMe();
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		if(getImageFile()!=null && getImageFile().getFileTransfer()!=null){
			getImageFile().setEmpCode(this.getEmpCode());
			getImageFile().upload();

		}
		
		if(session != null && session.getEmployee().getEmpCode().equals(getEmpCode())) {
			session.setEmployee(findMe());
			return new Object[] {new EmployeeInfo(findMe()), session};
		}
		
		Login login = new Login();
		login.setUserId(getEmpCode());
		login.setGuidedTour(true);
		login.getMetaworksContext().setWhen("edit");
		
		return new Object[] {new Remover(new ModalWindow()), login};
	}
	
	@Override
	public Object showDetail() throws Exception {
		IEmployee employee = findMe();
		employee.setMetaworksContext(getMetaworksContext());
		employee.getMetaworksContext().setHow("detail");
		employee.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		employee.getMetaworksContext().setWhere("inDetailView");
		
		
		return new Popup(new EmployeeInfo(employee));
	}
	
	@Override
	public ContactList addContact() throws Exception {
		Contact contact = new Contact();
		IUser friendUser = new User();
		friendUser.setUserId(getEmpCode());
		friendUser.setName(getEmpName());
		contact.setFriend(friendUser);
		contact.setNetwork(ContactList.LOCAL);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList();
		cp.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		cp.getMetaworksContext().setWhere(ContactList.LOCAL);
		
		cp.load(session.getUser().getUserId());
		
		return cp;
	}
	
	public void checkEmpCode() throws Exception {
			
		IEmployee employee = this.findMe();
		
		getMetaworksContext().setWhen("new2");
		
		if(employee.getEmpCode() != null)
			throw new Exception("이미 존재하는 empCode 입니다.");
	}
	@Override
	public Object[] subscribeStep1() throws Exception {
		Locale locale = new Locale();
		locale.setLanguage(this.getLocale());
		locale.load();
		
		getMetaworksContext().setWhen("new2");

		return new Object[]{locale, this};
	}
	@Override
	public Object subscribeStep2() throws Exception {
		if(!getPassword().equals(getConfirmPassword())){
			throw new Exception("Re-entered password doesn't match");
		}

		getMetaworksContext().setWhen("new3");

		return this;
	}
	@Override
	public Object subscribeStep3() throws Exception {
		getMetaworksContext().setWhen("finish");

		return this;
	}	
}
