package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.ILogin;

public class AddFollowerPanel {
	
	public AddFollowerPanel(){}
	
	public AddFollowerPanel(IUser user, String instanceId) throws Exception{
		contactList = new ContactList(user);
		
		setInstanceId(instanceId);
	}
	
	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

	ContactList contactList;
		public ContactList getContactList() {
			return contactList;
		}	
		public void setContactList(ContactList contactList) {
			this.contactList = contactList;
		}		

}
