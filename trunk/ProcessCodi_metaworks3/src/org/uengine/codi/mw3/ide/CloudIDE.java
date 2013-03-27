package org.uengine.codi.mw3.ide;

import java.io.File;

import org.eclipse.jdt.core.compiler.IProblem;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.TopPanel;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Session;

public class CloudIDE {
	public CloudIDE() {

		/*		setPageNavigator(new PageNavigator("ide"));

		Layout innerLayout = new Layout();

		ContentWindow contentWindow = new ContentWindow();
		contentWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());

		ConsoleWindow consoleWindow = new ConsoleWindow();
		consoleWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());

		if(session.getDefId() != null){
			ClassDefinition classDefinition = new ClassDefinition();
			classDefinition.setAlias(session.getDefId());
			classDefinition.afterDeserialization();
			classDefinition.getMetaworksContext().setWhen("view");

			contentWindow.setPanel(classDefinition);
		}

		innerLayout.setCenter(contentWindow);		
//		innerLayout.setSouth(consoleWindow);

		innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:'20%'");


		HintWindow hintWindow = new HintWindow();
		hintWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());
		hintWindow.load(session, getPageNavigator().getPageName());

		ResourceWindow resourceWindow = new ResourceWindow();		
		resourceWindow.getMetaworksContext().setHow(pageNavigator.getPageName());

		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:500, north__size:52");
		outerLayout.setNorth(new TopPanel(session));
		outerLayout.setWest(resourceWindow);
		//outerLayout.setEast(hintWindow);
		outerLayout.setCenter(innerLayout);		

		setLayout(outerLayout);		*/
	}

	Layout layout;
	public Layout getLayout() {
		return layout;
	}
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	JavaBuildPath javaBuildPath;
	@Hidden
	@AutowiredToClient
	public JavaBuildPath getJavaBuildPath() {
		return javaBuildPath;
	}
	public void setJavaBuildPath(JavaBuildPath javaBuildPath) {
		this.javaBuildPath = javaBuildPath;
	}

	ContentLibrary contentLibrary;
	public ContentLibrary getContentLibrary() {
		return contentLibrary;
	}
	public void setContentLibrary(ContentLibrary contentLibrary) {
		this.contentLibrary = contentLibrary;
	}

	ResourceLibrary resourceLibrary;
	public ResourceLibrary getResourceLibrary() {
		return resourceLibrary;
	}
	public void setResourceLibrary(ResourceLibrary resourceLibrary) {
		this.resourceLibrary = resourceLibrary;
	}

	String currentEditorId;
	public String getCurrentEditorId() {
		return currentEditorId;
	}
	public void setCurrentEditorId(String currentEditorId) {
		this.currentEditorId = currentEditorId;
	}

	public void load(String userId, String projectId){

		//String baseDir = "D:/ide_workspace/" + userId + "/" + projectId;
		String basePath = "/Users/somehow/codebase/" + userId + "/" + projectId;
		String srcPath = basePath + "/src";
		String defaultBuildOutputPath = basePath + "/WebContent/WEB-INF/classes";
		String libraryPath = basePath + "/WebContent/WEB-INF/lib";

		Login login = new Login();
		login.setUserId("test");
		login.setPassword("test");

		Session session = new Session();

		try {
			//session = login.loginService();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JavaBuildPath jbPath = new JavaBuildPath(libraryPath, defaultBuildOutputPath);
		jbPath.setBasePath(basePath);
		jbPath.setSrcPath(srcPath);
		jbPath.setDefaultBuildOutputPath(defaultBuildOutputPath);
		jbPath.setLibraryPath(libraryPath);

		ContentLibrary contentLib = new ContentLibrary();
		contentLib.load(jbPath.getLibraryPath(), jbPath.getDefaultBuildOutputPath());

		ResourceLibrary resourceLib = new ResourceLibrary();
		resourceLib.load(jbPath.getSrcPath(), jbPath.getLibraryPath(), jbPath.getDefaultBuildOutputPath());

		Navigator navigator = new Navigator();		
		navigator.load(basePath, projectId);

		CloudWindow navigatorWindow = new CloudWindow();
		navigatorWindow.getTabs().add(navigator);

		CloudWindow editorWindow = new CloudWindow("editor");
		JavaCodeEditor editor = new JavaCodeEditor(srcPath + "/examples" + File.separatorChar + "TestProject.java");

		editorWindow.getTabs().add(editor);

		Layout outerLayout = new Layout();
		outerLayout.setWest(navigatorWindow);
		outerLayout.setCenter(editorWindow);
		outerLayout.setNorth(new TopPanel(session));
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:500, north__size:52");

		this.setLayout(outerLayout);

		this.setJavaBuildPath(jbPath);
		this.setContentLibrary(contentLib);
		this.setResourceLibrary(resourceLib);

		try {
			CloudClassLoader ccl = new CloudClassLoader(jbPath.getLibraryPath(), jbPath.getDefaultBuildOutputPath());
			ccl.load();

			CompilationChecker compCheck = new CompilationChecker(ccl.getCl());
			IProblem[] problems = compCheck.getErrors("TestProject", editor.load());
			
			compCheck.display();
			
			/*
			boolean error = false;
			for (IProblem problem : problems) {
			   StringBuffer buffer = new StringBuffer();
			   buffer.append(problem.getMessage());
			   buffer.append(" line: ");
			   buffer.append(problem.getSourceLineNumber());
			   String msg = buffer.toString(); 
			   if(problem.isError()) {
			      error = true; 
			      msg = "Error:\n" + msg;
			   }	
			   else 
			      if(problem.isWarning())
			         msg = "Warning:\n" + msg;

			   System.out.println(msg);  
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		/*
		Map settings = new HashMap();
		settings.put(CompilerOptions.OPTION_LineNumberAttribute,CompilerOptions.GENERATE);
		settings.put(CompilerOptions.OPTION_SourceFileAttribute,CompilerOptions.GENERATE);

		CompileRequestorImpl requestor = new CompileRequestorImpl();
		Compiler compiler = new Compiler(new NameEnvironmentImpl(unit),
										DefaultErrorHandlingPolicies.proceedWithAllProblems(),
										settings,
										requestor,
										new DefaultProblemFactory(Locale.getDefault()));

		compiler.compile(new ICompilationUnit[] { unit });

		List problems = requestor.getProblems();
		boolean error = false;
		for (Iterator it = problems.iterator(); it.hasNext();) {
			IProblem problem = (IProblem)it.next();
			StringBuffer buffer = new StringBuffer();
			buffer.append(problem.getMessage());
			buffer.append(" line: ");
			buffer.append(problem.getSourceLineNumber());
			String msg = buffer.toString(); 
			if(problem.isError()) {
				error = true; 
				msg = "Error:\n" + msg;
			}	
			else 
			if(problem.isWarning())
				msg = "Warning:\n" + msg;

			System.out.println(msg);  
		}	

		if (!error) {
			try {
				ClassLoader loader = new CustomClassLoader(getClass().getClassLoader(),requestor.getResults());
				String className = CharOperation.toString(unit.getPackageName()) + "." + new String(unit.getMainTypeName());
				Class clazz = loader.loadClass(className);
				Method m = clazz.getMethod("main",new Class[] {String[].class});
				m.invoke(clazz,new Object[] { new String[0] });
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		 */
		/*
		IProject myProject = new Project();
		IProgressMonitor myProgressMonitor;
		myProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, myProgressMonitor);
		 */

	}


	@AutowiredFromClient(select="currentEditorId==filename")
	public JavaCodeEditor editor;
	
	@ServiceMethod(payload={"currentEditorId"}, keyBinding="Ctrl+S@Global", target=ServiceMethodContext.TARGET_NONE)
	public void save(){
		editor.save();
	}

	@ServiceMethod(payload={"currentEditorId"}, keyBinding="Ctrl+W@Global", target=ServiceMethodContext.TARGET_NONE)
	public Object close(){
		return new Remover(new CloudTab(this.getCurrentEditorId()));
	}


	@ServiceMethod(keyBinding="Ctrl+Shift+R@Global", target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow openResource(){
		OpenResource openResource = new OpenResource();

		return new ModalWindow(openResource, 600, 0, "Open Resource");
	}
}
