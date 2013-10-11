package org.uengine.oce;

import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.codi.mw3.model.OrganizationPerspectiveApp;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;

public class OcePerspectivePanel {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	
		
	OrganizationPerspectiveApp appPerspective;
		public OrganizationPerspectiveApp getAppPerspective() {
			return appPerspective;
		}
		
		public void setAppPerspective(OrganizationPerspectiveApp appPerspective) {
			this.appPerspective = appPerspective;
		}
		
	ProjectPerspective projectPerspective;
		public ProjectPerspective getProjectPerspective() {
			return projectPerspective;
		}	
		public void setProjectPerspective(ProjectPerspective projectPerspective) {
			this.projectPerspective = projectPerspective;
		}
		
	public OcePerspectivePanel() throws Exception{
		this(null);
	}

	public OcePerspectivePanel(Session session) throws Exception{
		if(session != null){
			//앱
			if("1".equals(GlobalContext.getPropertyString("app.use", "1"))){
				appPerspective = new OrganizationPerspectiveApp();
				appPerspective.session = session;
				appPerspective.select();
			}
			if("1".equals(GlobalContext.getPropertyString("project.use", "1"))){
				//프로젝트
				projectPerspective = new ProjectPerspective();
				projectPerspective.session = session;
				projectPerspective.select();
			}
		
		}//session
		
	}
	
}

