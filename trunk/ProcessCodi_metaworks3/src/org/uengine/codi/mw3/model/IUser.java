package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

public interface IUser extends IDAO{
	public static final String MW3_WHERE_ROLEUSER_PICKER_CALLER = "roleUserPickerCaller";
	public static final String MW3_WHERE_ROLEUSER_PICKER = "roleUserPicker";
	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	
	public String getName();
	public void setName(String name);
	
	public String getNetwork();
	public void setNetwork(String network);
	
//	@NonLoadable
//	@NonSavable
//	public String getInstanceId() ;
//	public void setInstanceId(String instanceId);
	
	@ServiceMethod(callByContent=true)
	public ContactList addContact() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] addFollower() throws Exception;
		
	@ServiceMethod(when="edit", inContextMenu=true, callByContent=true, target=TARGET_POPUP)
	public Popup pickUp() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_POPUP)
	public Popup openRoleUserPicker() throws Exception;

	@ServiceMethod(callByContent=true, target=TARGET_POPUP)
	public Popup detail() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_SELF)
	public UnstructuredProcessInstanceStarter chat() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] removeFollower() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] removeContact() throws Exception;
	
	@ServiceMethod(target="popup", payload={"userId", "network", "name"})
	public Popup info() throws Exception;

}
