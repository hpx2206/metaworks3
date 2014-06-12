package org.uengine.codi.mw3.processexplorer;



import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="운영 절차",
options={"hideLabels", "maximize"}, 
values={"true", "true"})
public class ProcessExploreWindow extends ContentWindow{
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
