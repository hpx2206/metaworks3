package org.essencia.ide;

import org.essencia.mini.kernel.Practice;
import org.essencia.mini.view.TreeNodeView;
import org.essencia.model.NewFolder;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;



public class ResourceContextMenu extends CloudMenu{
	
	public ResourceContextMenu(){
		
	}
	
	public ResourceContextMenu(TreeNodeView componentTreeNode){
		this.setComponentTreeNode(componentTreeNode);
		
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		
		this.add(new MenuItem("newFolder","Folder"));
		this.add(new MenuItem("newPractice","Practice"));
		this.add(new MenuItem("newMethod","Method"));
		this.add(new MenuItem("newProcess","Process"));
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder  newFolder = new NewFolder();
		return new ModalWindow(newFolder, 300, 150, "New Folder");
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newPractice(){
		Practice practice = new Practice();
		return new ModalWindow(practice, 300, 350, "practice");
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] newMethod(){
		return null;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] newProcess(){
		return null;
	}
	
}
