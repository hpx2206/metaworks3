package org.uengine.codi.mw3.knowledge;

import org.metaworks.dao.Database;
import org.metaworks.annotation.Id;

public class CloudInfo extends Database<ICloudInfo> implements ICloudInfo{

	String id;
	@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String serverInfo;
		public String getServerInfo() {
			return serverInfo;
		}
		public void setServerInfo(String serverInfo) {
			this.serverInfo = serverInfo;
		}
	
	String serverIp;
		public String getServerIp() {
			return serverIp;
		}
		public void setServerIp(String serverIp) {
			this.serverIp = serverIp;
		}

	String serverIpId;
		public String getServerIpId() {
			return serverIpId;
		}
		public void setServerIpId(String serverIpId) {
			this.serverIpId = serverIpId;
		}
	
	String rootId;
		public String getRootId() {
			return rootId;
		}
		public void setRootId(String rootId) {
			this.rootId = rootId;
		}

	String rootPwd;
		public String getRootPwd() {
			return rootPwd;
		}
		public void setRootPwd(String rootPwd) {
			this.rootPwd = rootPwd;
		}
}
