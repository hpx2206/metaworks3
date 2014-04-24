package org.uengine.codi.mw3.knowledge;

import java.io.Serializable;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"hideViewBox", "methodVAlign"}, values={"true", "top"})
public class ProjectServers implements ContextAware {

	@Autowired
	public ProcessManagerRemote processManager;
	
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
		
	ProjectServer[] serverList;
		@Face(options={"hideLabel"}, values={"true"})
		public ProjectServer[] getServerList() {
			return serverList;
		}
		public void setServerList(ProjectServer[] serverList) {
			this.serverList = serverList;
		}

	public ProjectServers(){
		this(null, null);
	}
	
	public ProjectServers(String projectId, String serverGroup){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setProjectId(projectId);
		this.setServerList(new ProjectServer[0]);
		this.setServerGroup(serverGroup);
	}
	
	public void load(){
		
		try {
			ArrayList<ProjectServer> serverList = new ArrayList<ProjectServer>();
			
			IInstance servers = Instance.loadServers(this.getProjectId());
			
			while(servers.next()){
				ProjectServer server = new ProjectServer();
				server.setInstId(servers.getInstId().toString());
				
				Serializable serial = null;
				serial = processManager.getProcessVariable(server.getInstId(), "", "ProjectServer");
				if(serial instanceof ProjectServer) {
					ProjectServer deserializableServer = (ProjectServer)serial;
					server.setType(deserializableServer.getType());
					server.setName(deserializableServer.getName());
					server.setIp(deserializableServer.getIp());
					
					/*if(!serverGroup.equals(deserializableServer.getGroup()))
						break;*/
				}else{
					break;
				}
				
				String status = (String)(Serializable)processManager.getProcessVariable(server.getInstId(), "", "status");
				String ip = (String)(Serializable)processManager.getProcessVariable(server.getInstId(), "", "vm_ip");
				if(ip != null)
					server.setIp(ip);
				
				server.setStatus(status);
				server.setMetaworksContext(new MetaworksContext());
				server.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
				server.getMetaworksContext().setWhen(status);
				server.status();
				
				serverList.add(server);
			}
			
			this.setServerList(serverList.toArray(new ProjectServer[serverList.size()]));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
