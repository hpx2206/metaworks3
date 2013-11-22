package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public interface IPerspectiveInfo {

	public Followers getFollowers();
	public void setFollowers(Followers followers);
	
	
	public void add() throws Exception;
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$Remove")
	public Object[] delete() throws Exception;
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$Modify")
	public ModalWindow modify()  throws Exception;
	public void load() throws Exception;
	public void followersLoad() throws Exception;
}
