package org.uengine.processmarket;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;

public class Index {

    MarketCategoryPanel marketCategoryPanel;

    MarketItemPanel marketItemPanel;

    ItemSearchBox itemSearchBox;

    public MarketCategoryPanel getMarketCategoryPanel() {
        return marketCategoryPanel;
    }

    public void setMarketCategoryPanel(MarketCategoryPanel marketCategoryPanel) {
        this.marketCategoryPanel = marketCategoryPanel;
    }

    @ServiceMethod
    public void load() throws Exception {
	this.marketCategoryPanel = new MarketCategoryPanel();
	this.marketCategoryPanel.setCategory(Category.loadRootCategory());
	MarketItemPanel mp = new MarketItemPanel();
	this.setMarketItemPanel(mp);
    }

    @ServiceMethod
    public Object[] upload() throws Exception {
	MarketItem item = new MarketItem();
	item.setItemFile(new MetaworksFile());
	item.setLogoImageFile(new MetaworksFile());
	item.setImageFile1(new MetaworksFile());
	item.setImageFile2(new MetaworksFile());
	item.setImageFile3(new MetaworksFile());
	item.setImageFile4(new MetaworksFile());
	item.setImageFile5(new MetaworksFile());
	
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
