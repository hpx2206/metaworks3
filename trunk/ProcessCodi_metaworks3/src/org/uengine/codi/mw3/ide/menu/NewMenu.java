package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.SQLClientEditor;
import org.uengine.codi.mw3.ide.editor.TerminalEditor;
import org.uengine.codi.mw3.ide.templete.NewClass;
import org.uengine.codi.mw3.ide.templete.NewFile;
import org.uengine.codi.mw3.ide.templete.NewFolder;
import org.uengine.codi.mw3.ide.templete.NewForm;
import org.uengine.codi.mw3.ide.templete.NewProcess;
import org.uengine.codi.mw3.ide.templete.NewRule;

public class NewMenu extends CloudMenu {

	@AutowiredFromClient(select="typeof resourceNode != 'undefined' && resourceNode.projectId == autowiredObject.id")
	public Project project;
	
	public NewMenu(){
		this(null);
	}
	
	public NewMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("new");
		this.setName("New");
		
		//this.add(new MenuItem("newPackage", "Package"));
		this.add(new MenuItem("newProcess", "Process"));
		//this.add(new MenuItem("newRole", "Role"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		//this.add(new MenuItem("newClass", "Class"));
		this.add(new MenuItem("newForm", "Form"));
		this.add(new MenuItem("newFolder", "Folder"));
		this.add(new MenuItem("newFile", "File"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		//this.add(new MenuItem("newTerminal", "Terminal"));
		//this.add(new MenuItem("newSQLClient", "SQL Client"));

	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newPackage(){
		return new ModalWindow();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newClass(){
		NewClass newClass = new NewClass();
		
		return new ModalWindow(newClass, 300, 150, "New Java Class");
	}
	
	@ServiceMethod(payload="resourceNode", target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newForm(){
		NewForm newForm = new NewForm();
		newForm.setResourceNode(this.getResourceNode());
		newForm.project = project;
		newForm.load();
		
		return new ModalWindow(newForm, 300, 150, "New Form");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder newFolder = new NewFolder();
		
		return new ModalWindow(newFolder, 300, 150, "New Folder");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFile(){
		NewFile newFile = new NewFile();
		
		return new ModalWindow(newFile, 300, 150, "$templete.file");
	}
	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newProcess(){
		NewProcess newProcess = new NewProcess();
		
		return new ModalWindow(newProcess, 300, 150, "$templete.process");
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newRule(){
		NewRule newRule = new NewRule();
		
		return new ModalWindow(newRule, 300, 150, "$templete.rule");
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
