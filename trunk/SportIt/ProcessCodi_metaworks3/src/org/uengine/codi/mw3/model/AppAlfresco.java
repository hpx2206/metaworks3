package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class AppAlfresco {
	IFrame content;
	public IFrame getContent() {
		return content;
	}
	public void setContent(IFrame content) {
		this.content = content;
	}
	
	public AppAlfresco(){
		IFrame content = new IFrame(GlobalContext.getPropertyString("app.alfresco.url", "http://uenginecloud:7070/alfresco"));
		
		this.setContent(content);
	}
}
