package org.uengine.codi.mw3;

import java.util.Date;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Session;

public class Loader {

	String userId;
		@Hidden
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	String password;
		@Hidden
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
	Session session;
		@Hidden
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	String lastVisitPage;
		public String getLastVisitPage() {
			return lastVisitPage;
		}
		public void setLastVisitPage(String lastVisitPage) {
			this.lastVisitPage = lastVisitPage;
		}
		
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object auth() throws Exception {
		
		boolean isAllow = false;
		
		Login login = new Login();
		login.setUserId(this.getUserId());
		login.setPassword(this.getPassword());
		
		Session session = null;
		
		try{
			session = login.loginService();
		}catch(Exception e){			
		}
		
		if(session != null){
			this.setSession(session);
		
			login.storeIntoServerSession();
			
			isAllow = true;
		}
		
		if(isAllow){	
			return this.goMain();
		}else
			return this.goLogin();
		
		
	}
	
	public Object goLogin() {
		return new Login();
	}
	public Object[] goMain() {
		Locale locale = new Locale();
		locale.setLanguage(this.getSession().getEmployee().getLocale());
		locale.load();

		MainPanel mainPanel = null;
		PageNavigator pageNavigator = new PageNavigator();
		pageNavigator.session = session;

		try{
			if("knowledge".equals(lastVisitPage)){
				mainPanel = pageNavigator.goKnowledge();
			}else if("pinterest".equals(lastVisitPage)){
				mainPanel = pageNavigator.goPinterest();
			}else if("ide".equals(lastVisitPage)){
				mainPanel = pageNavigator.goIDE();
			}else{
				String preferUX = session.getEmployee().getPreferUX();
				if("sns".equals(preferUX) || "".equals(preferUX)){
					mainPanel = pageNavigator.goSns();
				}else{
					mainPanel = pageNavigator.goProcess();
				}
			}
		}catch(Exception e){			
		}
		
		return new Object[]{locale, mainPanel};
	}		
}