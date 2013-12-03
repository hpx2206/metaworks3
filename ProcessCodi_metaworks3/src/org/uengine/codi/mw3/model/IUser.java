package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.Window;

public interface IUser extends IDAO{
	public static final String MW3_WHERE_ROLEUSER_PICKER_CALLER = "roleUserPickerCaller";
	public static final String MW3_WHERE_ROLEUSER_PICKER = "roleUserPicker";
	
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

	public boolean isUserChecked();
	public void setUserChecked(boolean userChecked);
	
	public boolean isGuest();
	public void setGuest(boolean guest);
	
	public String getInviteUser();
	public void setInviteUser(String inviteUser);	
	
//	@NonLoadable
//	@NonSavable
//	public String getInstanceId() ;
//	public void setInstanceId(String instanceId);
	
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Refresh addContact() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_APPEND)
	public Object[] addFollower() throws Exception;
		
	@ServiceMethod(when="edit", inContextMenu=true, callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	@Face(displayName="$PickUp")
	public Popup pickUp() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup openRoleUserPicker() throws Exception;

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup detail() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] showWall() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] showSchedule() throws Exception;
	
	@ServiceMethod(callByContent=true, target=TARGET_SELF)
	public UnstructuredProcessInstanceStarter chat() throws Exception;
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	@Face(displayName="$RemoveFromFollowers")
	@Available(when={"followers", "topicFollowers"})
	public Object[] removeFollower() throws Exception;
	
	@ServiceMethod(callByContent=true, inContextMenu=true, target=TARGET_APPEND)
	@Face(displayName="$RemoveFromContact")
	@Available(when={"contacts"})
	public Object[] removeContact() throws Exception;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK, payload={"userId", "network", "name"})
	public Popup info() throws Exception;
	
	@ServiceMethod(payload={"userId", "network", "name", "mood"})
	public void changeMood() throws Exception;
	
	@ServiceMethod(payload={"userId", "name"})
	public Window friends() throws Exception;

	@ServiceMethod(payload={"userId"}, target="none")
	IEmployee loadEmployee() throws Exception;
	
	@ServiceMethod(mouseBinding="drag")
	public Session drag();

	@ServiceMethod(mouseBinding="drop")
	public void drop();
	
	@ServiceMethod(callByContent=true, needToConfirm=true)
//	@Available(when={"followers"})
	@Face(displayName="$Unsubscribe")
	public Object[] unsubscribe() throws Exception;	

	@ServiceMethod(callByContent=true)
	@Face(displayName="$GuestToUser")
	public void guestToUser() throws Exception;
	
	@ServiceMethod(callByContent=true)
	@Face(displayName="$UserToGuest")
	public void userToGuest() throws Exception;

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
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Object[] showMenu() throws Exception;
}
