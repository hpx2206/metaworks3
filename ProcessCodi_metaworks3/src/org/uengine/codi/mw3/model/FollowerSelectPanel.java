package org.uengine.codi.mw3.model;

public class FollowerSelectPanel {

	FollowerSelectTab followerSelectTab;
		public FollowerSelectTab getFollowerSelectTab() {
			return followerSelectTab;
		}
		public void setFollowerSelectTab(FollowerSelectTab followerSelectTab) {
			this.followerSelectTab = followerSelectTab;
		}

	public void load(Session session, String type) throws Exception{
		
		FollowerSelectTab followerSelectTab = new FollowerSelectTab(session);
		
		OrganizationTree employeeTree = followerSelectTab.getEmployeeTree();
		employeeTree.setId("employee");
		employeeTree.setHiddenCheckBoxFolder(true);
		employeeTree.load();
		
		OrganizationTree deptTree = followerSelectTab.getDeptTree();
				 
		deptTree.setId("dept");
		deptTree.setHiddenEmployee(true);
		deptTree.load();
		
		
		ContactListPanel contactListPanel = followerSelectTab.getContactListPanel();
		contactListPanel.getMetaworksContext().setHow("followerCheck");
		contactListPanel.setId(type);
		contactListPanel.load(session.getUser().getUserId());
		contactListPanel.getLocalContactList().getMetaworksContext().setWhen(type);		
		contactListPanel.getSocialContactList().getMetaworksContext().setWhen(type);
		
//		followerSelectTab.getEmployeeTree().setTreeType(FollowerSelectContent.USER);
//		followerSelectTab.getEmployeeTree().session = session;
//		followerSelectTab.getEmployeeTree().init();
//		
//		followerSelectTab.getDeptTree().setTreeType(FollowerSelectContent.DEPT);
//		followerSelectTab.getDeptTree().session = session;
//		followerSelectTab.getDeptTree().init();
		
		setFollowerSelectTab(followerSelectTab);
	}
}
