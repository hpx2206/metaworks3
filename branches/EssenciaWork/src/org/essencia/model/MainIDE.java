package org.essencia.model;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.Workspace;
import org.metaworks.widget.layout.Layout;


public class MainIDE {
	
	final String NAVIGATOR = "navigator";
	final String EDITOR = "editor";
	
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
		MainWindow navigatorWindow = new MainWindow(null,NAVIGATOR);
		MainWindow editorWindow = new MainWindow(null,EDITOR);
		
		Layout layout = new Layout();
		layout.setId("main");
		layout.setName("main");
		layout.setCenter(editorWindow);
		layout.setWest(navigatorWindow);
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__size:250");
		
		this.setLayout(layout);
		return new MainPanel(this.getLayout());
	}
}
