package org.uengine.processmarket;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;

public class MarketItemPanel implements ContextAware {

    IMarketItem marketItem;

	    public IMarketItem getMarketItem() {
	    	return marketItem;
	    }
	
	    public void setMarketItem(IMarketItem marketItem) {
	    	this.marketItem = marketItem;
	    }
	    
	int categoryId;
	@Id  
		public int getCategoryId() {
			return categoryId;
		}
	
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public MarketItemPanel(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}

	@Face(displayName="새 아이템 등록")
	@ServiceMethod
	public Object[] newItem() throws Exception {
		
		
		MarketItem item = new MarketItem();
		item.setCategoryId(categoryId);
		item.setItemFile(new MetaworksFile());
		item.setLogoImageFile(new MetaworksImageFile());
		item.setImageFile1(new MetaworksImageFile());
		item.setImageFile2(new MetaworksImageFile());
		item.setImageFile3(new MetaworksImageFile());
		item.setImageFile4(new MetaworksImageFile());
		item.setImageFile5(new MetaworksImageFile());

		item.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);

		MarketItemPanel mp = new MarketItemPanel();
		mp.setMarketItem(item);
		mp.setCategoryId(categoryId);
		mp.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return new Object[] { mp };
	}
    
	 
}
