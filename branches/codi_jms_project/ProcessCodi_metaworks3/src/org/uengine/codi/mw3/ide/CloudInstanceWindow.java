package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="InstanceViewContent",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class CloudInstanceWindow extends ContentWindow {
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	public CloudInstanceWindow(){
		
	}
	public CloudInstanceWindow(String id){
		
	}
}
