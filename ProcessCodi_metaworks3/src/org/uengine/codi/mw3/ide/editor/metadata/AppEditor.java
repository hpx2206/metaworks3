package org.uengine.codi.mw3.ide.editor.metadata;

import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.widget.IFrame;

public class AppEditor extends Editor{

	IFrame frame;
		public IFrame getFrame() {
			return frame;
		}
		public void setFrame(IFrame frame) {
			this.frame = frame;
		}
	
	public AppEditor(){
		super();
	}
	public AppEditor(ResourceNode node){
		super(node);
	}
}
