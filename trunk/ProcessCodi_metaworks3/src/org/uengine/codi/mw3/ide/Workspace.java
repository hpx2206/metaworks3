package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.util.CodiFileUtil;

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
		// 테넌트의 메인 불러오기
		String mainPath = codebase + File.separatorChar + tenantId + File.separatorChar + "main";
		CodiFileUtil.mkdirs(mainPath);
		Project main = new Project();
		main.setId(tenantId + "Main");
		main.setPath(mainPath);
		main.load();
		projects.add(main);
		
		// 테넌트의 프로젝트 불러오기
		ProjectNode projectNode = new ProjectNode();
		projectNode.setCompanyId(tenantId);
		try {
			IProjectNode projectList = projectNode.completedProject();
			while(projectList.next()){
				ProjectNode node = new ProjectNode();
				node.copyFrom(projectList);
				String path = codebase + File.separatorChar + tenantId + File.separatorChar + node.getName();
				CodiFileUtil.mkdirs(path);
				
				Project project = new Project();
				project.setId(node.getName());
				project.setPath(path);
				project.load();
				
				projects.add(project);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		this.setProjects(projects);
	}
}
