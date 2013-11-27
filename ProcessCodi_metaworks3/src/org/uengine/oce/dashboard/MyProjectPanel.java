package org.uengine.oce.dashboard;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectTitle;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;

public class MyProjectPanel {
	
	@Face(displayName="$CreateProject")
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_POPUP)
	public ModalWindow addProject() throws Exception {

		ProjectTitle projectTitle = new ProjectTitle();
		projectTitle.setMetaworksContext(new MetaworksContext());
		
		if("1".equals(StartCodi.USE_IAAS))
			projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		else
			projectTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		projectTitle.session = session;
		projectTitle.setLogoFile(new MetaworksFile());
		
		return new ModalWindow(projectTitle , 500, 500, "$CreateProject");
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
	
	public void load(Session session) throws Exception {
		
		IProjectNode projectNode = ProjectNode.load(session);
		projectNode.getMetaworksContext().setHow("oce");
		setProjectNode(projectNode);
	}
	
	@AutowiredFromClient
	transient public Session session;
}
