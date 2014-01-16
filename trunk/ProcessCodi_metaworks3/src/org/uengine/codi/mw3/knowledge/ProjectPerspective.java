package org.uengine.codi.mw3.knowledge;

import org.uengine.codi.mw3.model.CollapsePerspective;

public class ProjectPerspective extends CollapsePerspective {

	ProjectPanel projectPanel;
		public ProjectPanel getProjectPanel() {
			return projectPanel;
		}
		public void setProjectPanel(ProjectPanel projectPanel) {
			this.projectPanel = projectPanel;
		}

	public ProjectPerspective() throws Exception {
		setLabel("Project");
	}	
	
	@Override
	protected void loadChildren() throws Exception {
		projectPanel = new ProjectPanel();
		projectPanel.session = session;
		projectPanel.load();
	}
}
