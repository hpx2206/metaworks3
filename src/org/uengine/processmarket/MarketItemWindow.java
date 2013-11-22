package org.uengine.processmarket;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;


@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="$Items"
	)
public class MarketItemWindow {
	
	
	MarketItemPanel marketItemPanel;

		public MarketItemPanel getMarketItemPanel() {
			return marketItemPanel;
		}
	
		public void setMarketItemPanel(MarketItemPanel marketItemPanel) {
			this.marketItemPanel = marketItemPanel;
		}
		
		
	
	public MarketItemWindow(){
		setMarketItemPanel(new MarketItemPanel());
	}

}
