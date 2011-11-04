package org.uengine.processmarket;

import org.metaworks.annotation.Face;

@Face(
	ejsPath="genericfaces/Window.ejs",
	options={"hideAddBtn", "hideLabels"},
	values={"true", "true"}
)
public class MarketItemPanel {

    IMarketItem marketItem;

    public IMarketItem getMarketItem() {
	return marketItem;
    }

    public void setMarketItem(IMarketItem marketItem) {
	this.marketItem = marketItem;
    }
}
