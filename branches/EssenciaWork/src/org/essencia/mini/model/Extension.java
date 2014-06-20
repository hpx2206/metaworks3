package org.essencia.model;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(displayName="Extension", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"},values={"tempExtends"})
public class Extension implements Serializable , ContextAware{
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String tempExtends;
		@Face(displayName="Extends")	
		public String getTempExtends() {
			return tempExtends;
		}
		public void setTempExtends(String tempExtends) {
			this.tempExtends = tempExtends;
		}

		
	public Extension(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
}
