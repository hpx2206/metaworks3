package org.uengine.codi.mw3.ide;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class OpenResource {

	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	String selectedResource;
		public String getSelectedResource() {
			return selectedResource;
		}
		public void setSelectedResource(String selectedResource) {
			this.selectedResource = selectedResource;
		}
		
	@ServiceMethod(payload="selectedResource", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] open(){
		return new Object[]{new Remover(new ModalWindow())};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
