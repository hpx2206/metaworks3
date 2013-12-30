package org.uengine.codi.mw3.project.oce;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="serverInfo")
public interface IServerInfo extends IDAO {

	public static final String SERVER_TYPE_KT = "kt"; 
	public static final String SERVER_TYPE_OPENSTACK = "openstack"; 
	public static final String SERVER_TYPE_AMAJON = "amajon"; 
	
	@Id
	public String getVmName();
	public void setVmName(String vmName);
	
	public String getIaasServerType();
	public void setIaasServerType(String iaasServerType);
	
	public String getJobId();
	public void setJobId(String jobId);
	
	public String getTempId();
	public void setTempId(String tempId);
	
	public String getCommand();
	public void setCommand(String command);
	
	public String getVmIp();
	public void setVmIp(String vmIp);
	
	public String getVmOutsideIp();
	public void setVmOutsideIp(String vmOutsideIp);
		
	public String getVmId();
	public void setVmId(String vmId);
	
	public String getVmPassword();
	public void setVmPassword(String vmPassword);
	
	public String getStatus();
	public void setStatus(String status);
	
}
