package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;

public class Role extends Database<IRole> implements IRole {
	
	public final static String ROLE = "role";
	
	@AutowiredFromClient
	public Session session;
	
	
	String roleCode;
		@Override
		public String getRoleCode() {
			return roleCode;
		}
		@Override
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
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

	MetaworksFile logoFile;
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}
	

	String comCode;
		@Override
		public String getComCode() {
			return comCode;
		}
		@Override
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}

	String descr;
		public String getDescr() {
			return descr;
		}
		public void setDescr(String descr) {
			this.descr = descr;
		}	

	String isDeleted;
		@Override
		public String getIsDeleted() {
			return isDeleted;
		}
		@Override
		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}
		
	boolean selected;	
		@Override
		public boolean isSelected() {
			return selected;
		}
		@Override
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
	EmployeeList employeeList;
		@Override
		public EmployeeList getEmployeeList() {
			return employeeList;
		}
		@Override
		public void setEmployeeList(EmployeeList employeeList) {
			this.employeeList = employeeList;
		}
		
	@Override
	public void drillDown() throws Exception {
		this.setSelected(!this.isSelected());
		
		EmployeeList employeeList = new EmployeeList();
		employeeList.setId("role_" + this.getRoleCode());
		employeeList.setMetaworksContext(this.getMetaworksContext());
		
		if (this.isSelected()) {
			IEmployee employee = new Employee();
			employee.setMetaworksContext(this.getMetaworksContext());
			employee.getMetaworksContext().setHow("tree");
			
			employeeList.setEmployee(employee.findByRole(this));
			
			setEmployeeList(employeeList);
		} else {
			setEmployeeList(employeeList);
		}
	}
		
	@Override
	public IRole findByGlobalCom() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM roleTable");
		sb.append(" WHERE comCode=?comCode");
		sb.append("   AND isDeleted=?isDeleted");
		
		IRole role = sql(sb.toString());
		role.setComCode(this.getComCode());
		role.setIsDeleted("0");
		role.select();
		role.setMetaworksContext(this.getMetaworksContext());
		
		return role;
	}
	
	@Override
	public Object subscribe() throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM roleUserTable");
		sb.append(" WHERE roleCode=?roleCode");		
		sb.append("   AND empCode=?empCode");
		
		IRoleUser roleUser = (IRoleUser)sql(IRoleUser.class, sb.toString());
		roleUser.setRoleCode(this.getRoleCode());
		roleUser.setEmpCode(session.getEmployee().getEmpCode());
		roleUser.select();
		
		if(roleUser.size() == 0){
			RoleUser insertRoleUser = new RoleUser();
			insertRoleUser.setRoleCode(this.getRoleCode());
			insertRoleUser.setEmpCode(session.getEmployee().getEmpCode());
			insertRoleUser.createDatabaseMe();
			insertRoleUser.flushDatabaseMe();
			
			Employee employee = new Employee();
			employee.setEmpCode(session.getEmployee().getEmpCode());
			employee.copyFrom(employee.databaseMe());
			employee.setMetaworksContext(this.getMetaworksContext());
			employee.getMetaworksContext().setHow("tree");
			
			EmployeeList employeeList = new EmployeeList();
			employeeList.setId("role_" + this.getRoleCode());
			
			return new ToAppend(employeeList, employee);
		}else{
			return null;
		}
	}
	
	@Override
	public Object[] saveMe() throws Exception {
		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0){			
			this.getLogoFile().upload();
		}
		
		if (getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_NEW)) {
			
			//역할 중복 검사
			Role role = new Role();
			role.setRoleCode(this.getRoleCode());
			IRole findRole = role.findByCode();
			
			if(findRole != null)
				throw new Exception("$DuplicateName");
			
			// 생성
			this.setComCode(session.getCompany().getComCode());
			this.setIsDeleted("0");
			
			
			this.getMetaworksContext().setWhere("navigator");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);				
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				this.setUrl(this.getLogoFile().getUploadedPath());
				this.setThumbnail(this.getLogoFile().getFilename());
			}
			
			createDatabaseMe();
			syncToDatabaseMe();
			
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleCode(roleCode);
			roleUser.setEmpCode(session.getEmployee().getEmpCode());
			roleUser.createDatabaseMe();
		} else {
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				this.setUrl(this.getLogoFile().getUploadedPath());
				this.setThumbnail(this.getLogoFile().getFilename());
			}
			
			syncToDatabaseMe();
			flushDatabaseMe();
		}
		
		return new Object[]{new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CHANGE), new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}

	@Override
	public Object[] removeMe() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getRoleCode());		
		role.copyFrom(role.databaseMe());
		role.setIsDeleted("1");
		
		role.syncToDatabaseMe();
		role.flushDatabaseMe();
		
		return new Object[]{new Refresh(new InstanceListPanel()), new Remover(role)};				
	}
	
	@Override
	public Popup editPopup() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getRoleCode());
		role.copyFrom(role.databaseMe());
		
		role.getMetaworksContext().setWhere("admin");
		role.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		role.setLogoFile(new MetaworksFile());
		
		Popup popup = new Popup();
		popup.setPanel(role);
		
		return popup;		
	}
	
	public IRole findByEmployee(IEmployee employee) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.*");
		sb.append("  FROM roleTable a, roleUserTable b");
		sb.append(" WHERE a.rolecode = b.rolecode");
		sb.append("   AND a.isDeleted=?isDeleted");
		sb.append("   AND b.empCode=?empCode");
		
		IRole role = sql(sb.toString());
		role.set("empCode", employee.getEmpCode());
		role.setIsDeleted("0");
		role.select();
		role.setMetaworksContext(this.getMetaworksContext());
		
		return role;		
	}
	
	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
		
		if(clipboard instanceof IUser){
			IUser user = (IUser) clipboard;
			
			IRoleUser alreadyExistChecker = (IRoleUser) Database.sql(IRoleUser.class, "select * from roleUserTable where empcode=?empcode and rolecode=?rolecode");
			alreadyExistChecker.setEmpCode(user.getUserId());
			alreadyExistChecker.setRoleCode(this.getRoleCode());
			alreadyExistChecker.select();
			if(alreadyExistChecker.next()){
				throw new Exception("The user is already existing");
			}
			
			try{
				RoleUser roleUser = new RoleUser();
				roleUser.setEmpCode(user.getUserId());
				roleUser.setRoleCode(this.getRoleCode());
				roleUser.createDatabaseMe();
				roleUser.flushDatabaseMe();
			}catch (Exception e) {
				System.out.println(e);
			}

			Role findRole = null;
			try{
				findRole = new Role();
				findRole.setRoleCode(this.getRoleCode());
				findRole.copyFrom(findRole.databaseMe());
				findRole.setMetaworksContext(this.getMetaworksContext());
				findRole.drillDown();
			}catch(Exception e){}
						
			if(findRole != null){
				return new Object[]{new Refresh(findRole)};
			}else{
				return null;
			}
		}
		session.setClipboard(null);
		return null;
	}
	
	@Override
	public Object[] loadRole() throws Exception{
		
		Locale locale = new Locale(session);
		locale.load();
		
		String title = locale.getString("$Role") + " - " + this.getDescr();
		
		InstanceListPanel instanceListPanel = Perspective.loadInstanceList(session, Perspective.MODE_ROLE, Perspective.TYPE_NEWSFEED, this.getRoleCode());
		instanceListPanel.setTitle(title);
		
		RoleInfo roleInfo = new RoleInfo(session);
		
		return new Object[]{session,  new ListPanel(instanceListPanel, roleInfo)};
	}
	
	
	public IRole findByCode() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from roleTable ");
		sb.append("where rolecode=?rolecode ");
		sb.append("and isDeleted='0'");
		
		IRole dao = null;
		
		try {
			dao = sql(sb.toString());
			dao.setRoleCode(this.getRoleCode());
			dao.select();
			
			if(!dao.next())
				dao = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao;
	}
}
