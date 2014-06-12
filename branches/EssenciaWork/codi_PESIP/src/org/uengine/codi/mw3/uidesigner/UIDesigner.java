package org.uengine.codi.mw3.uidesigner;

import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.ContentWindow;

public class UIDesigner extends ContentWindow{

	public UIDesigner() {
		super();
		
		Layout layout = new Layout();
		setPanel(layout);
		
		layout.setWest(new ComponentPalette());
		layout.setCenter(new UIDesignerMainPanel());
		
		layout.setSouth(new Trash());

	}
	
	

}
