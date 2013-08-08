package org.uengine.codi.mw3.Collaboration;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.metadata.MetadataProperty;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDefinitionNode;

public class ValuechainPerspective extends Perspective  implements ContextAware {
	@AutowiredFromClient
	public MetadataProperty metadataProperty;

	@AutowiredFromClient
	public Session session;
	
	Tree processDefinitionTree;
		public Tree getProcessDefinitionTree() {
			return processDefinitionTree;
		}
	
		public void setProcessDefinitionTree(Tree processDefinitionTree) {
			this.processDefinitionTree = processDefinitionTree;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public final static String TYPE_PROJECT 			= "project";
		
	public ValuechainPerspective() {
		setLabel("Valuechain");
	}

	@Override
	public void loadChildren() throws Exception {

		Workspace workspace = new Workspace();
		workspace.load();

		// TODO Auto-generated method stub
		ProcessDefinitionNode processDefinitionNode = new ProcessDefinitionNode();
		processDefinitionNode.setId(workspace.getId());
		processDefinitionNode.setRoot(true);
		processDefinitionNode.setHidden(true);

		for (Project project : workspace.getProjects()){
			processDefinitionNode.add(new ProcessDefinitionNode(project));
			processDefinitionNode.getMetaworksContext().setWhere("resource");
			processDefinitionNode.add(processDefinitionNode);
		
		}
		Tree tree = new Tree();
		tree.setId(workspace.getId());
		tree.setNode(processDefinitionNode);

		setProcessDefinitionTree(tree);

	}

	@Override
	protected void unloadChildren() throws Exception {
		setProcessDefinitionTree(null);
	}

	@Override
//	@ServiceMethod(payload={"id","name","path","type","folder","projectId"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action() {
		// TODO Auto-generated method stub
		if(this.getMetaworksContext() != null && "resource".equals(this.getMetaworksContext().getWhere())){
			
		}
		return super.action();
	}
	
	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showContextMenu() {
//		session.setClipboard(this);
//
//		return new Object[]{new Refresh(session), new ResourceContextMenu(this)};
		return null;
	}

}
