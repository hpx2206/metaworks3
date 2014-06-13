package org.essencia.model;

import org.essencia.ide.Navigator;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.Workspace;
import org.metaworks.widget.Tab;
import org.metaworks.widget.TabPanel;
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
		
		Navigator navigator = new Navigator();		
		navigator.load("CodeBase", "EssenciaWork");
		
		MainWindow navigatorWindow = new MainWindow(null, NAVIGATOR);
		navigatorWindow.setPanel(navigator);
		MainWindow editorWindow = new MainWindow(null, EDITOR);
		
		TabPanel tp = new TabPanel();

		Tab t = new Tab("tab");
		
		t.setTabContent("탭컨탠츠");
		tp.addTab(t);

		Tab t1 = new Tab("tab1");
		t1.setTabContent("탭컨탠츠1");
		tp.addTab(t1);
		
		TabPanel tp2 = new TabPanel();
		
		Tab t2 = new Tab("2");
		t2.setTabContent("탭컨탠츠2");
		tp2.addTab(t2);
		
		
		Tab t3 = new Tab("3");
		t3.setTabContent("탭컨탠츠3");
		tp2.addTab(t3);
		
		Layout innerLayout = new Layout();
		innerLayout.setCenter(tp);
		innerLayout.setSouth(tp2);
		editorWindow.setPanel(innerLayout);
		
		layout = new Layout();
		layout.setId("main");
		layout.setName("main");
		layout.setWest(navigatorWindow);
		layout.setCenter(editorWindow);
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__size:250");
		
		MainPanel mainPanel = new MainPanel();
		mainPanel.setPanel(getLayout());
		
		return mainPanel;
	}
}
