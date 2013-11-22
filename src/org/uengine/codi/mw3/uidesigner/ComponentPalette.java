package org.uengine.codi.mw3.uidesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.Tab;
import org.metaworks.widget.TabPanel;
import org.metaworks.widget.layout.Layout;

@Face(options="hideLabels", values="true")
public class ComponentPalette {
	
	ComponentWrapper[] componentWrappers;

		public ComponentWrapper[] getComponentWrappers() {
			return componentWrappers;
		}
	
		public void setComponentWrappers(ComponentWrapper[] componentWrappers) {
			this.componentWrappers = componentWrappers;
		}

	public ComponentPalette() {
		
		Layout layout = new Layout();
		layout.setCenter(new PlaceholderComponentWrapper());
		layout.setNorth(new PlaceholderComponentWrapper());
		layout.setSouth(new PlaceholderComponentWrapper());
		layout.setWest(new PlaceholderComponentWrapper());
		layout.setMetaworksContext(new MetaworksContext());
		layout.getMetaworksContext().setWhen("design");
		
		componentWrappers = new ComponentWrapper[]{
			new ComponentWrapper().init(layout),  //not good since the object will be cloned in server by sending from client which sent from server.
			TabPanelComponentWrapper.createNewForPalette(),
			new ComponentWrapper().init(new SelectBox())
		};

	}

	
}
