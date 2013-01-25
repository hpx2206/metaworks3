package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class PropertiesWindow extends ModalWindow {

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
		return new Object[]{new Remover(new PropertiesWindow()), new ApplyProperties(this.id, this.getPanel())};
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		return new Object[]{new Remover(new PropertiesWindow())};
		
	}

}
