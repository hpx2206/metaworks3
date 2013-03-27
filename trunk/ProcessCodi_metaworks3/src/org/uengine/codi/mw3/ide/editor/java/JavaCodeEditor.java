package org.uengine.codi.mw3.ide.editor.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.uengine.codi.mw3.ide.editor.Editor;
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

	public JavaCodeEditor(String filename, String type) {
		super(filename, type);
	}
	
	@ServiceMethod(loadOnce=true, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Menu editorMenu(){
		JavaEditorMenu editorMenu = new JavaEditorMenu();
		editorMenu.setContext(true);
		
		return editorMenu;
	}

	@ServiceMethod(payload={"filename", "content"}, target=ServiceMethodContext.TARGET_STICK)
	public Object[] quickMenu(){
		session.setClipboard(this);
		
		JavaSourceMenu sourceMenu = new JavaSourceMenu();
		sourceMenu.setContext(true);
		
		return new Object[]{new Refresh(session), sourceMenu};
	}
	
	public void save(){
		File file = new File(this.getFilename());
		try {
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			writer.write(this.getContent());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
