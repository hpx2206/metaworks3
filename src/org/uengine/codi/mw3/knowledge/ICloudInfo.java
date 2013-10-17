package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="cloudinfo")
public interface ICloudInfo extends IDAO{

	@Id
	public String getId();
	public void setId(String id);
	
	public String getServerInfo();
	public void setServerInfo(String serverInfo);

	public String getServerIp();
	public void setServerIp(String serverIp);

	public String getServerIpId();
	public void setServerIpId(String serverIpId);

	public String getRootId();
	public void setRootId(String rootId);

	public String getRootPwd();
	public void setRootPwd(String rootPwd);
}
