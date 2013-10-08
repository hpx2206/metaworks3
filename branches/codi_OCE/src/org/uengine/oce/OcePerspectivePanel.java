package org.uengine.oce;

import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.codi.mw3.model.OrganizationPerspectiveApp;
import org.uengine.codi.mw3.model.PerspectivePanel;
import org.uengine.codi.mw3.model.Session;

public class OcePerspectivePanel extends PerspectivePanel{

	public OcePerspectivePanel() throws Exception {
		super();
	}
	
	public OcePerspectivePanel(Session session) throws Exception {
		
		OrganizationPerspectiveApp appPerspective =	new OrganizationPerspectiveApp();
		appPerspective.session = session;
		appPerspective.select();

		this.setAppPerspective(appPerspective);
		
		ProjectPerspective projectPerspective = new ProjectPerspective();
		projectPerspective.session = session;
		projectPerspective.select();
		
		this.setProjectPerspective(projectPerspective);
		
	}
}
