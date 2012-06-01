package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class ContactPanel {
	
	public ContactPanel(){}
	
	public ContactPanel(IUser user) throws Exception{
		
		ContactListPanel contactListPanel = new ContactListPanel();		
		contactListPanel.getMetaworksContext().setWhen(ContactListPanel.CONTACT);
		contactListPanel.load(user.getUserId());		
		
		setContactListPanel(contactListPanel);
		setUser(user);		
		setSearchBox(new ContactSearchBox());		
	}

	IUser user;	
		public IUser getUser() {
			return user;
		}	
		public void setUser(IUser user) {
			this.user = user;
		}

	ContactSearchBox searchBox;	
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
	public Popup addContact(){
		Popup popup = new Popup();
		
//			popup.setPageX(this.pageX);
//			popup.setPageY(this.pageY);
		
		popup.setPanel(new AddContactPanel());
		popup.setName("AddContactPanel");
		
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup addLocalContact() throws Exception{
		Popup popup = new Popup();
		
//		popup.setPageX(this.pageX);
//		popup.setPageY(this.pageY);
		
		popup.setPanel(new AddLocalContactPanel(session));
		popup.setName("AddLocalContactPanel");
		
		return popup;
	}
	
	@AutowiredFromClient
	public Session session;
	
}
