package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(
		ejsPathMappingByContext=
	{
		"{how: 'and', face: 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/And.ejs'}",
		"{how: 'or', face: 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Or.ejs'}",
		"{how: 'eval', face: 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Evaluate.ejs'}"
	})
public class Condition implements ContextAware{
	
	public Condition(){
		
	}
	
	public boolean isMet(String scope) throws Exception {
		return false;
	}

	private String name;
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
}
