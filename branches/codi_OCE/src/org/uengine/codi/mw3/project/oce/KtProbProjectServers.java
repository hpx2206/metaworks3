package org.uengine.codi.mw3.project.oce;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectServer;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"hideViewBox", "methodVAlign"}, values={"true", "top"})
public class KtProbProjectServers extends KtProjectServers{
	
		
	KtProbProjectServer[] serverLists;
	@Face(options={"hideLabel"}, values={"true"})
	public KtProbProjectServer[] getServerLists() {
		return serverLists;
	}
	public void setServerLists(KtProbProjectServer[] serverLists) {
		this.serverLists = serverLists;
	}
		
	public KtProbProjectServers(){
		this(null, null);
	}
	
	public KtProbProjectServers(String projectId, String serverGroup){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setProjectId(projectId);
		this.setServerLists(new KtProbProjectServer[0]);
		this.setServerList(null);
		this.setServerGroup(serverGroup);
	}
	
	@Override
	public void loadOceServer() throws Exception{
		this.setProjectId(projectId);
		if( this.getServerGroup() == null || "".equals(this.getServerGroup())){
			this.setServerGroup(SERVER_PROB);	// default
		}
		
		ArrayList<KtProbProjectServer> serverList = new ArrayList<KtProbProjectServer>();
		
		CloudInfo cloudInfo = new CloudInfo();
		ICloudInfo iCloudInfo = cloudInfo.findServerByProjectId(projectId , serverGroup);
		while(iCloudInfo.next()){
			CloudInfo cInfo = new CloudInfo();
			cInfo.copyFrom(iCloudInfo);
			KtProbProjectServer server = new KtProbProjectServer();
			server.setId(cInfo.getId()+"");
			server.setProjectId(cInfo.getProjectId());
			server.setName(cInfo.getServerName());
			server.setIp(cInfo.getServerIp());
			server.setOsType(cInfo.getOsType());
			server.setWasType(cInfo.getWasType());
			server.setDbType(cInfo.getDbType());
			server.setServerGroup(this.getServerGroup());
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
		
		
		this.setServerLists(serverList.toArray(new KtProbProjectServer[serverList.size()]));
	}
	
}
