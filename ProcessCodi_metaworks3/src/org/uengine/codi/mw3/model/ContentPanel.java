package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.widget.Person;
import org.uengine.codi.mw3.widget.Tab;
import org.uengine.codi.mw3.widget.TabPanel;

@Face(ejsPath="genericfaces/Window.ejs",
      displayName="Content",
      options={"layoutPanelName"}, values={"wih"})
public class ContentPanel {
	
	private TabPanel tabPanel;
	
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public void setTabPanel(TabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

	public ContentPanel() {
		tabPanel = new TabPanel();
		tabPanel.addTab(new Tab("Tab1",new Person("홍세진",34)));
		tabPanel.addTab(new Tab("Tab2",new Person("이혜지",33)));
		tabPanel.addTab(new Tab("Tab3",new Person("홍예은",6)));
	}
}
