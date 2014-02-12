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
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataProperty;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.form.FormEditor;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.ide.editor.valuechain.ValueChainEditor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;

@Face(
		ejsPath = "dwr/metaworks/org/metaworks/component/TreeNode.ejs",
		ejsPathMappingByContext = {
				"{how:	'tree', face: 'dwr/metaworks/org/metaworks/component/TreeNode.ejs'}",
				"{how:	'resourcePicker', face: 'dwr/metaworks/org/metaworks/metadata/ResourceNodePicker.ejs'}"
		})
public class ResourceNode extends TreeNode implements ContextAware {

	public final static String TYPE_ACTIVITY 	= "activity";
	public final static String TYPE_ROLE 		= "role";
	public final static String TYPE_PROJECT	= "project";
	
	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public MetadataProperty metadataProperty;


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
	boolean conferenceMode;
		public boolean isConferenceMode() {
			return conferenceMode;
		}
		public void setConferenceMode(boolean conferenceMode) {
			this.conferenceMode = conferenceMode;
		}

	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String editorInstanceId;
		public String getEditorInstanceId() {
			return editorInstanceId;
		}
		public void setEditorInstanceId(String editorInstanceId) {
			this.editorInstanceId = editorInstanceId;
		}
		
	public ResourceNode(){
		setMetaworksContext(new MetaworksContext());
	}

	public ResourceNode(Project project){
		this.setId(project.getId());
		this.setName(project.getName());
		this.setType(TYPE_PROJECT);
		this.setFolder(true);

		this.setPath(project.getPath());
		this.setProjectId(project.getId());

		File file = new File(this.getPath());
		if(!file.exists()){
			System.out.println("not exists project codebase :" +file.getAbsolutePath());
			
			file.mkdirs();
			
			System.out.println("mkdirs");
		}
		
		setMetaworksContext(new MetaworksContext());
	}

	public ArrayList<TreeNode> loadChild() throws Exception {
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();

		System.out.println(this.getPath());
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

			// 디렉토리가 아닌 파일이지만 프로세스 타입이면 프로세스 노드를 만들어서 붙여야 한다.
			if(!childFile.isDirectory()){
				String type = ResourceNode.findNodeType(childFile.getAbsolutePath());
				
				if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
					
					ProcessNode processNode= new ProcessNode();
					processNode.setProjectId(this.getProjectId());
					processNode.setId(this.getId() + File.separatorChar + childFile.getName());
					processNode.setName(childFile.getName());
					processNode.setPath(this.getPath() + File.separatorChar + childFile.getName());
					processNode.setParentId(this.getId());
					processNode.setType(TreeNode.TYPE_FILE_PROCESS);
					processNode.setMetaworksContext(getMetaworksContext());
//					processNode.setFolder(true);
					
					child.add(processNode);
				
				} else {
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
		}
		
		return child;
	}
	
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception {
		return new ToAppend(this, this.loadChild());
	}

	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_POPUP)
	public Object findResource(){

		/*
		Navigator navigator = new Navigator();

		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(workspace.getId());
		resourceTree.setNode(workspaceNode);

		navigator.setResourceTree(resourceTree);
		navigator.setId("popupTree");
		*/
		
		Popup popup = new Popup();
		//popup.setPanel(navigator);
		popup.setName("Project Resource");

		return popup;	
	}

	public Editor beforeAction(){
		return beforeAction(true);
	}
	public Editor beforeAction(boolean isLoad){

		Editor editor = null;
		
		if(!this.isFolder()){
			String type = ResourceNode.findNodeType(this.getName());

			this.setType(type);

			if(type.equals(TreeNode.TYPE_FILE_JAVA)){
				if("UI".equals(this.getMetaworksContext().getWhen())){
					editor = new FormEditor(this);
				}else{
					editor = new JavaCodeEditor(this);
				}
			}else if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
				editor = new ProcessEditor(this);
			}else if(type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
				editor = new ValueChainEditor(this);
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
		
		if(isLoad)
			editor.load();

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
			Editor editor = this.beforeAction();

//			InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
//			if(  editor instanceof ProcessEditor ){
//				if( ((ProcessEditor)editor).getProcessDesignerInstanceId() != null ){
//					instanceViewThreadPanel.session = session;
//					instanceViewThreadPanel.setInstanceId(((ProcessEditor)editor).getProcessDesignerInstanceId());
//					instanceViewThreadPanel.load();
//				}
//			}
//			CloudInstanceWindow cloudInstanceWindow = new CloudInstanceWindow();
//			cloudInstanceWindow.setPanel(instanceViewThreadPanel);

			return new Object[]{ new ToAppend(new CloudWindow("editor"), editor) };
//			return new Object[]{new ToAppend(new CloudWindow("editor"), editor) , new Refresh(cloudInstanceWindow, true) };
		}
	}

	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId", "type"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		
		return new Object[]{new ResourceContextMenu(this, session) , new Refresh(session)};
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
			}else if(".wpd".equals(ext) || ".process2".equals(ext) || ".process".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_PROCESS;
			}else if(".valuechain".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_VALUECHAIN;
			}else if(".rule".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_RULE;
			}else if(".css".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_CSS;
			}else if(".jepg".equals(ext) || ".jpg".equals(ext) || ".gif".equals(ext) || ".png".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_IMAGE;
			}else if(".metadata".equals(ext)){
				nodeType = TreeNode.TYPE_FILE_METADATA;
			}
		}

		return nodeType;
	}

	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "projectId", "parentId"}, mouseBinding="drag")
	public Object drag() {
		System.out.println("drag : " + this.getId());
		
		//Project project = workspace.findProject(this.getProjectId());

//		this.setAlias(project.getBuildPath().makeFullClassName(this.getId()));;
		if( this.getProjectId() != null ){
			String id = this.getId();
//			String alias = id.substring(this.getProjectId().length()+1); 
			alias = ResourceNode.makeResourceName(id);
			this.setAlias(alias);
		}
		session.setClipboard(this);
		
		return session;
	}
	
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop(){
		
		if(this.isFolder() && !(this instanceof ProcessNode)){
			Object clipboard = session.getClipboard();
			
			if(clipboard instanceof ResourceNode){
				ResourceNode resourceNode = (ResourceNode)clipboard;
				if( !this.getId().equals(resourceNode.getParentId()) ){
					if(!ResourceNode.TYPE_PROJECT.equals(resourceNode.getType())){
						File file = new File(resourceNode.getPath());
						
						if(file.exists()){
							File dstFile = new File(this.getPath() + File.separatorChar + resourceNode.getName());
							
							file.renameTo(dstFile);
						}
						
						try {
							this.setExpanded(true);
							
							return new Object[]{new Refresh(this.expand()), new Remover(resourceNode)};
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return null;
	}
	
	@ServiceMethod
	public void deleteResourcePicker(){
		this.setId(null);
	}
	
	public static String makeResourceName(String resourceName){
		if(resourceName != null){
			if(resourceName.endsWith(".java")){
				resourceName = resourceName.substring(0, resourceName.length()-5);
			}
			if(resourceName.indexOf(File.separatorChar) > -1)
				resourceName = resourceName.substring(resourceName.indexOf(File.separatorChar)+1);
			else
				resourceName = null;
		}
		
		if(resourceName != null)
			resourceName = resourceName.replace(File.separatorChar, '.');
		
		return resourceName;
	}
	public static String makePackageName(String packageName){
		
		if(packageName != null){
			if(packageName.endsWith(".java")){
				packageName = packageName.substring(0, packageName.length()-5);

				if(packageName.lastIndexOf(File.separatorChar) > -1)
					packageName = packageName.substring(0, packageName.lastIndexOf(File.separatorChar));
				else
					packageName = null;
			}
			
			if(packageName.indexOf(File.separatorChar) > -1)
				packageName = packageName.substring(packageName.indexOf(File.separatorChar)+1);
			else
				packageName = null;

		}

		if(packageName != null)
			packageName = packageName.replace(File.separatorChar, '.');		
		
		
		return packageName;
	}
	
	public static String makeClassName(String className){
		if(className != null){
			if(className.endsWith(".java"))
				className = className.substring(0, className.length()-5);
			
			if(className.indexOf(File.separatorChar) > -1)
				className = className.substring(className.lastIndexOf(File.separatorChar)+1);

			
			className = className.replace(File.separatorChar, '.');
		}
		
		return className;
	}
}