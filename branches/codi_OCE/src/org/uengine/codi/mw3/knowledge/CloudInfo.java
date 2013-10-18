package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class CloudInfo extends Database<ICloudInfo> implements ICloudInfo{

	Long id;
	@Id
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
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
		
	String serverId;
		public String getServerId() {
			return serverId;
		}
		public void setServerId(String serverId) {
			this.serverId = serverId;
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
		
	public ICloudInfo findServerByProjectId(String projectId) throws Exception{
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId ");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.select();
		
		return dao;
	}
	
	public ICloudInfo findServerByServertId(String projectId, String serverId) throws Exception{
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId");
		selectbf.append(" and serverId = ?serverId");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.setServerId(serverId);
		dao.select();
		
		return dao;
	}
}
