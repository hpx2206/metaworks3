package org.uengine.kernel.designer.web;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;
import org.uengine.kernel.Role;

public class RoleView extends CanvasDTO implements ContextAware {
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	transient Role role;
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
	transient PropertiesWindow propertiesWindow;
	@Hidden
		public PropertiesWindow getPropertiesWindow() {
			return propertiesWindow;
		}
		public void setPropertiesWindow(PropertiesWindow propertiesWindow) {
			this.propertiesWindow = propertiesWindow;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = new ModalWindow();
		Role role = (Role)propertiesWindow.getPanel();
		role.setCurrentEditorId(this.getEditorId());
		role.setMetaworksContext(propertiesWindow.getMetaworksContext());
		role.setRoleView(this);
		
		popup.setId(this.getId());				// 꼭 필요함
		popup.setTitle(role.getDisplayName() != null ? role.getDisplayName().getText() : "role setting..");
		popup.setPanel(role);
		popup.setWidth(700);
		popup.setHeight(500);
		
		return popup;
	}
}
