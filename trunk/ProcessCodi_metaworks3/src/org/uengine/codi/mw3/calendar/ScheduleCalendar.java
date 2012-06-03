package org.uengine.codi.mw3.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.IWorkItem;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;

public class ScheduleCalendar implements ContextAware {
	@AutowiredFromClient
	public Session session;
	
	public ScheduleCalendar(){		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		getMetaworksContext().setHow("default");
		
		setSelDate(new Date());
		setViewMode("month");
	}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	Object data;
		@Hidden
		public Object getData() {
			return data;
		}
	
		public void setData(Object data) {
			this.data = data;
		}

	int schdId;		
		@Hidden
		public int getSchdId() {
			return schdId;
		}
		public void setSchdId(int schdId) {
			this.schdId = schdId;
		}
	String viewMode;	// month = 월  ,agendaWeek = 주		
		@Hidden
		public String getViewMode() {
			return viewMode;
		}
		public void setViewMode(String viewMode) {
			this.viewMode = viewMode;
		}

	Date selDate;
		@Hidden
		public Date getSelDate() {
			return selDate;
		}
		public void setSelDate(Date selDate) {
			this.selDate = selDate;
		}
	
	
	public void load() throws Exception {
		ArrayList<Map<String, String>> arrListData = new ArrayList<Map<String, String>>();		
		
		WorkItem schedule = new WorkItem();
		IWorkItem workitems = schedule.sql("select * from bpm_worklist where endpoint=?endpoint");
		workitems.setEndpoint(session.getUser().getUserId());
		workitems.select();
		
		DataConvert(arrListData, workitems, session.getUser().getUserId());
	
		setData(arrListData);
	}	

	public void DataConvert(ArrayList arrListData, IWorkItem schedule, String empCode) {
		String how = getMetaworksContext().getHow();
		
		Map column = new HashMap();
		
		try {				
			while(schedule.next()){
				String title = " ";
				
				if(!how.equals("small") && how != null)
					title = schedule.getTitle();
				
				if(title == null || title.equals(" ") || title.equals("null")) title = "-";
				
				column = new HashMap();
				column.put("id", schedule.getTaskId());
				column.put("title", title);
				
//				//조사 수행건은 완료예정일 기준으로 출력함.
//				if("INVST".equals(schedule.getSchdTypeCombo().getSelected()))
//					column.put("start", schedule.get);
//				else
				column.put("start", schedule.getStartDate());
				
				if(schedule.getEndDate()!=null){
					column.put("end", schedule.getEndDate());
				}
				else{
					column.put("end", schedule.getDueDate());
				}
					
				
				if(empCode.equals(schedule.getEndpoint()))
					column.put("color", "#47CDD8");
				else
					column.put("color", "#9AC604");
					
				
				arrListData.add(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@ServiceMethod(payload={"schdId"})
	public InstanceViewContent linkScheduleEvent() throws Exception{
		WorkItem schedule = new WorkItem();
		schedule.setTaskId(new Long(getSchdId()));
		
		
		Instance instance = new Instance();
		instance.setInstId(schedule.databaseMe().getInstId());
		
		instanceViewContent.load(instance);
		
		return instanceViewContent;
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
}
	
//	@ServiceMethod(callByContent=true)
//	public EastRightPanel linkScheduleDay() throws Exception{
//		return linkScheduleDay(session);
//	}
//	
//	public EastRightPanel linkScheduleDay(Session session) throws Exception{
//		SchedulePanel empSchedule = new SchedulePanel("employee");
//		empSchedule.load(session, getSelDate(), getViewMode(), getSchdId());
//
//		SchedulePanel partSchedule = new SchedulePanel("part");
//		partSchedule.load(session, getSelDate(), getViewMode(), 0);
//		
//		ArrayList<SchedulePanel> schedulePanels = new ArrayList<SchedulePanel>();
//		schedulePanels.add(empSchedule);
//		schedulePanels.add(partSchedule);
//		
//		EastRightPanel eastRightPanel = new EastRightPanel();
//		eastRightPanel.setContent(schedulePanels);
//		
//		return eastRightPanel;
//	}

	/*
	public EastRightPanel LinkMySchedule() {
		
		EastRightPanel eastRightPanel = new EastRightPanel();
		
		
		

		if(this.schid == null || "".equals(this.schid))
			schedule.setStartDate(this.seldate);
		else
			schedule.setSelSchid(this.schid);
		
		schedule.setViewid(this.viewMode);
		
		try {
			schedule.load(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eastRightPanel.setContent(schedule);
		
		return eastRightPanel;		
	}	*/
	
//	@ServiceMethod(payload={"selDate"})
//	public EastLeftPanel linkScheduleCalendar() throws Exception {
//		ScheduleCalendar scheduleCalendar = new ScheduleCalendar();
//		scheduleCalendar.setSelDate(getSelDate());
//		scheduleCalendar.load(session);
//			
//		EastLeftPanel eastLeftPanel = new EastLeftPanel();
//		eastLeftPanel.setContent(scheduleCalendar);
//
//		return eastLeftPanel;		
//	}		
//}
