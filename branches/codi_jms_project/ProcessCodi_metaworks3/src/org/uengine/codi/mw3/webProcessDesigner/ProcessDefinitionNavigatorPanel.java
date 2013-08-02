package org.uengine.codi.mw3.webProcessDesigner;

import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;

public class ProcessDefinitionNavigatorPanel {

	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}
	
	public void load(){
		System.out.println("===> load end0");
		
		// make workspace
		Workspace workspace = new Workspace();
		workspace.load();
		this.setWorkspace(workspace);
		
		Navigator navigator = new Navigator();		
		navigator.load(workspace);
		
		System.out.println("===> load start");
	}
	
}
