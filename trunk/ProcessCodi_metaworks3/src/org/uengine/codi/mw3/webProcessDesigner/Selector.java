package org.uengine.codi.mw3.webProcessDesigner;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;

public class Selector implements ContextAware {

	public final static String TYPE_TREE = "tree";
	
	Object target;
		public Object getTarget() {
			return target;
		}
		public void setTarget(Object target) {
			this.target = target;
		}

	String type;
		@Hidden
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String value;
		@Hidden
		@NotNull(message="먼저 선택해주세요.")
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
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
	
	public void load() {
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
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object openSelector(){
		this.load();
		
		return new Popup(this);
	}
}
