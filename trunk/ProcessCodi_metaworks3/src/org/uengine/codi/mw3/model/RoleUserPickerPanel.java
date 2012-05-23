package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class RoleUserPickerPanel implements ContextAware {

	public RoleUserPickerPanel() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhere(IUser.MW3_WHERE_ROLEUSER_PICKER);
	}
	
	public RoleUserPickerPanel(IUser loginUser) throws Exception{
		this();
		
		contactList = new ContactList(loginUser);
		contactList.setMetaworksContext(getMetaworksContext());
	}
	
	
	ContactList contactList;
	public ContactList getContactList() {
		return contactList;
	}	
	public void setContactList(ContactList contactList) {
		this.contactList = contactList;
	}	

	MetaworksContext metaworksContext;

	@Override
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}

	@Override
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
}
