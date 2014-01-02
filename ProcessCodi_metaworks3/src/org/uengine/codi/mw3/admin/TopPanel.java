package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ContentTopPanel;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.MenuTopPanel;
import org.uengine.codi.mw3.model.NotificationBadge;
import org.uengine.codi.mw3.model.SearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TodoBadge;
import org.uengine.codi.mw3.model.TopPanelUser;

public class TopPanel {

	public TopPanel(){
		
	}
	
	public TopPanel(Session session) {
		setSession(session);
		
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setWhere("topPanel");
		
		notificationBadge = new NotificationBadge();
		
		todoBadge = new TodoBadge();
		
		TopPanelUser topPanelUser = new TopPanelUser();
		try {
			topPanelUser.copyFrom(session.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLoginUser(topPanelUser);
		this.getLoginUser().setMetaworksContext(metaworksContext);

		setWindowPanel(new WindowPanel());
		
		MenuTopPanel menuTopPanel = new MenuTopPanel();
		this.setMenuTopPanel(menuTopPanel);

		setCompany(session.getCompany());
	}
	
	NotificationBadge notificationBadge;
		public NotificationBadge getNotificationBadge() {
			return notificationBadge;
		}
		public void setNotificationBadge(NotificationBadge notificationBadge) {
			this.notificationBadge = notificationBadge;
		}
		
	TodoBadge todoBadge;
		public TodoBadge getTodoBadge(){
			return todoBadge;
		}
		public void setTodoBadge(TodoBadge todoBadge){
			this.todoBadge = todoBadge;
		}
		
	String pageType;
		public String getPageType() {
			return pageType;
		}
		public void setPageType(String pageType) {
			this.pageType = pageType;
		}
	
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	TopPanelUser loginUser;
		public TopPanelUser getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(TopPanelUser loginUser) {
			this.loginUser = loginUser;
		}
		
	SearchBox searchBox;
		@Face(options={"keyupSearch"}, values={"true"})
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}
	
	String mode;
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}

	ICompany company;
		public ICompany getCompany() {
			return company;
		}
		public void setCompany(ICompany company) {
			this.company = company;
		}
		
	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
		
	ContentTopPanel contentTopPanel;
		public ContentTopPanel getContentTopPanel() {
			return contentTopPanel;
		}
		public void setContentTopPanel(ContentTopPanel contentTopPanel) {
			this.contentTopPanel = contentTopPanel;
		}

	MenuTopPanel menuTopPanel;
		public MenuTopPanel getMenuTopPanel() {
			return menuTopPanel;
		}
		public void setMenuTopPanel(MenuTopPanel menuTopPanel) {
			this.menuTopPanel = menuTopPanel;
		}

}
