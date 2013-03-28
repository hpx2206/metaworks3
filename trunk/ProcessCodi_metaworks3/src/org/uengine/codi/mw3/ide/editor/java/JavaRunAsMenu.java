package org.uengine.codi.mw3.ide.editor.java;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.menu.CloudMenu;
import org.uengine.codi.mw3.ide.view.Console;

public class JavaRunAsMenu extends CloudMenu {
	
	public JavaRunAsMenu(){
		this.setId("RunAs");
		this.setName("Run As");
		
		this.add(new MenuItem("javaApplication", "Java Application"));
		this.add(new MenuItem("metaworksApplication", "Metaworks Application"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object javaApplication() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof JavaCodeEditor){
			JavaCodeEditor editor = (JavaCodeEditor)clipboard;
			editor.jbPath = jbPath;
			
			ArrayList<JavaCodeError> errorList = (ArrayList<JavaCodeError>)editor.save();
			
			ArrayList<String> messages = new ArrayList<String>();
			
			if(errorList.size() == 0){
				messages.add("launch Java Application...");		
				
				editor.runJava();
			}
			
			return new ToAppend(new Console(), messages);
		}else{
			return null;
		}
		
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] metaworksApplication() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof JavaCodeEditor){
			JavaCodeEditor editor = (JavaCodeEditor)clipboard;
			editor.jbPath = jbPath;
			
			ArrayList<JavaCodeError> errorList = (ArrayList<JavaCodeError>)editor.save();
			
			ArrayList<String> messages = new ArrayList<String>();
			
			if(errorList.size() == 0){
				messages.add("launch Metaworks Application...");		
			}
			
			return new Object[]{new ToAppend(new Console(), messages), editor.runMetaworks()};
		}
		
		return null;
		
	}
}
