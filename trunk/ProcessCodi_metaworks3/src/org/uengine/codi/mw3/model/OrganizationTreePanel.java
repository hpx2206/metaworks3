package org.uengine.codi.mw3.model;

import org.metaworks.component.HorizontalSplitBox;

public class OrganizationTreePanel extends HorizontalSplitBox {
	
	public void load(Session session, String type){
		OrganizationTree deptTree = new OrganizationTree(session);;
		deptTree.setId("dept");
		deptTree.setHiddenEmployee(true);
		deptTree.load();

		FollowerSelectCommand followerSelectCommand = new FollowerSelectCommand();
		followerSelectCommand.setFollowerType(type);
		
		this.setTop(deptTree);
		this.setBottom(followerSelectCommand);
		this.setFixHeight(50);
		this.setAlign(FollowerSelectPanel.ALIGN_BOTTOM);
	}
	
}
