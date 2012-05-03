package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox {

	public ContactSearchBox(){	
		//super();
	}
	
	public ContactSearchBox(IUser user) {		
		setUser(user);
	}
	
	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		ContactList contactPanel = new ContactList();
		
		System.out.println(getKeyword());
		
		Contact contact = new Contact();
		contact.setUserId(getUser().getUserId());
		contact.setFriendName(getKeyword());
		contactPanel.setContacts(contact.findContactsWithFriendName());
		
		
		return contactPanel;
	}
		
}
