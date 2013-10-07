package org.uengine.codi.acl;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class MM_ACL extends Database<IMM_ACL> implements IMM_ACL{
	
	int aclId;
		public int getAclId() {
			return aclId;
		}
		public void setAclId(int aclId) {
			this.aclId = aclId;
		}
	
	String permission;
		public String getPermission() {
			return permission;
		}
		public void setPermission(String permission) {
			this.permission = permission;
		}

	String nodeId;
		public String getNodeId() {
			return nodeId;
		}
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		
	public Object addPermission() throws Exception{
		
		IMM_ACL keyGen = (IMM_ACL) Database.sql(IMM_ACL.class, "select max(aclid) + 1 as aclid from MM_ACL");
		keyGen.select();
		
		if(keyGen.next())
			setAclId(keyGen.getAclId());
		else
			throw new Exception("failed to generate key! for MM_ACL");

		IMM_ACL dbMe = createDatabaseMe();

		flushDatabaseMe(); //lets the added permission can be found following select SQL. You may remove this if there's no selection SQL immediately:
		
		MM_ACLManager aclManager = new MM_ACLManager();
		aclManager.setNodeId(getNodeId());
		aclManager.load();	//this doesn't recognize the new record is added if we didn't call 'flushDatabaseMe()' before since it is still cached only
		
		return aclManager;
	}
	
	public void updatePermission() throws Exception{
		databaseMe().setPermission(getPermission());
		
		getMetaworksContext().setWhen(WHEN_VIEW);
	}
	
	public Object deletePermission() throws Exception{
		IMM_ACL forDelete = (IMM_ACL) Database.sql(IMM_ACL.class, "delete from MM_ACL where aclid = ?aclid");
		forDelete.setAclId(getAclId());
		int i = forDelete.update();
		
		return this;//"Permission removed"; //let the screen message. you may give only blank string ("") for showing nothing
	}
	

	
	public IMM_ACL findAll() throws Exception{
//		IMM_ACL dao = (IMM_ACL) Database.sql(IMM_ACL.class, "select * from MM_ACL where nodeId=?nodeId");
		IMM_ACL dao = (IMM_ACL) Database.sql(IMM_ACL.class, "select aclid, nodeid, permission from MM_ACL where nodeId=?nodeId");
		dao.setNodeId(getNodeId());
		dao.select();
//		
//		if(!dao.next())
//			return this;
//		
		return dao;
	}
}
