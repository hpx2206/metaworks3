package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.RoleResolutionContext;

public class Role implements ContextAware , Cloneable{
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	RoleResolutionContext roleResolutionContext;
		public RoleResolutionContext getRoleResolutionContext() {
			return roleResolutionContext;
		}
		public void setRoleResolutionContext(RoleResolutionContext roleResolutionContext) {
			this.roleResolutionContext = roleResolutionContext;
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
		if(!rolePanel.getRoles().contains(added)){
			added.setMetaworksContext(new MetaworksContext());
			added.getMetaworksContext().setWhen("view");
			rolePanel.getRoles().add(added);
		}
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
	
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow roleSetting() throws Exception{
		RoleSettingPanel roleSettingPanel = new RoleSettingPanel(this);
		roleSettingPanel.load();
		return new ModalWindow(roleSettingPanel , 600, 450,  "롤셋팅" );
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
