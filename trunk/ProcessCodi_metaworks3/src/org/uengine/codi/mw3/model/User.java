package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;

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
		contact.setFriends(this);
		contact.setUserId(session.getUser().getUserId());
		contact.addContact();
		
		ContactList cp = new ContactList(session.getUser());
		return cp;		
	}
	
	@AutowiredFromClient
	public Session session;
	
}
