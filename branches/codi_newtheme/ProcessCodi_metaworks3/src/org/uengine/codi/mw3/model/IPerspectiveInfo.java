package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public interface IPerspectiveInfo {

	public Followers getFollowers();
	public void setFollowers(Followers followers);
	
	public Boolean getJoined();
	public void setJoined(Boolean joined);
	
	public void add() throws Exception;
	
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$Remove")
	public Object[] delete() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$Modify")
	public ModalWindow modify()  throws Exception;
	
	public void load() throws Exception;
	
	public void followersLoad() throws Exception;
	
	public void settingJoined() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] unSubscribe() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] subscribe() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true, eventBinding=EventContext.EVENT_CHANGE, bindingHidden=true)
	public void refresh() throws Exception;
}
