package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class InstanceSearchBox extends SearchBox {

	public InstanceSearchBox() {
	}	
	
	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception {
		session.setSearchKeyword(getKeyword());
		
		return new Object[]{Perspective.loadInstanceList(session, session.getLastPerspecteMode(), session.getLastPerspecteType(), session.getLastSelectedItem())};
	}
}
