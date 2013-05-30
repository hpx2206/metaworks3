package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Session;

public class ResourceNode extends TreeNode {

	@AutowiredFromClient
	public Session session;
	
	public final static String TYPE_PROJECT 			= "project";
	
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
	public ResourceNode(){
	}

	public ResourceNode(Project project){
		this.setId(project.getId());
		this.setName(project.getId());
		this.setType(TYPE_PROJECT);
		this.setFolder(true);

		this.setPath(project.getPath());
	}
	

	@Override
	@ServiceMethod(payload={"id", "path"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception {
		
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		
		File file = new File(this.getPath());
		String[] childFilePaths = file.list();

		// folder
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
			
			if(childFile.isDirectory()){
				ResourceNode node = new ResourceNode();				
				node.setId(this.getId() + File.separatorChar + childFile.getName());				
				node.setName(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
				node.setParentId(this.getId());
				node.setType(TreeNode.TYPE_FOLDER);
				node.setFolder(true);
				
				child.add(node);
			}
		}
				
		// file
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
			
			if(!childFile.isDirectory()){
				ResourceNode node = new ResourceNode();
				node.setId(this.getId() + File.separatorChar + childFile.getName());
				node.setName(childFile.getName());
				node.setPath(this.getPath() + File.separatorChar + childFile.getName());
				node.setParentId(this.getId());
				node.setType(findNodeType(node.getName()));
				
				child.add(node);
			}
		}
	
				
		return new ToAppend(this, child);
	}
	
	public Editor beforeAction(){
		
		
		Editor editor = null;
		
		if(!this.isFolder()){
			String type = ResourceNode.findNodeType(this.getName());
			
			if(type.equals(TreeNode.TYPE_FILE_JAVA)){
				editor = new JavaCodeEditor(this);
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
	@ServiceMethod(payload={"id", "name", "path", "folder"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action(){
		return new ToAppend(new CloudWindow("editor"), this.beforeAction());
	}
	
	@ServiceMethod(payload={"id", "name"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		
		return new Object[]{new Refresh(session), new ResourceContextMenu()};
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
			}else if(".wpd".equals(ext)){
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
	
	@ServiceMethod(payload={"id", "name"}, mouseBinding="drag")
	public Object drag() {
		session.setClipboard(this);
		
		return new Object[]{session};
	}
}