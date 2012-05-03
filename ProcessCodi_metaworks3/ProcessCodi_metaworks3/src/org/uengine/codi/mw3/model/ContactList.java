package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.ILogin;

public class ContactList implements ContextAware{

	public ContactList(){}
	
	MetaworksContext metaworksContext;
		
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
		
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
			this.contacts.setMetaworksContext(getMetaworksContext());
			
		}		
}
