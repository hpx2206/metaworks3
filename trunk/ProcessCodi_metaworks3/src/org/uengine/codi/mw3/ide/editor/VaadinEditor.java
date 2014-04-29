package org.uengine.codi.mw3.ide.editor;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.IFrame;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/editor/SQLClientEditor.ejs")
public class VaadinEditor extends Editor {

	public static int count = 0;
	
	IFrame frame;
	public IFrame getFrame() {
		return frame;
	}
	public void setFrame(IFrame frame) {
		this.frame = frame;
	}
	
	public VaadinEditor(){
		this.count++;
		this.setName("vaadin Client " + String.valueOf(this.count));
		this.setId("vaadinClientEditor" + String.valueOf(this.count)); 
		this.setType("vaadinEditor");
		
//		String host = GlobalContext.getPropertyString("pole.call.ip","localhost");
//		String port = GlobalContext.getPropertyString("pole.call.port","80");
//		String uri  = GlobalContext.getPropertyString("pole.call.db","/tadpole");
//		
//		String url = "http://" + host + ":" + port + uri;
		String url = "http://demo.vaadin.com/visualdesigner-nightly";
		
		IFrame frame = new IFrame();
		frame.setSrc(url);
		
		this.setFrame(frame);
	}	
	
	@Override
	public Object save(){
		return null;
	}
}
