package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs", 
	  options={"hideLabels", "maximize"}, 
	  values={"true", "true"})
public class ContentWindow extends Window {
	public ContentWindow(){
		super();
	}
	public ContentWindow(Object panel){
		super(panel);
	}	
}
