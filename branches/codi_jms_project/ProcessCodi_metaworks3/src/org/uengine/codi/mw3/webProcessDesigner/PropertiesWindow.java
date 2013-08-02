package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class PropertiesWindow extends ModalWindow implements Serializable , ContextAware{
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
		
	@ServiceMethod(callByContent=true)
	public void refresh(){
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] apply(){
		return new Object[]{new ApplyProperties(this.id, this.getPanel()), new Remover(new PropertiesWindow())};
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		return new Object[]{new Remover(new PropertiesWindow())};
		
	}

}
