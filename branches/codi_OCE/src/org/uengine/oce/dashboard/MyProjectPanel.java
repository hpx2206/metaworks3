package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectTitle;
import org.uengine.codi.mw3.model.Session;

public class MyProjectPanel {
	
	@Face(displayName="$CreateProject")
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_APPEND)
	public ModalWindow addProject() throws Exception {

		ProjectTitle projectTitle = new ProjectTitle();
		projectTitle.setMetaworksContext(new MetaworksContext());
		projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		projectTitle.session = session;
		projectTitle.setLogoFile(new MetaworksFile());
		projectTitle.setWarFile(new MetaworksFile());
		projectTitle.setSqlFile(new MetaworksFile());
		return new ModalWindow(projectTitle , 500, 300, "$CreateProject");
	}
	
	
	IProjectNode projectNode;
		public IProjectNode getProjectNode() {
			return projectNode;
		}
		public void setProjectNode(IProjectNode projectNode) {
			this.projectNode = projectNode;
		}

	public void load() throws Exception {
				
		IProjectNode projectNode = ProjectNode.load(session);
		projectNode.getMetaworksContext().setHow("oce");
		setProjectNode(projectNode);
	}
	
	@AutowiredFromClient
	transient public Session session;
}
