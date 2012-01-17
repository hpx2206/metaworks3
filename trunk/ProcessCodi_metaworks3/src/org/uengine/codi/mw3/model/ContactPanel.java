package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="Contact",
	  options={"hideLabels", "layoutPanelName"}, values={"true", "contact"})
public class ContactPanel {
	
	public ContactPanel(){}
	
	public ContactPanel(ILogin loginUser) throws Exception{
		
		contactList = new ContactList(loginUser);

	}

	ContactList contactList;

		public ContactList getContactList() {
			return contactList;
		}
	
		public void setContactList(ContactList contactList) {
			this.contactList = contactList;
		}
	
}
