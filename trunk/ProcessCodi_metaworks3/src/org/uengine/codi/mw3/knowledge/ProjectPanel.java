package org.uengine.codi.mw3.knowledge;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;


public class ProjectPanel {
	
	@Face(displayName="$CreateProject")
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_APPEND)
	public ModalWindow addProject() throws Exception {

		ProjectTitle projectTitle = new ProjectTitle();
		projectTitle.setMetaworksContext(new MetaworksContext());
		projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		projectTitle.session = session;
		
		return new ModalWindow(projectTitle , 500, 300,  "프로젝트생성");
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
		setProjectNode(projectNode);
	}
	
	@AutowiredFromClient
	transient public Session session;
}
