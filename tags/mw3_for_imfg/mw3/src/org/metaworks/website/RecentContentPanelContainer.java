package org.metaworks.website;

import org.metaworks.annotation.Face;

@Face(
		ejsPath="genericfaces/ObjectFace.ejs",
		options={"hideAddBtn", "hideLabels", "hideEditBtn", "hideNewBtn"},
		values={"true", "true", "true", "true"},
		displayName="Recent Contents"
	)

public class RecentContentPanelContainer {

	RecentContentPanel recentContentPanel;
	
		public RecentContentPanel getRecentContentPanel() {
			return recentContentPanel;
		}
	
		public void setRecentContentPanel(RecentContentPanel recentContentPanel) {
			this.recentContentPanel = recentContentPanel;
		}

		
	public RecentContentPanelContainer() throws Exception{
		setRecentContentPanel(new RecentContentPanel());
		getRecentContentPanel().setPage(0);
		getRecentContentPanel().fill();

	}
}
