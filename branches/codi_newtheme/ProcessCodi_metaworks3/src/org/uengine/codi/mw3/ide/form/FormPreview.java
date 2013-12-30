package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class FormPreview implements ContextAware {

	Object form;
		public Object getForm() {
			return form;
		}
		public void setForm(Object form) {
			this.form = form;
		}

	public FormPreview(){
		
	}
	
	public FormPreview(Object form){
		this.setForm(form);
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true)
	public void viewMode(){
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_VIEW})
	@ServiceMethod(callByContent=true)
	public void editMode(){
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@Available(when={MetaworksContext.WHEN_VIEW, MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true)	
	public void newMode(){
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);		
	}
}
