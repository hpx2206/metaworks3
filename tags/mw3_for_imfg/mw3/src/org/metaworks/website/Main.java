package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.addressbook.Person;

public class Main {

	Navigation navigation;
		@Face(
				options={"height"}, values={"400"}
				)
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

//	IFacebookLoginUser loginUser;
//		public IFacebookLoginUser getLoginUser() {
//			return loginUser;
//		}
//		public void setLoginUser(IFacebookLoginUser loginUser) {
//			this.loginUser = loginUser;
//		}
		
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
		
	@ServiceMethod
	public void load() throws Exception{
		
		Navigation navigation = new Navigation();
		
		if(session==null)
			session = new Session();
		
		if(session.loginUser!=null && session.loginUser.isAdmin()){
			MetaworksContext context = new MetaworksContext();
			context.setWhen(MetaworksContext.WHEN_EDIT);
			navigation.setMetaworksContext(context);
		}
		
		setNavigation(navigation);
		
		Menu homeMenu = new Menu();
		homeMenu.setName("Home");
		homeMenu.setMenuId(-1);
		session.setMenu(homeMenu);
		
		contentPanel = new ContentPanel();
		contentPanel.setMenu(homeMenu);
		contentPanel.session = session;
		contentPanel.load();
		
		feedbackPanel = new FeedbackPanel();
		feedbackPanel.session = session;
		feedbackPanel.load(homeMenu);

	
	}
	
}
