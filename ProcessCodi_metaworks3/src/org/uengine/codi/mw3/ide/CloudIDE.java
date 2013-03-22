package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Hidden;
import org.metaworks.widget.layout.Layout;
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

		Navigator navigator = new Navigator();		
		navigator.load(baseDir, projectId);

		Navigator navigator2 = new Navigator();
		navigator2.load("D:/", "D:/");
		
		CloudWindow navigatorWindow = new CloudWindow();
		navigatorWindow.getTabs().add(navigator);
		navigatorWindow.getTabs().add(navigator2);

		CloudWindow editorWindow = new CloudWindow("editor");
		
		Layout outerLayout = new Layout();
		outerLayout.setWest(navigatorWindow);
		outerLayout.setCenter(editorWindow);
		outerLayout.setNorth(new Session());
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:500, north__size:52");
		
		this.setLayout(outerLayout);

		JavaBuildPath jbPath = new JavaBuildPath(libraryPath, defaultBuildOutputPath);
		jbPath.load();
		
		this.setJavaBuildPath(jbPath);
	}
}
