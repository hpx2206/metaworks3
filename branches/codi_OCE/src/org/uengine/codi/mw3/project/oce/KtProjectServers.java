package org.uengine.codi.mw3.project.oce;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
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
	
	public void loadOceServer(String projectId, String projectName){
		ArrayList<KtProjectServer> serverList = new ArrayList<KtProjectServer>();
		
		// TODO	
		KtProjectServer server = new KtProjectServer();
		try {
			server.setType("DB");
			server.setName(projectName);
//			server.setIp(ip);
			server.setStatus(ProjectServer.SERVER_STATUS_RUNNING);
			server.setMetaworksContext(new MetaworksContext());
			server.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			server.getMetaworksContext().setWhen(ProjectServer.SERVER_STATUS_RUNNING);
//			server.status();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverList.add(server);
		
		KtProjectServer server1 = new KtProjectServer();
		try {
			server1.setType("WAS");
			server1.setName(projectName);
//			server1.setIp(ip);
			server1.setMetaworksContext(new MetaworksContext());
			server1.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			server1.getMetaworksContext().setWhen(ProjectServer.SERVER_STATUS_RUNNING);
//			server1.status();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverList.add(server1);
		
		this.setServerList(serverList.toArray(new KtProjectServer[serverList.size()]));
	}
}
