package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
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

	Long taskId;
		@Hidden
		public Long getTaskId() {
			return taskId;
		}
	
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}

	String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}

	Long rootInstId;
		@Hidden
		public Long getRootInstId() {
			return rootInstId;
		}
	
		public void setRootInstId(Long rootInstId) {
			this.rootInstId = rootInstId;
		}

	public InstanceViewContent(){
		
	}
	
	public void load(IInstance instance) throws Exception{
		//this.setInstanceName(instance.getName());
		
		instanceView.session = session;
		instanceView.setMetaworksContext(getMetaworksContext());
		instanceView.load(instance);
		
		this.setInstanceView(instanceView);
		
	}	
	


}
