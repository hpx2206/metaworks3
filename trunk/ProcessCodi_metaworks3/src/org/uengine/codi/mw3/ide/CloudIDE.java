package org.uengine.codi.mw3.ide;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.TopPanel;
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
		public JavaBuildPath getJavaBuildPath() {
			return javaBuildPath;
		}
		public void setJavaBuildPath(JavaBuildPath javaBuildPath) {
			this.javaBuildPath = javaBuildPath;
		}
		
	public void load(String userId, String projectId){
		
		String baseDir = "D:/ide_workspace/" + userId + "/" + projectId;
		String defaultBuildOutputPath = baseDir + "/WebContent/WEB-INF/classes";
		String libraryPath = baseDir + "/WebContent/WEB-INF/lib";

		Login login = new Login();
		login.setUserId("test");
		login.setPassword("test");
		
		Session session = new Session();
		
		try {
			session = login.loginService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Navigator navigator = new Navigator();		
		navigator.load(baseDir, projectId);

		CloudWindow navigatorWindow = new CloudWindow();
		navigatorWindow.getTabs().add(navigator);

		CloudWindow editorWindow = new CloudWindow("editor");
		JavaCodeEditor editor = new JavaCodeEditor("D:\\ide_workspace\\somehow\\codi\\src\\examples\\GoogleChart.java");
		editorWindow.getTabs().add(editor);
		
		Layout outerLayout = new Layout();
		outerLayout.setWest(navigatorWindow);
		outerLayout.setCenter(editorWindow);
		outerLayout.setNorth(new TopPanel(session));
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:500, north__size:52");
		
		this.setLayout(outerLayout);

		JavaBuildPath jbPath = new JavaBuildPath(libraryPath, defaultBuildOutputPath);
		jbPath.load();
		
		this.setJavaBuildPath(jbPath);
	}
	
	@ServiceMethod(keyBinding="Ctrl+S@Global", target=ServiceMethodContext.TARGET_NONE)
	public void save(){
		System.out.println("save");
	}
	
	@ServiceMethod(keyBinding="Ctrl+Shift+R@Global", target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow openResource(){
		OpenResource openResource = new OpenResource();
		
		return new ModalWindow(openResource, "Open Resource");
	}
}
