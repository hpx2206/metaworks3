package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class ContactList implements ContextAware {
	
	public static final String LOCAL = "local";
	public static final String FACEBOOK = "fb";
	
	public ContactList(){
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(String userId) throws Exception{
		load(userId, null);
	}
	public void load(String userId, String friendName) throws Exception{
		setId(getMetaworksContext().getWhen() + "_" + getMetaworksContext().getWhere());
		
		Contact contact = new Contact();
		contact.setMetaworksContext(getMetaworksContext());
		contact.setUserId(userId);
		contact.session = session;
		
		IUser friend = new User();
		friend.setName(friendName);
		friend.getMetaworksContext().setHow(this.getMetaworksContext().getHow());
		
		if(LOCAL.equals(this.getMetaworksContext().getWhere())){
			friend.setNetwork(LOCAL);			
			contact.setFriend(friend);
			
			setContacts(contact.loadContacts(this.isSelectedMore()));
			
			if(getContacts().size()==0){
				invitation = new Invitation();
				//invitation.session = session;
			}
			
		}else if(FACEBOOK.equals(this.getMetaworksContext().getWhere())){
			friend.setNetwork(FACEBOOK);
			contact.setFriend(friend);
			
			setContacts(contact.loadContacts(this.isSelectedMore()));
		}
	}
	
	@ServiceMethod
	public void moreView() throws Exception {
		this.setSelectedMore(true);
		load(session.getEmployee().getEmpCode());
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
	
	Invitation invitation;
		public Invitation getInvitation() {
			return invitation;
		}
		public void setInvitation(Invitation invitation) {
			this.invitation = invitation;
		}
		
	boolean isSelectedMore;
		public boolean isSelectedMore() {
			return isSelectedMore;
		}
		public void setSelectedMore(boolean isSelectedMore) {
			this.isSelectedMore = isSelectedMore;
		}
		
	@AutowiredFromClient
	public Session session;
}
