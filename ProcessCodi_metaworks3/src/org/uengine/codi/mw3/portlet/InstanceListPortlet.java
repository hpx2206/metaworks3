package org.uengine.codi.mw3.portlet;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.PersonalPerspective;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;

public class InstanceListPortlet {
	Session session;
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	Object content;
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}

	boolean loaded;
		@Hidden
		public boolean isLoaded() {
			return loaded;
		}
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	String cookieUserId;
		@Hidden
		public String getCookieUserId() {
			return cookieUserId;
		}
		public void setCookieUserId(String cookieUserId) {
			this.cookieUserId = cookieUserId;
		}
	String cookiePasswd;
		@Hidden
		public String getCookiePasswd() {
			return cookiePasswd;
		}
		public void setCookiePasswd(String cookiePasswd) {
			this.cookiePasswd = cookiePasswd;
		}
		
	String topicName;
		@Hidden
		public String getTopicName() {
			return topicName;
		}
		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
		
	@Hidden
	@ServiceMethod(callByContent=true)
	public Object[] load() throws Exception {
		Login login = new Login();
		login.setUserId("test");
		login.setPassword("test");
		
/*		if( getCookiePasswd() == null || getCookieUserId() == null){
			throw new Exception("로그인정보가 없습니다.");
		}
		login.setUserId(getCookieUserId());
		login.setPassword(getCookiePasswd());*/
		
		Session session = login.loginService();
		session.getMetaworksContext().setWhen("hidden");
		session.setLastPerspecteType("sns");
		session.getEmployee().setPreferUX("sns");
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		Object[] result = (Object[])personalPerspective.loadAllICanSee();
		
		this.setSession((Session)result[0]);
		this.setContent(result[1]);
		this.setLoaded(true);
		
		Locale locale = new Locale();
		locale.setLanguage(session.getEmployee().getLocale());
		locale.load();
		
		return new Object[]{locale, this};
	}
	
	@ServiceMethod(payload={"session"})
	public Object[] goNewspeed() throws Exception {
		System.out.println("goNewspeed");
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		return personalPerspective.loadAllICanSee();
	}
	
	@ServiceMethod(payload={"session"})
	public Object[] goTodo() throws Exception {
		System.out.println("goTodo");
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		return personalPerspective.loadInbox();
	}
	
	@ServiceMethod(payload={"session", "topicName"})
	public Object[] goTopic() throws Exception {
		return PersonalPerspective.loadInstanceListPanel(session, "topic", this.getTopicName());
	}
}
