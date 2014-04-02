package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.view.ContentListPanel;
import org.uengine.codi.mw3.view.NewInstanceContentListPanel;

public class PersonalPerspective extends Perspective {

	public PersonalPerspective() {
		setLabel("$Personal");
	}

	public Object[] load(String type) throws Exception {
		// init search keyword
		session.setSearchKeyword(null);

		InstanceListPanel instanceListPanel = loadInstanceList(session, Perspective.MODE_PERSONAL, type, session.getLastSelectedItem());
		
		NewInstancePanel newInstancePanel = new NewInstancePanel();
		newInstancePanel.load(session);
		
		NewInstanceContentListPanel contentListPanel = new NewInstanceContentListPanel();
		contentListPanel.setContent(instanceListPanel);
		contentListPanel.setNewInstancePanel(newInstancePanel);
		
		return new Object[]{session, contentListPanel};
	}
	
	@ServiceMethod
	public Object[] loadAllICanSee() throws Exception{
		session.setLastSelectedItem(session.getUser().getUserId());
		
		return this.load(Perspective.TYPE_NEWSFEED);
	}
	
	@ServiceMethod
	public Object[] loadAll() throws Exception{
		session.setLastSelectedItem(session.getUser().getUserId());
		
		return this.load(Perspective.TYPE_FOLLOWING);
	}
	
	@ServiceMethod
	public Object[] loadInbox() throws Exception{
		session.setLastSelectedItem(session.getUser().getUserId());
		
		return this.load(Perspective.TYPE_INBOX);
	}
	
	@ServiceMethod
	public Object[] loadRequest() throws Exception{
		session.setLastSelectedItem(session.getUser().getUserId());
		
		return this.load(Perspective.TYPE_STARTEDBYME);
	}
	
	@ServiceMethod
	public Object[] calendar() throws Exception{
		session.setLastSelectedItem(session.getUser().getUserId());
		
		ScheduleCalendar scheduleCalendar = new ScheduleCalendar();
		scheduleCalendar.session = session;
		scheduleCalendar.load();

		ContentListPanel contentListPanel = new ContentListPanel();
		contentListPanel.setContent(scheduleCalendar);

//		session.setWindowTitle(null);
		
		return new Object[]{session, contentListPanel};
	}
}
