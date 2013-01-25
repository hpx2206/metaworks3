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
	
	OrganizationTree deptTree;
		@Face(displayName="부서")
		public OrganizationTree getDeptTree() {
			return deptTree;
		}
		public void setDeptTree(OrganizationTree deptTree) {
			this.deptTree = deptTree;
		}
	public FollowerSelectTab() throws Exception{
		
	}
	public FollowerSelectTab(Session session) throws Exception{
		deptTree = new OrganizationTree(session);
		contactListPanel = new ContactListPanel();
	}
}
