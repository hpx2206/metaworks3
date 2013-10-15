package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;

public interface Tab {

	@Id
	public String getId();
	public void setId(String id);

	@Name
	public String getName();
	public void setName(String name);

	public String getType();
	public void setType(String type);

	public String getParentId();
	public void setParentId(String parentId);
	
	public String getClassName();
	public void setClassName(String className);
	
}
