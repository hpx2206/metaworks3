package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IContact extends IDAO{

	@Id
	@ORMapping(databaseFields = {"friendId", "friendName", "network", "mood"}, objectFields = {"userId", "name", "network", "mood"})
	public IUser getFriend();
	public void setFriend(IUser friend);

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

}
