package org.uengine.codi.mw3.model;

import org.metaworks.widget.ModalWindow;

public interface IPerspectiveInfo {

	public String getType();
	public void setType(String type);
	public void add();
	public Object[] delete();
	public ModalWindow modify()  throws Exception;
	public void load();
	public void followersLoad() throws Exception;
}
