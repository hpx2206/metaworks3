package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="Contact",
	  options={"hideLabels", "layoutPanelName"}, values={"true", "contact"})
public class ContactPanel {
	
	public ContactPanel(){}
	
	public ContactPanel(ILogin loginUser) throws Exception{
		
		contactList = new ContactList(loginUser);

		searchBox = new ContactSearchBox();
		searchBox.setMetaworksContext(new MetaworksContext());
		searchBox.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
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
	
}
