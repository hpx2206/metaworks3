package org.uengine.codi.mw3.collaboration;



import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="Content",
options={"hideLabels", "maximize"}, 
values={"true", "true"})
public class CollaborationContentWindow extends Window{

	public CollaborationContentWindow(){
		super();
	}
	
	public CollaborationContentWindow(Object panel) throws Exception{
		
		super(panel);
	}
	
	public CollaborationContentWindow(Object panel, String title){
		super(panel, title);
	}
	
	
	
	
}
