package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", displayName="연락처", 
	  options={"hideLabels", "hideHeader",  "innerLayoutName", "innerLayout", "outerLayoutName", "outerLayout"}, 
      values={"true", "true", "center", "inner", "west", "outer"})

public class ContactWindow {
	public ContactWindow() {}
	public ContactWindow(ILogin loginUser) throws Exception {
		this.contactPanel = new ContactPanel(loginUser);
	}
	
	ContactPanel contactPanel;
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
		}
}
