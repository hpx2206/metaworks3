package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.ILogin;

@Face(ejsPath="genericfaces/WindowTab.ejs", displayName="연락처", 
	  options={"hideLabels", "hideHeader",  "layout"}, 
      values={"true", "true", "center"})

public class ContactWindow {
	public ContactWindow() {}
	public ContactWindow(IUser user) throws Exception {
		this.contactPanel = new ContactPanel(user);
	}
	
	ContactPanel contactPanel;
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
		}
}
