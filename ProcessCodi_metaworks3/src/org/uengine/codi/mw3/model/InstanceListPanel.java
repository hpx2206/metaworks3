package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
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
	@ServiceMethod(inContextMenu=true)
	public void switchToScheduleCalendar() throws Exception{
		this.scheduleCalendar = new ScheduleCalendar();
		this.scheduleCalendar.session = session;
		this.scheduleCalendar.load();
	}
	
	//@Hidden
	@ServiceMethod(inContextMenu=true)
	public void switchToInstanceList() throws Exception{
		instanceList = new InstanceList();
		instanceList.init();
	}

		
	InstanceList instanceList;
		public InstanceList getInstanceList() {
			return instanceList;
		}
		public void setInstanceList(InstanceList instanceList) {
			this.instanceList = instanceList;
		}
	

	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		
		if(session.getEmployee().getIsAdmin())
			newInstancePanel.getMetaworksContext().setHow("admin");
		
		newInstancePanel.load();
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
	
}
