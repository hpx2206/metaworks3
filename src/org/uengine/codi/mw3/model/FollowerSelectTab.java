package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs"  )
public class FollowerSelectTab {

	ContactPanel contactPanel;
		@Face(displayName="$FollowerFriends")
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
		}
		
	OrganizationTreePanel deptTreePanel;
		@Face(displayName="$Organization")
		public OrganizationTreePanel getDeptTreePanel() {
			return deptTreePanel;
		}
		public void setDeptTreePanel(OrganizationTreePanel deptTreePanel) {
			this.deptTreePanel = deptTreePanel;
		}
	public FollowerSelectTab() throws Exception{
		
	}
	public void load(Session session, String type) throws Exception {
	
		ContactPanel contactPanel = new ContactPanel(session, type);
		this.setContactPanel(contactPanel);
		
		OrganizationTreePanel deptTreePanel = new OrganizationTreePanel();
		deptTreePanel.load(session, type);
		
		this.setDeptTreePanel(deptTreePanel);
	}
}
