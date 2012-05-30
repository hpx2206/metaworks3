package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox {

	public ContactSearchBox(){	
		super();
	}

	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		ContactList contactPanel = new ContactList();
		
		Contact contact = new Contact();
		contact.setUserId(session.getUser().getUserId());
		contactPanel.setContacts(contact.findContactsWithFriendName(getKeyword()));
				
		return contactPanel;
	}
	
		
}
