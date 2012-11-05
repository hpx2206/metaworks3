package org.uengine.processmarket;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.admin.TopPanel;
import org.uengine.codi.mw3.model.Session;

public class Market {
	
	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
//
//	MarketCategoryPanel marketCategoryPanel;
//		public MarketCategoryPanel getMarketCategoryPanel() {
//			return marketCategoryPanel;
//		}
//		public void setMarketCategoryPanel(MarketCategoryPanel marketCategoryPanel) {
//			this.marketCategoryPanel = marketCategoryPanel;
//		}		
//	
//
//	MarketItemPanel marketItemPanel;
//		public MarketItemPanel getMarketItemPanel() {
//			return marketItemPanel;
//		}
//		public void setMarketItemPanel(MarketItemPanel marketItemPanel) {
//			this.marketItemPanel = marketItemPanel;
//		}
//		
//		
//	ItemSearchBox itemSearchBox;
//		public ItemSearchBox getItemSearchBox() {
//			if (itemSearchBox == null) {
//				itemSearchBox = new ItemSearchBox();
//			}
//			return itemSearchBox;
//		}
//		public void setItemSearchBox(ItemSearchBox itemSearchBox) {
//			this.itemSearchBox = itemSearchBox;
//		}


	@ServiceMethod
	public void load() throws Exception {
		
		setLayout(new Layout());
		MarketCategoryPanel marketCategory = new MarketCategoryPanel();
		layout.setWest(marketCategory);
		layout.setCenter(new MarketItemWindow());
		layout.setNorth(new TopPanel(session));
		
		marketCategory.setCategory(Category.loadRootCategory());
		
		pageNavigator = new PageNavigator();
	}

	@AutowiredFromClient
	public Session session;
	
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}		


}
