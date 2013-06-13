package org.uengine.codi.mw3.marketplace;

import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;

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
		
	public MarketplaceWestPanel(){
		this.setSearchBox(new MarketplaceSearchBox());
		
		try {
			this.setCategory(Category.loadRootCategory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
