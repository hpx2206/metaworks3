package org.uengine.processmarket;

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
	mp.setMarketItem(searchItems);
	return mp;
    }
}
