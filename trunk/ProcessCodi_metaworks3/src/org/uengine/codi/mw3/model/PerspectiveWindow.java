package org.uengine.codi.mw3.model;

import org.metaworks.widget.Window;

public class PerspectiveWindow extends Window {
	
	public PerspectiveWindow() throws Exception {
		this(null);		
	}
	
	public PerspectiveWindow(Session session) throws Exception {
		setPanel(new PerspectivePanel(session));
	}

}
