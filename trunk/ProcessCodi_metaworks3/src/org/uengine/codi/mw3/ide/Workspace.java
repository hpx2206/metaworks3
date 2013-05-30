package org.uengine.codi.mw3.ide;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;

public class Workspace {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	ArrayList<Project> projects;
		@Hidden
		@AutowiredToClient
		public ArrayList<Project> getProjects() {
			return projects;
		}
		public void setProjects(ArrayList<Project> projects) {
			this.projects = projects;
		}

	public void load(String codebase, String tenantId){
		this.setId(tenantId);
		
		ArrayList<Project> projects = new ArrayList<Project>();
			
		// db query
		Project project_codi= new Project();
		project_codi.setId("codi");
		project_codi.setPath(codebase + "/" + tenantId + "/codi");
		
		projects.add(project_codi);
		
		
		Project project_mw3 = new Project();
		project_mw3.setId("mw3");
		project_mw3.setPath(codebase + "/" + tenantId + "/mw3");

		projects.add(project_mw3);
		
		
		
		this.setProjects(projects);
	}
}
