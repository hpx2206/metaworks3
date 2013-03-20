package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.Category;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="TopInstall"
	)
public class MarketplaceCenterWindow {

	MarketplaceCenterPanel centerPanel;
		public MarketplaceCenterPanel getCenterPanel() {
			return centerPanel;
		}
	
		public void setCenterPanel(MarketplaceCenterPanel centerPanel) {
			this.centerPanel = centerPanel;
		}
	
	public MarketplaceCenterWindow(Session session) throws Exception{
		
		centerPanel = new MarketplaceCenterPanel();
		centerPanel.session = session;
		centerPanel.load();
		
	}
}
