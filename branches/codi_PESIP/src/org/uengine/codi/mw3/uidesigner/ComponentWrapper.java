package org.uengine.codi.mw3.uidesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

public class ComponentWrapper implements ContextAware{
	
	Object component;
	
		public Object getComponent() {
			return component;
		}
	
		public void setComponent(Object component) {
			this.component = component;
		}
		
	String id;
	@Id	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	MetaworksContext metaworksContext;

		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	public ComponentWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComponentWrapper init(Object component) {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("palette");
		this.component = component;
		
		return this;
	}
	
	@AutowiredFromClient(onDrop=true)   //TODO: why it doesn't work?
	public ComponentWrapper droppedComponentWrapper;

	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", when="design")
	public Object onComponentDropped(){
		if(session.getClipboard() instanceof ComponentWrapper){
			ComponentWrapper droppedComponentWrapper = (ComponentWrapper)session.getClipboard();
			
			if(droppedComponentWrapper.getId()==null){//means from palette
				droppedComponentWrapper.setId(System.currentTimeMillis() + ""); //set the unique id (current time in millis is suffice for each client)
			}
				
			droppedComponentWrapper.setMetaworksContext(new MetaworksContext());
			droppedComponentWrapper.getMetaworksContext().setWhen("design");
			
			if(getComponent() instanceof IComponentDesigner){
				((IComponentDesigner)getComponent()).onDropped(droppedComponentWrapper);
				
				return this;
			}else{
				setComponentByDropping(droppedComponentWrapper);

				return this;
			}
			
		}
		
		return null;
	}
	
	protected void setComponentByDropping(ComponentWrapper droppedComponentWrapper){
		setComponent(droppedComponentWrapper);

	}

	
	@ServiceMethod(when="design", callByContent=true, mouseBinding="drag")//, target=ServiceMethodContext.TARGET_NONE)
	public Session drag(){
		session.setClipboard(this);
		return session;
	}

	@ServiceMethod(when="palette", callByContent=true/*it should not be needed anymore after the key-and-comp instance mapping is built */, mouseBinding="drag")//, target=ServiceMethodContext.TARGET_NONE)
	public Session chooseFromPalette(){
		session.setClipboard(this);
		return session;
	}

	
	@ServiceMethod(when="design", callByContent=true, inContextMenu=true, target="popup", mouseBinding="dbl-click")
	public ModalWindow edit(){
		ModalWindow editor = new ModalWindow(this);
		this.getMetaworksContext().setWhen("edit");
		
		return editor;
	}

	

}
