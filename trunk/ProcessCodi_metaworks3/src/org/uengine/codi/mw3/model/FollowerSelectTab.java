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
	
	OrganizationTree employeeTree;
	@Face(displayName="사원")
		public OrganizationTree getEmployeeTree() {
			return employeeTree;
		}
		public void setEmployeeTree(OrganizationTree employeeTree) {
			this.employeeTree = employeeTree;
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
		employeeTree = new OrganizationTree(session);
		deptTree = new OrganizationTree(session);
		contactListPanel = new ContactListPanel();
	}
}
