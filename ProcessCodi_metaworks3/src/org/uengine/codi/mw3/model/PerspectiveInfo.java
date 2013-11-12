package org.uengine.codi.mw3.model;

import org.metaworks.annotation.TypeSelector;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;


public class PerspectiveInfo implements IPerspectiveInfo{

	Followers followers;
		public Followers getFollowers() {
			return followers;
		}
		public void setFollowers(Followers followers) {
			this.followers = followers;
		}
	@Override
	public void add() throws Exception {
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
	public void load() throws Exception{
		// TODO Auto-generated method stub
		followers = new Followers();
	}

	@Override
	public void followersLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
