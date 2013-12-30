package org.uengine.codi.mw3.model;

public class ContactPerspective extends Perspective {
	
	public ContactPerspective() throws Exception {
		setLabel("Contact");
	}
	
	ContactPanel contactPanel;
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
		}

	@Override
	protected void loadChildren() throws Exception {
		contactPanel = new ContactPanel(session.getUser());
		contactPanel.session = session;
	}

}
