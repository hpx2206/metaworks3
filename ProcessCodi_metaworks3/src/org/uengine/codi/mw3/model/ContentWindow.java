package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs", 
      options={"hideLabels", "innerLayoutName", "innerLayout", "outerLayoutName", "outerLayout"}, 
      values={"true", "center", "middle", "center", "outer"})

public class ContentWindow  {
	public ContentWindow(){
	}
}
