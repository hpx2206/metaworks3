package org.uengine.processmarket;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;

public class Index {

    ICategory category;

    MarketItemPanel marketItemPanel;

    ItemSearchBox itemSearchBox;

    public ICategory getCategory() {
	return category;
    }

    public void setCategory(ICategory category) {
	this.category = category;
    }

    @ServiceMethod
    public void load() throws Exception {
	this.category = (ICategory) Category.loadRootCategory();
	MarketItemPanel mp = new MarketItemPanel();
	this.setMarketItemPanel(mp);
    }

    @ServiceMethod
    public Object[] upload() throws Exception {
	MarketItem item = new MarketItem();
	item.setItemFile(new MetaworksFile());
	item.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
	
	MarketItemPanel mp = new MarketItemPanel();
	mp.setMarketItem(item);

	return new Object[] {mp};
    }
    
    
    public MarketItemPanel getMarketItemPanel() {
	return marketItemPanel;
    }

    public void setMarketItemPanel(MarketItemPanel marketItemPanel) {
	this.marketItemPanel = marketItemPanel;
    }

    public ItemSearchBox getItemSearchBox() {
	if(itemSearchBox == null) {
	    itemSearchBox = new ItemSearchBox();
	}
	return itemSearchBox;
    }

    public void setItemSearchBox(ItemSearchBox itemSearchBox) {
	this.itemSearchBox = itemSearchBox;
    }

}
