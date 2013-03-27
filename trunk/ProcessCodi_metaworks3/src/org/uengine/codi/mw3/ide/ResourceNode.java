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
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Session;

public class ResourceNode extends TreeNode {
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public JavaBuildPath jbPath;
	
	@Override
	public Object expand() throws Exception {
		
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();

		File file = new File(jbPath.getBasePath() + File.separatorChar + this.getId());
		String[] childFilePaths = file.list();
		
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
			
			ResourceNode node = new ResourceNode();
			node.setId(this.getId() + File.separatorChar + childFile.getName());
			node.setName(childFile.getName());
			node.setParentId(this.getId());
			
			if(childFile.isDirectory()){
				node.setType(TreeNode.TYPE_FOLDER);
				node.setFolder(true);
			}
			else{
				node.setType(TreeNode.TYPE_FILE_TEXT);
			}
			
			child.add(node);
		}
				
		return new ToAppend(this, child);
	}
	
	@Override
	public Object action(){
		
		Editor editor;
		
		if(this.getName().endsWith(".java")){
			editor = new JavaCodeEditor(this.getId());
		}else if(this.getName().endsWith(".pwd")){
			editor = new ProcessEditor(this.getId());
		}else{
			int pos = this.getName().lastIndexOf(".");
			
			String ext = "";
			String type = "html";
			
			if(pos > -1){
				ext = this.getName().substring(pos);			
				
				if(".js".equals(ext)){
					type = "javascript";
				}else if(".xml".equals(ext)){
					type = "xml";
				}
			}

			editor = new Editor(this.getId(), type);
		}
		
		return new ToAppend(new CloudWindow("editor"), editor);
	}
	
	@ServiceMethod(payload={"id", "name"}, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Object[] showContextMenu() {
		session.setClipboard(this);
		
		return new Object[]{new Refresh(session), new ResourceContextMenu()};
		
		
		/*
		menu.setId("edit");
		menu.setName("Edit");
		
		// add undo menu item
		MenuItem undo = new MenuItem();
		undo.setId("undo");
		undo.setName("Undo");
		undo.setShortcut("Ctrl-Z");
		
		menu.add(undo);
		
		
		// add redo menu item
		MenuItem redo = new MenuItem();
		redo.setId("redo");
		redo.setName("Redo");
		redo.setShortcut("Ctrl-Shift-Z|Ctrl-Y");
		
		menu.add(redo);
		
		// divider
		menu.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		
		
		// add cut menu item
		MenuItem cut = new MenuItem();
		cut.setId("cut");
		cut.setName("Cut");
		cut.setShortcut("Ctrl-X");
		
		menu.add(cut);
		
		// add copy menu item
		MenuItem copy = new MenuItem();
		copy.setId("copy");
		copy.setName("Copy");
		copy.setShortcut("Ctrl-C");
		
		menu.add(copy);
		
		// add paste menu item
		MenuItem paste = new MenuItem();
		paste.setId("paste");
		paste.setName("Paste");
		paste.setShortcut("Ctrl-V");
		
		menu.add(paste);
		
		// add line menu item
		Menu lineMenu = new Menu();
		lineMenu.setId("line");
		lineMenu.setName("Line");
		lineMenu.setSub(true);
		
		MenuItem line = new MenuItem();
		line.setId("line");
		line.setName("Line");
		line.setSubMenu(lineMenu);
		
		menu.add(line);
		*/
	}
}
