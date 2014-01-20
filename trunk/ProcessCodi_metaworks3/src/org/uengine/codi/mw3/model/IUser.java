package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Group;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/model/IUser.ejs",
	  ejsPathMappingByContext={"{how: '" + IUser.HOW_INFO + "', face: 'dwr/metaworks/org/uengine/codi/mw3/model/IUserInfo.ejs'}"})
public interface IUser extends IDAO{
	public static final String MW3_WHERE_ROLEUSER_PICKER_CALLER = "roleUserPickerCaller";
	public static final String MW3_WHERE_ROLEUSER_PICKER = "roleUserPicker";

	public static final String HOW_PICKER 					= "picker";
	public static final String HOW_PICKERLIST 				= "pickerlist";
	
	public final static String HOW_INFO				 		= "info";
	
	public final static String WHERE_SELF			 		= "self";
	public final static String WHERE_FRIENDS		 		= "friends";
	public final static String WHERE_FOLLOWERS 	 			= "followers";
	public final static String WHERE_ADDCONTACT 			= "addcontact";
	public final static String WHERE_ADDFOLLOWER 			= "addfollower";
	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	
	@Name
	public String getName();
	public void setName(String name);
	
	public String getNetwork();
	public void setNetwork(String network);
	
	public String getMood();
	public void setMood(String mood);
	
	@Available(how=HOW_PICKERLIST)
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK)
	public Object[] pickUp() throws Exception;
	
	@Hidden(how={HOW_INFO, HOW_PICKER}, where={WHERE_SELF, WHERE_ADDCONTACT, WHERE_ADDFOLLOWER, WHERE_FOLLOWERS})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK)
	public Popup detail() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] showWall() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] showSchedule() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_SELF)
	public UnstructuredProcessInstanceStarter chat() throws Exception;
	
	@Available(condition="(typeof self == 'undefined' || !self) && (typeof friend == 'undefined' || !friend)")
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] addContactForInfo() throws Exception;
	
	@Available(where=WHERE_ADDCONTACT)
	@ServiceMethod(callByContent=true, target=TARGET_APPEND, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK)
	public Object[] addContactForList() throws Exception;

	@Available(condition="typeof friend != 'undefined' && friend")
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] removeContact() throws Exception;
	
	@Available(where=WHERE_ADDFOLLOWER)
	@ServiceMethod(callByContent=true, target=TARGET_APPEND, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK)
	public Object[] addFollower() throws RemoteException, Exception;

	@Available(where=WHERE_FOLLOWERS)
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] removeFollower() throws Exception;
	
	@Hidden
	@ServiceMethod(payload={"userId", "network", "name", "mood"})
	public void changeMood() throws Exception;

	@Hidden
	@ServiceMethod(payload={"userId"}, target="none")
	IEmployee loadEmployee() throws Exception;

	@ServiceMethod(mouseBinding="drag", payload={"userId", "name"})
	public Session drag();

	@Available(where={MW3_WHERE_ROLEUSER_PICKER_CALLER, MW3_WHERE_ROLEUSER_PICKER})
	@ServiceMethod(mouseBinding="drop")
	public void drop();
	
	@ServiceMethod(inContextMenu=true, needToConfirm=true, target="none")
	@Available(when={"admin"})
	public void addAsAdmin() throws Exception;
	
	@ServiceMethod(payload={"userId"})
	public int getBV(String empcode) throws Exception;

	@ServiceMethod(callByContent=true, needToConfirm=true, inContextMenu=true)
	@Face(displayName="$ApprovedSubscribe")
	@Available(when={"admin"})
	public void approvedSubscribe() throws Exception;
	
	public IUser findByDept(Dept dept) throws Exception;
	
	@Available(where={WHERE_SELF})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK)
	public Object[] showMenu() throws Exception;
	
	@Group(name="admin")
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] delUser() throws Exception;
	
	@Available(how=HOW_PICKER)
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup popupPicker() throws Exception;
	
}
