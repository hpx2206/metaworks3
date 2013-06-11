package org.uengine.codi.mw3.ide;

import java.util.ArrayList;

import org.metaworks.metadata.MetadataBundle;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.CodiClassLoader;
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
		public ArrayList<Project> getProjects() {
			return projects;
		}
		public void setProjects(ArrayList<Project> projects) {
			this.projects = projects;
		}

	public void load(){
		
//		String projectId = MetadataBundle.getProjectId();
		String tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		
		if( tenantId == null )	tenantId = "uengine";
		
		this.setId(tenantId);
		
		ArrayList<Project> projects = new ArrayList<Project>();
		// TODO codi 관리자는 root가 보인다.
		/*
		// 앱의 루트 불러오기 - codebase + projectId + "root"
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		CodiFileUtil.mkdirs(mainPath);
		Project main = new Project();
		main.setId(projectId + File.separatorChar + "root" );
		main.setPath(mainPath);
		main.load();
		projects.add(main);
		*/
		
		// 앱의 테넌트 불러오기 - codebase + projectId + tenantId
		String codeBasePath = CodiClassLoader.mySourceCodeBase();
		CodiFileUtil.mkdirs(codeBasePath);
		Project tenantMain = new Project();
		tenantMain.setId(tenantId);
		tenantMain.setPath(codeBasePath);
		tenantMain.load();
		projects.add(tenantMain);
		
		// 테넌트의 프로젝트 불러오기
		ProjectNode projectNode = new ProjectNode();
		projectNode.setCompanyId(tenantId);
		try {
			IProjectNode projectList = projectNode.completedProject();
			while(projectList.next()){
				ProjectNode node = new ProjectNode();
				node.copyFrom(projectList);
				String path = MetadataBundle.getProjectBasePath(node.getName());
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
