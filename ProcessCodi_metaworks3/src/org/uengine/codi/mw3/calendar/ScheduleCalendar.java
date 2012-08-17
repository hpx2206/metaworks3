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
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IWorkItem;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.NewInstanceWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.webservices.worklist.DefaultWorkList;

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
	String callType;
		public String getCallType() {
			return callType;
		}
		public void setCallType(String callType) {
			this.callType = callType;
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
		IWorkItem workitems = schedule.sql("select wl.*, pi.name instnm , pi.status instanceStatus from bpm_worklist wl, bpm_procinst pi where wl.instid = pi.instid and wl.endpoint=?endpoint and wl.type is null and pi.isdeleted != 1");
		workitems.setEndpoint(session.getUser().getUserId());
		workitems.select();
		DataConvert(arrListData, workitems, session.getUser().getUserId());
		
		Instance instance = new Instance();
		String sql = 	" select inst.* "+ 
						" from bpm_procinst inst , bpm_rolemapping follower "+
						" where inst.duedate is not null " +
						" and inst.isdeleted != 1  "+
						" and inst.instId = follower.instId "+
						" and follower.endpoint in ( ?initep ) ";
		
		IInstance iInstance = instance.sql(sql);
		iInstance.setInitEp(session.getUser().getUserId());
		iInstance.select();
		DataConvert(arrListData, iInstance, session.getUser().getUserId());
		
		setData(arrListData);
	}	

	public void DataConvert(ArrayList arrListData, IInstance iInstance, String empCode) {
		Map column = new HashMap();
		try {	
			while(iInstance.next()){
				column = new HashMap();
				String title = " ";
				title = iInstance.getName();
				if(title == null || title.equals(" ") || title.equals("null")) title = "-";
				boolean completed = "Completed".equals(iInstance.get("status"));
				
				column = new HashMap();
				column.put("id", iInstance.getInstId()+"");
				column.put("callType", "instance" );
				column.put("title", title );
				column.put("start", iInstance.getStartedDate());
				
				if(iInstance.getDueDate()!=null){
					column.put("end", iInstance.getDueDate());
				}
				else{
					column.put("end", iInstance.getFinishedDate());
				}
				if(empCode.equals(iInstance.getInitEp()))
					column.put("color", "#F5C510");
				else
					column.put("color", "#FFDB2F");
	
				if(completed){
					column.put("color", "#808080");
				}
				arrListData.add(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void DataConvert(ArrayList arrListData, IWorkItem schedule, String empCode) {
		String how = getMetaworksContext().getHow();
		
		Map column = new HashMap();
		
		try {				
			while(schedule.next()){
				String title = " ";
				boolean completed = "Completed".equals(schedule.get("instanceStatus"));

				if(!how.equals("small") && how != null)
					title = schedule.getTitle();
				
				if(title == null || title.equals(" ") || title.equals("null")) title = "-";
				
				column = new HashMap();
				column.put("id", schedule.getTaskId());
				column.put("callType", "workitem" );
				column.put("title", title + "-" + schedule.get("instnm") + (completed ? "(Completed)" : ""));
				
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
					column.put("color", "#F5C510");
				else
					column.put("color", "#FFDB2F");

				if(DefaultWorkList.WORKITEM_STATUS_RESERVED.equals(schedule.getStatus())){
					column.put("color", "#FE2020");
				}
				
				if(completed){
					column.put("color", "#808080");
				}
				
				arrListData.add(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@ServiceMethod(payload={"schdId", "callType"})
	public InstanceViewContent linkScheduleEvent() throws Exception{
		String instId = null;
		if( "workitem".equals(callType) ){
			WorkItem schedule = new WorkItem();
			schedule.setTaskId(new Long(getSchdId()));
			instId = schedule.databaseMe().getInstId().toString();
		}else{
			instId = getSchdId() + "";
		}
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		return instanceViewContent;
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@ServiceMethod(callByContent=true)
	public NewInstanceWindow linkScheduleDay() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.setDueDate(getSelDate());
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		newInstancePanel.getNewInstantiator().setTitle("");
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
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
