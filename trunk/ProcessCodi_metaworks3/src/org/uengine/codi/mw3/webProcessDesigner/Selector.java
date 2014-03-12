package org.uengine.codi.mw3.webProcessDesigner;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;

public class Selector implements ContextAware {

	public final static String TYPE_TREE = "tree";
	
	@AutowiredFromClient
	public Session session;
	
	Object target;
		public Object getTarget() {
			return target;
		}
		public void setTarget(Object target) {
			this.target = target;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public Selector(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("selector");
	}
	
	public void load() throws Exception{
	}
	
	@ServiceMethod(payload={"value", "name"}, validate=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] select(){
		
		this.getMetaworksContext().setHow("selector");
		
		return new Object[]{new Remover(new Popup()), new ToOpener(this)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		return new Object[]{new Remover(new Popup())};
	}
	

	@Hidden
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK)
	public Object openSelector()  throws Exception{
		this.load();
		
		return new Popup(this);
	}
}
