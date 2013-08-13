package org.uengine.codi.mw3.processexplorer;



import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="ProcessExplorerContent",
options={"hideLabels", "maximize"}, 
values={"true", "true"})
public class ProcessExplorerContentWindow extends Window{

	public ProcessExplorerContentWindow(){
		super();
	}
	
	public ProcessExplorerContentWindow(Object panel) throws Exception{
		
		super(panel);
	}
	
	public ProcessExplorerContentWindow(Object panel, String title){
		super(panel, title);
	}
	
	
	
	
}
