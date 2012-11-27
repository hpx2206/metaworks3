package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.webpd.WebCanvasPanel;

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

//	WebCanvasPanel WebCanvasPanel;
//		public WebCanvasPanel getWebCanvasPanel() {
//			return WebCanvasPanel;
//		}
//		public void setWebCanvasPanel(WebCanvasPanel webCanvasPanel) {
//			WebCanvasPanel = webCanvasPanel;
//		}

	public void load(String alias) throws Exception {
		processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
		processDesignerWebContentPanel.setAlias(alias);
		processDesignerWebContentPanel.load();
		
//		WebCanvasPanel = new WebCanvasPanel();
	}
	
	public void newProcess() throws Exception {
		processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
//		WebCanvasPanel = new WebCanvasPanel();
	}
}
