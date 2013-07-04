package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.dao.TransactionContext;
import org.metaworks.metadata.MetadataProperty;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.form.FormEditor;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;

@Face(
		ejsPath = "dwr/metaworks/org/metaworks/component/TreeNode.ejs",
		ejsPathMappingByContext = {
				"{how:	'tree', face: 'dwr/metaworks/org/metaworks/component/TreeNode.ejs'}",
				"{how:	'resourcePicker', face: 'dwr/metaworks/org/metaworks/metadata/ResourceNodePicker.ejs'}"
		})
public class ResourceNode extends TreeNode implements ContextAware {

	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public Workspace workspace;

	@AutowiredFromClient
	public MetadataProperty metadataProperty;

	public final static String TYPE_PROJECT 			= "project";

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

	boolean hasPick;
	public boolean isHasPick() {
		return hasPick;
	}
	public void setHasPick(boolean hasPick) {
		this.hasPick = hasPick;
	}

	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	public ResourceNode(){
		setMetaworksContext(new MetaworksContext());
	}

	public ResourceNode(Project project){
		this.setId(project.getId());
		this.setName(project.getId());
		this.setType(TYPE_PROJECT);
		this.setFolder(true);

		this.setPath(project.getPath());
		this.setProjectId(project.getId());

		setMetaworksContext(new MetaworksContext());
	}


	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception {

		ArrayList<TreeNode> child = new ArrayList<TreeNode>();

		File file = new File(this.getPath());
		String[] childFilePaths = file.list();

		// folder
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);

			if(childFile.isDirectory()){
				ResourceNode node = new ResourceNode();
				node.setProjectId(this.getProjectId());
				node.setId(this.getId() + File.separatorChar + childFile.getName());				
				node.setName(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
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
				ResourceNode node = new ResourceNode();
				node.setProjectId(this.getProjectId());
				node.setId(this.getId() + File.separatorChar + childFile.getName());
				node.setName(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
				node.setParentId(this.getId());
				node.setType(findNodeType(node.getName()));
				node.setMetaworksContext(getMetaworksContext());
				child.add(node);
			}
		}

		this.setChild(child);

		return this;
	}

	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_POPUP)
	public Object findResource(){

		// make workspace
		Workspace workspace = new Workspace();
		workspace.load();

		Navigator navigator = new Navigator();

		ResourceNode workspaceNode = new ResourceNode();
		workspaceNode.setId(workspace.getId());
		workspaceNode.setRoot(true);
		workspaceNode.setHidden(true);
		workspaceNode.setMetaworksContext(new MetaworksContext());
		workspaceNode.getMetaworksContext().setHow("tree");

		for(Project project : workspace.getProjects()){
			ResourceNode node = new ResourceNode(project);
			node.getMetaworksContext().setWhere("resource");
			workspaceNode.add(node);
		}

		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(workspace.getId());
		resourceTree.setNode(workspaceNode);

		navigator.setResourceTree(resourceTree);
		navigator.setId("popupTree");

		Popup popup = new Popup();
		popup.setPanel(navigator);

		return popup;	
	}

	public Editor beforeAction(){

		Editor editor = null;

		if(!this.isFolder()){
			String type = ResourceNode.findNodeType(this.getName());

			this.setType(type);

			if(type.equals(TreeNode.TYPE_FILE_JAVA)){
				editor = new FormEditor(this);
				editor.workspace = workspace;
				editor.load();
			}else if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
				editor = new ProcessEditor(this);
				try {
					editor.load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(type.equals(TreeNode.TYPE_FILE_METADATA)){
				editor = new MetadataEditor(this);
				try {
					((MetadataEditor)editor).loadPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				editor = new Editor(this);
			}
		}
		/*
		if(!this.isFolder()){

			String type = ResourceNode.findNodeType(this.getName());

			if(type.equals(TreeNode.TYPE_FILE_JAVA)){
				editor = new JavaCodeEditor(this.getId());
			}else if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
				editor = new ProcessEditor(this.getId());
				((ProcessEditor)editor).getProcessDesigner().setBasePath(jbPath.getBasePath());
				try {
					((ProcessEditor)editor).getProcessDesigner().load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(type.equals(TreeNode.TYPE_FILE_METADATA)){
				editor = new MetadataEditor(this.getId());
				editor.jbPath = jbPath;
				try {
					((MetadataEditor)editor).loadPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(type.equals(TreeNode.TYPE_FILE_RULE)){
				editor = new RuleEditor(this.getId());
				try {
					((RuleEditor)editor).load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				editor = new Editor(this.getId(), type);
			}
		}
		 */

		return editor;
	}

	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "projectId"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action(){
		
		if(this.getMetaworksContext() != null && "resource".equals(this.getMetaworksContext().getWhere())){
			metadataProperty.setResourceNode(this);

			//픽업되는 순간  xml에 저장하고 load 해서 미리보기 
			this.getMetaworksContext().setHow("resourcePicker");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			return new Object[]{new ToOpener(this), new Remover(new Popup())};	
		}else{
			return new ToAppend(new CloudWindow("editor"), this.beforeAction());
		}
	}

	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);

		return new Object[]{new Refresh(session), new ResourceContextMenu(this)};
	}

	public static String findNodeType(String name){
		String nodeType = TreeNode.TYPE_FILE_TEXT;

		int pos = name.lastIndexOf('.');
		if(pos > -1){
			String ext = name.substring(pos);

			if(".html".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_HTML;
			}else if(".java".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_JAVA;
			}else if(".ejs".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_EJS;
			}else if(".js".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_JS;
			}else if(".form".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_FORM;
			}else if(".wpd".equals(ext) || ".process2".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_PROCESS;
			}else if(".rule".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_RULE;
			}else if(".css".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_CSS;
			}else if(".jpg".equals(ext) || ".gif".equals(ext) || ".png".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_IMAGE;
			}else if(".metadata".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_METADATA;
			}

		}

		return nodeType;
	}

	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId", "type"}, mouseBinding="drag", target=ServiceMethodContext.TARGET_NONE)
	public void drag() {
		
		Project project = workspace.findProject(this.getProjectId());
		String 	projectSourcePath = project.getBuildPath().getSources().get(0).getPath(); 
		
		TransactionContext.getThreadLocalInstance().getRequest().getSession().setAttribute("projectSourcePath", projectSourcePath);
		
		this.setAlias(project.getBuildPath().makeFullClassName(this.getId()));;
	}
}