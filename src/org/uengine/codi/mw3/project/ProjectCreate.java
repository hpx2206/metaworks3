package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ProjectCreate implements ITool {
	
	String name;
		@Face(displayName="프로젝트명", options={"required"}, values={"true"})
		public String getName() {
			return name;
		}	
		public void setName(String name) {
			this.name = name;
		}
	
	String description;
		@Face(displayName="프로젝트설명")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	@Autowired
	public ProcessManagerRemote processManager;	
	
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
		
	}

}
