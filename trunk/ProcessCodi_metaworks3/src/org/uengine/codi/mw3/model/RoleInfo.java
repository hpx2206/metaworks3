package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;

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

}
