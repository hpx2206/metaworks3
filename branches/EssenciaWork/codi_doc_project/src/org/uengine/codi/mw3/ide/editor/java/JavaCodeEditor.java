package org.uengine.codi.mw3.ide.editor.java;

import java.util.ArrayList;
import java.util.List;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.CloudClassLoader;
import org.uengine.codi.mw3.ide.JavaBatchBuilder;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;

public class JavaCodeEditor extends Editor {

	public JavaCodeEditor() {
	}

	public JavaCodeEditor(ResourceNode resourceNode) {
		super(resourceNode);

		this.setType(Editor.TYPE_JAVA);
	}

	@ServiceMethod(payload = { "name", "filename", "content", "resourceNode" }, mouseBinding = "right", target = ServiceMethodContext.TARGET_STICK)
	public Object[] editorMenu() {
		session.setClipboard(this);

		JavaEditorMenu editorMenu = new JavaEditorMenu(this.getResourceNode());
		editorMenu.setContext(true);

		return new Object[] { new Refresh(session), editorMenu };
	}

	@ServiceMethod(payload = { "name", "filename", "content" }, target = ServiceMethodContext.TARGET_POPUP)
	public Object[] quickMenu() {
		session.setClipboard(this);

		JavaSourceMenu sourceMenu = new JavaSourceMenu();
		sourceMenu.setContext(true);

		return new Object[] { new Refresh(session), sourceMenu };
	}

	@Override
	public Object save() {
		super.save();

		Project project = workspace.findProject(this.getResourceNode().getProjectId());
		
		ArrayList<JavaCodeError> errorList = new ArrayList<JavaCodeError>();

		CloudClassLoader ccl = new CloudClassLoader();

		List<String> buildClass = new ArrayList<String>();
		List<String> classPath = ccl.makeClassPath(project.getBuildPath());

		buildClass.add(this.getResourceNode().getPath());

		JavaBatchBuilder jBatchBuilder = new JavaBatchBuilder();
		jBatchBuilder.setClassPath(classPath);
		jBatchBuilder.setSourcePath(project.getBuildPath().getSources().get(0)
				.getPath());
		jBatchBuilder.setOutputPath(project.getBuildPath()
				.getDefaultBuildOutputPath().getPath());
		jBatchBuilder.setBuildClass(buildClass);

		System.out.println("build : " + jBatchBuilder.build(errorList));

		for (JavaCodeError error : errorList) {
			System.out.println("[ERROR] " + error.getLineNumber() + ":"
					+ error.getMessage());

			/*
			 * if(!error.) error.setType(JavaCodeError.TYPE_WARNING);
			 */

			errorList.add(error);
		}

		/*
		 * if(jBatchBuilder.build(errorList)){ return errorList; }else{
		 */
		return null;
		// }

		/*
		 * try { CloudClassLoader ccl = new CloudClassLoader(jbPath);
		 * ccl.load();
		 * 
		 * CompilationChecker compCheck = new CompilationChecker(ccl.getCl());
		 * this.unit = compCheck.generateCompilationUnit(this.getContent());
		 * 
		 * IProblem[] problems = compCheck.getErrors(this.unit);
		 * compCheck.display();
		 * 
		 * for (IProblem problem : problems) { JavaCodeError error = new
		 * JavaCodeError();
		 * 
		 * error.setMessage(problem.getMessage());
		 * error.setLineNumber(problem.getSourceLineNumber());
		 * 
		 * if(!problem.isError()) error.setType(JavaCodeError.TYPE_WARNING);
		 * 
		 * errorList.add(error); }
		 * 
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
	}

	public Object runMetaworks(){
		try {
			Project project = workspace.findProject(this.getResourceNode().getProjectId());
			
			/*CloudClassLoader ccl = new CloudClassLoader();
			ccl.load(project.getBuildPath());
			*/
			
			/*
			String className = new String(this.unit.getMainTypeName());
			String packageName = CharOperation.toString(this.unit.getPackageName());
			*/
			
			//if(packageName != null && !packageName.isEmpty())
			//	className = packageName + "." + className;
				
			//CodiClassLoader.codiClassLoader.setSourcePath(new File[]{new File(project.getBuildPath().getSources().get(0).getPath())});
			//Thread.currentThread().setContextClassLoader(CodiClassLoader.codiClassLoader);
			
			/*
			CodiClassLoader cl = CodiClassLoader.codiClassLoader;
			cl.setSourcePath(new File[]{new File(project.getBuildPath().getSources().get(0).getPath())});
			Thread.currentThread().setContextClassLoader(cl);
			*/
			
			Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
			Object o = Thread.currentThread().getContextClassLoader().loadClass("examples.JavaTest").newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

			MetaworksRemoteService.getInstance().getMetaworksType("examples.JavaTest");
			
			Window outputWindow = new Window();
			outputWindow.setPanel(o);
			
			return outputWindow;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
