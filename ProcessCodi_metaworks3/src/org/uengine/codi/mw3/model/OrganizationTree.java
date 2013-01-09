package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.Tree;

public class OrganizationTree extends Tree {
	
	boolean hiddenEmployee;
		public boolean isHiddenEmployee() {
			return hiddenEmployee;
		}
		public void setHiddenEmployee(boolean hiddenEmployee) {
			this.hiddenEmployee = hiddenEmployee;
		}

	@AutowiredFromClient
	public Session session;
	
	public OrganizationTree(Session session) {
		this.session = session;
		this.setId("organization");
		this.setShowCheckBox(true);
	}
	
	public void load(){
		CompanyTreeNode rootNode = new CompanyTreeNode();
		rootNode.session = this.session;
		
		rootNode.setId(session.getCompany().getComCode());
		rootNode.setName(session.getCompany().getComName());
		rootNode.setRoot(true);
		rootNode.setChild(rootNode.loadExpand());
		
		this.setNode(rootNode);
	}
	
}
