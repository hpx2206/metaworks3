package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.model.Session;

public class ProcessDefinitionNavigatorPanel {

	@AutowiredFromClient
	public Session session;
	
	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}
	
	public void load(){
		
		// TODO : 뭐지? 확인 필요 cjw
		//Navigator navigator = new Navigator();		
		//navigator.load(workspace);
	}
	
}
