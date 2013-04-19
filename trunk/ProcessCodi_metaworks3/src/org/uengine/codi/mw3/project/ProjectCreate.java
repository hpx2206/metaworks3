package org.uengine.codi.mw3.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProjectCreate implements ITool {
	
	String name;
//		@Face(displayName="$ProjectName")	
		public String getName() {
			return name;
		}	
		public void setName(String name) {
			this.name = name;
		}
		
	String projectId;
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
