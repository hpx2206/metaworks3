package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

@Face(options={"hideEditBtn", "fieldOrder"}, values={"true", ",,"})
public class FormProperties extends Properties implements ContextAware {
	
	@AutowiredFromClient
	public Form form;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String id;
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		@Face(displayName="$form.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	public FormProperties(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
	@ServiceMethod(callByContent=true)
	public Object apply(){
		form.setId(this.getId());
		form.setName(this.getName());
		return form;
	}
	
}
