package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.editor.SQLClientEditor;
import org.uengine.codi.mw3.ide.editor.TerminalEditor;
import org.uengine.codi.mw3.ide.templete.NewClass;
import org.uengine.codi.mw3.ide.templete.NewFile;
import org.uengine.codi.mw3.ide.templete.NewFolder;
import org.uengine.codi.mw3.ide.templete.NewProcess;
import org.uengine.codi.mw3.ide.templete.NewRole;

public class NewMenu extends CloudMenu {

	public NewMenu(){

		this.setId("new");
		this.setName("New");
		
		//this.add(new MenuItem("newPackage", "Package"));
		this.add(new MenuItem("newProcess", "Process"));
		this.add(new MenuItem("newRole", "Role"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("newClass", "Class"));
		this.add(new MenuItem("newFolder", "Folder"));
		this.add(new MenuItem("newFile", "File"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("newTerminal", "Terminal"));
		this.add(new MenuItem("newSQLClient", "SQL Client"));
		
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
		
		return new ModalWindow(newClass, 300, 150, "New Java Class");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder newFolder = new NewFolder();
		
		return new ModalWindow(newFolder, 300, 150, "New Folder");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFile(){
		NewFile newFile = new NewFile();
		
		return new ModalWindow(newFile, 300, 150, "New File");
	}
	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newProcess(){
		NewProcess newProcess = new NewProcess();
		
		return new ModalWindow(newProcess, 300, 150, "New Process");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newRole(){
		NewRole newRole = new NewRole();
		
		return new ModalWindow(newRole, 300, 150, "New Role");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object newTerminal(){
		return new ToAppend(new CloudWindow("editor"), new TerminalEditor());
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object newSQLClient() throws Exception{
		
		return new ToAppend(new CloudWindow("editor"), new SQLClientEditor());
	}
}
