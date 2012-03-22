package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.ILogin;

public class ContactList {

	public ContactList(){}
	
	public ContactList(IUser user) throws Exception{
		Contact contact = new Contact();
		contact.setUserId(user.getUserId());
		
		contacts = contact.loadContacts();		
	}
	
	@AutowiredFromClient
	public Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}

	IContact contacts;	
		public IContact getContacts() {
			return contacts;
		}	
		public void setContacts(IContact contacts) {
			this.contacts = contacts;
		}		
}
