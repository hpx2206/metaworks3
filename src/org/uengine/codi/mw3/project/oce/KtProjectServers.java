package org.uengine.codi.mw3.project.oce;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"hideViewBox", "methodVAlign"}, values={"true", "top"})
public class KtProjectServers implements ContextAware{

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String serverGroup;
		@Id
		@Hidden
		public String getServerGroup() {
			return serverGroup;
		}
		public void setServerGroup(String serverGroup) {
			this.serverGroup = serverGroup;
		}
		
	KtProjectServer[] serverList;
	@Face(options={"hideLabel"}, values={"true"})
		public KtProjectServer[] getServerList() {
			return serverList;
		}
		public void setServerList(KtProjectServer[] serverList) {
			this.serverList = serverList;
		}
		
	public KtProjectServers(){
		this(null, null);
	}
	
	public KtProjectServers(String projectId, String serverGroup){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setProjectId(projectId);
		this.setServerList(new KtProjectServer[0]);
		this.setServerGroup(serverGroup);
	}
	
	public void loadOceServer() throws Exception{
		this.setProjectId(projectId);
		
		ArrayList<KtProjectServer> serverList = new ArrayList<KtProjectServer>();
		
		CloudInfo cloudInfo = new CloudInfo();
		ICloudInfo iCloudInfo = cloudInfo.findServerByProjectId(projectId);
		while(iCloudInfo.next()){
			CloudInfo cInfo = new CloudInfo();
			cInfo.copyFrom(iCloudInfo);
			KtProjectServer server = new KtProjectServer();
			server.setId(cInfo.getId()+"");
			server.setProjectId(cInfo.getProjectId());
			server.setName(cInfo.getServerName());
			server.setIp(cInfo.getServerIp());
			server.setOsType(cInfo.getOsType());
			server.setWasType(cInfo.getWasType());
			server.setDbType(cInfo.getDbType());
			if( cInfo.getServerIp() == null || "".equals(cInfo.getServerIp() )){
				server.setStatus("승인 대기중");
			}else{
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
