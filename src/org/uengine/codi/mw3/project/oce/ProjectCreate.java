package org.uengine.codi.mw3.project.oce;

import java.util.Date;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.codi.mw3.project.oce.KtProjectServers;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ProjectCreate implements ITool {
	
	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
	String projectName; 
	@Hidden
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}	
	String serviceTemplete;
	@Face(displayName="$project.server.serviceTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"KT_KOR-Central A"}, values={"KT_KOR-Central A"})
		public String getServiceTemplete() {
			return serviceTemplete;
		}
		public void setServiceTemplete(String serviceTemplete) {
			this.serviceTemplete = serviceTemplete;
		}
		
	String osTemplete;
	@Face(displayName="$project.server.osTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
			options={"Centos 6.3 64bit_jboss",
						"Centos 6.3 64bit_cubrid",
						"Centos 6.3 64bit_jboss_cubird",
						"Ubuntu 12.04 64bit_jboss",
						"Ubuntu 12.04 64bit_cubrid",
						"Ubuntu 12.04 64bit_jboss_cubird",
						"WIN 2008 R2 64bit [Korean]_jboss",
						"WIN 2008 R2 64bit [Korean]-cubrid",
						"WIN 2008 R2 64bit [Korean]_jboss_cubird"}, 
			values={"Centos 6.3 64bit_jboss",
						"Centos 6.3 64bit_cubrid",
						"Centos 6.3 64bit_jboss_cubird",
						"Ubuntu 12.04 64bit_jboss",
						"Ubuntu 12.04 64bit_cubrid",
						"Ubuntu 12.04 64bit_jboss_cubird",
						"WIN 2008 R2 64bit [Korean]_jboss",
						"WIN 2008 R2 64bit [Korean]-cubrid",
						"WIN 2008 R2 64bit [Korean]_jboss_cubird"})
		public String getOsTemplete() {
			return osTemplete;
		}
		public void setOsTemplete(String osTemplete) {
			this.osTemplete = osTemplete;
		}

	String hwTemplete;
	@Face(displayName="$project.server.hwTemplete", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", 
			options={"1 vCore	1 GB	100GB",
						"1 vCore	2 GB	100GB",
						"2 vCore	2 GB	100GB",
						"2 vCore	4 GB	100GB",
						"4 vCore	4 GB	100GB",
						"4 vCore	8 GB	100GB",
						"8 vCore	8 GB	100GB",
						"8 vCore	16 GB	100GB",
						"12 vCore	16 GB	100GB"}, 
			values={"1 vCore	1 GB	100GB",
						"1 vCore	2 GB	100GB",
						"2 vCore	2 GB	100GB",
						"2 vCore	4 GB	100GB",
						"4 vCore	4 GB	100GB",
						"4 vCore	8 GB	100GB",
						"8 vCore	8 GB	100GB",
						"8 vCore	16 GB	100GB",
						"12 vCore	16 GB	100GB"})
		public String getHwTemplete() {
			return hwTemplete;
		}
		public void setHwTemplete(String hwTemplete) {
			this.hwTemplete = hwTemplete;
		}

	

	@Autowired
	public ProcessManagerRemote processManager ; 	
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		CloudInfo cloudInfo = new CloudInfo();
		cloudInfo.setId(cloudInfo.createNewId());
		cloudInfo.setProjectId(projectId);
		cloudInfo.setServerName(this.getProjectName());
		cloudInfo.setOsTemplete(this.getOsTemplete());
		cloudInfo.setHwTemplete(this.getHwTemplete());
		cloudInfo.setServiceTemplete(this.getServiceTemplete());
		cloudInfo.setModdate(new Date());
		cloudInfo.setServerGroup(KtProjectServers.SERVER_DEV);
		cloudInfo.setStatus(ProjectServer.SERVER_STATUS_STARTING);
		cloudInfo.createDatabaseMe();
		
		cloudInfo.flushDatabaseMe();
	}

}
