package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WindowPanel;

public class ProcessTopPanel {

	public ProcessTopPanel(Session session) throws Exception {
		setSession(session);
		setWindowPanel(new WindowPanel());
		
		tray = new Tray();
		tray.session = session;
		tray.load();
		
		notificationBadge = new NotificationBadge();
		
		todoBadge = new TodoBadge();
		
		IUser loginUser = new User();
		loginUser.setUserId(session.getUser().getUserId());
		loginUser.setName(session.getUser().getName());
		
		setLoginUser(loginUser);
		
		if("oce".equals(session.getUx())){
			InstanceSearchBox searchBox = new InstanceSearchBox();
			searchBox.setMetaworksContext(new MetaworksContext());
			searchBox.getMetaworksContext().setWhere("topPanel");
			searchBox.setKeyUpSearch(true);
			searchBox.setKeyEntetSearch(true);
			setSearchBox(searchBox);
		}
	}
	
	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
	boolean isAdmin;
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

	Tray tray;
		public Tray getTray() {
			return tray;
		}
		public void setTray(Tray tray) {
			this.tray = tray;
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
	
	public ProcessTopPanel(){
			
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow  companyRepInfo() throws Exception{
		Company company = new Company();
		company.setComCode(session.getEmployee().getGlobalCom());
		ICompany findCompany = company.findByCode();
		if(findCompany!=null){
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.setPanel(findCompany);
			modalWindow.setWidth(400);
			modalWindow.setHeight(200);
			modalWindow.setTitle("$setComapnyRep");
			modalWindow.setMetaworksContext(new MetaworksContext());
			modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			return modalWindow;
		}
		return null;
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object feedBackInfo() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		ContactUs contactUs = new ContactUs();
		contactUs.session = session;
		modalWindow.setPanel(contactUs);
		modalWindow.setTitle("Contact Us");
		modalWindow.setWidth(700);
		modalWindow.setHeight(700);
		modalWindow.setMetaworksContext(new MetaworksContext());
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return modalWindow;
	}
	
}
