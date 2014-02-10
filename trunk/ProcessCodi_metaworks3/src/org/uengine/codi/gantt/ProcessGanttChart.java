package org.uengine.codi.gantt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ComplexActivity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.SubProcessActivity;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessGanttChart {

	public static final String SUBPREFIX = "sub";
	
	String instId;
		public String getInstId() {
			return instId;
		}
	
		public void setInstId(String instId) {
			this.instId = instId;
		}
	private String uid;
		@Face(options = { "prefix" }, values = { "@" })
		public String getUid() {
			return uid;
		}
	
		public void setUid(String uid) {
			this.uid = uid;
		}
	private String name;
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	private String subject;
		public String getSubject() {
			return subject;
		}
	
		public void setSubject(String subject) {
			this.subject = subject;
		}
	private String category;
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
	private String description;
		public String getDescription() {
			return description;
		}
	
		public void setDescription(String description) {
			this.description = description;
		}
	private String author;
		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) {
			this.author = author;
	}
	private Date creationDate;
		public Date getCreationDate() {
			return creationDate;
		}
		
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
	private Date updatedDate;
		public Date getUpdatedDate() {
			return updatedDate;
		}
		
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
	private Date startDate;
		public Date getStartDate() {
			return startDate;
		}
	
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
	private Date finishDate;
		public Date getFinishDate() {
			return finishDate;
		}
	
		public void setFinishDate(java.util.Date date) {
			this.finishDate = date;
		}
	private String status;
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	private TasksData tasks;
		@Face(options = { "upperCase" }, values = { "first" })
		public TasksData getTasks() {
			return tasks;
		}
	
		public void setTasks(TasksData tasks) {
			this.tasks = tasks;
		}
	private TaskData taskData;
	
	private String preActEndDate;
		public String getPreActEndDate() {
			return preActEndDate;
		}
	
		public void setPreActEndDate(String preActEndDate) {
			this.preActEndDate = preActEndDate;
		}
	private String preActEndDate_run;
		public String getPreActEndDate_run() {
			return preActEndDate_run;
		}
	
		public void setPreActEndDate_run(String preActEndDate_run) {
			this.preActEndDate_run = preActEndDate_run;
		}
	private TaskData subParent;
		public TaskData getSubParent() {
			return subParent;
		}
	
		public void setSubParent(TaskData subParent) {
			this.subParent = subParent;
		}
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;

	private SimpleDateFormat sdf;
	private Calendar cal;
	private DateFormat df;

	public ProcessGanttChart() {
		uid = "";
		name = "";
		subject = "";
		category = "";
		description = "";
		author = "";
		creationDate = null;
		updatedDate = null;
		startDate = null;
		finishDate = null;
		status = "";
		tasks = new TasksData();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal = Calendar.getInstance();
		df = new SimpleDateFormat("yyyy-MM-dd");
		preActEndDate = "";
		preActEndDate_run = "";
	}

	@ServiceMethod(callByContent=true)
	public ProcessGanttChart load() throws Exception {
		ProcessInstance instance = processManager.getProcessInstance(instId);

		//process
		setUid(instance.getRootProcessInstanceId());
		setName(instance.getName());
		setDescription(instance.getInfo());
		setStatus(instance.getStatus());

		ProcessDefinition processDefinition = instance.getProcessDefinition();

		// taskData 맵핑
		ArrayList<Activity> childActivities = ((ComplexActivity) processDefinition).getChildActivities();

		for (int i = 0; i < childActivities.size(); i++) {
			Activity act = (Activity) childActivities.get(i);

				taskData = new TaskData();
				
			if (act instanceof HumanActivity) {
				
				//only for human activity
				taskData.setisLeaf("true");
				taskData.setParent(null);
				taskData = logic(processDefinition, instance, act, taskData);
				getTasks().taskList.add(taskData);

			
			}else if(act instanceof SubProcessActivity){				
				//get subProcess
                SubProcessActivity subAct = (SubProcessActivity)act;
                String defVerId = ProcessDefinition.splitDefinitionAndVersionId(subAct.getDefinitionId())[0];
                ProcessDefinition pd = processManager.getProcessDefinition(defVerId);
                
                //only for subProcess
				taskData.setisLeaf("false");
				taskData.setParent(null);
				taskData = logic(pd, instance, subAct, taskData);
				setSubParent(taskData);
				getTasks().taskList.add(taskData);

				//for subActivities
				ArrayList<Activity> subActs = pd.getChildActivities();
				
				Iterator itr = subActs.iterator();
				
				while(itr.hasNext()){
					taskData = new TaskData();
					Activity subLeaf = (Activity)itr.next();
					taskData.setisLeaf("true");
					taskData.setParent(getSubParent().getUid());
					taskData = logic(processDefinition, instance, subLeaf, taskData);
					getTasks().taskList.add(taskData);
				}//while
				
				setSubParent(null);

			}//end else if
				
		}//end for
		return this;
	}//end load
	
	
	
	
	public TaskData logic(ProcessDefinition processDefinition, ProcessInstance instance, Activity act, TaskData taskData) throws Exception{
		
		if(taskData.getParent()==null){
			taskData.setUid(act.getTracingTag());
		}else{
			taskData.setUid(SUBPREFIX + act.getTracingTag());
		}
		
		taskData.setType(act.getName().toString());
		taskData.setWbsLevel(1);

		//From setup
		Vector prevActivities = act.getPreviousActivities();
		if (prevActivities != null) {
			Iterator itr = prevActivities.iterator();
			if (itr != null) {
				while (itr.hasNext()) {
					Activity prevActvity = (Activity) itr.next();
					if(taskData.getUid().startsWith(SUBPREFIX)){
						taskData.setFrom(SUBPREFIX + prevActvity.getTracingTag());
					}else{
						taskData.setFrom(prevActvity.getTracingTag());
					}
				}
			} else {
				taskData.setFrom("");
			}
		}


		//To setup
		if(taskData.getUid().startsWith(SUBPREFIX)){
			//subActivities 에 prefix
			taskData.setTo(SUBPREFIX + String.valueOf((Integer.valueOf(taskData.getUid().substring(3))+1)));
		}else{
			taskData.setTo(String.valueOf(Integer.valueOf(act.getTracingTag()) + 1));
		}

		
		taskData.setTitle(act.getName().getText());
		taskData.setDescription(act.getProcessDefinition().getDescription() == null ? "" : act.getProcessDefinition().getDescription().getText());
		
		
		
		// plan
		taskData.setCompleteRate(100);
		taskData.setStartDate("".equals(getPreActEndDate()) ? sdf.format(act.getStartedTime(instance).getTime()) : getPreActEndDate());
		if(act instanceof HumanActivity){
			taskData.setDuration(((HumanActivity) act).getDuration());
		}else{
			taskData.setDuration(processDefinition.getDuration());
		}
		if(taskData.getUid().startsWith(SUBPREFIX) && taskData.getFrom() == null){
			//subprocess 의 first activity의 plan을 subprocess의 일정에 속하게 설정
			taskData.setStartDate(getSubParent().getStartDate());
		}
		cal.setTime(df.parse(taskData.getStartDate()));
		cal.add(Calendar.DATE, taskData.getDuration());
		taskData.setEndDate(sdf.format(cal.getTime()));
		setPreActEndDate(taskData.getEndDate());

		
		// status
		if(taskData.getUid().startsWith(SUBPREFIX) && !Activity.STATUS_COMPLETED.equals(getSubParent().getStatus()) && taskData.getFrom() == null){
			taskData.setStatus(Activity.STATUS_READY);
		}else{
			taskData.setStatus(act.getStatus(instance));
		}

		
		
		// started_run, end_run, duration_run
		if (Activity.STATUS_COMPLETED.equals(taskData.getStatus())) {

			taskData.setStartDate_run(sdf.format(act.getStartedTime(instance).getTime()));//
			taskData.setEndDate_run(sdf.format(act.getEndTime(instance).getTime()));
			setPreActEndDate_run(taskData.getEndDate_run());
			/* EndDate_run - StartDate_run => elapsedTime 사용
			 * long diffSec =
			 * (eDate.parse(taskData.getEndDate_run()).getTime() -
			 * sDate.parse(taskData.getStartDate_run()).getTime()) /
			 * 1000; long diffDay = diffSec/60*60*24;
			 * taskData.setDuration_run(((int)diffDay)+1);
			 */
			taskData.setDuration_run(((int)(act.getElapsedTimeAsLong(instance) / 86400000L)) + 1);
			taskData.setCompleteRate_run(100);

		} else if (Activity.STATUS_RUNNING.equals(taskData.getStatus())) {
			
			if(taskData.getUid().startsWith(SUBPREFIX) && taskData.getFrom() == null){
				//subprocess 의 first activity의 plan을 subprocess의 일정에 속하게 설정
				taskData.setStartDate_run(getSubParent().getStartDate_run());
			}else{
				taskData.setStartDate_run(sdf.format(act.getStartedTime(instance).getTime()));
			}
			int elapsedDate = ((int)(act.getElapsedTimeAsLong(instance) / 86400000L));
 			taskData.setDuration_run(elapsedDate==0 ? elapsedDate + 2 : elapsedDate + 1);
			cal.setTime(df.parse(taskData.getStartDate_run()));//
			cal.add(Calendar.DATE, taskData.getDuration_run());//
			taskData.setEndDate_run(sdf.format(cal.getTime()));//
			setPreActEndDate_run(taskData.getEndDate_run());//
			taskData.setCompleteRate_run(50);

		} else if (Activity.STATUS_READY.equals(taskData.getStatus())) {
			
			if(taskData.getUid().startsWith(SUBPREFIX) && taskData.getFrom() == null){
				//subprocess 의 first activity의 plan을 subprocess의 일정에 속하게 설정
				taskData.setStartDate_run(getSubParent().getStartDate_run());
			}else{
				taskData.setStartDate_run(getPreActEndDate_run());
			}
			taskData.setDuration_run(taskData.getDuration());
			cal.setTime(df.parse(taskData.getStartDate_run()));//
			cal.add(Calendar.DATE, taskData.getDuration_run());//
			taskData.setEndDate_run(sdf.format(cal.getTime()));//
			setPreActEndDate_run(taskData.getEndDate_run());//
			taskData.setCompleteRate_run(1);

		}
		return taskData;
	}// end logic
	
	

}//end class