package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;

public class ContactList implements ContextAware {
	
	public static final String LOCAL = "local";
	public static final String SOCIAL = "social";
	
	public ContactList(){
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(String userId) throws Exception{
		load(userId, null);
	}
	public void load(String userId, String keyword) throws Exception{
		setId(getMetaworksContext().getWhen() + "_" + getMetaworksContext().getWhere());
		
		Contact contact = new Contact();
		contact.setMetaworksContext(getMetaworksContext());
		contact.setUserId(userId);
			
		if(LOCAL.equals(this.getMetaworksContext().getWhere()))
			setContacts(contact.loadLocalContacts(keyword));
		else if(SOCIAL.equals(this.getMetaworksContext().getWhere()))
		setContacts(contact.loadSocialContacts(keyword));
	}

	String id;
		@Id
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	IContact contacts;	
		public IContact getContacts() {
			return contacts;
		}	
		public void setContacts(IContact contacts) {
			this.contacts = contacts;			
		}		
}
