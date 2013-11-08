package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class AppDetail {

	IApp value;
		public IApp getValue() {
			return value;
		}
		public void setValue(IApp value) {
			this.value = value;
		}
		
	public AppDetail(){
		
	}
	
	public AppDetail(IApp value){
		this.setValue(value);		
	}

}
