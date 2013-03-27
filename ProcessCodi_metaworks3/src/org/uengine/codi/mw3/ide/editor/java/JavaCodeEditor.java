package org.uengine.codi.mw3.ide.editor.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jdt.core.compiler.IProblem;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Menu;
import org.uengine.codi.mw3.ide.CloudClassLoader;
import org.uengine.codi.mw3.ide.CompilationChecker;
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

	@Override
	public Object save(){
		super.save();

		ArrayList<JavaCodeError> errorList = new ArrayList<JavaCodeError>();
		
		try {
			CloudClassLoader ccl = new CloudClassLoader(jbPath);
			ccl.load();

			CompilationChecker compCheck = new CompilationChecker(ccl.getCl());
			IProblem[] problems = compCheck.getErrors(this.getContent());
			compCheck.display();

			for (IProblem problem : problems) {
				JavaCodeError error = new JavaCodeError();
				
				error.setMessage(problem.getMessage());
				error.setLineNumber(problem.getSourceLineNumber());
				
				if(!problem.isError())
					error.setType(JavaCodeError.TYPE_WARNING);
				
				errorList.add(error);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return errorList;
	}
}
