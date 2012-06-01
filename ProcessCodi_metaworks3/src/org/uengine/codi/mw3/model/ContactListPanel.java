package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;

public class ContactListPanel implements ContextAware {

	public static final String CONTACT = "contact";
	public static final String FOLLOWER = "follower";

	
	public ContactListPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(String userId) throws Exception {
		String id = this.getId();
		
		ContactList localContactList = new ContactList();
		localContactList.setId(id + "_" + ContactList.LOCAL);
		localContactList.load(userId);
		
		setLocalContactList(localContactList);
		
		ContactList socialContactList = new ContactList();
		socialContactList.setId(id + "_" + ContactList.SOCIAL);
		socialContactList.loadSocial(userId);
		
		setSocialContactList(socialContactList);
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

	ContactList localContactList;
		public ContactList getLocalContactList() {
			return localContactList;
		}
		public void setLocalContactList(ContactList localContactList) {
			this.localContactList = localContactList;
		}
	
	ContactList socialContactList;
		public ContactList getSocialContactList() {
			return socialContactList;
		}
		public void setSocialContactList(ContactList socialContactList) {
			this.socialContactList = socialContactList;
		}	
}
