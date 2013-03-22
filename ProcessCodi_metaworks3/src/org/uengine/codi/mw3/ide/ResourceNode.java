package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Session;

public class ResourceNode extends TreeNode {
	@AutowiredFromClient
	public Session session;
	
	@Override
	public Object expand() throws Exception {
		
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();

		File file = new File(this.getId());
		String[] childFilePaths = file.list();
		
		for(int i=0; i<childFilePaths.length; i++){
			File childFile = new File(this.getId() + "/" + childFilePaths[i]);
			
			ResourceNode node = new ResourceNode();
			node.setId(childFile.getAbsolutePath());
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
		return new ToAppend(new CloudWindow("editor"), new JavaCodeEditor(this.getId()));
	}
	
	@ServiceMethod(loadOnce=true, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Menu showContextMenu() {
		session.setClipboard(this);
		
		return new ResourceContextMenu();
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
