package org.uengine.processmarket;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ItemSearchBox {

    String keyword = "";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    @ServiceMethod(callByContent = true)
    public MarketItemPanel search() throws Exception {
	MarketItemPanel mp = new MarketItemPanel();
	IMarketItem searchItems = MarketItem.loadCategoryItemsByKeyword(this.keyword);
	searchItems.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
	searchItems.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	mp.setMarketItem(searchItems);
	return mp;
    }
}
