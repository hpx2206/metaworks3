package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.templete.FileRenamer;
import org.uengine.codi.mw3.ide.templete.NewFolder;
import org.uengine.codi.mw3.model.Session;

public class ValueChainContextMenu extends Menu {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public Project project;
	
	public ValueChainContextMenu(){
	}
	public ValueChainContextMenu(TreeNode treeNode){
		
		this.setId(treeNode.getId() + "ValueChainContext");
		this.setName("ValueChainContext");
		this.setContext(true);
		
		if( treeNode instanceof MajorProcessDefinitionNode){
			this.add(new MenuItem("newFolder", "$resource.menu.new.folder"));
			this.add(new MenuItem("selectProcess", "$resource.menu.selectProcess"));
		}
		
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("remove", "$resource.menu.remove"));
		this.add(new MenuItem("rename", "$resource.menu.rename"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object selectProcess(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof MajorProcessDefinitionNode){
			MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)clipboard;
			node.session = session;
			node.project = project;
			return node.selectProcess();			
		}else{
			return null;
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] remove(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof MajorProcessDefinitionNode){
			MajorProcessDefinitionNode node = (MajorProcessDefinitionNode)clipboard;
			return new Object[]{ new Remover(node, true)};
		}else if(clipboard instanceof MinorProcessDefinitionNode){
			MinorProcessDefinitionNode node = (MinorProcessDefinitionNode)clipboard;
			return new Object[]{ new Remover(node, true)};
		}
		return null;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder newFolder = new NewFolder();
		
		return new ModalWindow(newFolder, 300, 150, "New Folder");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow rename(){
		FileRenamer fileRenamer = new FileRenamer();
		
		return new ModalWindow(fileRenamer, 300, 150, "Rename..");
	}

}
