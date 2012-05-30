package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class Popup implements ContextAware {
	
	public Popup(){
		this(400,300, null);
	}

	public Popup(Object panel){		
		this(400,300, panel);
	}
	
	public Popup(int width, int height){
		this(width,height, null);
	}	
	
	public Popup(int width, int height, Object panel){
		setWidth(width);
		setHeight(height);
		setPanel(panel);
	}	

	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}		
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		

	String name;		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	int width;	
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}

	int height;
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}	
	
	@ServiceMethod(when=MetaworksContext.WHEN_NEW, callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup openPopup(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return this;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW)
	public Popup closePopup(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return this;
	}
	
}


