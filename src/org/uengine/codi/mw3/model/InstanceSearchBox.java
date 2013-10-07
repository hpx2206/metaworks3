package org.uengine.codi.mw3.model;

public class InstanceSearchBox extends SearchBox {

	public InstanceSearchBox() {
	}	
	
	@Override
	public Object[] search() throws Exception {
		session.setSearchKeyword(getKeyword());
		return Perspective.loadInstanceListPanel(session, session.getLastPerspecteType(), session.getLastSelectedItem());
	}
}
