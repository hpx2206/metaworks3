package org.uengine.codi.mw3.ide;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.IDETopPanel;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Session;

public class CloudIDE {
	
	@AutowiredFromClient
	public Locale localeManager;
		
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
		
	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
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

	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}
	
	public void load(Session session) throws Exception{
		// make workspace
		Workspace workspace = new Workspace();
		workspace.load(session);
		this.setWorkspace(workspace);
		
		Navigator navigator = new Navigator();		
		navigator.load(workspace);

		CloudWindow navigatorWindow = new CloudWindow("explorer");
		navigatorWindow.getTabs().add(navigator);
		
		CloudWindow editorWindow = new CloudWindow("editor");
		
//		CloudInstanceWindow instanceWindow = new CloudInstanceWindow();
		/*
		ResourceNode resourceNode = new ResourceNode();
		resourceNode.setProjectId("test0001");
		resourceNode.setName("uengine.metadata");
		resourceNode.setId(resourceNode.getProjectId() + File.separatorChar + "uengine.metadata");
		resourceNode.setPath(codebase +File.separatorChar +  tenantId + File.separatorChar + resourceNode.getId());
		
		System.out.println(resourceNode.getPath());
		
		MetadataEditor editor = new MetadataEditor(resourceNode);
		try {
			editor.loadPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editorWindow.getTabs().add(editor);
		*/
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(editorWindow);
//		centerLayout.setEast(instanceWindow);
		
		//centerLayout.setCenter(editorWindow);
		//centerLayout.setSouth(etcWindow);
		centerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, east__size:250");
		
		Layout outerLayout = new Layout();
		outerLayout.setWest(navigatorWindow);
		outerLayout.setCenter(centerLayout);
		outerLayout.setNorth(new IDETopPanel(session));
		
		/*
		if(false && "oce".equals(session.getUx())){
			outerLayout.setNorth(new ProcessTopPanel(session));
			
		}else{
			outerLayout.setNorth(new TopPanel(session));
		}
		*/
		
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:250, north__size:52");

		this.setLayout(outerLayout);
		

	}
	
	public void load(Session session, String projectId){
		/*
		String userId =  session.getUser().getUserId();
		
		String codebasePath = GlobalContext.getPropertyString("codebase", "codebase");
		
		String basePath = codebasePath + File.separatorChar  + "users" + File.separatorChar + userId + File.separatorChar + projectId;
		String srcPath = File.separatorChar + "src";
		String defaultBuildOutputPath = File.separatorChar + "WebContent" + File.separatorChar + "WEB-INF" + File.separatorChar + "classes";
		String libraryPath = File.separatorChar + "WebContent" + File.separatorChar + "WEB-INF" + File.separatorChar + "lib";

		CodiFileUtil.mkdirs(basePath + File.separatorChar +srcPath);
		CodiFileUtil.mkdirs(basePath + File.separatorChar +defaultBuildOutputPath);
		CodiFileUtil.mkdirs(basePath + File.separatorChar +libraryPath);

		JavaBuildPath jbPath = new JavaBuildPath();
		jbPath.setBasePath(basePath);
		jbPath.setSrcPath(srcPath);
		jbPath.setDefaultBuildOutputPath(defaultBuildOutputPath);
		jbPath.setLibraryPath(libraryPath);

		CloudClassLoader ccl = new CloudClassLoader(jbPath);
		ccl.load();
		
		ContentLibrary contentLib = new ContentLibrary();
		contentLib.load(ccl);

		ResourceLibrary resourceLib = new ResourceLibrary();
		resourceLib.load(jbPath.getBasePath());

		Navigator navigator = new Navigator();		
		navigator.load(basePath, projectId);

		CloudWindow navigatorWindow = new CloudWindow("explorer");
		navigatorWindow.getTabs().add(navigator);

		CloudWindow editorWindow = new CloudWindow("editor");
		//JavaCodeEditor editor = new JavaCodeEditor(File.separatorChar + "src" + File.separatorChar + "examples" + File.separatorChar + "TestProject.java");
		//editorWindow.getTabs().add(editor);
		
		Servers servers = new Servers();
		servers.load();
		
		CloudWindow etcWindow = new CloudWindow("etc");
		etcWindow.getTabs().add(new Console());
		etcWindow.getTabs().add(servers);
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(editorWindow);
		//centerLayout.setSouth(etcWindow);
		centerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:150");
		
		Layout outerLayout = new Layout();
		outerLayout.setWest(navigatorWindow);
		outerLayout.setCenter(centerLayout);
		outerLayout.setNorth(new TopPanel(session));
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:250, north__size:52");

		this.setLayout(outerLayout);

		this.setJavaBuildPath(jbPath);
		this.setContentLibrary(contentLib);
		this.setResourceLibrary(resourceLib);

		this.setPageNavigator(new PageNavigator("ide"));
		*/
		
		/*
		Map settings = new HashMap();
		settings.put(CompilerOptions.OPTION_LineNumberAttribute,CompilerOptions.GENERATE);
		settings.put(CompilerOptions.OPTION_SourceFileAttribute,CompilerOptions.GENERATE);

		CompileRequestorImpl requestor = new CompileRequestorImpl();
		Compiler compiler = new Compiler(new NameEnviro	nmentImpl(unit),
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
	
	@AutowiredFromClient(select="typeof currentEditorId!='undefined' && currentEditorId==autowiredObject.id")
	public Editor editor;
	
	@ServiceMethod(payload={"currentEditorId", "workspace"}, keyBinding="Ctrl+S@Global", target=ServiceMethodContext.TARGET_APPEND)
	public Object save() throws Exception{
		editor.workspace = this.getWorkspace();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
						
		modalWindow.setTitle("$SaveCompleteTitle");
		modalWindow.setPanel(localeManager.getString("$SaveCompleteMessage"));
		modalWindow.getButtons().put("$Confirm", new Refresh(editor.save()));		
		
		return modalWindow;
	}

	@ServiceMethod(payload={"currentEditorId"}, keyBinding="Ctrl+W@Global", target=ServiceMethodContext.TARGET_NONE)
	public Object close(){
		return new Remover(new CloudTab(this.getCurrentEditorId()));
	}

	@ServiceMethod(keyBinding="Ctrl+Shift+R@Global", target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow openResource(){
		OpenResource openResource = new OpenResource();

		return new ModalWindow(openResource, 600, 450, "Open Resource");
	}
}
