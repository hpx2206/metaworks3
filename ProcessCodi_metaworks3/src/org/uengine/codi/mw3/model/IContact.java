package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;



public interface IContact extends IDAO{
	
	@ORMapping(databaseFields = {"friendId", "friendName"}, objectFields = {"userId", "name"})
	public IUser getFriend();
	public void setFriend(IUser friend);
	
	@Id
	public String getUserId();
	public void setUserId(String userId);

	@ServiceMethod(when="addFollower", callByContent=true)
	public Followers addFollower() throws RemoteException, Exception;

	@ServiceMethod(when="pickUp", callByContent=true, target="opener")
	public User pickUp() throws RemoteException, Exception;
	
	@ServiceMethod(callByContent=true, target="opener")
	public User roleUserPickUp() throws RemoteException, Exception;

}
