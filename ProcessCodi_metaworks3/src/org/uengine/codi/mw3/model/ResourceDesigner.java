package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public abstract class ResourceDesigner extends ContentWindow{
	
	String alias;
	@Id
		public String getAlias() {
			return alias;
		}
	
		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	
	@ServiceMethod
	abstract public void load();

}
