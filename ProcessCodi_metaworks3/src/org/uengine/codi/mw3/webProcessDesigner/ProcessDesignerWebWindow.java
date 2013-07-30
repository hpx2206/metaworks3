package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "ProcessDesigner Window", options = { "hideLabels" }, values = { "true" })
public class ProcessDesignerWebWindow  extends ContentWindow{
	
	@Autowired
	ProcessDesignerContentPanel processDesignerContentPanel;
		public ProcessDesignerContentPanel getProcessDesignerContentPanel() {
			return processDesignerContentPanel;
		}
		public void setProcessDesignerContentPanel(
				ProcessDesignerContentPanel processDesignerContentPanel) {
			this.processDesignerContentPanel = processDesignerContentPanel;
		}

	public void load(String alias) throws Exception {
		processDesignerContentPanel = new ProcessDesignerContentPanel();
		processDesignerContentPanel.setAlias(alias);
		processDesignerContentPanel.loadOld();
		
//		WebCanvasPanel = new WebCanvasPanel();
	}
	
	public void newProcess() throws Exception {
		processDesignerContentPanel = new ProcessDesignerContentPanel();
//		WebCanvasPanel = new WebCanvasPanel();
	}
}
