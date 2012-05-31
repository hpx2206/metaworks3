package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs", 
	  options={"hideLabels", "maximize"}, 
	  values={"true", "true"})
public abstract class ResourceDesigner extends ContentWindow {
	
	String alias;
		@Id
		@Hidden
		public String getAlias() {
			return alias;
		}
	
		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	
	@ServiceMethod
	@Hidden
	abstract public void load();

}
