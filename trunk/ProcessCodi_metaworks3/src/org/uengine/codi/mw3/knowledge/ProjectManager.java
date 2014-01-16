package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProjectManager implements ContextAware {

	public ProcessManagerRemote processManager;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	ProjectInfo projectInfo;
		@AutowiredToClient
		@Face(displayName="$project.info")
		public ProjectInfo getProjectInfo() {
			return projectInfo;
		}
		public void setProjectInfo(ProjectInfo projectInfo) {
			this.projectInfo = projectInfo;
		}
	
	ProjectServersPanel projectServersPanel;
		public ProjectServersPanel getProjectServersPanel() {
			return projectServersPanel;
		}
		public void setProjectServersPanel(ProjectServersPanel projectServersPanel) {
			this.projectServersPanel = projectServersPanel;
		}
		
	public ProjectManager() {
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	public void load(String projectId) throws Exception{
		// TODO: 작업해야함
		//ProjectInfo projectInfo = new ProjectInfo(projectId);
		//projectInfo.load();
		
		ProjectServersPanel projectServersPanel = new ProjectServersPanel();
		projectServersPanel.processManager = processManager;
		projectServersPanel.load(projectId);
		
		this.setProjectInfo(projectInfo);
		this.setProjectServersPanel(projectServersPanel);		
	}

}