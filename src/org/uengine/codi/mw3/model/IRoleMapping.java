package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name = "BPM_ROLEMAPPING")
public interface IRoleMapping extends IDAO {

	@Id
	public Long getRoleMappingId();
	public void setRoleMappingId(Long roleMappingId);

	public Long getRootInstId();
	public void setRootInstId(Long rootInstId);

	public String getRoleName();
	public void setRoleName(String roleName);

	public String getEndpoint();
	public void setEndpoint(String endpoint);
}
