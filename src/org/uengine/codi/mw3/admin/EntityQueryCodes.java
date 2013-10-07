
package org.uengine.codi.mw3.admin;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.QuerySourceCode;

public class EntityQueryCodes implements ContextAware {

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	/*
	public EntityQueryCodes(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		setQuerySourceCode(new QuerySourceCode());
		setEntityModeler(new EntityModeler());
	}
	

		
	@ServiceMethod(when="edit")
	public void next(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	*/
}
