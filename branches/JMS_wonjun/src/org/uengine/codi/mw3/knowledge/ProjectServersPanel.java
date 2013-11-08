package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.Face;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
public class ProjectServersPanel {

	public ProcessManagerRemote processManager;
		
	ProjectServers devServers;
		@Face(displayName="$project.servers.dev")
		public ProjectServers getDevServers() {
			return devServers;
		}
		public void setDevServers(ProjectServers devServers) {
			this.devServers = devServers;
		}
		
	ProjectServers prodServers;
		@Face(displayName="$project.servers.prod")
		public ProjectServers getProdServers() {
			return prodServers;
		}
		public void setProdServers(ProjectServers prodServers) {
			this.prodServers = prodServers;
		}
		
	public void load(String projectId){
		ProjectServers devServers = new ProjectServers(projectId, "dev");
		devServers.processManager = processManager;
		devServers.load();

		ProjectServers prodServers = new ProjectServers(projectId, "prod");
		prodServers.processManager = processManager;
		//prodServers.load();

		this.setDevServers(devServers);
		this.setProdServers(prodServers);

	}
}
