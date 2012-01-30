package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.Tab;
import org.uengine.codi.mw3.widget.TabPanel;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "EntityDesigner Window", options = { "hideLabels" }, values = { "true" })
public class EntityDesignerWindow {

	public EntityDesignerWindow(String parentFolder) throws Exception {
		EntityDesignerContentPanel entityDesigner = new EntityDesignerContentPanel();
		entityDesigner.newEntity(parentFolder);

		tabPanel = new TabPanel();
		tabPanel.addTab(new Tab("Table", entityDesigner));
		tabPanel.addTab(new Tab("Data", "미구현"));
		tabPanel.addTab(new Tab("Query", "미구현"));

	}

	TabPanel tabPanel;

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public void setTabPanel(TabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}
}
