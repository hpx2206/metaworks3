package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.knowledge.WfNode;

public class RoleMappingDefinition extends Database<IRoleMappingDefinition> implements IRoleMappingDefinition{
	
	public static final String ROLE_DEF_TYPE_ROLE = "role";  
	public static final String ROLE_DEF_TYPE_USER = "user";  
	
	String roleDefId;		
		public String getRoleDefId() {
			return roleDefId;
		}
		public void setRoleDefId(String roleDefId) {
			this.roleDefId = roleDefId;
		}

	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	IUser mappedUser;
		public IUser getMappedUser() {
			return mappedUser;
		}
		public void setMappedUser(IUser mappedUser) {
			this.mappedUser = mappedUser;
		}

	String comCode;
		public String getComCode() {
			return comCode;
		}
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}
		
	String mappedRoleCode;
		public String getMappedRoleCode() {
			return mappedRoleCode;
		}
		public void setMappedRoleCode(String mappedRoleCode) {
			this.mappedRoleCode = mappedRoleCode;
		}
		
	String roleDefType;
		public String getRoleDefType() {
			return roleDefType;
		}
		public void setRoleDefType(String roleDefType) {
			this.roleDefType = roleDefType;
		}

	@AutowiredFromClient
	public Session session;
	
	@Override
	public void drop() {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IUser){
			
			IUser user = (IUser) clipboard;
			
			setMappedUser(user);
		}	
	
	}
	
	public RoleMappingDefinition findRoleMappingDefinition(String roledefId, String comCode) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM bpm_roledef ");
		sb.append("  where roledefId=?roledefId ");
		sb.append("  and comCode=?comCode ");

		IRoleMappingDefinition roleMappingDefinition = (IRoleMappingDefinition)sql(IRoleMappingDefinition.class, sb.toString());
		roleMappingDefinition.setRoleDefId(roledefId);
		roleMappingDefinition.setComCode(comCode);
		roleMappingDefinition.select();
		
		if( roleMappingDefinition.next() ){
			RoleMappingDefinition roleDef = new RoleMappingDefinition();
			roleDef.copyFrom(roleMappingDefinition);
			return roleDef;
		}else{
			return null;
		}
		
	}
	
}
