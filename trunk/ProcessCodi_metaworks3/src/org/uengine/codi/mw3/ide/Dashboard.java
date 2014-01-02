package org.uengine.codi.mw3.ide;

import org.uengine.codi.mw3.knowledge.ProjectPanel;
import org.uengine.codi.mw3.model.Application;
import org.uengine.codi.mw3.model.Session;

public class Dashboard extends Application {

	Object content;
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

	public Dashboard(Session session) throws Exception{
		
		ProjectPanel projectPanel = new ProjectPanel();
		projectPanel.session = session;
		projectPanel.load();

		this.setContent(projectPanel);
		
	}
}
