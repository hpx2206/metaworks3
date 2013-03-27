package org.uengine.codi.mw3.ide;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class Templete implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public Templete(){
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setMetaworksContext(metaworksContext);
	}
}
