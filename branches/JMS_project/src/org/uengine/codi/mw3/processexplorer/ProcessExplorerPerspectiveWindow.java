package org.uengine.codi.mw3.processexplorer;


import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.Session;
public class ProcessExplorerPerspectiveWindow extends Window{

	public ProcessExplorerPerspectiveWindow() throws Exception{
		this(null);
	}
	
	public ProcessExplorerPerspectiveWindow(Session session) throws Exception{
		setPanel(new ProcessExplorerPerspectivePanel(session));
		setTitle("$ProcessExplorerPerspectivePanel");
	}
}
