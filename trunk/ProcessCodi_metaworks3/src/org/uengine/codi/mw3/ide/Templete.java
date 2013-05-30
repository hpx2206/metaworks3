package org.uengine.codi.mw3.ide;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;

public class Templete implements ContextAware {
	
	@AutowiredFromClient
	public Session session;
	
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
