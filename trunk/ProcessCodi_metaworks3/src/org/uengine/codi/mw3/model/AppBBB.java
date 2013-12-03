package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class AppBBB {
	IFrame content;
	public IFrame getContent() {
		return content;
	}
	public void setContent(IFrame content) {
		this.content = content;
	}
	
	public AppBBB(){
		IFrame content = new IFrame(GlobalContext.getPropertyString("app.bbb.url", "http://uenginecloud"));
		
		this.setContent(content);
	}


}
