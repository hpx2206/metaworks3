package org.uengine.codi.mw3.ide.editor.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.CloudClassLoader;
import org.uengine.codi.mw3.ide.JavaBatchBuilder;
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
		
		this.setType(Editor.TYPE_JAVA);
	}

	//private ICompilationUnit unit;
	
	@ServiceMethod(payload={"name", "filename", "content"}, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Object[] editorMenu(){
		session.setClipboard(this);

		JavaEditorMenu editorMenu = new JavaEditorMenu();
		editorMenu.setContext(true);

		return new Object[]{new Refresh(session), editorMenu};
	}

	@ServiceMethod(payload={"name", "filename", "content"}, target=ServiceMethodContext.TARGET_POPUP)
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

		CloudClassLoader ccl = new CloudClassLoader(jbPath);
		ccl.load();
		
		List<String> buildClass = new ArrayList<String>();		
		List<String> classPath = ccl.makeClassPath();
		
		JavaBatchBuilder jBatchBuilder = new JavaBatchBuilder();
		jBatchBuilder.setClassPath(classPath);
		jBatchBuilder.setOutputPath(jbPath.getBasePath() + File.separatorChar + jbPath.getDefaultBuildOutputPath());
		jBatchBuilder.setBuildClass(buildClass);
		
		if(jBatchBuilder.build(errorList)){
			return errorList;
		}else{
			return null;
		}
		
		/*
		try {
			CloudClassLoader ccl = new CloudClassLoader(jbPath);
			ccl.load();

			CompilationChecker compCheck = new CompilationChecker(ccl.getCl());
			this.unit = compCheck.generateCompilationUnit(this.getContent());
			
			IProblem[] problems = compCheck.getErrors(this.unit);
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
		*/
	}
}
