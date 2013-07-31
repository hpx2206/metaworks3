package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="bpm_roledef")
public interface IRoleMappingDefinition extends IDAO{
	
	@Id
	public String getRoleDefId();
	public void setRoleDefId(String roleDefId);
	
	public String getRoleName();
	public void setRoleName(String roleName);
		
	public String getDefId();
	public void setDefId(String defId);

	public String getComCode();
	public void setComCode(String comCode);

	
	@ORMapping(databaseFields="mappedUserId", objectFields="userId")
	public IUser getMappedUser();
	public void setMappedUser(IUser mappedUser);
	
	@ServiceMethod(callByContent=true, mouseBinding="drop")
	public void drop();

}
