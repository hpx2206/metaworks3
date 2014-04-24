package org.uengine.codi.mw3.marketplace;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.model.Session;

public class AppMapList implements ContextAware {

	public AppMapList() {
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(Session session) throws Exception {

		AppMapping appMap = new AppMapping();
		appMap.setComCode(session.getCompany().getComCode());
		appMap.session = session;
		IAppMapping appMapList = appMap.findMyApps();
		appMapping = new ArrayList<AppMapping>();
		
		while(appMapList.next()){
			appMap = new AppMapping();
			int index = 0;
			int appId = appMapList.getAppId();
			
			App app = new App();
			app.setAppId(appId);
			
			IApp appList = app.databaseMe();
			
			appMap.setAppId(appId);
			appMap.setAppName(appList.getAppName());
			appMap.setComCode(session.getCompany().getComCode());
			appMap.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			appMap.getMetaworksContext().setWhere("mapList");
			appMap.setLogoFile(appList.getLogoFile());
			
			String projectId = appList.getProjectId();
			
			ProjectNode project = new ProjectNode();
			project.setId(projectId);
			
			IProjectNode projectNode = project.findById();
			
			while(projectNode.next()){
				appMap.setProjectName(projectNode.getName());
			}
			
			appMapping.add(index, appMap);
			index ++;
			
		}
		setAppMapping(appMapping);
	}
	
	ArrayList<AppMapping> appMapping;
		public ArrayList<AppMapping> getAppMapping() {
			return appMapping;
		}
		public void setAppMapping(ArrayList<AppMapping> appMapping) {
			this.appMapping = appMapping;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

		
	@AutowiredFromClient
	public Session session;
}
