package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Order;

@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
public class AddFollowerPanel {
	
	ContactPanel contactPanel;
		@Order(value=1)
		@Face(displayName="$FollowerFriends")
		@Available(condition="contactPanel")
		public ContactPanel getContactPanel() {
			return contactPanel;
		}
		public void setContactPanel(ContactPanel contactPanel) {
			this.contactPanel = contactPanel;
		}
		
	DeptList deptList;
		@Order(value=2)
		@Face(displayName="$Organization")
		@Available(condition="deptList")
		public DeptList getDeptList() {
			return deptList;
		}
		public void setDeptList(DeptList deptList) {
			this.deptList = deptList;
		}
		
	public AddFollowerPanel(Session session, Follower follower) throws Exception {
		ContactPanel contactPanel = new ContactPanel();
		contactPanel.session = session;
		contactPanel.setFollower(follower);
		contactPanel.load();
		contactPanel.getMetaworksContext().setWhere(User.WHERE_ADDFOLLOWER);
		
		this.setContactPanel(contactPanel);
	}
	
	public void loadDept(Session session, Follower follower) throws Exception{

		DeptList deptList = new DeptList();
		deptList.setId("/ROOT/");
		deptList.setFollower(follower);
		deptList.loadWithCheckFollowedDept(session);
		
		this.setDeptList(deptList);
	}
}
