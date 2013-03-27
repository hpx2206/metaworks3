package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.JavaBuildPath;
import org.uengine.codi.mw3.ide.templete.NewClass;
import org.uengine.codi.mw3.ide.templete.NewFolder;
import org.uengine.codi.mw3.ide.templete.NewProcess;


public class NewMenu extends ResourceMenu {

	@AutowiredFromClient
	public JavaBuildPath jbPath;
	
	public NewMenu(){

		this.setId("new");
		this.setName("New");
		
		//this.add(new MenuItem("newPackage", "Package"));
		this.add(new MenuItem("newProcess", "Process"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("newClass", "Class"));
		this.add(new MenuItem("newFolder", "Folder"));
		
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newPackage(){
		return new ModalWindow();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newClass(){
		NewClass newClass = new NewClass();
		newClass.session = session;
		newClass.jbPath = jbPath;
		newClass.load();
		
		return new ModalWindow(newClass);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder newFolder = new NewFolder();
		
		return new ModalWindow(newFolder);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newProcess(){
		NewProcess newProcess = new NewProcess();
		
		return new ModalWindow(newProcess);
	}
}
