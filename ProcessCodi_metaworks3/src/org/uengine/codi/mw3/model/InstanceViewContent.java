package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.springframework.beans.factory.annotation.Autowired;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="InstanceViewContent",
	  options={"hideLabels", "maximize"}, 
	  values={"true", "true"})
public class InstanceViewContent extends ContentWindow {
	
	public Session session;

	//ProcessManagerRemote processManager;	
	//InstanceView instanceView;	

	@Autowired
	@Name
	public InstanceView instanceView;			
		public InstanceView getInstanceView() {
			return instanceView;
		}
	
		public void setInstanceView(InstanceView instanceView) {
			this.instanceView = instanceView;
		}
	
		
	String instanceName;
	@Name
	@Hidden
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

	public InstanceViewContent(){
		
	}
	
	public void load(IInstance instance) throws Exception{
		//instanceView = new InstssanceView();		
		//instanceView.processManager = this.processManager;
		
		instanceView.load(instance);

		setInstanceName(instanceView.getInstanceName());
//		if(instance instanceof IInstance){
//			setInstanceName(instance.getName());
//			
//		}else
//			setInstanceName(((Instance)instance).databaseMe().getName());
	}	

}
