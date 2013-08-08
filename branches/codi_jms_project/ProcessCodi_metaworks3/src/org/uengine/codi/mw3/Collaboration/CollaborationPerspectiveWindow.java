package org.uengine.codi.mw3.Collaboration;


import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.Session;
public class CollaborationPerspectiveWindow extends Window{

	public CollaborationPerspectiveWindow() throws Exception{
		this(null);
	}
	
	public CollaborationPerspectiveWindow(Session session) throws Exception{
		setPanel(new CollaborationPerspectivePanel(session));
		setTitle("$CollaborationPerspectivePanel");
	}
}
