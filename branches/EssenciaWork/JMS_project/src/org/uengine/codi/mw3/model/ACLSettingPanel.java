package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.acl.MM_ACLManager;

@Face(ejsPath="genericfaces/Window.ejs", displayName="ACL Setting")
public class ACLSettingPanel extends ContentWindow{

	MM_ACLManager aclManager;
	
		@Face(displayName=" ")
		public MM_ACLManager getAclManager() {
			return aclManager;
		}
	
		public void setAclManager(MM_ACLManager aclManager) {
			this.aclManager = aclManager;
		}
	
	@ServiceMethod
	public void load(){
		aclManager = new MM_ACLManager();
		aclManager.setNodeId(getNodeId());
	}
	
	
	String nodeId;
		@Id
		@Face(displayName=" ")
		public String getNodeId() {
			return nodeId;
		}
	
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}
	
}
