package org.uengine.processmarket;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.SearchBox;

public class ItemSearchBox extends SearchBox{

    
    @ServiceMethod(callByContent = true)
    public Object[] search() throws Exception {
		MarketItemPanel mp = new MarketItemPanel();
		IMarketItem searchItems = MarketItem.loadCategoryItemsByKeyword(getKeyword());
		searchItems.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
		searchItems.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		mp.setMarketItem(searchItems);
		return new Object[]{mp};
    }
}
