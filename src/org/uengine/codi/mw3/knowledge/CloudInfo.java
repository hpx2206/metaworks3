package org.uengine.codi.mw3.knowledge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.annotation.Id;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;

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
		
	String serverName;
		public String getServerName() {
			return serverName;
		}
		public void setServerName(String serverName) {
			this.serverName = serverName;
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
		
	String osTemplete;
		public String getOsTemplete() {
			return osTemplete;
		}
		public void setOsTemplete(String osTemplete) {
			this.osTemplete = osTemplete;
		}


	String hwTemplete;
		public String getHwTemplete() {
			return hwTemplete;
		}
		public void setHwTemplete(String hwTemplete) {
			this.hwTemplete = hwTemplete;
		}

	String serviceTemplete;
		public String getServiceTemplete() {
			return serviceTemplete;
		}
		public void setServiceTemplete(String serviceTemplete) {
			this.serviceTemplete = serviceTemplete;
		}
	
	
	Date moddate;
		public Date getModdate() {
			return moddate;
		}
		public void setModdate(Date moddate) {
			this.moddate = moddate;
		}
		
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

	String serverGroup;
		public String getServerGroup() {
			return serverGroup;
		}
		public void setServerGroup(String serverGroup) {
			this.serverGroup = serverGroup;
		}
		
	public CloudInfo(){
		setServerGroup("dev");	// default
	}
	public ICloudInfo findServerByProjectId(String projectId , String serverGroup) throws Exception{
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId ");
		selectbf.append(" and serverGroup = ?serverGroup");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.setServerGroup(serverGroup);
		dao.select();
		
		return dao;
	}
	
	public ICloudInfo findServerByServerName(String projectId, String serverName, String serverGroup) throws Exception{
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId");
		selectbf.append(" and serverName = ?serverName");
		selectbf.append(" and serverGroup = ?serverGroup");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.setServerName(serverName);
		dao.setServerGroup(serverGroup);
		dao.select();
		
		return dao;
	}
	
	public ICloudInfo findServerByServertId(String projectId, String serverId, String serverGroup) throws Exception{
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId");
		selectbf.append(" and serverId = ?serverId");
		selectbf.append(" and serverGroup = ?serverGroup");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.setServerId(serverId);
		dao.setServerGroup(serverGroup);
		dao.select();
		
		return dao;
	}
	
	public Long createNewId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("cloudinfo", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		return number.longValue();
	}
}
