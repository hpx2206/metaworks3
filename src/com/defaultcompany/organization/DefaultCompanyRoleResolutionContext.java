package com.defaultcompany.organization;

import java.util.Map;

import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleMapping;
import org.uengine.kernel.RoleResolutionContext;
import org.uengine.util.dao.ConnectiveDAO;
import org.uengine.util.dao.IDAO;

public class DefaultCompanyRoleResolutionContext extends RoleResolutionContext {

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	/*
	private static final String URL_RoleListXML_JSP = "/usermanager/roleListXML.jsp";
	private static final String URL_GroupListXML_JSP = "/usermanager/groupListXML.jsp";

	public static void metaworksCallback_changeMetadata(Type type){
		RoleResolutionContext.metaworksCallback_changeMetadata(type); //call the super's one first

		FieldDescriptor fd;
		
		fd = type.getFieldDescriptor("RoleId");	
		fd.setInputter(new XMLValueInput(URL_RoleListXML_JSP));
		
		fd = type.getFieldDescriptor("GroupId");	
		fd.setInputter(new XMLValueInput(URL_GroupListXML_JSP));
	}
	*/

	public RoleMapping getActualMapping(
		ProcessDefinition pd,
		ProcessInstance instance,
		String tracingTag,
		Map options)
		throws Exception {
		// TODO Auto-generated method stub
		
		RoleMapping rm = RoleMapping.create();
		
		IDAO roleUser = null;
//		try {
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
			
			if(roleUser != null)
			{
				while(roleUser.next()){
					String endpoint = roleUser.getString("empCode");
					
					rm.setDispatchingOption(Role.DISPATCHINGOPTION_AUTO);
					rm.setEndpoint(endpoint);
					rm.fill(instance);
					rm.moveToAdd();
				}
			}
//		} finally {
//			instance.getProcessTransactionContext().releaseResources();
//		}
//		
		return rm;
	}

	public String toString() {
		return getDisplayName();
	}

	public String getDisplayName() {
		StringBuilder dispName = new StringBuilder();
		
//		System.out.println("======== getDisplayName() ============");
//		if ( getReferenceRole() != null )
//		System.out.println("======== getReferenceRole().getName() : " + getReferenceRole().getName());
//		System.out.println("======== getRoleId() : " + getRoleId());
//		System.out.println("======== getGroupId() : " + getGroupId());
		
		if ( getRoleId() != null ) {
			if ( getGroupId() != null ){
				dispName.append("Member who is assigned as the role '"+getRoleId() + "' and in '" + getGroupId() + "' group.");
			} else if ( getReferenceRole() != null && getReferenceRole().getName() != null ) {
				dispName.append("Member who is assigned as the role '"+getRoleId() + "' and in '" + getReferenceRole().getDisplayName() + "'\'s group.");
			} else {
				dispName.append("Member who is assigned as the role '"+getRoleId() + "'");
			}
		} else {
			if ( getGroupId() != null ){
				dispName.append("Member who is in '" + getGroupId() + "' group.");
			} else if ( getReferenceRole() != null && getReferenceRole().getName() != null ) {
				dispName.append("Member who is in '" + getReferenceRole().getDisplayName() + "'\'s group.");
			} else {
				dispName.append("");
			}
		}
		
		return dispName.toString();
	}
	
	String roleId;
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String string) {
			roleId = string;
		}
		
	String groupId;
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
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


}