package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs"  )
public class FollowerSelectTab {
	ContactListPanel contactListPanel;
		@Face(displayName="친구")
		public ContactListPanel getContactListPanel() {
			return contactListPanel;
		}
		public void setContactListPanel(ContactListPanel contactListPanel) {
			this.contactListPanel = contactListPanel;
		}
	
	OrganizationTreePanel deptTreePanel;
		@Face(displayName="부서")
		public OrganizationTreePanel getDeptTreePanel() {
			return deptTreePanel;
		}
		public void setDeptTreePanel(OrganizationTreePanel deptTreePanel) {
			this.deptTreePanel = deptTreePanel;
		}
	public FollowerSelectTab() throws Exception{
		
	}
	public void load(Session session, String type) throws Exception {

		ContactListPanel contactListPanel = new ContactListPanel();
		contactListPanel.getMetaworksContext().setHow("follower");
		contactListPanel.setId(type);
		contactListPanel.load(session.getUser().getUserId());
		contactListPanel.getLocalContactList().getMetaworksContext().setWhen(type);

		this.setContactListPanel(contactListPanel);
		
		
		OrganizationTreePanel deptTreePanel = new OrganizationTreePanel();
		deptTreePanel.load(session, type);
		
		this.setDeptTreePanel(deptTreePanel);
	}
}
