package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", options="hideLabels", values="true")
public class InstanceViewContent extends ContentPanel{
	

	
	public void load(IInstance instance) throws Exception{
		instanceView = new InstanceView();
		instanceView.load(instance);
	}
	

	InstanceView instanceView;
	
		public InstanceView getInstanceView() {
			return instanceView;
		}
	
		public void setInstanceView(InstanceView instanceView) {
			this.instanceView = instanceView;
		}

}
