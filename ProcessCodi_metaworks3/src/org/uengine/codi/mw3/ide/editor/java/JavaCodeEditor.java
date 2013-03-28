package org.uengine.codi.mw3.ide.editor.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.aspectj.org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.ide.CloudClassLoader;
import org.uengine.codi.mw3.ide.CompilationChecker;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.platform.Console;
import org.uengine.codi.platform.SecurityContext;

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

	private ICompilationUnit unit;
	
	@ServiceMethod(payload={"name", "filename", "content"}, mouseBinding="right", target=ServiceMethodContext.TARGET_STICK)
	public Object[] editorMenu(){
		session.setClipboard(this);

		JavaEditorMenu editorMenu = new JavaEditorMenu();
		editorMenu.setContext(true);

		return new Object[]{new Refresh(session), editorMenu};
	}

	@ServiceMethod(payload={"name", "filename", "content"}, target=ServiceMethodContext.TARGET_STICK)
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
			this.unit = compCheck.generateCompilationUnit(this.getContent());
			
			IProblem[] problems = (IProblem[]) compCheck.getErrors(this.unit);
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

	public Object runJava(){
		
		try {
			CloudClassLoader ccl = new CloudClassLoader(jbPath);
			ccl.load();
			
			String className = new String(this.unit.getMainTypeName());
			String packageName = CharOperation.toString(this.unit.getPackageName());
			if(packageName != null && !packageName.isEmpty())
				className = packageName + "." + className;
						
			Class clazz = ccl.getCl().loadClass(className);
			
			System.setOut(new PrintStream(new ByteArrayOutputStream()){
				public void println(String str){
						Console.addLog(str);
				}

				public void println(Object x) {
						Console.addLog(""+x);
				}
				
			});	

			System.setErr(new PrintStream(new ByteArrayOutputStream()){
				public void println(String str){
				}
				public void print(String str){
				}
				public void println(Object x) {
				}
			});
			
			
			Method m = clazz.getMethod("main",	new Class[] { String[].class });
			m.invoke(clazz, new Object[] { new String[0] });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Object runMetaworks(){
		
		try {
			CloudClassLoader ccl = new CloudClassLoader(jbPath);
			ccl.load();
			
			String className = new String(this.unit.getMainTypeName());
			String packageName = CharOperation.toString(this.unit.getPackageName());
			if(packageName != null && !packageName.isEmpty())
				className = packageName + "." + className;
				
			Object o = ccl.getCl().loadClass(className).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

			Window outputWindow = new Window();
			outputWindow.setPanel(o);
			
			return outputWindow;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
