package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.ILogin;

public class ContactList {

	public ContactList(){
	}
	
	public void load(String userId) throws Exception{
		Contact contact = new Contact();
		contact.setUserId(userId);
		
		setContacts(contact.loadContacts());
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
