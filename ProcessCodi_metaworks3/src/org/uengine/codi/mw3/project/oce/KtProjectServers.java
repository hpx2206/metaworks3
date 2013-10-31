package org.uengine.codi.mw3.project.oce;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"hideViewBox", "methodVAlign"}, values={"true", "top"})
public class KtProjectServers implements ContextAware{
	
	public static final String SERVER_DEV = "dev";
	public static final String SERVER_PROB = "prob";

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String projectId;
		@Id
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String serverGroup;
		@Hidden
		public String getServerGroup() {
			return serverGroup;
		}
		public void setServerGroup(String serverGroup) {
			this.serverGroup = serverGroup;
		}
		
	KtProjectServer[] serverList;
	@Face(options={"hideLabel"}, values={"true"})
	@Available(when=KtProjectServers.SERVER_DEV)
		public KtProjectServer[] getServerList() {
			return serverList;
		}
		public void setServerList(KtProjectServer[] serverList) {
			this.serverList = serverList;
		}
		
	public KtProjectServers(){
		this(null, SERVER_DEV);
	}
	
	public KtProjectServers(String projectId, String serverGroup){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(SERVER_DEV);
		
		this.setProjectId(projectId);
		this.setServerList(new KtProjectServer[0]);
		this.setServerGroup(serverGroup);
	}
	
	public void loadOceServer() throws Exception{
		this.setProjectId(projectId);
		if( this.getServerGroup() == null || "".equals(this.getServerGroup())){
			this.setServerGroup(SERVER_DEV);	// default
		}
		
		ArrayList<KtProjectServer> serverList = new ArrayList<KtProjectServer>();
		
		CloudInfo cloudInfo = new CloudInfo();
		ICloudInfo iCloudInfo = cloudInfo.findServerByProjectId(projectId , serverGroup);
		while(iCloudInfo.next()){
			CloudInfo cInfo = new CloudInfo();
			cInfo.copyFrom(iCloudInfo);
			KtProjectServer server = new KtProjectServer();
			server.setId(cInfo.getId()+"");
			server.setProjectId(cInfo.getProjectId());
			server.setName(cInfo.getServerName());
			server.setIp(cInfo.getServerIp());
			server.setOsTemplete(cInfo.getOsTemplete());
			server.setHwTemplete(cInfo.getHwTemplete());
			server.setServiceTemplete(cInfo.getServiceTemplete());
			server.setServerGroup(this.getServerGroup());
			if( cInfo.getStatus() != null && ProjectServer.SERVER_STATUS_STARTING.equals(cInfo.getStatus())){
				server.setStatus("승인 대기중..");
			}else if( cInfo.getStatus() != null && ProjectServer.SERVER_STATUS_STOPPING.equals(cInfo.getStatus())){
				server.setStatus("삭제 요청중..");
			}else if( cInfo.getStatus() != null && ProjectServer.SERVER_STATUS_RUNNING.equals(cInfo.getStatus())){
				server.setStatus(ProjectServer.SERVER_STATUS_RUNNING);
			}
			
			server.setMetaworksContext(new MetaworksContext());
			server.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			server.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			serverList.add(server);
		}
		
		
		this.setServerList(serverList.toArray(new KtProjectServer[serverList.size()]));
	}
	
}
