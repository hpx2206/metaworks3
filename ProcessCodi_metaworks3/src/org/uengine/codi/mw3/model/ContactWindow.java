package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs", 
	  displayName="$Contact",
	  //nipa, 데모 반영 위해서 관점/친구 영역 컬러 원복합니다.(회색->파랑색)
	  /*
	  options={"hideLabels", "minimize", "color"}, 
      values={"true", "true","gray"})
	  */
	  options={"hideLabels", "minimize"}, 
	  values={"true", "true"})

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
