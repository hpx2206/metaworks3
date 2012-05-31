package org.uengine.codi.mw3.model;

import org.metaworks.dao.Database;

public class RoleMapping extends Database<IRoleMapping> implements IRoleMapping {

	Long roleMappingId;
	Long rootInstId;
	String roleName;
	String endpoint;

	public RoleMapping() {
		super();
	}

	public RoleMapping(Long roleMappingId, String roleName, String endpoint) {
		super();
		this.roleMappingId = roleMappingId;
		this.roleName = roleName;
		this.endpoint = endpoint;
	}

	@Override
	public Long getRoleMappingId() {
		return roleMappingId;
	}

	@Override
	public void setRoleMappingId(Long roleMappingId) {
		this.roleMappingId = roleMappingId;
	}

	@Override
	public Long getRootInstId() {
		return rootInstId;
	}

	@Override
	public void setRootInstId(Long rootInstId) {
		this.rootInstId = rootInstId;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getEndpoint() {
		return endpoint;
	}

	@Override
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void deleteByInfo() throws Exception {
		RoleMapping roleMapping = new RoleMapping();
		StringBuffer querry = new StringBuffer();
		querry.append("select * from bpm_rolemapping ");
		querry.append("where rootinstid=?rootInstId ");
		querry.append("and rolename=?roleName ");
		querry.append("and endpoint=?endpoint ");
		
		IRoleMapping findRoleMapping = (IRoleMapping)sql(querry.toString());
		findRoleMapping.setRootInstId(getRootInstId());
		findRoleMapping.setRoleName(getRoleName());
		findRoleMapping.setEndpoint(getEndpoint());
		findRoleMapping.select();
		if(findRoleMapping.next()) {
			roleMapping.setRoleMappingId(findRoleMapping.getRoleMappingId());
			roleMapping.deleteDatabaseMe();
		}
	}

}
