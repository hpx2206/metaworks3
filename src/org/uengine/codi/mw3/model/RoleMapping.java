package org.uengine.codi.mw3.model;

import org.metaworks.dao.Database;

public class RoleMapping extends Database<IRoleMapping> implements IRoleMapping {
	public static final String ROLEMAPPING_FOLLOWER_ROLENAME_FREFIX = "follower_";
	
	Long roleMappingId;
	Long rootInstId;
	String roleName;
	String endpoint;

	public RoleMapping() {
		super();
	}

	public RoleMapping(Long rootInstId, String roleName, String endpoint) {
		super();
		this.rootInstId = rootInstId;
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
	
	public boolean confirmFollower() throws Exception {
		StringBuffer querry = new StringBuffer();
		querry.append("select * from bpm_rolemapping ");
		querry.append("where rootinstid=?rootInstId ");
		querry.append("and rolename=?roleName ");
		querry.append("and endpoint=?endpoint ");
		
//		System.out.println(getRootInstId() + "," + getRoleName() + "," + getEndpoint());
		
		IRoleMapping findRoleMapping = (IRoleMapping)sql(querry.toString());
		
		findRoleMapping.setRootInstId(getRootInstId());
		findRoleMapping.setRoleName(getRoleName());
		findRoleMapping.setEndpoint(getEndpoint());
		findRoleMapping.select();
		if(findRoleMapping.next()) {
			return true;
		} else {
			return false;
		}		
	}
	
	public boolean deleteByInfo(Session session) throws Exception {
		RoleMapping roleMapping = new RoleMapping();
		StringBuffer querry = new StringBuffer();
		querry.append("select * from bpm_rolemapping ");
		querry.append("where rootinstid=?rootInstId ");
		querry.append("and rolename=?roleName ");
		querry.append("and endpoint=?endpoint ");
		
//		System.out.println(getRootInstId() + "," + getRoleName() + "," + getEndpoint());
		
		IRoleMapping findRoleMapping = (IRoleMapping)sql(querry.toString());
		
		findRoleMapping.setRootInstId(getRootInstId());
		findRoleMapping.setRoleName(getRoleName());
		findRoleMapping.setEndpoint(getEndpoint());
		findRoleMapping.select();
		if(findRoleMapping.next()) {
			roleMapping.setRoleMappingId(findRoleMapping.getRoleMappingId());
			roleMapping.deleteDatabaseMe();
			return true;
		} else {
			return false;
		}
	}
	
	public static IRoleMapping allFollower(Long instId) throws Exception{
		StringBuffer str = new StringBuffer();
		// rolomapping 테이블에서  follower 를 가져올 경우에 instId 와 endpoint가 동일한 결과를 가져올수 있다
		// 그리하여 group by 로 묶어서 중복된 데이터를 제거함
		str.append(" select * from ( ")
			.append("	select * from bpm_rolemapping where rootinstid=?rootInstId " )
			.append(" ) rolemapping ")
			.append(" group by rolemapping.endpoint ");
		
		IRoleMapping findRoleMapping = (IRoleMapping) Database.sql(IRoleMapping.class, str.toString());
		findRoleMapping.set("rootInstId", instId);
		findRoleMapping.select();
		
		return findRoleMapping;
	}

}
