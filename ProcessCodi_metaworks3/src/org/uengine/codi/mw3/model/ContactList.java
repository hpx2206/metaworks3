package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class ContactList {

	public ContactList(){}
	
	public ContactList(ILogin user) throws Exception{
		Contact contact = new Contact();
		contact.setUserId(user.getUserId());
		
		contacts = contact.loadContacts();
		
		popup = new Popup();
		popup.setPanel(new AddContactPanel());
		popup.setName("AddContactPanel");
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
				
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
	
	Popup popup;
		public Popup getPopup() {
			return popup;
		}	
		public void setPopup(Popup popup) {
			this.popup = popup;
		}
}
