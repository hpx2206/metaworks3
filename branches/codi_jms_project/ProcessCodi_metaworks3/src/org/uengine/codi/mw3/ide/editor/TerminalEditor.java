package org.uengine.codi.mw3.ide.editor;

import org.uengine.codi.mw3.widget.IFrame;

public class TerminalEditor extends Editor {

	public static int count = 0;
	
	IFrame frame;
		public IFrame getFrame() {
			return frame;
		}
		public void setFrame(IFrame frame) {
			this.frame = frame;
		}
		
	public TerminalEditor(){	
		this.count++;
		
		this.setName("Terminal " + String.valueOf(this.count));
		this.setId("TerminalEditor" + String.valueOf(this.count)); 
		this.setType("terminal");
		
		IFrame frame = new IFrame();
		frame.setSrc("http://192.168.0.10:6080/vnc_auto.html?token=5af77cf4-8127-4137-b272-659ad1f9595f&title=test(89e3fd1f-3602-4b14-9e19-94407004797e)");
		
		this.setFrame(frame);
	}	
	
	@Override
	public Object save(){
		return null;
	}
}
