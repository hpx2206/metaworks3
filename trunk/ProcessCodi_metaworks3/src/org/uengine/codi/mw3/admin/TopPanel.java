package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.NotificationBadge;
import org.uengine.codi.mw3.model.SearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TodoBadge;
import org.uengine.codi.mw3.model.TopCenterPanel;
import org.uengine.codi.mw3.model.TopMenuPanel;
import org.uengine.codi.mw3.model.TopPanelUser;

public class TopPanel {

	public TopPanel(){
		
	}
	
	public TopPanel(Session session) {
		setSession(session);
		
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
		this.getLoginUser().getMetaworksContext().setHow(IUser.HOW_SELF);
		
		TopMenuPanel topMenuPanel = new TopMenuPanel();
		this.setTopMenuPanel(topMenuPanel);

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
		
	TopCenterPanel topCenterPanel;
	public TopCenterPanel getTopCenterPanel() {
		return topCenterPanel;
	}
	public void setTopCenterPanel(TopCenterPanel topCenterPanel) {
		this.topCenterPanel = topCenterPanel;
	}

	TopMenuPanel topMenuPanel;
	public TopMenuPanel getTopMenuPanel() {
		return topMenuPanel;
	}
	public void setTopMenuPanel(TopMenuPanel topMenuPanel) {
		this.topMenuPanel = topMenuPanel;
	}

}
