package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IContact extends IDAO{

	@NonLoadable
	@NonSavable
	public boolean isChecked();
	public void setChecked(boolean checked);
	
	public String getFriendId();
	public void setFriendId(String friendId);
	
	@ORMapping(databaseFields = {"friendId", "friendName", "network", "mood"}, objectFields = {"userId", "name", "network", "mood"})
	public IUser getFriend();
	public void setFriend(IUser friend);

	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	
//	@NonSavable
//	@NonLoadable
//	public String getMood();
//	public void setMood(String mood);
//	
	
	@ServiceMethod(when="pickUp", callByContent=true, target="opener")
	public User pickUp() throws RemoteException, Exception;
	
	@ServiceMethod(callByContent=true, target="opener")
	public User roleUserPickUp() throws RemoteException, Exception;
	
	@Available(how="comitter")
	@ServiceMethod(callByContent=true, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, target=ServiceMethodContext.TARGET_SELF)
	public void check();
}
