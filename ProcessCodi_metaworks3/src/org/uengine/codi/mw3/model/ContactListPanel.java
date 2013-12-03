package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.uengine.kernel.GlobalContext;

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
		
//		ContactList localContactList = new ContactList();
//		localContactList.getMetaworksContext().setHow(getMetaworksContext().getHow());
//		localContactList.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
//		localContactList.getMetaworksContext().setWhere(ContactList.LOCAL);
//		localContactList.session = session;
//		localContactList.load(userId, keyword);
//		
//		setLocalContactList(localContactList);
//		
//		if("1".equals(GlobalContext.getPropertyString("facebook.use", "1"))){
//			ContactList socialContactList = new ContactList();
//			socialContactList.getMetaworksContext().setHow(getMetaworksContext().getHow());
//			socialContactList.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
//			socialContactList.getMetaworksContext().setWhere(ContactList.FACEBOOK);
//			socialContactList.load(userId, keyword);
//			
//			setSocialContactList(socialContactList);
//		}		
		
		ContactList contactList = new ContactList();
		contactList.getMetaworksContext().setHow(getMetaworksContext().getHow());
		contactList.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
		contactList.session = session;
		contactList.load(userId, keyword);
		setContactList(contactList);
		
		
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

	ContactList contactList;
		public ContactList getContactList() {
			return contactList;
		}
		
		public void setContactList(ContactList contactList) {
			this.contactList = contactList;
		}
	
//	ContactList socialContactList;
//		public ContactList getSocialContactList() {
//			return socialContactList;
//		}
//		public void setSocialContactList(ContactList socialContactList) {
//			this.socialContactList = socialContactList;
//		}	
		


	Invitation invitation;
		public Invitation getInvitation() {
			return invitation;
		}
		public void setInvitation(Invitation invitation) {
			this.invitation = invitation;
		}
		
	ArrayList<IUser> checkNodes;
		public ArrayList<IUser> getCheckNodes() {
			return checkNodes;
		}
		public void setCheckNodes(ArrayList<IUser> checkNodes) {
			this.checkNodes = checkNodes;
		}

	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Object addContact() throws Exception{
		Popup popup = new Popup();
		
		popup.setPanel(new UnifiedAddContactPanel(session));
		
		return popup;
	}

}
