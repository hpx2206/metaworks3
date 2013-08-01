package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs", 
	  displayName="$Contact",
	  options={"hideLabels", "minimize" , "color"}, 
      values={"true", "true","blue"})

public class ContactWindow {
	public ContactWindow() {
	}
	
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
