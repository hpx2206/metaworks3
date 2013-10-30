package org.uengine.codi.mw3.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.CommentWorkItem;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IWorkItem;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.InstanceViewThreadPanel;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.NewInstanceWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.webservices.worklist.DefaultWorkList;



@Face(
	ejsPathMappingByContext={
		"{where: 'oce_dashboard', face: 'dwr/metaworks/org/uengine/oce/ScheduleCalendar_dashboard.ejs'}",
	}
)
public class ScheduleCalendar implements ContextAware {
	@AutowiredFromClient
	public Session session;
	
	public static final String CALLTYPE_WORKITEM = "workitem";
	public static final String CALLTYPE_INSTANCE = "instance";
	
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
		
	Date endDate;
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	boolean allDay;
		public boolean isAllDay() {
			return allDay;
		}
		public void setAllDay(boolean allDay) {
			this.allDay = allDay;
		}
		
	public void load() throws Exception {
		String userId = session.getUser().getUserId();
		loadByUserId(userId);
	}	
	
	public void loadByUserId(String userId) throws Exception {
		ArrayList<Map<String, String>> arrListData = new ArrayList<Map<String, String>>();		
		
		WorkItem schedule = new WorkItem();
//		IWorkItem workitems = schedule.sql("select wl.*, pi.name instnm , pi.status instanceStatus from bpm_worklist wl, bpm_procinst pi where wl.instid = pi.instid and wl.endpoint=?endpoint and wl.type is null and pi.isdeleted != 1");
		//wl.duedate 호출 대신 pi의 duedate 호출(nipa반영:cjs)
		/*String wsql ="select wl.taskid, wl.title, wl.description, wl.endpoint, wl.status, " +
							"wl.priority, wl.startdate, wl.enddate, pi.duedate, wl.instid, wl.defid, " +
							"wl.defname, wl.trctag, wl.tool, wl.parameter, wl.groupid, wl.groupname, " +
							"wl.isurgent, wl.hasattachfile, wl.hascomment, wl.documentcategory, wl.isdeleted, " +
							"wl.rootinstid, wl.dispatchoption, wl.dispatchparam1, wl.rolename, wl.resname, " +
							"wl.refrolename, wl.ext1, wl.ext2, wl.ext3, wl.ext4, wl.ext5,  wl.ext6,  wl.ext7, " +
							"wl.ext8, wl.ext9, wl.ext10, wl.prttskid,  wl.grptaskid, wl.execscope, " +
							"wl.savedate, wl.abstract,  wl.type,  wl.content, wl.extfile, " +
							"wl.prevver, wl.nextver,  wl.majorver, wl.minorver,  pi.name instnm , pi.status instancestatus " +
							"from bpm_worklist wl, bpm_procinst pi " +
							"where wl.instid = pi.instid and wl.endpoint=?endpoint " +
							"and pi.isdeleted != 1 and wl.duedate is not null and wl.defid is not null";
		
		IWorkItem workitems = schedule.sql(wsql);
		workitems.setEndpoint(userId);
		workitems.select();
		DataConvert(arrListData, workitems, userId);
		*/
		
		/*
		Instance instance = new Instance();
		String wsql ="select * from bpm_procinst where  defverid != 'Unstructured.process' and initep=?endpoint and isdeleted != 1";
		IInstance iInstanceProcess = instance.sql(wsql);
		iInstanceProcess.setInitEp(userId);
		iInstanceProcess.select();
		DataConvert(arrListData, iInstanceProcess, userId);
		*/
		
		String sql = 	" select distinct(inst.INSTID), inst.DEFVERID, inst.DEFID, inst.DEFNAME, inst.DEFPATH, inst.DEFMODDATE, inst.STARTEDDATE, inst.FINISHEDDATE, inst.DUEDATE, inst.STATUS, inst.INFO, inst.NAME, inst.ISDELETED, inst.ISADHOC, inst.ISARCHIVE, inst.ISSUBPROCESS, inst.ISEVENTHANDLER, inst.ROOTINSTID, inst.MAININSTID, inst.MAINDEFVERID, inst.MAINACTTRCTAG, inst.MAINEXECSCOPE, inst.ABSTRCPATH, inst.DONTRETURN, inst.MODDATE, inst.EXT1, inst.INITEP, inst.INITRSNM, inst.CURREP, inst.CURRRSNM, inst.STRATEGYID, inst.PREVCURREP, inst.PREVCURRRSNM, inst.STARCNT, inst.STARRSNM, inst.STARFLAG, inst.ABSTRACTINST, inst.CURRTRCTAG, inst.PREVTRCTAG, inst.INITCOMCD, inst.SECUOPT, inst.lastcmnt, inst.initcmpl "+ 
						" from bpm_procinst inst , bpm_rolemapping follower "+
						" where inst.duedate is not null " +
						" and inst.isdeleted != 1  "+
						" and inst.instId = follower.instId "+
						" and follower.endpoint in ( ?initep )" + 
						" order by inst.duedate";
						//" and inst.defverId = 'Unstructured.process'";
		
		Instance instance = new Instance();
		IInstance iInstance = instance.sql(sql);
		iInstance.setInitEp(userId);
		iInstance.select();		
		
		DataConvert(arrListData, iInstance, userId);
		
		setShowUserId(userId);
		setData(arrListData);
	}

	public void DataConvert(ArrayList arrListData, IInstance iInstance, String empCode) {
		
		String prevDate = null;
		int prevCnt = 1;
		
		Map column = new HashMap();
		try {	
			while(iInstance.next()){
				column = new HashMap();
				String title = iInstance.getName();
				
				column = new HashMap();
				column.put("id", iInstance.getInstId().toString());
				column.put("callType", "instance" );
				column.put("title", title );
				column.put("start", iInstance.getDueDate());
				
				if(iInstance.getExt1() != null && "false".equals(iInstance.getExt1())){
					column.put("allDay", false);
				}else{
					column.put("allDay", true);
				}
				/*
				if(iInstance.getDueDate()!=null){
					column.put("end", iInstance.getDueDate());
				}
				else{
					column.put("end", iInstance.getFinishedDate());
				}
				*/
				
				if(empCode.equals(iInstance.getInitEp()))
					column.put("color", "#348017");
				else
					column.put("color", "#C48189");
	
				if("Completed".equals(iInstance.get("status"))){
					column.put("color", "#808080");
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String dueDate = df.format(iInstance.getDueDate());
				
				if(!"oce".equals(session.getUx()) || prevDate == null || !prevDate.equals(dueDate)){
					arrListData.add(column);
					
					prevDate = dueDate;
					prevCnt = 1;
					
					if("oce".equals(session.getUx())){
						((HashMap)arrListData.get(arrListData.size()-1)).put("callType", "more" );
						((HashMap)arrListData.get(arrListData.size()-1)).put("title", prevCnt +  "건");
					}
				}else{
					prevCnt++;
					
					((HashMap)arrListData.get(arrListData.size()-1)).put("callType", "more" );
					((HashMap)arrListData.get(arrListData.size()-1)).put("title", prevCnt +  "건");
				}
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
				boolean completed = "Completed".equals(schedule.get("status"));

				if(!how.equals("small") && how != null)
					title = (String)schedule.get("name");
				
				if(title == null || title.equals(" ") || title.equals("null")) title = "-";
				
				column = new HashMap();
				column.put("id", schedule.getTaskId());
				column.put("callType", "workitem" );
				column.put("title", title + "-" + schedule.get("name") + (completed ? "(Completed)" : ""));
				
//				//조사 수행건은 완료예정일 기준으로 출력함.
//				if("INVST".equals(schedule.getSchdTypeCombo().getSelected()))
//					column.put("start", schedule.get);
//				else
//				column.put("start", schedule.getStartDate());
				column.put("start", schedule.getDueDate());	// 마지막 날만 보이도록 셋팅함
				
				/*
				if(schedule.getEndDate()!=null){
					column.put("end", schedule.getEndDate());
				}
				else{
					column.put("end", schedule.getDueDate());
				}
				*/
					
				
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
	
	@ServiceMethod(payload={"schdId", "callType"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] linkScheduleEvent() throws Exception{
		
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
		
		if("more".equals(this.getCallType())){
			IInstance scview = instance.databaseMe();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String dueDate = df.format(scview.getDueDate());
			
			session.setLastPerspecteType("calendar");
			session.setLastSelectedItem(dueDate);
			
			InstanceList instList = new InstanceList(session);
			instList.session = session;
			instList.load();
			
			Popup popup = new Popup();
			popup.setName("Todo List");
			popup.setPanel(instList);
			
			return new Object[]{ popup };
		}else{
			if("sns".equals(session.getEmployee().getPreferUX()) ){
				IInstance scview = instance.databaseMe();
				
				InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
				panel.getMetaworksContext().setHow("instanceList");
				panel.getMetaworksContext().setWhere("sns");
				panel.session = session;
				panel.load(instId);
				scview.setInstanceViewThreadPanel(panel);
				
				InstanceList list = new InstanceList(session);
				list.setInstances(scview);
				
				return new Object[]{new Refresh(list)};
				
			} else if("oce".equals(session.getUx())){
				session.setLastSelectedItem("goSns");
				session.setUx("sns");
				return new Object[]{new MainPanel(new Main(session, String.valueOf(instId)))};
			} else {

				instanceViewContent.session = session;
				instanceViewContent.load(instance);
				
				return new Object[]{new Refresh(instanceViewContent)};
				
			}
		}
		
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	@ServiceMethod(callByContent=true)
	public Object[] linkScheduleDay() throws Exception{
		if( getShowUserId() != null && !getShowUserId().equalsIgnoreCase(session.getUser().getUserId())){
			// 다른 유저의 달력을 클릭하였을 경우 새로글쓰기를 막는다.
			return null;
		}
		
		String title = "";
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale(session.getEmployee().getLocale()));
		
		if(!allDay)
			dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, new Locale(session.getEmployee().getLocale()));

		String date = dateFormat.format(getSelDate());
		SimpleDateFormat sf = (SimpleDateFormat) dateFormat;
		String pattern = sf.toLocalizedPattern();
		
		if( getSelDate() != null ){
			title = "[" + date + "]";
			
			Calendar c = Calendar.getInstance ( );
			c.setTime(getSelDate());
			c.set ( c.HOUR_OF_DAY  , +23);
			c.set ( c.MINUTE  , +59);
			
			this.setSelDate(c.getTime());			
		}

		WorkItem newInstantiator = new CommentWorkItem();
		newInstantiator.setWriter(session.getUser());		
		newInstantiator.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		newInstantiator.setDueDate(this.getSelDate());
		//newInstantiator.setStartDate();
		newInstantiator.setTitle(title);
		newInstantiator.setExt1(this.getViewMode());
		newInstantiator.setExt2(String.valueOf(this.allDay));
		newInstantiator.scheduleCalendar = this;
		
		if("sns".equals(session.getEmployee().getPreferUX()) ){
			if( newInstancePanel != null ){
				newInstantiator.getMetaworksContext().setHow("sns");
				
				NewInstancePanel newInstancePanel = this.newInstancePanel;
				newInstancePanel.setNewInstantiator(newInstantiator);
				
				return new Object[]{newInstancePanel};
			}
			return null;
		}else{
			NewInstancePanel newInstancePanel =  new NewInstancePanel();
			
			newInstancePanel.setDueDate(this.getSelDate());
			/*
			if(this.getEndDate() != null){
				newInstancePanel.setDueDate(this.getEndDate());
			}else{
				newInstancePanel.setDueDate(dueDate);
			}*/
			
			newInstancePanel.session = session;
			newInstancePanel.load(session);
			
			newInstancePanel.setNewInstantiator(newInstantiator);
			
			newInstantiator.newInstancePanel = newInstancePanel;
		
			return new Object[]{new NewInstanceWindow(newInstancePanel)};
		}
	}
	
}
