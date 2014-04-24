package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Application;
import org.uengine.codi.mw3.widget.IFrame;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class IFrameApplication extends Application {

	IFrame content;
		public IFrame getContent() {
			return content;
		}
		public void setContent(IFrame content) {
			this.content = content;
		}
	
}
