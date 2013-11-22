package org.uengine.codi.mw3.processexplorer;



import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="ProcessExploreWindow",
options={"hideLabels", "maximize"}, 
values={"true", "true"})
public class ProcessExploreWindow extends Window{
	public ProcessExploreWindow(){
		super();
	}
	
	public ProcessExploreWindow(Object panel) throws Exception{
		
		super(panel);
	}
	
	public ProcessExploreWindow(Object panel, String title){
		super(panel, title);
	}
	
	
	
	
}
