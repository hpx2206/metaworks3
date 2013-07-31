package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.widget.layout.Layout;

public class ProcessViewWindow {

	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}

	//DefinitionViewWindow의 Layout Setting
	public void load() {

		layout = new Layout();
		Layout centerInLayout = new Layout();
		
		ProcessNavigatorPanel processNavigatorPanel = new ProcessNavigatorPanel();
		processNavigatorPanel.setDefId(defId);
		processNavigatorPanel.load();
		
		ProcessViewPanel processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(defId);
		processViewPanel.load();
		
		centerInLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:50");
		centerInLayout.setNorth(processNavigatorPanel);
		centerInLayout.setCenter(processViewPanel);
		
		layout.setCenter(centerInLayout);

		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		processAttributePanel.setDefId(defId);
		processAttributePanel.load();
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__size:500");
		layout.setEast(processAttributePanel);
		
	}

}
