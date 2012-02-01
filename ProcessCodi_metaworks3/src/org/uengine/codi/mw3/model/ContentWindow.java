package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

//@Face(ejsPath="genericfaces/Window.ejs", displayName="ContentWindow", options={"hideLabels"}, values={"true"})
public class ContentWindow extends Window{
	public ContentWindow(){
		setPanel(new ContentPanel());
	}
}
