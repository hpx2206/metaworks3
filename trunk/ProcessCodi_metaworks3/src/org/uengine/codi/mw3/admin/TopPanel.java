package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.NotificationBadge;
import org.uengine.codi.mw3.model.SearchBox;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TodoBadge;

public class TopPanel {

	public TopPanel(){
		
	}
	
	public TopPanel(Session session) {
		setSession(session);
		setWindowPanel(new WindowPanel());
		
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setWhere("topPanel");
		
		notificationBadge = new NotificationBadge();
		
		todoBadge = new TodoBadge();
		
		setLoginUser(session.getUser());
		this.getLoginUser().setMetaworksContext(metaworksContext);
		
		setCompany(session.getCompany());
		
		/*
		if("oce".equals(session.getUx())){
			InstanceSearchBox searchBox = new InstanceSearchBox();
			searchBox.setMetaworksContext(metaworksContext);
			searchBox.setKeyUpSearch(true);
			searchBox.setKeyEntetSearch(true);
			setSearchBox(searchBox);
		}
		*/
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
		
	IUser loginUser;
		public IUser getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(IUser loginUser) {
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

	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
		
	ICompany company;
		public ICompany getCompany() {
			return company;
		}
		public void setCompany(ICompany company) {
			this.company = company;
		}
	
}
