package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

import org.uengine.codi.mw3.ILogin;

public class ContactPanel {
	
	public ContactPanel(){}
	
	public ContactPanel(IUser user) throws Exception{
		
		contactList = new ContactList(user);

		searchBox = new ContactSearchBox(user);
/*		searchBox.setMetaworksContext(new MetaworksContext());
		searchBox.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
*/		
	}

	ContactSearchBox searchBox;	
		public ContactSearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(ContactSearchBox searchBox) {
			this.searchBox = searchBox;
		}	
	
	ContactList contactList;
		public ContactList getContactList() {
			return contactList;
		}	
		public void setContactList(ContactList contactList) {
			this.contactList = contactList;
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
		
//		popup.setPageX(this.pageX);
//		popup.setPageY(this.pageY);
		
		popup.setPanel(new AddContactPanel());
		popup.setName("AddContactPanel");
		
		return popup;
	}	
}
