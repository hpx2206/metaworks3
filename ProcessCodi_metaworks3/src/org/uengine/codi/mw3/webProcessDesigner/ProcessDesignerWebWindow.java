package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.metaworks.widget.layout.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "ProcessDesigner Window", options = { "hideLabels" }, values = { "true" })
public class ProcessDesignerWebWindow  extends ContentWindow{
	
	@Autowired
	ProcessDesignerWebContentPanel processDesignerWebContentPanel;
	@Autowired
		public ProcessDesignerWebContentPanel getProcessDesignerWebContentPanel() {
			return processDesignerWebContentPanel;
		}
	
		public void setProcessDesignerWebContentPanel(
				ProcessDesignerWebContentPanel processDesignerWebContentPanel) {
			this.processDesignerWebContentPanel = processDesignerWebContentPanel;
		}

	public void load(String alias) throws Exception {
		processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
		processDesignerWebContentPanel.setAlias(alias);
		processDesignerWebContentPanel.load();
	}
	
	public void newProcess() throws Exception {
		processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
	}
}
