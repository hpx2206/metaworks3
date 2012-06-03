package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox {

	public ContactSearchBox(){	
		super();
	}

	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
		String userId = session.getUser().getUserId();

		ContactListPanel contactListPanel = new ContactListPanel();		
		contactListPanel.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		contactListPanel.load(userId, getKeyword());
		
		return new Object[]{contactListPanel.getLocalContactList(), contactListPanel.getSocialContactList()};
	}
}
