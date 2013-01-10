package org.uengine.codi.mw3.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.CommentWorkItem;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IWorkItem;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.InstanceViewThreadPanel;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.NewInstanceWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.webservices.worklist.DefaultWorkList;

import com.efsol.util.StringUtils;

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
	String showUserId;
		@Hidden
		public String getShowUserId() {
			return showUserId;
		}
		public void setShowUserId(String showUserId) {
			this.showUserId = showUserId;
		}
	
	public void load() throws Exception {
		String userId = session.getUser().getUserId();
		loadByUserId(userId);
	}	
	
	public void loadByUserId(String userId) throws Exception {
		ArrayList<Map<String, String>> arrListData = new ArrayList<Map<String, String>>();		
		
		WorkItem schedule = new WorkItem();
		IWorkItem workitems = schedule.sql("select wl.*, pi.name instnm , pi.status instanceStatus from bpm_worklist wl, bpm_procinst pi where wl.instid = pi.instid and wl.endpoint=?endpoint and wl.type is null and pi.isdeleted != 1");
		workitems.setEndpoint(userId);
		workitems.select();
		DataConvert(arrListData, workitems, userId);
		
		Instance instance = new Instance();
		String sql = 	" select distinct(inst.INSTID), inst.DEFVERID, inst.DEFID, inst.DEFNAME, inst.DEFPATH, inst.DEFMODDATE, inst.STARTEDDATE, inst.FINISHEDDATE, inst.DUEDATE, inst.STATUS, inst.INFO, inst.NAME, inst.ISDELETED, inst.ISADHOC, inst.ISARCHIVE, inst.ISSUBPROCESS, inst.ISEVENTHANDLER, inst.ROOTINSTID, inst.MAININSTID, inst.MAINDEFVERID, inst.MAINACTTRCTAG, inst.MAINEXECSCOPE, inst.ABSTRCPATH, inst.DONTRETURN, inst.MODDATE, inst.EXT1, inst.INITEP, inst.INITRSNM, inst.CURREP, inst.CURRRSNM, inst.STRATEGYID, inst.PREVCURREP, inst.PREVCURRRSNM, inst.STARCNT, inst.STARRSNM, inst.STARFLAG, inst.ABSTRACTINST, inst.CURRTRCTAG, inst.PREVTRCTAG, inst.INITCOMCD, inst.SECUOPT, inst.lastcmnt, inst.initcmpl "+ 
						" from bpm_procinst inst , bpm_rolemapping follower "+
						" where inst.duedate is not null " +
						" and inst.isdeleted != 1  "+
						" and inst.instId = follower.instId "+
						" and follower.endpoint in ( ?initep ) ";
		
		IInstance iInstance = instance.sql(sql);
		iInstance.setInitEp(userId);
		iInstance.select();
		DataConvert(arrListData, iInstance, userId);
		
		setShowUserId(userId);
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
//				column.put("start", iInstance.getStartedDate();
				column.put("start", iInstance.getDueDate());	// set startDate equals endDate
				
				if(iInstance.getDueDate()!=null){
					column.put("end", iInstance.getDueDate());
				}
				else{
					column.put("end", iInstance.getFinishedDate());
				}
				if(empCode.equals(iInstance.getInitEp()))
					column.put("color", "#348017");
				else
					column.put("color", "#C48189");
	
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
//				column.put("start", schedule.getStartDate());
				column.put("start", schedule.getDueDate());	// 마지막 날만 보이도록 셋팅함
				
				if(schedule.getEndDate()!=null){
					column.put("end", schedule.getEndDate());
				}
				else{
					column.put("end", schedule.getDueDate());
				}
					
				
				if(empCode.equals(schedule.getEndpoint()))
					column.put("color", "#348017");
				else
					column.put("color", "#C48189");

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
	public Object[] linkScheduleEvent() throws Exception{
		if("sns".equals(session.getEmployee().getPreferUX()) ){
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
		
		IInstance scview = instance.databaseMe();
		
		InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
		panel.getMetaworksContext().setHow("instanceList");
		panel.getMetaworksContext().setWhere("sns");
		panel.session = session;
		panel.load(instId);
		scview.setInstanceViewThreadPanel(panel);
		
		InstanceList list = new InstanceList();
		
		list.init();  //instanceList에서 page 변경으로 인해서 추가해줌.
		list.setInstances(scview);
		
		return new Object[]{list};
		
		} else {
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
			
			return new Object[]{instanceViewContent};
			
		}
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	public InstanceListPanel instanceListPanel;
	
	@ServiceMethod(callByContent=true)
	public Object[] linkScheduleDay() throws Exception{
		if( getShowUserId() != null && !getShowUserId().equalsIgnoreCase(session.getUser().getUserId())){
			// 다른 유저의 달력을 클릭하였을 경우 새로글쓰기를 막는다.
			return null;
		}
		Calendar c = Calendar.getInstance ( );
		c.setTime(getSelDate());
		c.set ( c.HOUR_OF_DAY  , +23);
		c.set ( c.MINUTE  , +59);
		Date dueDate = c.getTime();
		String title = "";
		if( getSelDate() != null ){
			title = "[일정:" + new SimpleDateFormat("yyyy/MM/dd").format(getSelDate()) + "]" ;
		}
		if("sns".equals(session.getEmployee().getPreferUX()) ){
			if( instanceListPanel != null ){
				WorkItem newInstantiator = new CommentWorkItem();
				newInstantiator.setWriter(session.getUser());
				newInstantiator.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				newInstantiator.getMetaworksContext().setHow("sns");
				newInstantiator.getMetaworksContext().setWhere("sns");
				newInstantiator.setInstantiation(true);
				newInstantiator.setDueDate(dueDate);
				newInstantiator.setTitle(title);
				
				instanceListPanel.setNewInstantiator(newInstantiator);
				return new Object[]{instanceListPanel};
			}
			return null;
		}else{
			NewInstancePanel newInstancePanel =  new NewInstancePanel();
			newInstancePanel.setDueDate(dueDate);
			newInstancePanel.session = session;
			newInstancePanel.load(session);
			newInstancePanel.getNewInstantiator().setTitle(title);
			
			return new Object[]{new NewInstanceWindow(newInstancePanel)};
		}
	}
	
}
