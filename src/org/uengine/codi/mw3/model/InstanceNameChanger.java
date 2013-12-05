package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.calendar.ScheduleCalendarEvent;


public class InstanceNameChanger implements ContextAware{
	
	public InstanceNameChanger() {
		this.setMetaworksContext(new MetaworksContext());
	}
	
	public MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	@AutowiredFromClient
	public Session session;
	
	String instanceName;
		@Name
		@ValidatorSet({
		    @Validator(name=ValidatorContext.VALIDATE_MAX, options={"250"}, message="인스턴스명은 250자 이하로 입력해야 합니다.")
		})
		//@Face(ejsPath="genericfaces/richText.ejs", options={"cols=")
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

	//good example for Refresh
	@ServiceMethod(callByContent=true, keyBinding="enter", target="popup")
	public Refresh change() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(new Long(instanceId));
		
		IInstance instanceRef = instance.databaseMe();
		instanceRef.setName(getInstanceName());
		
		ScheduleCalendarEvent scEvent = new ScheduleCalendarEvent();
		scEvent.setTitle(instanceRef.getName());
		scEvent.setId(instanceRef.getInstId().toString());
		scEvent.setStart(instanceRef.getDueDate());
		scEvent.setEnd(instanceRef.getDueDate());
		scEvent.setAllDay(true);
		scEvent.setCallType(ScheduleCalendar.CALLTYPE_INSTANCE);
		scEvent.setComplete(Instance.INSTNACE_STATUS_COMPLETED.equals(instanceRef.getStatus()));
		
		MetaworksRemoteService.pushTargetScript(Login.getSessionIdWithUserId(session.getUser().getUserId()),
				"if(mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar')!=null) mw3.getAutowiredObject('org.uengine.codi.mw3.calendar.ScheduleCalendar').__getFaceHelper().addEvent",
				new Object[]{scEvent});
		
		return new Refresh(instance.databaseMe());
	}
	
}
