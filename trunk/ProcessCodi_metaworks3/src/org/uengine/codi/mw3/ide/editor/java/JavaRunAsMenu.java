package org.uengine.codi.mw3.ide.editor.java;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.menu.CloudMenu;
import org.uengine.codi.mw3.ide.view.Console;

public class JavaRunAsMenu extends CloudMenu {
	
	@AutowiredFromClient(select="resourceNode.projectId == autowiredObject.id")
	public Project project; 

	public JavaRunAsMenu(){
		
	}
	
	public JavaRunAsMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("RunAs");
		this.setName("Run As");
		
		this.add(new MenuItem("javaApplication", "Java Application"));
		this.add(new MenuItem("metaworksApplication", "Metaworks Application"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object javaApplication() throws Exception {
		/*Object clipboard = session.getClipboard();
		if(clipboard instanceof JavaCodeEditor){
			JavaCodeEditor editor = (JavaCodeEditor)clipboard;
			editor.jbPath = jbPath;
			
			ArrayList<JavaCodeError> errorList = (ArrayList<JavaCodeError>)editor.save();
			
			ArrayList<String> messages = new ArrayList<String>();
			
			if(errorList != null && errorList.size() == 0){
				messages.add("launch Java Application...");		
				
				//editor.runJava();
			}
			
			return new ToAppend(new Console(), messages);
		}else{
			return null;
		}*/
		
		return null;
		
	}
	
	@ServiceMethod(payload="resourceNode", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] metaworksApplication() throws Exception {
		System.out.println("metaworksApplication");
		System.out.println(project);
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof JavaCodeEditor){
			JavaCodeEditor editor = (JavaCodeEditor)clipboard;
			editor.project = project;
			
			//ArrayList<JavaCodeError> errorList = (ArrayList<JavaCodeError>)editor.save();
			
			ArrayList<String> messages = new ArrayList<String>();
			
			/*if(errorList != null && errorList.size() == 0){
				messages.add("launch Metaworks Application...");		
			}*/
			
			//return new Object[]{new ToAppend(new Console(), messages), editor.runMetaworks()};
			return new Object[]{new ModalWindow(editor.runMetaworks(), 0, 0, "Run")};
		}
		
		return null;
		
	}
}
