package org.uengine.codi.mw3.ide.libraries;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.IRole;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Role;
import org.uengine.codi.mw3.model.Session;

public class PerspectiveRole extends Perspective {

	public PerspectiveRole() {
		setLabel("Role");
	}
	
	ArrayList<LibraryRole> libraryRoleList;
		public ArrayList<LibraryRole> getLibraryRoleList() {
			return libraryRoleList;
		}
		public void setLibraryRoleList(ArrayList<LibraryRole> libraryRoleList) {
			this.libraryRoleList = libraryRoleList;
		}

	@Override
	protected void loadChildren() throws Exception {
		libraryRoleList = new ArrayList<LibraryRole>();
		Role role = new Role();
		role.getMetaworksContext().setWhere("navigatorIDE");
		role.setComCode(session.getEmployee().getGlobalCom());
		IRole iRole = role.findByGlobalCom();
		while(iRole.next()){
			LibraryRole libraryRole = new LibraryRole();
			libraryRole.copyFrom(iRole);
			libraryRoleList.add(libraryRole);
		}
		
	}

	@Override
	protected void unloadChildren() throws Exception {
		libraryRoleList = null;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup addRole() {
		IRole role = (IRole) new Role();
		role.getMetaworksContext().setWhere("admin");
		role.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		Popup popup = new Popup();
		popup.setPanel(role);
		
		return popup;
	}
	
	@AutowiredFromClient
	public Session session;
	
}
