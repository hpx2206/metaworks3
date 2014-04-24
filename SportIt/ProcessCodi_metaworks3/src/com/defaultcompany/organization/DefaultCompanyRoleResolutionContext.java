package com.defaultcompany.organization;

import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.GroupSelector;
import org.uengine.codi.mw3.webProcessDesigner.RoleSelector;
import org.uengine.codi.mw3.webProcessDesigner.UserSelector;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleMapping;
import org.uengine.kernel.RoleResolutionContext;
import org.uengine.util.dao.ConnectiveDAO;
import org.uengine.util.dao.IDAO;

public class DefaultCompanyRoleResolutionContext extends RoleResolutionContext implements ContextAware {

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public DefaultCompanyRoleResolutionContext(){
		userSelector = new UserSelector();
		groupSelector = new GroupSelector();
		roleSelector = new RoleSelector();
	}

	public RoleMapping getActualMapping(
		ProcessDefinition pd,
		ProcessInstance instance,
		String tracingTag,
		Map options)
		throws Exception {
		// TODO Auto-generated method stub
		
		RoleMapping rm = RoleMapping.create();
		
		IDAO roleUser = null;
		if ( getRoleId() != null ) {
			if ( getGroupId() != null  && !"".equals(getGroupId())){ 
				roleUser = ConnectiveDAO.createDAOImpl(
						instance.getProcessTransactionContext(), 
						"select R.EMPCODE from emptable E, PARTTABLE P, ROLEUSERTABLE R where E.ISDELETED='0' and P.PARTCODE = ?groupCode and E.PARTCODE=P.PARTCODE and R.ROLECODE = ?roleCode and R.EMPCODE = E.EMPCODE ",
						IDAO.class
				);
				
				roleUser.set("roleCode", getRoleId());
				roleUser.set("groupCode", getGroupId());
				roleUser.select();
			} else if ( getReferenceRole() != null && getReferenceRole().getMapping(instance) != null ) {
				roleUser = ConnectiveDAO.createDAOImpl(
						instance.getProcessTransactionContext(), 
						"select R.EMPCODE from emptable E, (select PARTCODE, EMPCODE from emptable where EMPCODE= ?empCode) T, ROLEUSERTABLE R where E.ISDELETED='0' and E.PARTCODE =  T.PARTCODE and R.ROLECODE = ?roleCode and R.EMPCODE = E.EMPCODE ",
						IDAO.class
				);
				
				roleUser.set("empCode", getReferenceRole().getMapping(instance).getEndpoint());
				roleUser.set("roleCode", getRoleId());
				roleUser.select();
			} else {
				roleUser = ConnectiveDAO.createDAOImpl(
						instance.getProcessTransactionContext(), 
						" SELECT RU.EMPCODE FROM ROLEUSERTABLE RU " +
						" 	INNER JOIN emptable EMP ON EMP.EMPCODE = RU.EMPCODE" +
						"		AND EMP.ISDELETED='0' " +
						" WHERE ROLECODE = ?roleCode",
						IDAO.class
				);
				
				roleUser.set("roleCode", getRoleId());
				roleUser.select();
			}
		} else {
			if ( getGroupId() != null ){
				roleUser = ConnectiveDAO.createDAOImpl(
						instance.getProcessTransactionContext(), 
						"select EMPCODE from emptable where ISDELETED='0' and PARTCODE =  ?PARTCODE ",
						IDAO.class
				);
				
				roleUser.set("PARTCODE", getGroupId());
				roleUser.select();
			} else if ( getReferenceRole() != null && getReferenceRole().getMapping(instance) != null ) {
				roleUser = ConnectiveDAO.createDAOImpl(
						instance.getProcessTransactionContext(), 
						"select E.EMPCODE from emptable E, (select PARTCODE, EMPCODE from emptable where EMPCODE= ?empCode) T where E.ISDELETED='0' and E.PARTCODE =  T.PARTCODE",
						IDAO.class
				);
				
				roleUser.set("empCode", getReferenceRole().getMapping(instance).getEndpoint());
				roleUser.select();
			} else {
				
			}
		}
			
		if(roleUser != null){
			while(roleUser.next()){
				String endpoint = roleUser.getString("empCode");
				
				rm.setDispatchingOption(Role.DISPATCHINGOPTION_AUTO);
				rm.setEndpoint(endpoint);
				rm.fill(instance);
				rm.moveToAdd();
			}
		}else{
			if( getUserId() != null ){	// directmapping
				rm.setDispatchingOption(Role.DISPATCHINGOPTION_AUTO);
				rm.setEndpoint(getUserId());
				rm.fill(instance);
				rm.moveToAdd();
			} else {
				rm = null;
			}
		}
		return rm;
	}

	public String toString() {
		return getDisplayName();
	}
	
	String displayName;
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public String getDisplayName() {
			if( "error".equals(displayName)){
				return displayName;
			}
			
			StringBuilder dispName = new StringBuilder();
			if( getUserId() != null ){
				dispName.append("Member who is  '"+getUserName() + "' ");
			}else if ( getRoleId() != null ) {
				if ( getGroupId() != null ){
					dispName.append("Member who is assigned as the role '"+getRoleName() + "' and in '" + getGroupName() + "' group.");
				} else if ( getReferenceRole() != null && getReferenceRole().getName() != null ) {
					dispName.append("Member who is assigned as the role '"+getRoleName() + "' and in '" + getReferenceRole().getDisplayName() + "'\'s group.");
				} else {
					dispName.append("Member who is assigned as the role '"+getRoleName() + "'");
				}
			} else {
				if ( getGroupId() != null ){
					dispName.append("Member who is in '" + getGroupName() + "' group.");
				} else if ( getReferenceRole() != null && getReferenceRole().getName() != null ) {
					dispName.append("Member who is in '" + getReferenceRole().getDisplayName() + "'\'s group.");
				} else {
					dispName.append("");
				}
			}
			
			return dispName.toString();
		}
		
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}

	String roleId;
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String string) {
			roleId = string;
		}
	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

	String groupId;
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
	String groupName;
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

	Role relativeRole;
		public Role getReferenceRole() {
			return relativeRole;
		}
		public void setRelativeRole(Role referenceRole) {
			this.relativeRole = referenceRole;
		}
		
	public String getName() {
		return "By Role";
	}

	public String[] getDispatchingParameters() {
		return new String[]{getRoleId(), null};
	}

	public int getDispatchingOption() {
		// TODO Auto-generated method stub
		return Role.DISPATCHINGOPTION_RACING;
	}
	transient UserSelector userSelector;
		public UserSelector getUserSelector() {
			return userSelector;
		}
		public void setUserSelector(UserSelector userSelector) {
			this.userSelector = userSelector;
		}

	transient RoleSelector roleSelector;
		public RoleSelector getRoleSelector() {
			return roleSelector;
		}
		public void setRoleSelector(RoleSelector roleSelector) {
			this.roleSelector = roleSelector;
		}

	transient GroupSelector groupSelector;
		public GroupSelector getGroupSelector() {
			return groupSelector;
		}
		public void setGroupSelector(GroupSelector groupSelector) {
			this.groupSelector = groupSelector;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] select(){
		if( "error".equals(this.getDisplayName())){
			return null;
		}else{
			return new Object[]{new ToOpener(this), new Remover(new ModalWindow())};
		}
	}
	
	@Override
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openPicker() throws Exception{
		if( userName != null ){
			userSelector.setName(userName);
		}else if( roleName != null ){
			roleSelector.setName(roleName);
		}else if( groupName != null ){
			groupSelector.setName(groupName);
		}
		
		ModalWindow popup = new ModalWindow();
		popup.setHeight(400);
		popup.setWidth(600);
		popup.setTitle("역할선택");
		popup.setPanel(this);
		
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setWhen(metaworksContext.WHEN_EDIT);
		metaworksContext.setHow("pick");
		popup.setMetaworksContext(metaworksContext);
		
		return popup;
	}
		
}