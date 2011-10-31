package org.metaworks.website;

import org.metaworks.MetaworksContext;
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

	IFacebookLoginUser loginUser;
		public IFacebookLoginUser getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(IFacebookLoginUser loginUser) {
			this.loginUser = loginUser;
		}

	@ServiceMethod
	public void load() throws Exception{
		
		Navigation navigation = new Navigation();
		
		if(loginUser!=null && loginUser.isAdmin()){
			MetaworksContext context = new MetaworksContext();
			context.setWhen(MetaworksContext.WHEN_EDIT);
			navigation.setMetaworksContext(context);
		}
		
		setNavigation(navigation);
		
		Menu homeMenu = new Menu();
		homeMenu.setMenuId(-1);
		
		contentPanel = new ContentPanel();
		contentPanel.setMenu(homeMenu);
		contentPanel.loginUser = loginUser;
		contentPanel.load();
		
		feedbackPanel = new FeedbackPanel();
		feedbackPanel.loginUser = loginUser;
		feedbackPanel.load(homeMenu);		
	}
	
}
