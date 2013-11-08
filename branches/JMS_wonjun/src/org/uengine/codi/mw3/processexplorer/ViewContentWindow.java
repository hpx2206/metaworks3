package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="폼 윈도우",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class ViewContentWindow extends ContentWindow{
	public ViewContentWindow(){
		super();
	}
	
	public ViewContentWindow(Object panel) throws Exception{
		
		super(panel);
	}
	
	public ViewContentWindow(Object panel, String title){
		super(panel, title);
	}
	
	
	
	
}
