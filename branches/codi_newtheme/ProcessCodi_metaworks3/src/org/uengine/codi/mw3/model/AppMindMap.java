package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class AppMindMap {
	IFrame content;
	public IFrame getContent() {
		return content;
	}
	public void setContent(IFrame content) {
		this.content = content;
	}
	
	public AppMindMap(){
		IFrame content = new IFrame(GlobalContext.getPropertyString("app.mindmup.url", "http://uenginecloud:7070/mindmup"));
		
		this.setContent(content);
	}
}
