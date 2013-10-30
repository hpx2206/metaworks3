package org.uengine.codi.mw3.project.oce;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"projectIp,serviceTemplete,osTemplete,hwTemplete"})
public class ProjectInfoForm implements ITool {

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
	
	String projectIp;
	@Face(displayName="생성된 IP 정보")
		public String getProjectIp() {
			return projectIp;
		}
		public void setProjectIp(String projectIp) {
			this.projectIp = projectIp;
		}
	String serviceTemplete;
	@Face(displayName="$project.server.serviceTemplete")
		public String getServiceTemplete() {
			return serviceTemplete;
		}
		public void setServiceTemplete(String serviceTemplete) {
			this.serviceTemplete = serviceTemplete;
		}
		
	String osTemplete;
	@Face(displayName="$project.server.osTemplete")
		public String getOsTemplete() {
			return osTemplete;
		}
		public void setOsTemplete(String osTemplete) {
			this.osTemplete = osTemplete;
		}

	String hwTemplete;
	@Face(displayName="$project.server.hwTemplete")
		public String getHwTemplete() {
			return hwTemplete;
		}
		public void setHwTemplete(String hwTemplete) {
			this.hwTemplete = hwTemplete;
		}
		
	public ProjectInfoForm(){
		
	}
	
	@Override
	public void onLoad() throws Exception {
//		System.out.println("ProjectInfoForm , projectId = " + projectId);
//		System.out.println("ProjectInfoForm , projectName = " + projectName);
		CloudInfo cloudInfo = new CloudInfo();
		ICloudInfo iCInfo = cloudInfo.findServerByServerName(projectId , projectName , KtProjectServers.SERVER_DEV);
		if( iCInfo.next() ){
			setProjectIp(cloudInfo.getServerIp());
			setOsTemplete(cloudInfo.getOsTemplete());
			setHwTemplete(cloudInfo.getHwTemplete());
			setServiceTemplete(cloudInfo.getServiceTemplete());
		}else{
			
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		
	}
}
