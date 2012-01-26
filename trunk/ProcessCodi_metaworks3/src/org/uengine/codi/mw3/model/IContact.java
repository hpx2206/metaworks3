package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;



public interface IContact extends IDAO{
	
	@ORMapping(databaseFields = { "friendId"}, objectFields = { "userId"})
	public IUser getFriends();
	public void setFriends(IUser friends);
	
	public String getUserId();
	public void setUserId(String userId);

	public String getFriendId();
	public void setFriendId(String friendId);
	
	public String getFriendName();
	public void setFriendName(String friendName);

	@ServiceMethod(when="addFollower", callByContent=true)
	public Followers addFollower() throws RemoteException, Exception;

}
