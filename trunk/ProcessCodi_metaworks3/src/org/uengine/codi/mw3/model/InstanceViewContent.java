package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;

@Face(ejsPath="genericfaces/Window.ejs", displayName="InstanceViewContent", options={"hideLabels"}, values={"true"})
public class InstanceViewContent extends ContentWindow {

	//ProcessManagerRemote processManager;	
	//InstanceView instanceView;	

	@Autowired
	InstanceView instanceView;			
		public InstanceView getInstanceView() {
			return instanceView;
		}
	
		public void setInstanceView(InstanceView instanceView) {
			this.instanceView = instanceView;
		}
	
	public InstanceViewContent(){
		
	}
	
	public void load(IInstance instance) throws Exception{
		//instanceView = new InstssanceView();		
		//instanceView.processManager = this.processManager;
		
		instanceView.load(instance);
	}	

}
