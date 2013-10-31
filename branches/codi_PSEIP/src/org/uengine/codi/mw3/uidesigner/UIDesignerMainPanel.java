package org.uengine.codi.mw3.uidesigner;

import org.metaworks.annotation.Face;

@Face(options="hideLabels", values="true")
public class UIDesignerMainPanel {
	

	ComponentWrapper placeholderComponentWrapper;

		public ComponentWrapper getPlaceholderComponentWrapper() {
			return placeholderComponentWrapper;
		}
	
		public void setPlaceholderComponentWrapper(
				ComponentWrapper placeholderComponentWrapper) {
			this.placeholderComponentWrapper = placeholderComponentWrapper;
		}

	public UIDesignerMainPanel() {
		setPlaceholderComponentWrapper(new PlaceholderComponentWrapper());
	}
	
		
	
}
