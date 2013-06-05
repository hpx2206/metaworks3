package org.uengine.codi.mw3.ide;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.component.Tree;

public class ResourceTree extends Tree implements ContextAware {

	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	public ResourceTree(){
		super();
		setMetaworksContext(new MetaworksContext());
	}
}
