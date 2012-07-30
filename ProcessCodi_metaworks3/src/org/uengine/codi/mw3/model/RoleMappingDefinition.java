package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.knowledge.WfNode;

public class RoleMappingDefinition extends Database<IRoleMappingDefinition> implements IRoleMappingDefinition{
	
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
	
}
