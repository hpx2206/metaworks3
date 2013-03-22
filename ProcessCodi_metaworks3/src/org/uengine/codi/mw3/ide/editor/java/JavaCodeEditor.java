package org.uengine.codi.mw3.ide.editor.java;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.menu.ResourceContextMenu;
import org.uengine.codi.mw3.model.Session;

public class JavaCodeEditor extends Editor {

	@AutowiredFromClient
	public Session session;
	
	public JavaCodeEditor(){
		super();
	}
	
	public JavaCodeEditor(String filename) {
		super(filename);
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	public Menu quickMenu(){
		session.setClipboard(this);
		
		return new ResourceContextMenu();
	}
}
