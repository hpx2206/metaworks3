package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.widget.ModalWindow;

public class RoleInfo extends PerspectiveInfo{

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
	public Object[] delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalWindow modify() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() throws Exception {
		this.followersLoad();
		
	}

	@Override
	public void followersLoad() throws Exception {
		/*
		 * TODO role follow 
		 * 
		 * followers = new RoleFollowers();
		 * followers.session = session;
		 * followers.load();
		  
		 */
		
	}

}
