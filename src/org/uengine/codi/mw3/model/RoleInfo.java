package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;

public class RoleInfo extends PerspectiveInfo{

	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
	public RoleInfo(){
		
	}
	public RoleInfo(Session session){
		this.session = session;
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] delete() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getId());		
		role.copyFrom(role.databaseMe());
		role.setIsDeleted("1");
		
		role.syncToDatabaseMe();
		role.flushDatabaseMe();
		
		return new Object[]{new Refresh(new InstanceListPanel()),new Remover(role)};
	}

	@Override
	public ModalWindow modify() throws Exception {
		Role role = new Role();
		role.setRoleCode(this.getId());
		role.copyFrom(role.databaseMe());
		
		role.getMetaworksContext().setWhere("admin");
		role.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		role.setLogoFile(new MetaworksFile());
		
		
		
		return new ModalWindow(role, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$EditTopic");
	}

	@Override
	public void load() throws Exception {
		this.followersLoad();
		
		Role role = new Role();
		role.setRoleCode(this.getId());
		
		try {
			role.copyFrom(role.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(role.getDescr());
		this.setDescription(role.getDescr());
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(role.getUrl());
		logoFile.setFilename(role.getThumbnail());
		this.setLogoFile(logoFile);
		
		
	}

	@Override
	public void followersLoad() throws Exception {
		followers = new RoleFollowers();
		followers.session = session;
		followers.load();
	}
	
	
	@Face(displayName="$role.Subscribe")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object subscribe() throws Exception {
		
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleCode(this.getId());
		roleUser.setEmpCode(session.getEmployee().getEmpCode());
		IRoleUser findRoleUser = roleUser.findMe();
		
		if(findRoleUser != null){
			throw new Exception("$AlreadyExistingRole");
		}
		
		roleUser.createDatabaseMe();
		roleUser.flushDatabaseMe();
		
		this.getFollowers().session = session;
		this.getFollowers().load();
			
		return new Refresh(this.followers);
	
	}

}
