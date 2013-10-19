package org.uengine.codi.mw3.knowledge;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="cloudinfo")
public interface ICloudInfo extends IDAO{

	@Id
	public Long getId();
	public void setId(Long id);
	
	public String getProjectId();
	public void setProjectId(String projectId);
	
	public String getServerName();
	public void setServerName(String serverName);
	
	public String getServerInfo();
	public void setServerInfo(String serverInfo);

	public String getServerIp();
	public void setServerIp(String serverIp);

	public String getServerId();
	public void setServerId(String serverId);
	
	public String getServerIpId();
	public void setServerIpId(String serverIpId);

	public String getRootId();
	public void setRootId(String rootId);

	public String getRootPwd();
	public void setRootPwd(String rootPwd);
	
	public String getOsType();
	public void setOsType(String osType);
	
	public String getWasType();
	public void setWasType(String wasType);
	
	public String getDbType() ;
	public void setDbType(String dbType);
	
	public Date getModdate();
	public void setModdate(Date moddate);
	
}
