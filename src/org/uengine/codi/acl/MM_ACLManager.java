package org.uengine.codi.acl;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Face(displayName="ACL Manager", ejsPath="genericfaces/ObjectFaces_Vertical.ejs")
public class MM_ACLManager {

	IMM_ACL acl;
	@Face(displayName="acl")
		public IMM_ACL getAcl() {
			return acl;
		}
		public void setAcl(IMM_ACL acl) {
			this.acl = acl;
		}

	IMM_ACL newAcl;
	@Face(displayName=" ")
		public IMM_ACL getNewAcl() {
			return newAcl;
		}
		public void setNewAcl(IMM_ACL newAcl) {
			this.newAcl = newAcl;
		}

	String nodeId;
		@Id
		public String getNodeId() {
			return nodeId;
		}
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		
	@ServiceMethod
	public void load() throws Exception{
		acl = new MM_ACL();
		acl.setNodeId(getNodeId());
		acl = acl.findAll();
		
		//newAcl = ""; // let the empty div
	}
	
	@ServiceMethod
	public void addPermission() throws Exception{
		load();
		
		newAcl = new MM_ACL();
		((IMM_ACL)newAcl).setNodeId(getNodeId());
		
		MetaworksContext context = new MetaworksContext();
		context.setWhen(IDAO.WHEN_NEW);
		
		((IMM_ACL)newAcl).setMetaworksContext(context);
	}
	
}
