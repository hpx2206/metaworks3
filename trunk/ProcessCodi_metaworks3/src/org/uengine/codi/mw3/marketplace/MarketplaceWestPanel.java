package org.uengine.codi.mw3.marketplace;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;

public class MarketplaceWestPanel {
	
	
	MarketplaceSearchBox searchBox;
		public MarketplaceSearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(MarketplaceSearchBox searchBox) {
			this.searchBox = searchBox;
		}

	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
		
	MarketplaceMenu marketplaceMenu;
		public MarketplaceMenu getMarketplaceMenu() {
			return marketplaceMenu;
		}
		public void setMarketplaceMenu(MarketplaceMenu marketplaceMenu) {
			this.marketplaceMenu = marketplaceMenu;
		}
		
	public MarketplaceWestPanel(){
		this.setSearchBox(new MarketplaceSearchBox());
		this.setMarketplaceMenu(new MarketplaceMenu());
		
		try {
			this.setCategory(Category.loadRootCategory());
			this.getCategory().getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
