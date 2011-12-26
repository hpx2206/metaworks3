package org.metaworks.website;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;

public class RecentContentPanel implements ContextAware{

	RecentContentPanel moreRecentContentPanel;
		public RecentContentPanel getMoreRecentContentPanel() {
			return moreRecentContentPanel;
		}
		public void setMoreRecentContentPanel(RecentContentPanel moreRecentContentPanel) {
			this.moreRecentContentPanel = moreRecentContentPanel;
		}

	IContents recentContents;
		public IContents getRecentContents() {
			return recentContents;
		}
		public void setRecentContents(IContents recentContents) {
			this.recentContents = recentContents;
		}

	int page;
		@Id
		@Hidden
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
	public void fill() throws Exception{
		setRecentContents(Contents.loadRecentContents(getPage()));
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("filled");

		moreRecentContentPanel = new RecentContentPanel();
		moreRecentContentPanel.setPage(getPage()+1);
		
		moreRecentContentPanel.setMetaworksContext(new MetaworksContext());
		moreRecentContentPanel.getMetaworksContext().setWhen("not-filled");
	}
	
	@ServiceMethod(when="not-filled")
	public void more() throws Exception{
		fill();
	}
	
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	

}
