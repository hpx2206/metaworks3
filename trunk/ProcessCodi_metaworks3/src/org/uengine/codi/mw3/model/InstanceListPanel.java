package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;

public class InstanceListPanel {
	
	@AutowiredFromClient
	public Session session;
	
	public InstanceListPanel(){		
		setSearchBox(new SearchBox());
		
		instanceList = new InstanceList();
		instanceList.init();
	}

	SearchBox searchBox;		
		@Face(options={"keyupSearch"}, values={"true"})
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}

	ScheduleCalendar scheduleCalendar;
		public ScheduleCalendar getScheduleCalendar() {
			return scheduleCalendar;
		}
		public void setScheduleCalendar(ScheduleCalendar scheduleCalendar) {
			this.scheduleCalendar = scheduleCalendar;
		}
		
	//@Hidden
	@Face(displayName="Calendar View")
	@ServiceMethod(inContextMenu=true)
//	@Test(scenario="first", starter=true, instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.model.ContactListPanel.chat()")
	@Test(scenario="first", starter=true, instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.admin.PageNavigator.goKnowledge()")	
	public void switchToScheduleCalendar() throws Exception{
		this.setInstanceList(null);
		this.scheduleCalendar = new ScheduleCalendar();
		this.scheduleCalendar.session = session;
		this.scheduleCalendar.load();
	}
	
	//@Hidden
	@Face(displayName="List View")
	@ServiceMethod(inContextMenu=true)
	public void switchToInstanceList() throws Exception{
		this.setScheduleCalendar(null);
	
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		InstanceListPanel instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		
		setInstanceList(instanceListPanel.getInstanceList());
	}

		
	InstanceList instanceList;
		public InstanceList getInstanceList() {
			return instanceList;
		}
		public void setInstanceList(InstanceList instanceList) {
			this.instanceList = instanceList;
		}
	

	@Test(scenario="first", instruction="새 프로세스를 시작합니다.", next="autowiredObject.org.uengine.codi.mw3.model.NewInstancePanel.initiate()")
	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		
		if(session.getEmployee().getIsAdmin())
			newInstancePanel.getMetaworksContext().setHow("admin");
		
		newInstancePanel.load();
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
	
}
