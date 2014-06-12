package org.uengine.codi.mw3.marketplace;

import org.metaworks.MetaworksContext;

public class MarketplacePanel {

	MarketplaceWestPanel westPanel;
		public MarketplaceWestPanel getWestPanel() {
			return westPanel;
		}
		public void setWestPanel(MarketplaceWestPanel westPanel) {
			this.westPanel = westPanel;
		}
		
	MarketplaceCenterPanel centerPanel;
		public MarketplaceCenterPanel getCenterPanel() {
			return centerPanel;
		}
		public void setCenterPanel(MarketplaceCenterPanel centerPanel) {
			this.centerPanel = centerPanel;
		}
			
	public MarketplacePanel(){
		this.setWestPanel(new MarketplaceWestPanel());
		this.setCenterPanel(new MarketplaceCenterPanel());
		
		try {
			AppList appList = new AppList();
			appList.load(null, null);
			
			this.getCenterPanel().setAppList(appList);
			this.getCenterPanel().getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}