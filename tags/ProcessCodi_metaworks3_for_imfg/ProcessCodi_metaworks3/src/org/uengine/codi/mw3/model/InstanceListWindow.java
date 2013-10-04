package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",  
      options={"hideLabels", "minimize"}, 
      values={"true", "true"})

public class InstanceListWindow extends Window {
	
	public InstanceListWindow(){}
	
	public InstanceListWindow(Session session) throws Exception {
		this.session = session;
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.setPreloaded(true);

		
		if(instanceListPanel.isPreloaded()){
			PersonalPerspective personalPerspective = new PersonalPerspective();
			personalPerspective.session = session;
			this.instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		}else{
			this.setInstanceListPanel(instanceListPanel);	
		}
	}
	
	InstanceListPanel instanceListPanel;
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
	
		
	@AutowiredFromClient
	public Session session;
}