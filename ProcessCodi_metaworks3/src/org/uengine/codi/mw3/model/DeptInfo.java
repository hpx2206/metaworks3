package org.uengine.codi.mw3.model;

import org.metaworks.widget.ModalWindow;

public class DeptInfo extends PerspectiveInfo{


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
		System.out.println("dept ========= ");
		followers = new DeptFollowers();
		followers.load();
		
	}

	@Override
	public void followersLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
