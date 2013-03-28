package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;

public interface CloudContent {
	
	@Id
	public String getId();
	public void setId(String id);

	@Name
	public String getName();
	public void setName(String name);

	public String getType();
	public void setType(String type);

}
