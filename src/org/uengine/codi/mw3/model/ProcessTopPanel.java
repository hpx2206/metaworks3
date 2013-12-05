package org.uengine.codi.mw3.model;

import javax.xml.ws.ServiceMode;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WindowPanel;

public class ProcessTopPanel {

	public ProcessTopPanel() {
		
	}
	public ProcessTopPanel(Session session) throws Exception {
		setSession(session);
		setWindowPanel(new WindowPanel());
		
		tray = new Tray();
		tray.session = session;
		tray.load();
		
		notificationBadge = new NotificationBadge();
		notificationBadge.session = session;
		notificationBadge.refresh();
		
		todoBadge = new TodoBadge();
		todoBadge.session = session;
		todoBadge.refresh();
		
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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, callByContent=true)
	public ModalWindow serviceChoice() {
		ServiceChoicePanel serviceChoicePanel = new ServiceChoicePanel();
		serviceChoicePanel.session = session;
		serviceChoicePanel.setOrderInfo("발주 정보");
		serviceChoicePanel.setKoreaPublicService("한국형 공공서비스");
		serviceChoicePanel.setExportConsulting("수출 컨설팅 지원");
		serviceChoicePanel.setUserFunctionImprovement("사용자 기능개선");
		serviceChoicePanel.setExportEnterprisePool("국내외 수출 기업 pool");
		serviceChoicePanel.setSocialNetworking("소셜 네트워킹");
		serviceChoicePanel.setKnolMarket("지식마켓");

		ModalWindow modalWindow = new ModalWindow(serviceChoicePanel, 700, 400, "서비스 선택");
		
		return modalWindow;
	}		
		
}
