package org.uengine.processmarket;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(
	ejsPath="genericfaces/Window.ejs",
	options={"hideAddBtn", "hideLabels"},
	values={"false", "true"}
)
public class MarketItemPanel implements ContextAware{

    IMarketItem marketItem;

    public IMarketItem getMarketItem() {
	return marketItem;
    }

    public void setMarketItem(IMarketItem marketItem) {
	this.marketItem = marketItem;
    }

    
    MetaworksContext metaworksContext;

        public MetaworksContext getMetaworksContext() {
            return metaworksContext;
        }
    
        public void setMetaworksContext(MetaworksContext metaworksContext) {
            this.metaworksContext = metaworksContext;
        }
   
    
    
}
