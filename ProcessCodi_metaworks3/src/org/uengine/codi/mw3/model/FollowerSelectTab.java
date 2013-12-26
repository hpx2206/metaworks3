package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
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
		
	OrganizationTree deptTree;
		@Face(displayName="$Organization")
		@Available(where={"userDeptTab"})
		public OrganizationTree getDeptTree() {
			return deptTree;
		}
		public void setDeptTree(OrganizationTree deptTree) {
			this.deptTree = deptTree;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public FollowerSelectTab() throws Exception{
		
	}
	
	public void load(Session session, String type) throws Exception {
	
		ContactPanel contactPanel = new ContactPanel(session, type);
		this.setContactPanel(contactPanel);

		OrganizationTree deptTree = new OrganizationTree(session);
		deptTree.setId("dept");
		deptTree.setHiddenEmployee(true);
		deptTree.setHiddenCheckBoxFolder(true);
		deptTree.load();		
		
		this.setDeptTree(deptTree);

	}
}
