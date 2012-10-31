package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.knowledge.WfPanel;

@Face( options={"fieldOrder"},values={"panel,scheduleCalendar,processInstanceMonitor"} )
public class MuiltiViewTab {
	
	WfPanel panel;
		@Face(displayName="지식")
		public WfPanel getPanel() {
			return panel;
		}
		public void setPanel(WfPanel panel) {
			this.panel = panel;
		}
		
	ScheduleCalendar scheduleCalendar;
		@Face(displayName="일정")
		public ScheduleCalendar getScheduleCalendar() {
			return scheduleCalendar;
		}
		public void setScheduleCalendar(ScheduleCalendar scheduleCalendar) {
			this.scheduleCalendar = scheduleCalendar;
		}
	
	ProcessInstanceMonitor processInstanceMonitor;
		@Face(displayName="프로세스")
		public ProcessInstanceMonitor getProcessInstanceMonitor() {
			return processInstanceMonitor;
		}
		public void setProcessInstanceMonitor(
				ProcessInstanceMonitor processInstanceMonitor) {
			this.processInstanceMonitor = processInstanceMonitor;
		}
		

	public void load() throws Exception{
		this.scheduleCalendar = new ScheduleCalendar(); 
		this.panel = new WfPanel();
		this.processInstanceMonitor = new ProcessInstanceMonitor();
	}
}
