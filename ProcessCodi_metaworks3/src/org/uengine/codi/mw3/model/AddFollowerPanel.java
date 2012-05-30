package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.ILogin;

public class AddFollowerPanel implements ContextAware{
	
	public AddFollowerPanel(){
		
		setMetaworksContext(new MetaworksContext());
		
	}
	
	public AddFollowerPanel(IUser loginUser, String instanceId) throws Exception{
		this();
		
		ContactList contactList = new ContactList();
		contactList.load(loginUser.getUserId());
		
		setContactList(contactList);
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
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
