package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs",  
options={"hideLabels", "minimize", "color"}, 
values={"true", "true", "gray"})
public class PerspectiveWindow extends Window {
	
	public PerspectiveWindow() throws Exception {
		this(null);		
	}
	
	public PerspectiveWindow(Session session) throws Exception {
		setPanel(new PerspectivePanel(session));
		// title 을 '관점' 으로 셋팅해줌..
		setTitle("$Navigation");
	}

}
