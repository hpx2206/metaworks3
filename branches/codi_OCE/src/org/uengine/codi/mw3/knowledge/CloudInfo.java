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
		
	String osType;
		public String getOsType() {
			return osType;
		}
		public void setOsType(String osType) {
			this.osType = osType;
		}
	String wasType;
		public String getWasType() {
			return wasType;
		}
		public void setWasType(String wasType) {
			this.wasType = wasType;
		}
	String dbType;
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
		}
	
	
	Date moddate;
		public Date getModdate() {
			return moddate;
		}
		public void setModdate(Date moddate) {
			this.moddate = moddate;
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
	
	public ICloudInfo findServerByServerName(String projectId, String serverName) throws Exception{
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from cloudinfo ");
		selectbf.append(" where projectId = ?projectId");
		selectbf.append(" and serverName = ?serverName");
		
		ICloudInfo dao = (ICloudInfo) sql(ICloudInfo.class,	selectbf.toString());
		dao.setProjectId(projectId);
		dao.setServerName(serverName);
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
