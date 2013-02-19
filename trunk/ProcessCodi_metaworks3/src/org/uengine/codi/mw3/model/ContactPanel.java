package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ContactPanel {
	
	public ContactPanel(){}
	
	public ContactPanel(IUser user) throws Exception{
		
		ContactListPanel contactListPanel = new ContactListPanel();		
		contactListPanel.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		contactListPanel.load(user.getUserId());		
		
		user.getMetaworksContext().setWhen("self");
		
		setContactListPanel(contactListPanel);
		setUser(user);		
		setSearchBox(new ContactSearchBox());
		getSearchBox().setMetaworksContext(contactListPanel.getLocalContactList().getMetaworksContext());
	}

	IUser user;	
		public IUser getUser() {
			return user;
		}	
		public void setUser(IUser user) {
			this.user = user;
		}

	ContactSearchBox searchBox;
		@Face(options={"keyupSearch"}, values={"true"})
		public ContactSearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(ContactSearchBox searchBox) {
			this.searchBox = searchBox;
		}	
	
	ContactListPanel contactListPanel;
		public ContactListPanel getContactListPanel() {
			return contactListPanel;
		}
		public void setContactListPanel(ContactListPanel contactListPanel) {
			this.contactListPanel = contactListPanel;
		}

	int pageX;
		@Hidden
		public int getPageX() {
			return pageX;
		}	
		public void setPageX(int pageX) {
			this.pageX = pageX;
		}

	int pageY;
		@Hidden
		public int getPageY() {
			return pageY;
		}	
		public void setPageY(int pageY) {
			this.pageY = pageY;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object addContact() throws Exception{
		Popup popup = new Popup();
		
		//ModalWindow popup = new ModalWindow();
		
//			popup.setPageX(this.pageX);
//			popup.setPageY(this.pageY);
		
		popup.setPanel(new UnifiedAddContactPanel(session));
		//popup.setTitle("연락처 추가");
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object addLocalContact() throws Exception{
		ModalWindow popup = new ModalWindow();
//		Popup popup = new Popup();
		
//		popup.setPageX(this.pageX);
//		popup.setPageY(this.pageY);
		
		popup.setPanel(new AddLocalContactPanel(session));
		popup.setTitle("연락처 추가");
		
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object invite() throws Exception{
		ModalWindow popup = new ModalWindow();
//		Popup popup = new Popup();
		
//		popup.setPageX(this.pageX);
//		popup.setPageY(this.pageY);
		
		Invitation invitation = new Invitation();
		invitation.session = session;
		
		popup.setPanel(invitation);
		popup.setTitle("연락처 추가");
		
		return popup;
	}
	
	@AutowiredFromClient
	public Session session;
	
}
