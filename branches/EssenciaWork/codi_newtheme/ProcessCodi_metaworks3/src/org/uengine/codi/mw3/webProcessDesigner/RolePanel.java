package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.kernel.Role;

public class RolePanel implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String editorId;
	@Id
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
	String parentEditorId;
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}
	ArrayList<Role> roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	public RolePanel(){
		setMetaworksContext(new MetaworksContext());
		roleList = new ArrayList<Role>();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showWholeRole(){
		
		RolePanel rolePanel = new RolePanel();
		rolePanel.setEditorId(editorId+"_dummy");
		rolePanel.setParentEditorId(editorId);
		rolePanel.setRoleList(this.getRoleList());
		
		rolePanel.getMetaworksContext().setHow("list");
		rolePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		modalWindow.setTitle("");
		modalWindow.setPanel(rolePanel);
		
		return modalWindow;
	}
}
