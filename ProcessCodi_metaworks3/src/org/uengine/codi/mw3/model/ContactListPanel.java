package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;

public class ContactListPanel implements ContextAware {

	public static final String CONTACT = "contacts";
	public static final String FOLLOWER = "followers";

	
	public ContactListPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	
	//TODO: please delete after Fake
	@ServiceMethod(target="popup")
	@Test(scenario="first", instruction="친구중 하나와 채팅을 나눌 수 있습니다. 아무 친구나 선택하세요...", next="autowiredObject.org.metaworks.codi.mw3.admin.PageNavigator.goKnowledge()")
	public void chat(){
		
	}
	
	public void load(String userId) throws Exception {
		load(userId, null);
	}
	
	public void load(String userId, String keyword) throws Exception {
		setId(getMetaworksContext().getWhen());
		
		ContactList localContactList = new ContactList();
		localContactList.getMetaworksContext().setHow(getMetaworksContext().getHow());
		localContactList.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
		localContactList.getMetaworksContext().setWhere(ContactList.LOCAL);
		localContactList.load(userId, keyword);
		
		setLocalContactList(localContactList);
		
		ContactList socialContactList = new ContactList();
		socialContactList.getMetaworksContext().setHow(getMetaworksContext().getHow());
		socialContactList.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
		socialContactList.getMetaworksContext().setWhere(ContactList.FACEBOOK);
		socialContactList.load(userId, keyword);
		
		setSocialContactList(socialContactList);
		
		
//		if(localContactList.getContacts().size() + socialContactList.getContacts().size() == 0){
//			invitation = new Invitation();
//			invitation.setName(keyword);
//			//invitation.session = session;
//		}
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
		
	Invitation invitation;


		public Invitation getInvitation() {
			return invitation;
		}
	
		public void setInvitation(Invitation invitation) {
			this.invitation = invitation;
		}
}
