package org.uengine.codi.mw3.processexplorer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.component.Tree;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;

public class ValuechainPerspective extends Perspective  implements ContextAware {

	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}	
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
	@AutowiredFromClient
	public ProcessViewerPanel processViewerPanel;
	
	@Override
	public void loadChildren() throws Exception {
		String treeId = "valuechain";
		Workspace workspace = new Workspace();
		workspace.load();
	
		// TODO Auto-generated method stub
		ProcessDefinitionNode processDefinitionNode = new ProcessDefinitionNode();
		processDefinitionNode.setId(workspace.getId());
		processDefinitionNode.setRoot(true);
		processDefinitionNode.setHidden(true);
		processDefinitionNode.setTreeId(treeId);
		
		
		for (Project project : workspace.getProjects()){
			ProcessDefinitionNode node = new ProcessDefinitionNode(project);
			node.setTreeId(treeId);
	
			processDefinitionNode.add(node);
		
		}
		Tree tree = new Tree();
		tree.setId(treeId);
		tree.setNode(processDefinitionNode);

		setProcessDefinitionTree(tree);

	}

	@AutowiredFromClient
	ProcessNameView processNameView;



}
