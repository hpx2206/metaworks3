package org.uengine.codi.mw3.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.tadpole.Tadpole;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class Employee extends Database<IEmployee> implements IEmployee {

	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public Locale localeManager;
	
/*	IUser user;	
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}*/

	boolean validEmail;
		public boolean isValidEmail() {
			return validEmail;
		}
		public void setValidEmail(boolean validEmail) {
			this.validEmail = validEmail;
		}	


	String empCode;
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		
	String empName;
		public String getEmpName() {
			return empName;
		}
	
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		
	String facebookId;
		public String getFacebookId() {
			return facebookId;
		}
		public void setFacebookId(String facebookId) {
			this.facebookId = facebookId;
		}

	IDept dept;
		public IDept getDept() {
			return dept;
		}
		public void setDept(IDept dept) {
			this.dept = dept;
		}

	String partCode;
		public String getPartCode() {
			return partCode;
		}	
		public void setPartCode(String partCode) {
			this.partCode = partCode;
		}
	
	String password;
	String confirmPassword;	
	boolean isAdmin;
	String jikName;
	// IDept dept;
	// ICompany company;
	

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

	boolean approved;
		public boolean isApproved() {
			return approved;
		}
		public void setApproved(boolean approved) {
			this.approved = approved;
		}
		
	boolean guest;
		public boolean isGuest() {
			return guest;
		}
		public void setGuest(boolean guest) {
			this.guest = guest;
		}
		
	@Override
	public IEmployee load() throws Exception {
		String errorMessage;
		if (getEmpCode() != null) {
			IEmployee emp = (IEmployee) findMe();
			
			if(emp.getIsDeleted() != null && emp.getIsDeleted().equals("1")) {
				errorMessage = "<font color=blue>There's no such ID. Please subscribe.</font>";
			} else if(getPassword().equals(emp.getPassword())) {
				emp = findMe();				
				// emp = databaseMe();

				getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				return emp;				
			}	else {
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
			employee.getDept().getMetaworksContext().setHow("picker");
			/*
			Dept dept = new Dept();
			
			dept.getMetaworksContext().setHow("info");
			dept.setPartCode(employee.getDept().getPartCode());
			dept.setPartName(employee.getDept().getPartName());
			
			employee.setDept(dept);
			*/
		}
		return employee;

	}
	@Override
	public IEmployee findMeByEmpName() throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select EMPTABLE.*, PARTTABLE.PARTNAME from ");
		sb.append("EMPTABLE LEFT OUTER JOIN PARTTABLE on EMPTABLE.partcode=PARTTABLE.partcode ");
		sb.append("where EMPTABLE.EMPNAME like ?empName ");
		
		IEmployee findEmployee = (IEmployee) sql(sb.toString());
		findEmployee.set("empName", "%" + getEmpName() + "%" );
		findEmployee.select();
		findEmployee.setMetaworksContext(this.getMetaworksContext());
		
		return findEmployee;
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
	public IEmployee findByDeptOther() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.*, b.PARTNAME");
		sb.append("  FROM empTable a");
		sb.append("  LEFT OUTER JOIN partTable b on a.partcode=b.partcode");
		sb.append(" WHERE a.globalCom=?globalCom");
		sb.append("   AND a.isDeleted=?isDeleted");
		sb.append("   AND NOT EXISTS");
		sb.append(" 	(SELECT partCode");
		sb.append(" 	   FROM partTable c");
		sb.append(" 	  WHERE c.globalCom=?globalCom");
		sb.append(" 	    AND a.partCode = c.partCode)");
				
		IEmployee deptEmployee = sql(sb.toString());
		deptEmployee.setGlobalCom(this.getGlobalCom());
		deptEmployee.setIsDeleted("0");
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
		return Perspective.loadInstanceListPanel(session, "organization", getEmpCode(), "사원 : " + this.getEmpName() + "(" + this.getJikName() +")");
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
		employee.getMetaworksContext().setWhere(this.getMetaworksContext().getWhere());

		employee.setImageFile(new PortraitImageFile());
		employee.getImageFile().getMetaworksContext().setWhen(WHEN_EDIT);
		
		return new EmployeeInfo(employee);
	}	
	
	@Override
	public boolean saveMe() throws Exception {
		if (getMetaworksContext().getWhen().startsWith(MetaworksContext.WHEN_NEW)) {
			checkRegistered();

			this.setIsDeleted("0");
			this.setApproved(true);
			
			if(this.getGlobalCom()==null){
				this.setGlobalCom("uEngine");
			}else{
				
				Company company = new Company();
				company.setComCode(this.getGlobalCom());

				try{
					company.databaseMe();					
				}catch(Exception e){		
					company.createDatabaseMe();
					company.syncToDatabaseMe();
					
					this.setIsAdmin(true);
					this.setApproved(true);
					
					this.createCodi();
				}
			}
			
			createDatabaseMe();
		} else {
			this.setPartCode(this.getDept().getPartCode());
			// if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_EDIT))
			syncToDatabaseMe();
		}
		flushDatabaseMe();
		
		//codi user 추가 시 이행하도록 이동
		//getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		if(getImageFile()!=null && getImageFile().getFileTransfer()!=null && getImageFile().getFileTransfer().getFilename()!=null){
			getImageFile().setEmpCode(this.getEmpCode());
			getImageFile().upload();
		}
		
		return true;
	}
	
	@Override
	public boolean createCodi() throws Exception {
		
		if (getMetaworksContext().getWhen().startsWith(MetaworksContext.WHEN_NEW)) {
			
			Employee emp = new Employee();
			emp.setEmpCode(GlobalContext.getPropertyString("codi.user.id") + "." + this.globalCom);
			emp.setEmpName(GlobalContext.getPropertyString("codi.user.name"));
			emp.setGlobalCom(this.globalCom);
			emp.setLocale(this.getLocale());
			emp.setApproved(true);
			
			if(emp.findMe().getEmpCode() != null && emp.findMe().getEmpCode().equals(GlobalContext.getPropertyString("codi.user.id") + "." + this.globalCom))
				return false;
			
			emp.createDatabaseMe();
			
			this.createCodiThumNail(emp.getEmpCode());
		}
		
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		
		return true;
	}
	
	@Override
	public void createCodiThumNail(String target) throws Exception {
		
		String inputPath = GlobalContext.getPropertyString("fastcat.server.host") + ":" + GlobalContext.getPropertyString("fastcat.server.port") 
							+ GlobalContext.WEB_CONTEXT_ROOT + "/images/portrait/codi.jpg";
		
		String outputPath = GlobalContext.getPropertyString("filesystem.path") + "/portrait/" + target + ".jpg";
		
		URL url = new URL(inputPath);
		
		try {
			InputStream fis = url.openStream();
			FileOutputStream fos = new FileOutputStream(outputPath);
			
			int bytesRead = 0;
			
			byte[] buffer = new byte[1024];   
			while ((bytesRead = fis.read(buffer, 0, 1024)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Object[] saveEmployeeInfo() throws Exception {	
		
		this.saveMe();
		
		if(session != null && session.getEmployee().getEmpCode().equals(getEmpCode())) {
			session.setEmployee(findMe());
			
			return new Object[] {new Refresh(new EmployeeInfo(this)), new Refresh(session)};
		}
		
		if(this.getIsAdmin() == false && !this.isApproved()){
			Session session = new Session();
			session.setEmployee(this);
			session.fillSession();
			session.setGuidedTour(true);
			
			CommentWorkItem newComment = new CommentWorkItem();
			newComment.processManager = processManager;
			newComment.session = session;

			newComment.setTitle(localeManager.getResourceBundle().getProperty("RequestJoinApprovedMessage"));
			newComment.save();
			
			processManager.applyChanges();
		}
		
		
		if("1".equals(GlobalContext.getPropertyString("tadpole.use", "1"))){
			
			StringBuffer param = new StringBuffer();
			
			//parameter로 넘겨줘야 할 값 == comcode, userid, pw, name
			param.append("?comcode=").append(this.getGlobalCom()).append("&");
			param.append("userId=").append(this.getEmpCode()).append("&");
			param.append("pw=").append(this.getPassword()).append("&");
			param.append("userName=").append(this.getEmpName());
			
			Tadpole tadpole = new Tadpole();
			tadpole.session = session;
			tadpole.createUserAtTadpole(param.toString());
		}
		
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
		
		modalWindow.setTitle("$JoinCompleteTitle");
		modalWindow.setPanel(localeManager.getString("$JoinCompleteMessage"));
		modalWindow.getButtons().put("$Confirm", null);
		modalWindow.getCallback().put("$Confirm", "forward");
		
		return new Object[] {modalWindow, new Refresh(this)};
	}
	
	public void checkRegistered() throws Exception {
		boolean alreadyRegistered = false;
		try{
			
//				if(this.getEmpCode()==null){
			this.setEmpCode(this.getEmail());
//				}
			
			this.databaseMe(); 
			
			alreadyRegistered = true;
		}catch(Exception e){}

		if(alreadyRegistered)	
			throw new Exception("$AlreadyExistingUser");
	}
	
	@Override
	public Object showDetail() throws Exception {
		IEmployee employee = findMe();
		employee.setMetaworksContext(getMetaworksContext());
		employee.getMetaworksContext().setHow("detail");
		employee.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		employee.getMetaworksContext().setWhere("inDetailWindow");
		
		
		return new ModalWindow(new EmployeeInfo(employee), 700, 400, employee.getEmpName());
	}
	
	@Override
	public Refresh addContact() throws Exception {
		Contact contact = new Contact();
		IUser friendUser = new User();
		friendUser.setUserId(getEmpCode());
		friendUser.setName(getEmpName());		
		friendUser.setNetwork(ContactList.LOCAL);		
		
		contact.setFriendId(friendUser.getUserId());
		contact.setFriend(friendUser);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList();
		cp.setId(ContactList.LOCAL);
		cp.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		cp.getMetaworksContext().setWhere(ContactList.LOCAL);
		
		cp.load(session.getUser().getUserId());
		
		return new Refresh(cp);
	}
	@Override
	public void addTopicUser() throws Exception {
		System.out.println(getEmpName());
	}
	
	
	@Override
	public void checkEmpCode() throws Exception {
			
		IEmployee employee = this.findMe();
		
		if(employee.getEmpCode() != null)
			throw new Exception("이미 존재하는 empCode 입니다.");
	}

	public boolean checkValidEmail() throws Exception{
		int posAt = this.getEmail().indexOf("@");
		
		if(this.getEmail().length() != 0 && posAt > -1 ) {
			String[] splitEmail = this.getEmail().split("@", -1);
			
			if(splitEmail[0].length() != 0 && splitEmail[1].length() != 0){
				return true;
			}
		}
		return false;
	}

	@Override
	public String checkId() throws Exception {
	
		String valid = "invalid";

		if(checkValidEmail()){
			this.setEmpCode(this.getEmail());
			IEmployee employee = this.findMe();
		
			if(employee.getEmpCode() == null)
				valid = "valid";
			else
				valid = "duplicate";
		}
		return valid;
	}

	@Override
	public Object[] subscribeStep1() throws Exception {
		
		
		Locale locale = new Locale();
		locale.setLanguage(this.getLocale());
		locale.load();
		
		int posAt = this.getEmpCode().indexOf("@");
		if(posAt > -1){
			String domain = this.getEmpCode().substring(posAt +1);
			if(domain.indexOf("'") > -1) throw new Exception("email address is invalid");
			
			IEmployee exisingUserWhoInTheSameDomain = sql("select * from emptable where email like '%" + domain + "'");
			exisingUserWhoInTheSameDomain.select();
			
			if(exisingUserWhoInTheSameDomain.next()){
				String globalCom = exisingUserWhoInTheSameDomain.getGlobalCom();
				this.setGlobalCom(globalCom);
			}else{
				this.setGlobalCom(domain);
			}
		}
		
		getMetaworksContext().setWhen("new2");

		return new Object[]{locale, this};
	}
	@Override
	public Object subscribeStep2() throws Exception {
		if(!this.checkValidEmail()){
			throw new Exception("Is that email is already subscribed");
		}

		
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
	
	@Override
	public Object[] unsubscribe() throws Exception {
		
		if(session.getEmployee().getEmpCode().equals(this.getEmpCode()) || session.employee.getIsAdmin()) {
			
			Employee employee = new Employee();
			employee.setEmpCode(this.empCode);		
			employee.databaseMe().setIsDeleted("1");		
			
			if(session.getEmployee().getEmpCode().equals(this.getEmpCode()))
				return new Object[]{session.logout()};
			else
				return new Object[]{new Remover(employee , true)};
		}
		else
			throw new Exception("관리자나 본인이 아니면 탈퇴할 수 없습니다");		
	}	
	
	public Object[] logout() throws Exception{
		if (this.getMetaworksContext().getWhere().equals("inDetailWindow"))
			return new Object[]{new Remover(new ModalWindow()), session.logout()};
		else
			return new Object[]{new Remover(new Popup()), session.logout()};
	}
	@Override
	public Session drag() throws Exception {
		session.setClipboard(this);
		return session;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object forward() throws Exception {
		Session session = new Session();
		session.setEmployee(this);
		session.fillSession();
		session.setGuidedTour(true);

		Login login = new Login();
		login.storeIntoServerSession(session);
		
		ModalWindow removeWindow = new ModalWindow();
		removeWindow.setId("subscribe");
		
		return new Object[]{new Remover(removeWindow, true), new Remover(new ModalWindow()), new ToOpener(new MainPanel(new Main(session)))};		
	}
}
