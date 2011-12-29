package org.uengine.codi.acl;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

import javax.persistence.*;

@Table(name="MM_ACL")
@Face(displayName=" ")
public interface IMM_ACL extends IDAO{

	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE) //TODO: further, "org.metaworks.dao.Database" utility will automatically generates the key if this annotation has been specified.
	@Hidden
	public int getAclId();
	public void setAclId(int aclId);

	@Face(displayName="Permission: ")
	@Range(
			options={"Read","Modify", "Delete", "Post"},
			values={"R","M", "D", "P"}
	)
	public String getPermission();
	public void setPermission(String permission);
	
	@Hidden
	public String getNodeId();
	public void setNodeId(String nodeId);
	
	  
	@ServiceMethod(when = WHEN_NEW, callByContent=true)
	public Object addPermission() throws Exception;
	
	@ServiceMethod(when = WHEN_EDIT, callByContent=true)
	public void updatePermission() throws Exception;
	
	public IMM_ACL findAll() throws Exception;

	@ServiceMethod
	public Object deletePermission() throws Exception;

}
