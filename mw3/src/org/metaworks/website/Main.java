package org.metaworks.website;

import org.metaworks.annotation.ServiceMethod;

public class Main {

	Navigation navigation;
		public Navigation getNavigation() {
			return navigation;
		}
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

	ContentPanel contentPanel;
		public ContentPanel getContentPanel() {
			return contentPanel;
		}
		public void setContentPanel(ContentPanel contentPanel) {
			this.contentPanel = contentPanel;
		}
		
	FeedbackPanel feedbackPanel;
		public FeedbackPanel getFeedbackPanel() {
			return feedbackPanel;
		}
		public void setFeedbackPanel(FeedbackPanel feedbackPanel) {
			this.feedbackPanel = feedbackPanel;
		}

	IUser loginUser;
		public IUser getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(IUser loginUser) {
			this.loginUser = loginUser;
		}
	
	@ServiceMethod
	public void load() throws Exception{
		setNavigation(new Navigation());
		
		Menu homeMenu = new Menu();
		homeMenu.setMenuId(-1);
		
		contentPanel = new ContentPanel();
		contentPanel.setMenu(homeMenu);
		contentPanel.load();
		
		feedbackPanel = new FeedbackPanel();
		feedbackPanel.load(homeMenu);		
	}
	
}
