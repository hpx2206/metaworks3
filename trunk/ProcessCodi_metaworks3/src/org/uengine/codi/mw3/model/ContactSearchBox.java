package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox {

	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{
		ContactList contactPanel = new ContactList();
		
		Contact contact = new Contact();
		contact.setUserId(session.login.getUserId());
		contact.setFriendName(getKeyword());
		contactPanel.setContacts(contact.findContactsWithFriendName());
		
		return contactPanel;
	}
		
}
