package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="$processthread",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class CloudInstanceWindow extends ContentWindow {
	
	String id;
		@Hidden
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
