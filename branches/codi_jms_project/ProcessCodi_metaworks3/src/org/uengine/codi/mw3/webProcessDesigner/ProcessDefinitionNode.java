package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;

public class ProcessDefinitionNode extends TreeNode  implements ContextAware {
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
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
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
			
	public final static String TYPE_PROJECT = "project";
	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public Workspace workspace;
	
	@AutowiredFromClient
	public ProcessViewPanel processViewPanel;
	@AutowiredFromClient
	public ProcessViewerPanel processViewerPanel;
	
	public ProcessDefinitionNode(){
		setMetaworksContext(new MetaworksContext());
	}
	public ProcessDefinitionNode(Project project){
		this.setId(project.getId());
		this.setName(project.getId());
		this.setType(TYPE_PROJECT);
		this.setFolder(true);
		
		this.setPath(project.getPath());
		this.setProjectId(project.getId());
		setMetaworksContext(new MetaworksContext());
	}
	public ArrayList<TreeNode> loadExpand(){
		
		return null;
	}
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand(){
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();

		File file = new File(this.getPath());
		String[] childFilePaths = file.list();

		// folder
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);

			if(childFile.isDirectory()){
				ProcessDefinitionNode node = new ProcessDefinitionNode();
				node.setProjectId(this.getProjectId());
				node.setId(this.getId() + File.separatorChar + childFile.getName());				
				node.setName(childFile.getName());
				node.setDefId(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
				node.setAlias(this.getPath() + File.separatorChar + childFile.getName());
				node.setParentId(this.getId());
				node.setType(TreeNode.TYPE_FOLDER);
				node.setMetaworksContext(getMetaworksContext());
				node.setFolder(true);
				
				child.add(node);
			}
		}

		// file
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);

			if(!childFile.isDirectory()){
				String type = ResourceNode.findNodeType(childFile.getName());
				if(!type.equals(TreeNode.TYPE_FILE_PROCESS)){
					continue;
				}
				ProcessDefinitionNode node = new ProcessDefinitionNode();
				node.setProjectId(this.getProjectId());
				node.setId(this.getId() + File.separatorChar + childFile.getName());
				node.setName(childFile.getName());
				node.setDefId(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
				node.setAlias(this.getPath() + File.separatorChar + childFile.getName());
				node.setParentId(this.getId());
				node.setType(type);
				node.setMetaworksContext(getMetaworksContext());
				child.add(node);
			}
		}

		this.setChild(child);

		return this;
	}
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_POPUP)
	public Object findResource(){
		
			Workspace workspace = new Workspace();
			workspace.load();

			ProcessViewNavigator navigator = new ProcessViewNavigator();

			ProcessDefinitionNode definitionNode = new ProcessDefinitionNode();
			definitionNode.setId(workspace.getId());
			definitionNode.setRoot(true);
			definitionNode.setHidden(true);
			definitionNode.setMetaworksContext(new MetaworksContext());
			definitionNode.getMetaworksContext().setHow("tree");

			for(Project project : workspace.getProjects()){
				ResourceNode node = new ResourceNode(project);
				node.getMetaworksContext().setWhere("resource");
				definitionNode.add(node);
			}

			Tree tree = new Tree();
			
			tree.setId(workspace.getId());
			tree.setNode(definitionNode);

			navigator.setProcessDefinitionTree(tree);
			navigator.setId("popupTree");

			Popup popup = new Popup();
			popup.setPanel(navigator);
			
			
			return popup;	
	}
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "projectId","defId","alias"},mouseBinding="left", target=ServiceMethodContext.TARGET_APPEND)
	public Object action(){
		if( alias != null && !this.isFolder() && this.getType().equals(TreeNode.TYPE_FILE_PROCESS)){
			processViewerPanel = new ProcessViewerPanel();
			processViewerPanel.setDefinitionId(defId);
			processViewerPanel.setAlias(alias);
			processViewerPanel.loadDefnitionView();
			return new Object[] { new Refresh(processViewerPanel.processViewPanel) };
		}else{
			return null;
		}
	}
}
