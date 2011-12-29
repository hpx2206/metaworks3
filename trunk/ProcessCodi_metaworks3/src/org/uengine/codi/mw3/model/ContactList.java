package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class ContactList {
	
	public ContactList(){}
	
	public ContactList(ILogin user) throws Exception{
		Contact contact = new Contact();
		contact.setUserId(user.getUserId());
		
		contacts = contact.loadContacts();
		
		searchBox = new ContactSearchBox();

	}
	
	ContactSearchBox searchBox;
		
		public ContactSearchBox getSearchBox() {
			return searchBox;
		}
	
		public void setSearchBox(ContactSearchBox searchBox) {
			this.searchBox = searchBox;
		}

	IContact contacts;
	
		public IContact getContacts() {
			return contacts;
		}
	
		public void setContacts(IContact contacts) {
			this.contacts = contacts;
		}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public AddContactPanel addContact(){
		return new AddContactPanel();
	}
	
	@AutowiredFromClient
	public Session session;

	
}
