package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox {

	public ContactSearchBox(){	
		//super();
	}
	
	public ContactSearchBox(IUser user) {		
		setUser(user);
	}
	
	IUser user;
		
		public IUser getUser() {
			return user;
		}
	
		public void setUser(IUser user) {
			this.user = user;
		}

	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		ContactList contactPanel = new ContactList();
		
		System.out.println(getKeyword());
		
		Contact contact = new Contact();
		contact.setUserId(getUser().getUserId());
		contactPanel.setContacts(contact.findContactsWithFriendName(getKeyword()));
		
		
		return contactPanel;
	}
	
		
}
