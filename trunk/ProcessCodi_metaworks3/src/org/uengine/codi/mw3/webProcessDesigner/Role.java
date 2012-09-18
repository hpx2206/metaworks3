package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class Role implements ContextAware , Cloneable{
	
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
		

	@Override
		public boolean equals(Object obj) {
			return obj!=null && obj instanceof Role && ((Role)obj).getName().equals(getName());
		}

	@ServiceMethod(when="edit", callByContent=true)
	public RolePanel add() throws CloneNotSupportedException{
		
		Role added = (Role) this.clone();
		added.setMetaworksContext(new MetaworksContext());
		added.getMetaworksContext().setWhen("view");
		rolePanel.getRoles().add(added);
		
		setName("");
		getMetaworksContext().setWhen("edit");

		rolePanel.setNewRole(this);
		
		return rolePanel;
	}
	
	@ServiceMethod(when="view", callByContent=true)
	public RolePanel remove(){
		rolePanel.getRoles().remove(this);
		return rolePanel;
	}
	
	@AutowiredFromClient
	public RolePanel rolePanel;
	
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, mouseBinding="drag-enableDefault")
	public Session drag() throws Exception {
		session.setClipboard( this );
		return session;
	}
}
