package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.knowledge.WorkflowyNode;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.SearchBox;

public class ResourceSearchBox extends SearchBox {

	public ResourceSearchBox(){	
		super();
	}
	
	public ResourceSearchBox(IUser user) {		
		setUser(user);
	}
	
	@ServiceMethod(callByContent=true)
	public Object search() throws Exception{	
		
		return null;
	}
		
}
