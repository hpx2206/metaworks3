package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "ProcessDesigner Window", options = { "hideLabels" }, values = { "true" })
public class ProcessDesignerWebWindow  extends ContentWindow{
	
	@Autowired
	WpdContentPanel wpdContentPanel;
		public WpdContentPanel getWpdContentPanel() {
			return wpdContentPanel;
		}
		public void setWpdContentPanel(WpdContentPanel wpdContentPanel) {
			this.wpdContentPanel = wpdContentPanel;
		}

	@Autowired

//	WebCanvasPanel WebCanvasPanel;
//		public WebCanvasPanel getWebCanvasPanel() {
//			return WebCanvasPanel;
//		}
//		public void setWebCanvasPanel(WebCanvasPanel webCanvasPanel) {
//			WebCanvasPanel = webCanvasPanel;
//		}

	public void load(String alias) throws Exception {
		wpdContentPanel = new WpdContentPanel();
		wpdContentPanel.setAlias(alias);
		wpdContentPanel.loadOld();
		
//		WebCanvasPanel = new WebCanvasPanel();
	}
	
	public void newProcess() throws Exception {
		wpdContentPanel = new WpdContentPanel();
//		WebCanvasPanel = new WebCanvasPanel();
	}
}
