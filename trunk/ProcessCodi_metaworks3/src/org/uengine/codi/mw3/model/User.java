package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;

public class User extends Database<IUser> implements IUser{
	
	String name;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}

	String userId;
		public String getUserId() {
			return userId;
		}	
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	public ContactList addContact() throws Exception {
		Contact contact = new Contact();
		contact.setFriend(this);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList();
		cp.load(session.getUser().getUserId());
		
		return cp;		
	}
	
	
	@AutowiredFromClient
	public Session session;

	@Override
	public Popup pickUp() throws Exception {
		Popup popup = new Popup();
		
		
		AddFollowerPanel userPicker = new AddFollowerPanel(fromHttpSession(), null);
		userPicker.setMetaworksContext(getMetaworksContext()); // propagate context
		
		popup.setPanel(userPicker);
		popup.setName("AddFollowerPanel");
		
		return popup;
	}
	
	public static User fromHttpSession(){
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession();
		
		User login = new User();
		
		if(session!=null){
			login.setUserId((String) session.getAttribute("userId"));
			
			return login;
		}
		
		return null;
	
	}

	@Override
	public Popup openRoleUserPicker() throws Exception {
		Popup popup = new Popup();
		
		RoleUserPickerPanel roleUserPicker = new RoleUserPickerPanel(session.getUser());
		popup.setPanel(roleUserPicker);
		popup.setName("Role User Pickup Panel");
		return popup;
	}
	
}
