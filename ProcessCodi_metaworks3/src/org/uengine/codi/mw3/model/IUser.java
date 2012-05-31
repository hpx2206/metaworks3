package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IUser extends IDAO{
	public static final String MW3_WHERE_ROLEUSER_PICKER_CALLER = "roleUserPickerCaller";
	public static final String MW3_WHERE_ROLEUSER_PICKER = "roleUserPicker";
	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	
	public String getName();
	public void setName(String name);
	
//	@NonLoadable
//	@NonSavable
//	public String getInstanceId() ;
//	public void setInstanceId(String instanceId);
	
	@ServiceMethod(when="addContact", callByContent=true)
	public ContactList addContact() throws Exception;
	
	@ServiceMethod(when="pickUp", callByContent=true, target=TARGET_POPUP)
	public Popup pickUp() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_POPUP)
	public Popup openRoleUserPicker() throws Exception;

	@ServiceMethod(callByContent=true, target=TARGET_POPUP)
	public Popup detail() throws Exception;
	
	@ServiceMethod
	public UnstructuredProcessInstanceStarter chat() throws Exception;

	@ServiceMethod(callByContent=true, inContextMenu=true)
	public Object[] removeFollower() throws Exception;
}
