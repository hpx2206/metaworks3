package org.uengine.codi.mw3.model;

import org.metaworks.widget.ModalWindow;

public interface IPerspectiveInfo {

	public Followers getFollowers();
	public void setFollowers(Followers followers);
	
	public void add() throws Exception;
	public Object[] delete();
	public ModalWindow modify()  throws Exception;
	public void load() throws Exception;
	public void followersLoad() throws Exception;
}
