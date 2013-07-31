package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.metadata.MetadataBundle;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.util.CodiFileUtil;
import org.uengine.kernel.GlobalContext;

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

	public Workspace(){
		this.setProjects(new ArrayList<Project>());
	}
	
	public void load(){
		
		String tenantId = null;
		if( TenantContext.getThreadLocalInstance() != null ){
			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
			this.setId(tenantId);
		}
		
		// TODO codi 관리자는 root가 보인다.
		// 앱의 루트 불러오기 - codebase + projectId + "root"
		String projectId = MetadataBundle.getProjectId();
		
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		CodiFileUtil.mkdirs(mainPath);
		
		Project main = new Project();
		main.setId(projectId);
		main.setPath(mainPath);
		main.load();
		projects.add(main);		
		
		if("1".equals(GlobalContext.getPropertyString("project.use", "1"))){
			// 테넌트의 프로젝트 불러오기
			try {
				IProjectNode projectList = ProjectNode.completedProject(tenantId);
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
		}
	}
	
	public void addTenant(){
		
		// 앱의 테넌트 불러오기 - codebase + projectId + tenantId
		String codeBasePath = CodiClassLoader.mySourceCodeBase();
		CodiFileUtil.mkdirs(codeBasePath);
		Project tenantMain = new Project();
		tenantMain.setId(this.getId());
		tenantMain.setPath(codeBasePath);
		tenantMain.load();
		
		this.getProjects().add(tenantMain);
	}
	
	public void addProject(String tenantId, String projectId){
		
		String path = MetadataBundle.getProjectBasePath(projectId, tenantId);
		
		CodiFileUtil.mkdirs(path);
		
		Project project = new Project();
		project.setId(projectId);
		project.setPath(path);
		project.load();
		
		this.getProjects().add(project);
	}
	
	public Project findProject(String projectId){
		for(Project project : this.getProjects()){
			if(projectId.equals(project.getId()))
				return project;
		}
		
		return null;
	}
}
