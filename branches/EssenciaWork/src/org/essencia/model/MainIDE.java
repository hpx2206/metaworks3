package org.essencia.model;
import org.essencia.ide.Navigator;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.Workspace;
import org.metaworks.widget.layout.Layout;


public class MainIDE {
	
	final static String NAVIGATOR = "NavigatorWindow";
	final static String EDITOR = "EditorWindow";
	final static String PROPERTIES = "PropertiesWindow";
	
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
		
	@ServiceMethod(callByContent=true)
	public MainPanel load(){
		
		
		Navigator navigator = new Navigator();		
		navigator.load("CodeBase", "EssenciaWork");
		
		MainWindow navigatorWindow = new MainWindow(null,NAVIGATOR);
		navigatorWindow.setPanel(navigator);
		MainWindow editorWindow = new MainWindow(null,EDITOR);
		PropertiesWindow propertiesWindow = new PropertiesWindow();
		
		Layout layout = new Layout();
		layout.setId("main");
		layout.setName("main");
		layout.setCenter(editorWindow);
		layout.setWest(navigatorWindow);
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__size:250");
		
		
		Layout outerLayout = new Layout();
		outerLayout.setId("outer");
		outerLayout.setCenter(layout);
		outerLayout.setEast(propertiesWindow);
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, east__size:350");
		
		this.setLayout(outerLayout);
		
		return new MainPanel(this.getLayout());
	}
}
