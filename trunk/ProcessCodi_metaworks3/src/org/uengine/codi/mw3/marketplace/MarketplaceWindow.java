package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;

@Face(displayName="$AppStore", ejsPath="dwr/metaworks/genericfaces/Window.ejs", options={"hideLabels", "hideTitleBar", "hideGuideBox"}, values={"true", "true", "true"})
public class MarketplaceWindow {

	MarketplacePanel marketplace;
		public MarketplacePanel getMarketplace() {
			return marketplace;
		}
		public void setMarketplace(MarketplacePanel marketplace) {
			this.marketplace = marketplace;
		}
		
	public MarketplaceWindow(){
		this.setMarketplace(new MarketplacePanel());
	}
	
}
