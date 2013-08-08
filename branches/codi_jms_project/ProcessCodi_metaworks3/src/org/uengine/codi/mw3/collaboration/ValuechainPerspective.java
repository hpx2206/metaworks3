package org.uengine.codi.mw3.collaboration;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
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
	
	String projectId;
		public String getProjectId() {
			return projectId;
		}
	
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String path;
		public String getPath() {
			return path;
		}
	
		public void setPath(String path) {
			this.path = path;
		}
	
	String name;
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
	String id;
		@Id
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
	String type;
		public String getType() {
			return type;
		}
	
		public void setType(String type) {
			this.type = type;
		}
	
	boolean folder;
		public boolean isFolder() {
			return folder;
		}
	
		public void setFolder(boolean folder) {
			this.folder = folder;
		}
		
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
			processDefinitionNode.getMetaworksContext().setWhere("resource");
			processDefinitionNode.add(new ProcessDefinitionNode(project));
		
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
	
	
	


//	@Override
//	@ServiceMethod(inContextMenu=true)
//	public Object action() {
//		// TODO Auto-generated method stub
//		
//		if(this.getMetaworksContext() != null && "resource".equals(this.getMetaworksContext().getWhere())){
//			metadataProperty.setValuechainPerspective(this);
//		}
//		return super.action();
//	}
//	

}
