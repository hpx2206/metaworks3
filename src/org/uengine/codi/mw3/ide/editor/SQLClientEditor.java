package org.uengine.codi.mw3.ide.editor;

import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class SQLClientEditor extends Editor {

	public static int count = 0;
	
	IFrame frame;
		public IFrame getFrame() {
			return frame;
		}
		public void setFrame(IFrame frame) {
			this.frame = frame;
		}
		
	public SQLClientEditor(){
		this.count++;
		
		this.setName("SQL Client " + String.valueOf(this.count));
		this.setId("SQLClientEditor" + String.valueOf(this.count)); 
		this.setType("sqlclient");
		
		String host = GlobalContext.getPropertyString("pole.call.ip");
		String port = GlobalContext.getPropertyString("pole.call.port");
		String uri  = GlobalContext.getPropertyString("pole.call.db");
		
		String url = "http://" + host + ":" + port + uri;
		
		IFrame frame = new IFrame();
		frame.setSrc(url);
		
		this.setFrame(frame);
	}	
	
	@Override
	public Object save(){
		return null;
	}
}
