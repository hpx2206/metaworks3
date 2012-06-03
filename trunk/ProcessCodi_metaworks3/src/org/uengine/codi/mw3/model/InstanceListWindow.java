package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs", 
	  displayName="개인중심 - 내가 할 일", 
      options={"hideLabels", "minimize"}, 
      values={"true", "true"})

public class InstanceListWindow {
	

	public InstanceListWindow(){}
	public InstanceListWindow(Session session) throws Exception {
		switchToInstanceListPanel();
	}
	
	InstanceListPanel instanceListPanel;
		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
		
	ScheduleCalendar scheduleCalendar;
		public ScheduleCalendar getScheduleCalendar() {
			return scheduleCalendar;
		}
		public void setScheduleCalendar(ScheduleCalendar scheduleCalendar) {
			this.scheduleCalendar = scheduleCalendar;
		}
		
	@Hidden
	@ServiceMethod(inContextMenu=true)
	public void switchToScheduleCalendar() throws Exception{
		this.scheduleCalendar = new ScheduleCalendar();
		this.scheduleCalendar.session = session;
		this.scheduleCalendar.load();
	}
	
	@Hidden
	@ServiceMethod(inContextMenu=true)
	public void switchToInstanceListPanel() throws Exception{
		this.instanceListPanel = new InstanceListPanel();
		this.instanceListPanel.session = session;
	}
		
	@AutowiredFromClient
	public Session session;
}
