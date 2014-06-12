package org.uengine.codi.mw3.project.oce;

import org.metaworks.dao.Database;

public class ServerInfo extends Database<IServerInfo> implements IServerInfo {
		
	String vmName;
		public String getVmName() {
			return vmName;
		}
		public void setVmName(String vmName) {
			this.vmName = vmName;
		}
		
	String iaasServerType;
		public String getIaasServerType() {
			return iaasServerType;
		}
		public void setIaasServerType(String iaasServerType) {
			this.iaasServerType = iaasServerType;
		}

	String jobId;
		public String getJobId() {
			return jobId;
		}
		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
		
	String tempId;
		public String getTempId() {
			return tempId;
		}
		public void setTempId(String tempId) {
			this.tempId = tempId;
		}

	String command;
		public String getCommand() {
			return command;
		}
		public void setCommand(String command) {
			this.command = command;
		}

	String vmIp;
		public String getVmIp() {
			return vmIp;
		}
		public void setVmIp(String vmIp) {
			this.vmIp = vmIp;
		}
		
	String vmOutsideIp;
		public String getVmOutsideIp() {
			return vmOutsideIp;
		}
		public void setVmOutsideIp(String vmOutsideIp) {
			this.vmOutsideIp = vmOutsideIp;
		}

	String vmId;
		public String getVmId() {
			return vmId;
		}
		public void setVmId(String vmId) {
			this.vmId = vmId;
		}
		
	String vmPassword;
		public String getVmPassword() {
			return vmPassword;
		}
		public void setVmPassword(String vmPassword) {
			this.vmPassword = vmPassword;
		}
		
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	public IaaSConnectionFectory connectServer() throws Exception{
		IaaSConnectionFectory iaaSConnectionFectory = null;

		IServerInfo serverInfo = this.databaseMe();
		this.copyFrom(serverInfo);
		
		if(IServerInfo.SERVER_TYPE_KT.equals(this.getIaasServerType())){
			iaaSConnectionFectory = new KTConnectionFectory();
		}
		
		if( iaaSConnectionFectory != null ){
			iaaSConnectionFectory.setServerInfo(this);
		}
		return iaaSConnectionFectory;
	}
}
