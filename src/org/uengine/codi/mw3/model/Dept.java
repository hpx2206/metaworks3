package org.uengine.codi.mw3.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.AdminEastPanel;
import org.uengine.codi.mw3.knowledge.TopicPanel;
import org.uengine.processmanager.ProcessManagerRemote;

public class Dept extends Database<IDept> implements IDept {
	String partCode;
	String partName;
	String parentPartCode;
	String isDeleted;
	String description;
//	String comCode;
	
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

	String thumbnail;
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
	
	public Dept(String partCode){
		this.partCode = partCode;
	}
	
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

	MetaworksFile logoFile;
	public MetaworksFile getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(MetaworksFile logoFile) {
		this.logoFile = logoFile;
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

	
	public IDept findByCode() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from partTable ");
		sb.append("where partcode=?partcode");
		
		IDept dao = null;
		
		try {
			dao = sql(sb.toString());
			dao.setPartCode(this.getPartCode());
			dao.select();
			
			if(!dao.next())
				dao = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao;
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
		/*setSelected(!getSelected());
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
				if( "addContact".equals(this.getMetaworksContext().getWhere()) ){
					// TODO 이렇게 분기하는게 좋은 코드는 아니지만 우선 적용함 - 2/17 김형국
					employee.getMetaworksContext().setWhere("addContact");
				}else{
					employee.getMetaworksContext().setWhere("navigator");
				}
				
				EmployeeList employeeList = new EmployeeList();			
				employeeList.setMetaworksContext(this.getMetaworksContext());
				employeeList.setId(this.getPartCode());
				employeeList.setEmployee(employee.findByDept(this));
				
				setDeptEmployee(employeeList);
			}
		}*/
	}
		
	public Object[] loadDeptList() throws Exception {
		return Perspective.loadInstanceListPanel(session, "organization.group", getPartCode(), "부서 : " + this.getPartName());
	}

	@Override
	public Popup editDeptInfo() throws Exception {
		Dept dept = new Dept();
		dept.setPartCode(this.getPartCode());
		dept.copyFrom(dept.databaseMe());
		
		dept.getMetaworksContext().setWhere("admin");
		dept.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		dept.setLogoFile(new MetaworksFile());
		
		Popup popup = new Popup();
		popup.setPanel(dept);
		
		return popup;		
	}

	@AutowiredFromClient
	public Session session;

	@Override
	public Object[] saveDeptInfo() throws Exception {

		this.getMetaworksContext().setHow("tree");
		
		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0){			
			this.getLogoFile().upload();
		}
		
				
		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			//그룹 중복 검사
			Dept dept = new Dept();
			dept.setPartCode(this.getPartName());
			IDept findDept = dept.findByCode();
			
			if(findDept != null)
				throw new Exception("$DuplicateName");
			
			// �앹꽦
			this.setPartCode(this.getPartName());
			this.setGlobalCom(session.getCompany().getComCode());
			this.setIsDeleted("0");		
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				this.setUrl(this.getLogoFile().getUploadedPath());
				this.setThumbnail(this.getLogoFile().getFilename());
			}
			createDatabaseMe();
			syncToDatabaseMe();			

			DeptList deptList = new DeptList();			
			if(this.getParent_PartCode() == null)				
				deptList.setId("/ROOT/");
			else
				deptList.setId(this.getParent_PartCode());
			
			//deptFollow에 dept생성자 추가.
			Employee emp = new Employee();
			emp.setEmpCode(session.getEmployee().getEmpCode());
			emp.copyFrom(emp.databaseMe());
			emp.setPartCode(this.getPartCode());
			emp.syncToDatabaseMe();
			emp.flushDatabaseMe();
			
//			this.notiToCompany();
//			return new Object[]{new Remover(new Popup()), new ToAppend(deptList, this)};
			
			Object[] returnObj = this.loadDeptList();
			Object[] returnObject = new Object[ returnObj.length + 2];
			for (int i = 0; i < returnObj.length; i++) {
				if( returnObj[i] instanceof InstanceListPanel){
					returnObject[i] = new Refresh(returnObj[i]);
				}else{
					returnObject[i] = new Refresh(returnObj[i]);
				}			
			}
			returnObject[returnObj.length] = new ToAppend(deptList, this);
			returnObject[returnObj.length+1] = new Remover(new Popup());
			return returnObject;

//			return null;
			
			//drillDown
			
			//return new Object[] { returnValueDept, resultSaveDeptPanel };
			
			//returnValueDept = findRefreshableParentDept();
		} else {
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				this.setUrl(this.getLogoFile().getUploadedPath());
				this.setThumbnail(this.getLogoFile().getFilename());
			}
			syncToDatabaseMe();
			flushDatabaseMe();
			
			return new Object[]{new Remover(new Popup()), new Remover(new ModalWindow()), new Refresh(this)};
		}
		//}
		// TODO execute drillDown()
		// returnValueDept.drillDown();
		
		//return new Object[] { returnValueDept, resultSaveDeptPanel };
	}
	
	public void notiToCompany() throws Exception{
		Notification noti = new Notification();
		INotiSetting notiSetting = new NotiSetting();
		Instance instance = new Instance();
		noti.session = session;
		instance = this.createWorkItem();
		
		Employee employee = new Employee();
		employee.setEmpCode(session.getEmployee().getEmpCode());
		employee.copyFrom(employee.databaseMe());
		IEmployee findResult = employee.findByGlobalCom(employee.getGlobalCom());
		INotiSetting findNotiSetting;
		Employee codi = new Employee();
		codi.setEmpCode("0");
		
		while(findResult.next()){
			findNotiSetting = notiSetting.findByUserId(findResult.getEmpCode());
			if(findNotiSetting.next()){
				if(findNotiSetting.isModiOrgan()){
					noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
					noti.setUserId(findResult.getEmpCode());
					noti.setActorId(session.getEmployee().getEmpName());
					noti.setConfirm(false);
					noti.setInstId(instance.getInstId());
					noti.setInputDate(Calendar.getInstance().getTime());
					if(MetaworksContext.WHEN_VIEW.equals(this.getMetaworksContext().getWhen())){
						noti.setActAbstract(session.getUser().getName() + "님이 부서 " + this.getPartName() + "를 생성하였습니다.");
					}
					else if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
						noti.setActAbstract(session.getUser().getName() + "님이  " + this.getPartName() + " 부서를 제거하였습니다.");
					}
					
		
					//워크아이템에서 노티를 추가할때와 동일한 로직을 수행하도록 변경
			//			noti.createDatabaseMe();
			//			noti.flushDatabaseMe();
					
					noti.add(instance);
				
					String followerSessionId = Login.getSessionIdWithUserId(employee.getEmpCode());
					
					try{
						//NEW WAY IS GOOD
						Browser.withSession(followerSessionId, new Runnable(){
			
							@Override
							public void run() {
								//refresh notification badge
								ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
							}
							
						});
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}				
	}
	
	public Instance createWorkItem() throws Exception{
		Employee representiveMailEmp = new Employee();
		representiveMailEmp.setEmpCode("0");
		
		
		IEmployee repMailEmp = representiveMailEmp.findMe();
		
		
		SystemWorkItem comment = new SystemWorkItem();
		comment.processManager = processManager;
		comment.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		User theFirstWriter;
		theFirstWriter = new User();
		theFirstWriter.setName(repMailEmp.getEmpName());
		
		comment.setWriter(theFirstWriter);
		if(MetaworksContext.WHEN_VIEW.equals(this.getMetaworksContext().getWhen())){
			comment.setSystemMessage(session.getUser().getName() + "님이 부서 " + this.getPartName() + "를 생성하였습니다.");
		}
		else if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			comment.setSystemMessage(session.getUser().getName() + "님이  " + this.getPartName() + " 부서를 제거하였습니다.");
		}
		
		comment.setStartDate(new Date());
		
		comment.session = session;
		comment.add();
		
		Instance instance = new Instance();
		instance.setInstId(comment.getInstId());
		instance.copyFrom(instance.databaseMe());
		
		return instance;
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
		this.setPartName(dept.getPartName());
		
/*		if( (this.getChildren() != null && this.getChildren().size() > 0) || (this.getDeptEmployee() != null && this.getDeptEmployee().size() > 0))
			throw new Exception("�섏쐞 �몃뱶媛�議댁옱�섎㈃ ��젣�����놁뒿�덈떎.");*/
				
		dept.syncToDatabaseMe();
		dept.flushDatabaseMe();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		//this.notiToCompany();
		
		return new Object[]{new Refresh(new InstanceListPanel()), new Remover(dept)};		
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
		newDept.setLogoFile(new MetaworksFile());
		
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
		
		dept.getMetaworksContext().setHow("picker");
		dept.getMetaworksContext().setWhen("edit");
		
		return new Object[] {new ToOpener(dept), new Remover(new Popup())};
	}
	
	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
			IEmployee employeeInClipboard = (IEmployee) clipboard;
			
			Employee locatorForEmployeeInClipboard = new Employee();
			locatorForEmployeeInClipboard.setEmpCode(employeeInClipboard.getEmpCode());
			
			locatorForEmployeeInClipboard.databaseMe().setPartCode(this.getPartCode());
			locatorForEmployeeInClipboard.flushDatabaseMe();
			
			return new Object[]{new Remover(employeeInClipboard)};
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}
